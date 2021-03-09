package com.zwt.shop.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.JsonObject;
import com.zwt.shop.base.BaseApiService;
import com.zwt.shop.base.Result;
import com.zwt.shop.dto.SkuDTO;
import com.zwt.shop.dto.SpuDTO;
import com.zwt.shop.dto.SpuDetailDTO;
import com.zwt.shop.entity.*;
import com.zwt.shop.mapper.*;
import com.zwt.shop.service.SpuService;
import com.zwt.shop.utils.ObjectUtils;
import com.zwt.shop.utils.zBeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName SpuServiceImpl
 * @Description: TODO
 * @Author zhuwentong
 * @Date 2021/1/5
 * @Version V1.0
 **/
@RestController
public class SpuServiceImpl extends BaseApiService implements SpuService {
    @Resource
    private SpuMapper spuMapper;
    @Resource
    private BrandMapper brandMapper;
    @Resource
    private CategoryMapper categoryMapper;
    @Resource
    private SkuMapper skuMapper;
    @Resource
    private SpuDetailMapper spuDetailMapper;
    @Resource
    private StockMapper stockMapper;


    @Override
    public Result<JsonObject> goodsXiajia(SpuDTO spuDTO) {
        SpuEntity spuEntity = zBeanUtils.copyBean(spuDTO, SpuEntity.class);
        spuEntity.setSaleable(spuEntity.getSaleable()==1?0:1);
        spuMapper.updateByPrimaryKeySelective(spuEntity);


        return this.setResultSuccess();
    }

    @Override
    public Result<JsonObject> goodsDelete(Integer spuId) {

        if(spuId==null) return this.setResultError("id为null");

        spuMapper.deleteByPrimaryKey(spuId);
        spuDetailMapper.deleteByPrimaryKey(spuId);


        deleteAll(spuId);

        return this.setResultSuccess();
    }

    @Override
    @Transactional
    public Result<List<JsonObject>> goodsEdit(SpuDTO spuDTO) {
        Date date = new Date();
        //通过SpuId修改spu表
        SpuEntity spuEntity = zBeanUtils.copyBean(spuDTO, SpuEntity.class);
        spuEntity.setLast_update_time(date);
        spuMapper.updateByPrimaryKeySelective(spuEntity);
        //修改spuDetail
        SpuDetailEntity spuDetailEntity = zBeanUtils.copyBean(spuDTO.getSpuDetail(), SpuDetailEntity.class);
        spuDetailMapper.updateByPrimaryKeySelective(spuDetailEntity);
        //修改sku
        //通过spuId查询sku表

        deleteAll(spuEntity.getId());
        //删除之后 需要把新的数据新增上去

        this.addAll(spuDTO,spuEntity,date);
        return this.setResultSuccess();
    }

    private void deleteAll(Integer spuId){
        Example example = new Example(SkuEntity.class);
        example.createCriteria().andEqualTo("spuId",spuId);
        List<SkuEntity> skuEntities = skuMapper.selectByExample(example);
        List<Long> skuId = skuEntities.stream().map(sku -> sku.getId()).collect(Collectors.toList());
        skuMapper.deleteByIdList(skuId);
        stockMapper.deleteByIdList(skuId);
    }

    private void addAll(SpuDTO spuDTO ,SpuEntity spuEntity ,Date date){
        List<SkuDTO> skus = spuDTO.getSkus();
        skus.stream().forEach(skuDTO -> {
            SkuEntity skuEntity = zBeanUtils.copyBean(skuDTO, SkuEntity.class);
            skuEntity.setSpuId(spuEntity.getId());
            skuEntity.setCreateTime(date);
            skuEntity.setLastUpdateTime(date);
            skuMapper.insertSelective(skuEntity);
            StockEntity stockEntity = new StockEntity();
            stockEntity.setSkuId(skuEntity.getId());
            stockEntity.setStock(skuDTO.getStock());
            stockMapper.insertSelective(stockEntity);
        });
    }


    @Override
    public Result<List<SkuDTO>> skuBySpuIdStock(Integer spuId) {
        //使用spuid 查询出sku里面的数据

       List<SkuDTO> skuDTOList = skuMapper.skuBySpuIdStock(spuId);

        return this.setResultSuccess(skuDTOList);
    }

    @Override
    public Result<JsonObject> spuByIdGetDetail(Integer spuId) {

        SpuDetailEntity spuDetailEntity = spuDetailMapper.selectByPrimaryKey(spuId);

        return this.setResultSuccess(spuDetailEntity);
    }

    @Override
    public Result<SpuDetailDTO> spuGetDetail(Integer spuId) {
        SpuDetailEntity spuDetailEntity = spuDetailMapper.selectByPrimaryKey(spuId);
        return this.setResultSuccess(spuDetailEntity);
    }

    @Override
    @Transactional
    public Result<List<JsonObject>> goodsSave(SpuDTO spuDTO) {
        //新增spu表
        Date date = new Date();
        SpuEntity spuEntity = zBeanUtils.copyBean(spuDTO, SpuEntity.class);
        spuEntity.setSaleable(1);
        spuEntity.setValid(1);
        spuEntity.setCreate_time(date);
        spuEntity.setLast_update_time(date);
        spuMapper.insertSelective(spuEntity);
        //新增skus
        this.addAll(spuDTO,spuEntity,date);
        //新增spuDetail
        SpuDetailEntity spuDetail = spuDTO.getSpuDetail();
        spuDetail.setSpuId(spuEntity.getId());
        spuDetailMapper.insertSelective(spuDetail);



        return this.setResultSuccess();
    }



    @Override
    public Result<List<SpuDTO>> SpuList(SpuDTO spuDTO) {

        if(ObjectUtils.isNotNull(spuDTO.getPage())&&ObjectUtils.isNotNull(spuDTO.getRows())){
            PageHelper.startPage(spuDTO.getPage(),spuDTO.getRows());
        }
        if(ObjectUtils.isNotNull((spuDTO.getSort()))&&ObjectUtils.isNotNull(spuDTO.getOrder())){
            PageHelper.orderBy(spuDTO.getOrderBy());
        }

        Example example = new Example(SpuEntity.class);
        Example.Criteria criteria = example.createCriteria();

        if (ObjectUtils.isNotNull(spuDTO.getSaleable())&&spuDTO.getSaleable() < 2){
            criteria.andEqualTo("saleable",spuDTO.getSaleable());
        }
        if(!StringUtils.isEmpty(spuDTO.getTitle())){
            criteria.andLike("title","%"+ spuDTO.getTitle()  +"%");
        }
        criteria.andEqualTo(spuDTO.getId());





        //查询出 所有的标题 id brandId cid
        List<SpuEntity> entities = spuMapper.selectByExample(example);
        //用map进行遍历
        List<Object> collect = entities.stream().map(spuEntity -> {

            //把属性copy给dto
            SpuDTO spuDTO1 = zBeanUtils.copyBean(spuEntity, SpuDTO.class);
            //可以使用集合进行查询的selectByIdList函数,Arrays.asList可以放置多个参数
            List<CategoryEntity> categoryEntities = categoryMapper.
                    selectByIdList(Arrays.asList(spuEntity.getCid1(), spuEntity.getCid2(), spuEntity.getCid3()));
            // set进CategoryName                      用cid查询出来的数据进行遍历
            spuDTO1
                    .setCategoryName(categoryEntities
                            .stream()
                            .map(categoryEntity -> categoryEntity.getName())
                            .collect(Collectors.joining("/")));
            //通过brandId 进行查询
            BrandEntity brandEntity = brandMapper.selectByPrimaryKey(spuDTO1.getBrand_id());
            spuDTO1.setBrandName(brandEntity.getName());


            return spuDTO1;
        }).collect(Collectors.toList());


        //查询商品分类 以及 品牌


        PageInfo<SpuEntity> pageInfo = new PageInfo<>(entities);
        return this.setResult(200,pageInfo.getTotal()+"",collect);
    }
}

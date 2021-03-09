package com.zwt.shop.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.JsonObject;
import com.zwt.shop.base.BaseApiService;
import com.zwt.shop.base.Result;
import com.zwt.shop.dto.BrandDTO;
import com.zwt.shop.entity.BrandEntity;
import com.zwt.shop.entity.CategoryBrandEntity;
import com.zwt.shop.mapper.BrandMapper;
import com.zwt.shop.mapper.CategoryBrandMapper;
import com.zwt.shop.service.BrandService;
import com.zwt.shop.utils.ObjectUtils;
import com.zwt.shop.utils.PinyinUtil;
import com.zwt.shop.utils.zBeanUtils;
import io.netty.util.internal.ObjectUtil;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName BrandServiceImpl
 * @Description: TODO
 * @Author zhuwentong
 * @Date 2020/12/25
 * @Version V1.0
 **/
@RestController
public class BrandServiceImpl extends BaseApiService implements BrandService {

    @Resource
    public BrandMapper brandMapper;

    @Resource
    public CategoryBrandMapper categoryBrandMapper;


    @Override
    public Result<PageInfo<BrandEntity>> getBrandInfo(BrandDTO brandDTO) {

        if(ObjectUtils.isNotNull(brandDTO.getPage()) && ObjectUtils.isNotNull(brandDTO.getRows()))
            PageHelper.startPage(brandDTO.getPage(),brandDTO.getRows());

        if(!StringUtils.isEmpty(brandDTO.getSort())) PageHelper.orderBy(brandDTO.getOrderBy());

        BrandEntity brandEntity = zBeanUtils.copyBean(brandDTO,BrandEntity.class);

        Example example = new Example(BrandEntity.class);
        Example.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(brandEntity.getName()))
            criteria.andLike("name","%" + brandEntity.getName() + "%");
        if(ObjectUtils.isNotNull(brandDTO.getId()))
            criteria.andEqualTo("id",brandDTO.getId());

        List<BrandEntity> brandEntities = brandMapper.selectByExample(example);
        PageInfo<BrandEntity> pageInfo = new PageInfo<>(brandEntities);

        return this.setResultSuccess(pageInfo);
    }


    @Override
    public Result<List<BrandEntity>> getBrandByIds(String brandIds) {

        List<Integer> ids = Arrays.asList(brandIds.split(",")).stream().map(id -> {
            return Integer.parseInt(id);
        }).collect(Collectors.toList());
        List<BrandEntity> brandEntities = brandMapper.selectByIdList(ids);


        return this.setResultSuccess(brandEntities);
    }

    @Override
    public Result<List<BrandEntity>> getBrandIdByCategoryId(Integer cid) {

        //通过cid查询brandName数据
       List<BrandEntity> CbList = brandMapper.getBrandIdByCategoryId(cid);

        return this.setResultSuccess(CbList);
    }

    @Override
    public Result<JsonObject> deleteById(Integer id) {

        brandMapper.deleteByPrimaryKey(id);
        Example example = new Example(CategoryBrandEntity.class);
        example.createCriteria().andEqualTo("brandId" , id);
        categoryBrandMapper.deleteByExample(example);
        return this.setResultSuccess();
    }

    @Override
    public Result<JsonObject> brandPut(BrandDTO brandDTO) {
        //把值copy到entity里面
        BrandEntity brandEntity = zBeanUtils.copyBean(brandDTO, BrandEntity.class);
        brandEntity.setLetter(PinyinUtil.getUpperCase(String.valueOf(brandEntity.getName().toCharArray()[0]), false).toCharArray()[0]);
        brandMapper.updateByPrimaryKeySelective(brandEntity);
        //先通过brandId删除中间表的数据
        Example example = new Example(CategoryBrandEntity.class);
        example.createCriteria().andEqualTo("brandId",brandEntity.getId());
        categoryBrandMapper.deleteByExample(example);


        //这是categories id
        String categories = brandDTO.getCategories();
        List<CategoryBrandEntity> categoryBrandEntities = new ArrayList<>();
        if(categories.contains(",")){
            String[] split = categories.split(",");
            for (String s : split) {
                CategoryBrandEntity categoryBrandEntity = new CategoryBrandEntity();
                categoryBrandEntity.setCategoryId(Integer.valueOf(s));
                categoryBrandEntity.setBrandId(brandEntity.getId());
                categoryBrandEntities.add(categoryBrandEntity);
            }
            categoryBrandMapper.insertList(categoryBrandEntities);
        }else{
            CategoryBrandEntity categoryBrandEntity = new CategoryBrandEntity();
            categoryBrandEntity.setCategoryId(Integer.valueOf(categories));
            categoryBrandEntity.setBrandId(brandEntity.getId());
            categoryBrandMapper.insert(categoryBrandEntity);
        }
        return this.setResultSuccess();
    }

    @Override
    public Result<List<BrandEntity>> getBrandApi(BrandDTO brandDTO) {

            //分页 page 1 rows 一页多少
        if (ObjectUtils.isNotNull(brandDTO.getPage())&&ObjectUtils.isNotNull(brandDTO.getRows())){
            PageHelper.startPage(brandDTO.getPage(),brandDTO.getRows());
        }

        if(!StringUtils.isEmpty(brandDTO.getSort())){
            PageHelper.orderBy(brandDTO.getOrderBy());
        }

//        BrandEntity brandEntity = new BrandEntity();
//        //copy bean 把数据 copy给 brandEntity
//        BeanUtils.copyProperties(brandDTO,brandEntity);
        //这里是把 传过来的 DTO copy到 brandEntity里面
        BrandEntity brandEntity = zBeanUtils.copyBean(brandDTO, BrandEntity.class);
       // brandEntity.setLetter(PinyinUtil.getUpperCase(String.valueOf(brandEntity.getName().toCharArray()[0]), false).toCharArray()[0]);
        Example example = new Example(BrandEntity.class);
        Example.Criteria criteria = example.createCriteria();
        //进行模糊查询
        criteria.andLike("name","%"+ brandEntity.getName()  +"%");
        criteria.andEqualTo("id",brandDTO.getId());

        List<BrandEntity> brandEntityList = brandMapper.selectByExample(example);
        //这里面放的就是我们要传的值
        PageInfo<BrandEntity> pageInfo = new PageInfo<>(brandEntityList);


        return this.setResultSuccess(pageInfo);
    }

    @Override
    @Transactional
    public Result<JsonObject> brandSave(BrandDTO brandDTO) {

        if(brandDTO ==null){return this.setResultError("---"); }

        //里面放着前台传来的值
        BrandEntity brandEntity = zBeanUtils.copyBean(brandDTO, BrandEntity.class);
        brandEntity.setLetter(PinyinUtil.getUpperCase(String.valueOf(brandEntity.getName().toCharArray()[0]), false).toCharArray()[0]);
        //新增 获取到新增id
        brandMapper.insert(brandEntity);

        //这是categories id
        String categories = brandDTO.getCategories();
        List<CategoryBrandEntity> categoryBrandEntities = new ArrayList<>();
        if(categories.contains(",")){
            String[] split = categories.split(",");
            for (String s : split) {
                CategoryBrandEntity categoryBrandEntity = new CategoryBrandEntity();
                categoryBrandEntity.setCategoryId(Integer.valueOf(s));
                categoryBrandEntity.setBrandId(brandEntity.getId());
                categoryBrandEntities.add(categoryBrandEntity);
            }
            categoryBrandMapper.insertList(categoryBrandEntities);
        }else{
            CategoryBrandEntity categoryBrandEntity = new CategoryBrandEntity();
            categoryBrandEntity.setCategoryId(Integer.valueOf(categories));
            categoryBrandEntity.setBrandId(brandEntity.getId());
            categoryBrandMapper.insert(categoryBrandEntity);
        }
        return this.setResultSuccess();

    }



}

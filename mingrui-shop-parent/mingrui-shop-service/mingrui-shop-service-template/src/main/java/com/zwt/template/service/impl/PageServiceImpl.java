package com.zwt.template.service.impl;

import com.github.pagehelper.PageInfo;
import com.zwt.shop.base.Result;
import com.zwt.shop.dto.*;
import com.zwt.shop.entity.BrandEntity;
import com.zwt.shop.entity.CategoryEntity;
import com.zwt.shop.entity.SpecParamEntity;
import com.zwt.shop.entity.SpecificationEntity;
import com.zwt.shop.utils.zBeanUtils;
import com.zwt.template.controller.PageController;
import com.zwt.template.feign.BrandFeign;
import com.zwt.template.feign.CategoryFeign;
import com.zwt.template.feign.GoodsFeign;
import com.zwt.template.feign.SpecificationFeign;
import com.zwt.template.service.PageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName PageServiceImpl
 * @Description: TODO
 * @Author zhuwentong
 * @Date 2021/3/8
 * @Version V1.0
 **/
@Service
public class PageServiceImpl implements PageService {

    @Resource
    private GoodsFeign goodsFeign;
    @Resource
    private CategoryFeign categoryFeign;

    @Resource
    private BrandFeign brandFeign;

    @Resource
    private SpecificationFeign specificationFeign;

    @Override
    public Map<String, Object> getGoodsInfo(Integer spuId) {
        Map<String, Object> map = new HashMap<>();
        //spu信息
        SpuDTO spuDTO = new SpuDTO();
        spuDTO.setId(spuId);
        SpuDTO spuData=null;
        if(spuId!=null && spuId != 0){
            Result<List<SpuDTO>> spuList = goodsFeign.SpuList(spuDTO);
             spuData = spuList.getData().get(0);
            map.put("spuData",spuData);
        }
        //spudetail信息
        Result<SpuDetailDTO> spuDetailDTOResult = goodsFeign.spuGetDetail(spuId);
        SpuDetailDTO spuDetailDTOResultData = spuDetailDTOResult.getData();
        map.put("spuDetailData",spuDetailDTOResultData);
        //查询分类信息
        List<CategoryEntity> categoryEntity = categoryFeign.getCateByIds(String.join(",",
                Arrays.asList(
                        spuData.getCid1() + "",
                        spuData.getCid2() + "",
                        spuData.getCid3() + ""))).getData();
        map.put("categoryData",categoryEntity);
        //查询品牌信息
        BrandDTO brandDTO = new BrandDTO();
        brandDTO.setId(spuData.getBrand_id());
        Result<PageInfo<BrandEntity>> brandInfo = brandFeign.getBrandInfo(brandDTO);
        if(brandInfo.isAccess()){
            BrandEntity brandEntity = brandInfo.getData().getList().get(0);
            map.put("brandData",brandEntity);
        }

        //skus
        Result<List<SkuDTO>> spuIdList = goodsFeign.skuBySpuIdStock(spuId);
        if(spuIdList.isAccess()){

            map.put("skuData",spuIdList.getData());
        }
        //规格组+规格参数
        SpecificationDTO specificationDTO = new SpecificationDTO();
        specificationDTO.setCid(spuData.getCid3());
        Result<List<SpecificationEntity>> specificationList = specificationFeign.specificationList(specificationDTO);
        List<SpecificationEntity> specificationListData = specificationList.getData();
        List<SpecificationDTO> specificationDTOS = specificationListData.stream().map(specificationData -> {
            SpecificationDTO specificationDTO1 = zBeanUtils.copyBean(specificationData, SpecificationDTO.class);
            //规格参数-通用参数
            SpecParamDTO specParamDTO = new SpecParamDTO();
            specParamDTO.setGroupId(specificationDTO1.getId());
            specParamDTO.setGeneric(true);

            Result<List<SpecParamEntity>> specList = specificationFeign.specList(specParamDTO);
            if (specList.isAccess()) {
                specificationDTO1.setSpecList(specList.getData());
            }
            return specificationDTO1;
        }).collect(Collectors.toList());
        map.put("specGroupAndParam",specificationDTOS);

        SpecParamDTO specParamDTO = new SpecParamDTO();
        specParamDTO.setCid(spuData.getCid3());
        specParamDTO.setGeneric(false);
        Result<List<SpecParamEntity>> specList = specificationFeign.specList(specParamDTO);
        if(specList.isAccess()){
            List<SpecParamEntity> specParamData = specList.getData();
            Map<Integer, String> specParamMap = new HashMap<>();
            specParamData.stream().forEach(specParam -> specParamMap.put(specParam.getId(),specParam.getName()));
        map.put("specParamMap",specParamMap);
        }


        return map;

    }
}

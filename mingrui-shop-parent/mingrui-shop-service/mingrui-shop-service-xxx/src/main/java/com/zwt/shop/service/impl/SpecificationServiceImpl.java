package com.zwt.shop.service.impl;

import com.google.gson.JsonObject;
import com.zwt.shop.base.BaseApiService;
import com.zwt.shop.base.Result;
import com.zwt.shop.dto.SpecificationDTO;
import com.zwt.shop.entity.SpecificationEntity;
import com.zwt.shop.mapper.SpecificationMapper;
import com.zwt.shop.service.SpecificationService;
import com.zwt.shop.utils.zBeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName SpecificationServiceImpl
 * @Description: TODO
 * @Author zhuwentong
 * @Date 2021/1/4
 * @Version V1.0
 **/
@RestController
public class SpecificationServiceImpl extends BaseApiService implements SpecificationService {

        @Resource
        private SpecificationMapper specificationMapper;

    @Override
    public Result<List<JsonObject>> deleteById(Integer id) {
        specificationMapper.deleteByPrimaryKey(id);
        return this.setResultSuccess();
    }

    @Override
    @Transactional
    public Result<List<JsonObject>> speciEdit(SpecificationDTO specificationDTO) {
        specificationMapper.updateByPrimaryKeySelective(zBeanUtils.copyBean(specificationDTO,SpecificationEntity.class));

        return this.setResultSuccess();
    }

    @Override
    @Transactional
    public Result<List<JsonObject>> speciSave(SpecificationDTO specificationDTO) {

        specificationMapper.insertSelective(zBeanUtils.copyBean(specificationDTO,SpecificationEntity.class));
        return this.setResultSuccess();
    }

    @Override
    public Result<List<SpecificationEntity>> specificationList(SpecificationDTO specificationDTO) {

        Example example = new Example(SpecificationEntity.class);
        example.createCriteria().andEqualTo("cid", zBeanUtils.copyBean(specificationDTO, SpecificationEntity.class).getCid());

        List<SpecificationEntity> cId = specificationMapper.selectByExample(example);

        return this.setResultSuccess(cId);
    }
}

package com.zwt.shop.service.impl;

import com.google.gson.JsonObject;
import com.zwt.shop.base.BaseApiService;
import com.zwt.shop.base.Result;
import com.zwt.shop.dto.SpecParamDTO;
import com.zwt.shop.dto.SpecificationDTO;
import com.zwt.shop.entity.SpecParamEntity;
import com.zwt.shop.entity.SpecificationEntity;
import com.zwt.shop.mapper.SpecParamMapper;
import com.zwt.shop.mapper.SpecificationMapper;
import com.zwt.shop.service.SpecificationService;
import com.zwt.shop.utils.zBeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
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

        @Resource
        private SpecParamMapper specParamMapper;

    @Override
    public Result<List<JsonObject>> specDelete(Integer id) {
        if(id != null ){



            specParamMapper.deleteByPrimaryKey(id);
        }

        return this.setResultSuccess();
    }

    @Override
    public Result<List<JsonObject>> specEdit(SpecParamDTO specParamDTO) {
        if(specParamDTO != null){
            specParamMapper.updateByPrimaryKey(zBeanUtils.copyBean(specParamDTO, SpecParamEntity.class));
        }
        return this.setResultSuccess();
    }

    @Override
    public Result<List<JsonObject>> specSave(SpecParamDTO specParamDTO) {
        if(specParamDTO != null ){
            specParamMapper.insertSelective(zBeanUtils.copyBean(specParamDTO,SpecParamEntity.class));
        }
        return this.setResultSuccess();
    }

    @Override
    public Result<List<SpecParamEntity>> specList(SpecParamDTO specParamDTO) {
        SpecParamEntity specParamEntity = zBeanUtils.copyBean(specParamDTO, SpecParamEntity.class);
        Example example = new Example(SpecParamEntity.class);
        example.createCriteria().andEqualTo("groupId",specParamEntity.getGroupId());
        List<SpecParamEntity> specParamEntities = specParamMapper.selectByExample(example);
        return this.setResultSuccess(specParamEntities);
    }

    @Override
    public Result<List<JsonObject>> deleteById(Integer id) {

        if(id != null){
//            //通过id 查出数据     条件查询出的规格
            Example example = new Example(SpecParamEntity.class);
            //通过groupID 去tb_spec_param表里查询数据
            example.createCriteria().andEqualTo("groupId",id);
            List<SpecParamEntity> list = specParamMapper.selectByExample(example);
            //如果有的话 那么无法删除
            if (list.size()!=0){
                return this.setResultError("被绑定 无法删除");
            }


        }
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

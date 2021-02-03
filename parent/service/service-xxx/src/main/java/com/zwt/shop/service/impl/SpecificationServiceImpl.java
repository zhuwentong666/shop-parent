package com.zwt.shop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mr.zwt.shop.base.BaseApiService;
import com.mr.zwt.shop.base.Result;
import com.mr.zwt.shop.utils.BaiduBeanUtil;
import com.mr.zwt.shop.utils.ObjectUtil;
import com.zwt.shop.mapper.SpecGroupMapper;
import com.zwt.shop.mapper.SpecParamMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import shop.dto.SpecGroupDTO;
import shop.dto.SpecParamDTO;
import shop.entity.SpecGroupEntity;
import shop.entity.SpecParamEntity;
import shop.service.SpecificationService;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName SpecificationServiceImpl
 * @Description: TODO
 * @Author lss
 * @Date 2021/1/25
 * @Version V1.0
 **/
@RestController
public class SpecificationServiceImpl extends BaseApiService implements SpecificationService {

    @Resource
    private SpecGroupMapper specGroupMapper;

    @Resource
    private SpecParamMapper specParamMapper;

    @Override
    public Result<List<SpecGroupEntity>> getSpecGroupInfo(SpecGroupDTO specGroupDTO) {

        Example example = new Example(SpecGroupEntity.class);
        if (ObjectUtil.isNotNull(specGroupDTO.getCid())){
            example.createCriteria().andEqualTo("cid", BaiduBeanUtil.copyProperties(specGroupDTO,SpecGroupEntity.class).getCid());
        }
        List<SpecGroupEntity> specGroupEntities = specGroupMapper.selectByExample(example);
        return this.setResultSuccess(specGroupEntities);
    }
    @Transactional
    @Override
    public Result<JSONObject> saveSpecGroupInfo(SpecGroupDTO specGroupDTO) {
        specGroupMapper.insertSelective(BaiduBeanUtil.copyProperties(specGroupDTO,SpecGroupEntity.class));
        return this.setResultSuccess();
    }
    @Transactional
    @Override
    public Result<JSONObject> editSpecGroupInfo(SpecGroupDTO specGroupDTO) {
        specGroupMapper.updateByPrimaryKeySelective(BaiduBeanUtil.copyProperties(specGroupDTO,SpecGroupEntity.class));
        return this.setResultSuccess();
    }
    @Transactional
    @Override
    public Result<JSONObject> deleteSpecGroupInfo(Integer id) {
        Example example = new Example(SpecParamEntity.class);
        example.createCriteria().andEqualTo("groupId",id);
        List<SpecParamEntity> specParamEntities = specParamMapper.selectByExample(example);
        if (specParamEntities.size()!=0){
            return this.setResultError("该节点下有数据不能删除");
        }
        specGroupMapper.deleteByPrimaryKey(id);
        return this.setResultSuccess();
    }

    @Override
    public Result<List<SpecParamEntity>> getSpecParamInfo(SpecParamDTO specParamDTO) {
        SpecParamEntity specParamEntity = BaiduBeanUtil.copyProperties(specParamDTO, SpecParamEntity.class);
        Example example = new Example(SpecParamEntity.class);
        Example.Criteria criteria = example.createCriteria();
        if (ObjectUtil.isNotNull(specParamEntity.getGroupId()))
        criteria.andEqualTo("groupId",specParamEntity.getGroupId());
        if (ObjectUtil.isNotNull(specParamEntity.getCid()))
            criteria.andEqualTo("cid",specParamEntity.getCid());
        List<SpecParamEntity> specParamEntities = specParamMapper.selectByExample(example);
        return this.setResultSuccess(specParamEntities);
    }
    @Transactional
    @Override
    public Result<JSONObject> saveSpecParamInfo(SpecParamDTO specParamDTO) {
        SpecParamEntity specParamEntity = BaiduBeanUtil.copyProperties(specParamDTO, SpecParamEntity.class);
        if (null == specParamEntity.getName()) return this.setResultError("参数名不能为空");
        specParamMapper.insertSelective(specParamEntity);
        return this.setResultSuccess();
    }
    @Transactional
    @Override
    public Result<JSONObject> editSpecParamInfo(SpecParamDTO specParamDTO) {
        SpecParamEntity specParamEntity = BaiduBeanUtil.copyProperties(specParamDTO, SpecParamEntity.class);
        specParamMapper.updateByPrimaryKeySelective(specParamEntity);
        return this.setResultSuccess();
    }
    @Transactional
    @Override
    public Result<JSONObject> deleteSpecParamInfo(Integer id) {
        specParamMapper.deleteByPrimaryKey(id);
        return this.setResultSuccess();
    }
}

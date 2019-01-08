package com.shtel.secure.platform.finishType.service;

import com.shtel.secure.platform.finishType.model.FinishType;
import com.shtel.secure.platform.finishType.model.mapper.FinishTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class FinishTypeService {

    @Autowired
    private FinishTypeMapper finishTypeMapper;

    public FinishType getFinishTypeByGourpIdAndUrl(String virtualGroupId, String url) {
        Example example = new Example(FinishType.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("virtualGroupId", virtualGroupId);
        criteria.andEqualTo("url", url);
        return finishTypeMapper.selectOneByExample(example);
    }

    public int updateFinishType(FinishType finishType) {
        return finishTypeMapper.updateByPrimaryKeySelective(finishType);
    }

    public int insertFinishType(FinishType finishType) {
        return finishTypeMapper.insert(finishType);
    }
}

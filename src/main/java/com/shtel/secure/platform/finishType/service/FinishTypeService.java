package com.shtel.secure.platform.finishType.service;

import com.shtel.secure.platform.finishType.model.FinishType;
import com.shtel.secure.platform.finishType.model.mapper.FinishTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinishTypeService {

    @Autowired
    private FinishTypeMapper finishTypeMapper;

    public FinishType getFinishTypeByGourpId(String groupId) {
        return finishTypeMapper.selectByPrimaryKey(groupId);
    }

    public int updateFinishType(FinishType finishType) {
        return finishTypeMapper.updateByPrimaryKeySelective(finishType);
    }
}

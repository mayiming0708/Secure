package com.shtel.secure.platform.type.service;

import com.shtel.secure.platform.type.model.Type;
import com.shtel.secure.platform.type.model.mapper.TypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeService {
    @Autowired
    private TypeMapper typeMapper;

    public Type getTypeByNameEn(String nameEn) {
        return typeMapper.getTypeByName(nameEn);
    }

    public Type getTypeById(int id) {
        return typeMapper.selectByPrimaryKey(id);
    }
}

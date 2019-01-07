package com.shtel.secure.platform.type.service;

import com.shtel.secure.platform.type.moel.Type;
import com.shtel.secure.platform.type.moel.mapper.TypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

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

package com.shtel.secure.platform.type.moel.mapper;

import com.shtel.secure.platform.type.moel.Type;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

@Component
@org.apache.ibatis.annotations.Mapper
public interface TypeMapper extends Mapper<Type> {
    @Select("select id,name_en as nameEn,name_cn as nameCn from ws_type where name_en = #{nameEn}")
    public Type getTypeByName(@Param("nameEn") String nameEn);
}

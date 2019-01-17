package com.shtel.secure.platform.perfom.model.mapper;

import com.shtel.secure.platform.perfom.model.Perform;
import com.shtel.secure.platform.perfom.model.PerformReq;

import java.util.List;

/**
 * @Auther: 陈文强
 * @Date: 2019/1/17 10:57
 * @Description: 满足展示逻辑动态可控Mapper
 */
public interface BasicMapper {

    List<Perform> selectWebDetail(PerformReq performReq);

    int countWebDetail(PerformReq performReq);
}

package com.shtel.secure.platform.perfom.model.mapper;

import com.shtel.secure.platform.issue.model.Task;
import com.shtel.secure.platform.perfom.model.Perform;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Auther: 陈文强
 * @Date: 2019/1/15 15:25
 * @Description: PerformMapper
 */
public interface PerformMapper extends Mapper<Task> {

    @Select("SELECT \n" +
            "a.virtual_group_id virtualGroupId,\n" +
            "b.url,\n" +
            "IF(b.siteinfo is null,-1,b.siteinfo) siteinfo,\n" +
            "IF(b.availability is null,-1,b.availability) availability,\n" +
            "IF(b.black_links is null,-1,b.black_links) blackLinks,\n" +
            "IF(b.sql_injection is null,-1,b.sql_injection) sqlInjection,\n" +
            "IF(b.keyword is null,-1,b.keyword) keyword,\n" +
            "IF(b.xss is null,-1,b.xss) xss,\n" +
            "IF(b.webvul is null,-1,b.webvul) webvul,\n" +
            "IF(b.info_leak is null,-1,b.info_leak) infoLeak,\n" +
            "IF(b.cgi is null,-1,b.cgi) cgi,\n" +
            "IF(b.csrf is null,-1,b.csrf) csrf,\n" +
            "IF(b.form_crack is null,-1,b.form_crack) formCrack,\n" +
            "b.risk_url_count riskUrlCount,\n" +
            "b.risk_info_count riskInfoCount,\n" +
            "b.risk_high_count riskHighCount,\n" +
            "b.risk_middle_count riskMiddleCount,\n" +
            "b.risk_low_count riskLowCount,\n" +
            "b.score,\n" +
            "a.create_time createTime,\n" +
            "IF(a.finish_rate = 1,a.update_time,NOW()) endTime,\n" +
            "a.finish_rate finishRate\n" +
            "FROM \n" +
            "(SELECT virtual_group_id,create_time,finish_rate,update_time FROM ws_task WHERE user_id=#{userId} and is_success=1 AND is_period=#{isPeriod}) a \n" +
            "LEFT JOIN\n" +
            "ws_finish_type b\n" +
            "ON \n" +
            "a.virtual_group_id=b.virtual_group_id\n" +
            "order by a.create_time desc")
    List<Perform> selectWebDetail(@Param("userId") String userId,@Param("isPeriod") int isPeriod);

    @Select("SELECT \n" +
            "count(a.virtual_group_id)\n" +
            "FROM \n" +
            "(SELECT virtual_group_id,create_time,finish_rate,update_time FROM ws_task WHERE user_id=2 and is_success=1 AND is_period=0) a \n" +
            "LEFT JOIN\n" +
            "ws_finish_type b\n" +
            "ON \n" +
            "a.virtual_group_id=b.virtual_group_id  \n" +
            "order by a.create_time ")
    int countWebDetail(@Param("userId") String userId,@Param("isPeriod") int isPeriod);
}

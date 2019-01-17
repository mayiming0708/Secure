package com.shtel.secure.platform.perfom.model.mapper;

import com.shtel.secure.platform.issue.model.Task;
import com.shtel.secure.platform.perfom.model.Perform;
import com.shtel.secure.platform.perfom.model.PerformReq;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
            "(SELECT virtual_group_id,create_time,finish_rate,update_time FROM ws_task WHERE user_id=#{userId} and is_success=1) a \n" +
            "LEFT JOIN\n" +
            "ws_finish_type b\n" +
            "ON \n" +
            "a.virtual_group_id=b.virtual_group_id\n where state=0" +
            "order by a.create_time desc")
    List<Perform> selectWebDetail(@Param("userId") String userId);

    @Select("SELECT \n" +
            "count(a.virtual_group_id)\n" +
            "FROM \n" +
            "(SELECT virtual_group_id,create_time,finish_rate,update_time FROM ws_task WHERE user_id=2 and is_success=1 AND is_period=0) a \n" +
            "LEFT JOIN\n" +
            "ws_finish_type b\n" +
            "ON \n" +
            "a.virtual_group_id=b.virtual_group_id where state=0  \n" +
            "order by a.create_time ")
    int countWebDetail(@Param("userId") String userId);

    @Select("SELECT " +
            "COUNT(virtual_group_id)" +
            " FROM " +
            "ws_task")
    int countTask();

    @Select("SELECT SUM(siteinfo) siteinfoCount,SUM(availability) availabilityCount," +
            "SUM(risk_high_count) riskHighCount,SUM(risk_middle_count) riskMiddleCount," +
            "SUM(risk_low_count) riskLowCount,COUNT(DISTINCT(url)) urlCount" +
            " FROM ws_finish_type where state=0;")
    PerformReq countWebAndBugCounts();

    @Select("SELECT CEIL(AVG(b.time)/60) " +
            "FROM " +
            "(select SUM(UNIX_TIMESTAMP(end_at)-UNIX_TIMESTAMP(start_at)) time,virtual_group_id FROM ws_result_event GROUP BY virtual_group_id) b;")
    int getAvgWebTime();

    @Select("SELECT \n" +
            "name_en nameEn,\n" +
            "name_cn nameCn\n" +
            "FROM \n" +
            "ws_type a\n" +
            "LEFT JOIN\n" +
            "ws_risk b\n" +
            "on a.risk_level_id=b.id \n" +
            "WHERE b.`level`=8\n")
    List<PerformReq> getCNENname();

    @Select("SELECT \n" +
            "if(sum(black_links) is null,0,sum(black_links)) blackLinks,\n" +
            "if(sum(malscan) is null,0,sum(malscan)) malscan,\n" +
            "if(sum(sql_injection) is null,0,sum(sql_injection)) sqlInjection,\n" +
            "if(sum(xss) is null,0,SUM(xss)) xss,\n" +
            "if(sum(webvul) is NULL,0,SUM(webvul)) webvul,\n" +
            "if(sum(info_leak) is null,0,sum(info_leak)) infoLeak,\n" +
            "if(sum(cgi) is null ,0,SUM(cgi)) cgi,\n" +
            "if(sum(keyword) is null ,0,SUM(keyword)) keyword,\n" +
            "if(sum(csrf) is null ,0,SUM(csrf)) csrf,\n" +
            "if(sum(form_crack) is null,0,SUM(form_crack)) formCrack\n" +
            "FROM ws_finish_type where state=0")
    PerformReq getBugCount();

    @Select("SELECT url,Max(score) score FROM ws_finish_type  where state=0 GROUP BY url ORDER BY score DESC")
    List<PerformReq> getTopUrl();

    @Update("update ws_task set status = -1 where virtual_group_id = #{virtual_group_id} ")
    int deleteTask(String virtual_group_id);

    @Update("update ws_finish_type set state = -1 where virtual_group_id = #{virtual_group_id} ")
    int deleteURL(String virtual_group_id);
}

package com.shtel.secure.platform.issue.model.mapper;

import com.shtel.secure.platform.issue.model.Task;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Auther: 陈文强
 * @Date: 2019/1/7 15:50
 * @Description: TaskMapper接口
 */
public interface TaskMapper extends Mapper<Task> {
    @Insert("insert into " +
            "ws_task " +
            "(virtual_group_id,user_id,urls,black_links,malscan,keyword,sql_injection,xss,webvul,info_leak,cgi,csrf,is_period,is_success,form_crack,message,finish_rate) " +
            "values " +
            "(#{task.virtualGroupId},#{task.userId},#{task.urls},#{task.blackLinks},#{task.malscan},#{task.keyword},#{task.sqlInjection},#{task.xss}," +
            "#{task.webvul},#{task.infoLeak},#{task.cgi},#{task.csrf},#{task.isPeriod},#{task.isSuccess},#{task.formCrack},#{task.message},#{task.finishRate})")
   // @Options(useGeneratedKeys=true,keyProperty="task.id")
    int insertTaskRecord(@Param("task")Task task);

    @Update("update ws_task set finish_rate=#{finishRate} where virtual_group_id=#{virtual_group_id}")
    int updateFinishRate(float finishRate,String virtual_group_id);
}

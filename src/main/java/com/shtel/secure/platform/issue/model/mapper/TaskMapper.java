package com.shtel.secure.platform.issue.model.mapper;

import com.shtel.secure.platform.issue.model.Task;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Auther: 陈文强
 * @Date: 2019/1/7 15:50
 * @Description: TaskMapper接口
 */
public interface TaskMapper extends Mapper<Task> {
    @Insert("insert into " +
            "ws_task " +
            "(virtual_group_id,user_id,urls,black_links,malscan,keyword,`sql`,xss,webvul,info_leak,cgi,csrf,is_period,is_success,form_crack,message) " +
            "values " +
            "(#{task.virtualGroupId},#{task.userId},#{task.urls},#{task.blackLinks},#{task.malscan},#{task.keyword},#{task.sql},#{task.xss}," +
            "#{task.webvul},#{task.infoLeak},#{task.cgi},#{task.csrf},#{task.isPeriod},#{task.isSuccess},#{task.formCrack},#{task.message})")
   // @Options(useGeneratedKeys=true,keyProperty="task.id")
    int insertTaskRecord(@Param("task")Task task);
}

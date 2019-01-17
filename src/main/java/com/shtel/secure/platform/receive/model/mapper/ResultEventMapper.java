package com.shtel.secure.platform.receive.model.mapper;

import com.shtel.secure.platform.receive.model.ResultEvent;
import com.shtel.secure.platform.receive.model.Temp;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Component
public interface ResultEventMapper extends Mapper<ResultEvent> {
    @Select("SELECT\n" +
            "\tws_result_event.id,\n" +
            "\tws_result_event.receive_time as receiveTime,\n" +
            "\tws_result_event.virtual_group_id as virtualGroupId,\n" +
            "\tws_result_event.`value`,\n" +
            "\tws_result_event.total,\n" +
            "\tws_result_event.start_at as startAt,\n" +
            "\tws_result_event.task_id as taskId,\n" +
            "\tws_type.name_cn as moduleType,\n" +
            "\tws_result_event.group_id as groupId,\n" +
            "\tws_result_event.site_id as siteId,\n" +
            "\tws_result_event.site,\n" +
            "\tws_result_event.end_at as endAt\n" +
            "FROM\n" +
            "\tws_result_event,\n" +
            "\tws_type\n" +
            "WHERE\n" +
            "\tvirtual_group_id = #{virtualGroupId} and site = #{site}\n" +
            "\tand ws_result_event.module_type=ws_type.id")
    List<ResultEvent> getResultEventsByGroupAndUrl(@Param("virtualGroupId") String virtualGroupId, @Param("site") String site);

    @Select("SELECT\n" +
            "\tws_result_event.id,\n" +
            "\tws_result_event.receive_time as receiveTime,\n" +
            "\tws_result_event.virtual_group_id as virtualGroupId,\n" +
            "\tws_result_event.`value`,\n" +
            "\tws_result_event.total,\n" +
            "\tws_result_event.start_at as startAt,\n" +
            "\tws_result_event.task_id as taskId,\n" +
            "\tws_result_event.group_id as groupId,\n" +
            "\tws_result_event.site_id as siteId,\n" +
            "\tws_result_event.site,\n" +
            "\tws_result_event.end_at as endAt\n" +
            "FROM\n" +
            "\tws_result_event LEFT JOIN ws_task on ws_task.virtual_group_id = ws_result_event.virtual_group_id\n" +
            "WHERE\n" +
            "\tws_task.user_id = #{userId} \n" +
            "\tAND ws_result_event.site = #{site}")
    List<ResultEvent> getResultEventsByUserIdAndUrl(@Param("userId") Integer userId, @Param("site") String site);
}

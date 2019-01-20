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

    @Select("( SELECT\n" +
            "\tws_result_event.id,\n" +
            "\tws_result_event.receive_time AS receiveTime,\n" +
            "\tws_result_event.virtual_group_id AS virtualGroupId,\n" +
            "\tws_result_event.`value`,\n" +
            "\tws_result_event.total,\n" +
            "\tws_result_event.start_at AS startAt,\n" +
            "\tws_result_event.task_id AS taskId,\n" +
            "\tws_result_event.group_id AS groupId,\n" +
            "\tws_result_event.site_id AS siteId,\n" +
            "\tws_result_event.module_type AS moduleType,\n" +
            "\tws_result_event.site,\n" +
            "\tws_result_event.end_at AS endAt,\n" +
            "\tws_result_event.report_url AS reportUrl \n" +
            "\tFROM\n" +
            "\t\tws_result_event\n" +
            "\t\tLEFT JOIN ws_task ON ws_task.virtual_group_id = ws_result_event.virtual_group_id \n" +
            "\tWHERE\n" +
            "\t\tws_task.user_id = #{userId}\n" +
            "\t\t\n" +
            "\t\tAND ws_result_event.site = #{site}\n" +
            "\t\t\n" +
            "\t\tAND ws_result_event.module_type = 'siteinfo' \n" +
            "\tORDER BY\n" +
            "\t\treceiveTime DESC \n" +
            "\t\tLIMIT 10 \n" +
            "\t) UNION\n" +
            "\t(\n" +
            "\tSELECT\n" +
            "\t\tws_result_event.id,\n" +
            "\t\tws_result_event.receive_time AS receiveTime,\n" +
            "\t\tws_result_event.virtual_group_id AS virtualGroupId,\n" +
            "\t\tws_result_event.`value`,\n" +
            "\t\tws_result_event.total,\n" +
            "\t\tws_result_event.start_at AS startAt,\n" +
            "\t\tws_result_event.task_id AS taskId,\n" +
            "\t\tws_result_event.group_id AS groupId,\n" +
            "\t\tws_result_event.site_id AS siteId,\n" +
            "\t\tws_result_event.module_type AS moduleType,\n" +
            "\t\tws_result_event.site,\n" +
            "\t\tws_result_event.end_at AS endAt,\n" +
            "\t\tws_result_event.report_url AS reportUrl \n" +
            "\tFROM\n" +
            "\t\tws_result_event\n" +
            "\t\tLEFT JOIN ws_task ON ws_task.virtual_group_id = ws_result_event.virtual_group_id \n" +
            "\tWHERE\n" +
            "\t\tws_task.user_id = #{userId}\n" +
            "\t\t\n" +
            "\t\tAND ws_result_event.site = #{site}\n" +
            "\t\t\n" +
            "\t\tAND ws_result_event.module_type = 'availability' \n" +
            "\tORDER BY\n" +
            "\t\treceiveTime DESC \n" +
            "\t\tLIMIT 10 \n" +
            "\t) UNION\n" +
            "\t(\n" +
            "\tSELECT\n" +
            "\t\tws_result_event.id,\n" +
            "\t\tws_result_event.receive_time AS receiveTime,\n" +
            "\t\tws_result_event.virtual_group_id AS virtualGroupId,\n" +
            "\t\tws_result_event.`value`,\n" +
            "\t\tws_result_event.total,\n" +
            "\t\tws_result_event.start_at AS startAt,\n" +
            "\t\tws_result_event.task_id AS taskId,\n" +
            "\t\tws_result_event.group_id AS groupId,\n" +
            "\t\tws_result_event.site_id AS siteId,\n" +
            "\t\tws_result_event.module_type AS moduleType,\n" +
            "\t\tws_result_event.site,\n" +
            "\t\tws_result_event.end_at AS endAt,\n" +
            "\t\tws_result_event.report_url AS reportUrl \n" +
            "\tFROM\n" +
            "\t\tws_result_event\n" +
            "\t\tLEFT JOIN ws_task ON ws_task.virtual_group_id = ws_result_event.virtual_group_id \n" +
            "\tWHERE\n" +
            "\t\tws_task.user_id = #{userId}\n" +
            "\t\t\n" +
            "\t\tAND ws_result_event.site = #{site}\n" +
            "\t\t\n" +
            "\t\tAND ws_result_event.module_type = 'content' \n" +
            "\tORDER BY\n" +
            "\t\treceiveTime DESC \n" +
            "\t\tLIMIT 10 \n" +
            "\t) UNION\n" +
            "\t(\n" +
            "\tSELECT\n" +
            "\t\tws_result_event.id,\n" +
            "\t\tws_result_event.receive_time AS receiveTime,\n" +
            "\t\tws_result_event.virtual_group_id AS virtualGroupId,\n" +
            "\t\tws_result_event.`value`,\n" +
            "\t\tws_result_event.total,\n" +
            "\t\tws_result_event.start_at AS startAt,\n" +
            "\t\tws_result_event.task_id AS taskId,\n" +
            "\t\tws_result_event.group_id AS groupId,\n" +
            "\t\tws_result_event.site_id AS siteId,\n" +
            "\t\tws_result_event.module_type AS moduleType,\n" +
            "\t\tws_result_event.site,\n" +
            "\t\tws_result_event.end_at AS endAt,\n" +
            "\t\tws_result_event.report_url AS reportUrl \n" +
            "\tFROM\n" +
            "\t\tws_result_event\n" +
            "\t\tLEFT JOIN ws_task ON ws_task.virtual_group_id = ws_result_event.virtual_group_id \n" +
            "\tWHERE\n" +
            "\t\tws_task.user_id = #{userId}\n" +
            "\t\t\n" +
            "\t\tAND ws_result_event.site = #{site}\n" +
            "\t\t\n" +
            "\t\tAND ws_result_event.module_type = 'weakness' \n" +
            "\tORDER BY\n" +
            "\t\treceiveTime DESC \n" +
            "\tLIMIT 10 \n" +
            "\t)" +
            "\torder by receiveTime desc")
    List<ResultEvent> getResultEventsByUserIdAndUrl(@Param("userId") Integer userId, @Param("site") String site);
}

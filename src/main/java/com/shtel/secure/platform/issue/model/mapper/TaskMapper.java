package com.shtel.secure.platform.issue.model.mapper;

import com.shtel.secure.platform.issue.model.Task;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Auther: 陈文强
 * @Date: 2019/1/7 15:50
 * @Description: TaskMapper接口
 */
@Component
public interface TaskMapper extends Mapper<Task> {
}

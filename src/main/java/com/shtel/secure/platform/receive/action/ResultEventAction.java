package com.shtel.secure.platform.receive.action;

import com.shtel.secure.platform.finishType.model.FinishType;
import com.shtel.secure.platform.finishType.service.FinishTypeService;
import com.shtel.secure.platform.receive.model.ResultEvent;
import com.shtel.secure.platform.receive.service.ResultEventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.plaf.synth.SynthScrollBarUI;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/service")
public class ResultEventAction {

    private static final Logger logger = LoggerFactory.getLogger(ResultEventAction.class);

    @Autowired
    private ResultEventService resultEventService;
    @Autowired
    private FinishTypeService finishTypeService;

    @RequestMapping(value = "/resultEvent", method = RequestMethod.GET)
    public ResultEvent resultEventById(@RequestParam("id") String id) {
        return resultEventService.getResultEventById(id);
    }


    /**
     * 根据group_id查找一个group的所有resultEvent
     * @param groupId
     * @return
     */
    @RequestMapping(value = "/resultEvents", method = RequestMethod.GET)
    public Map<String, ResultEvent> resultEventByGroup(@RequestParam("groupId") String groupId) {
        Map<String, ResultEvent> map = new HashMap<>();
        try {
            FinishType finishType = finishTypeService.getFinishTypeByGourpId(groupId);
            for (Field field : finishType.getClass().getDeclaredFields()) {
                if ("groupId".equals(field.getName())) {
                    continue;
                }
                field.setAccessible(true);
                map.put(field.getName(), resultEventService.getResultEventById((String) field.get(finishType)));
            }
        } catch (Exception e) {
            logger.info("|ERROR" + e);
        }

        return map;
    }
}

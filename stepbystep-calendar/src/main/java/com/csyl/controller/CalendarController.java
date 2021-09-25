package com.csyl.controller;

import com.csyl.deserialization.JsonUtil;
import com.csyl.model.MonthModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 霖
 */
@RestController
@RequestMapping(value = "/calendar")
public class CalendarController {

    @RequestMapping(value = "/init")
    public String initCalendar() {
        return JsonUtil.objToJson(new MonthModel());
    }
}

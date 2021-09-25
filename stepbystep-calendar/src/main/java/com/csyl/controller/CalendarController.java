package com.csyl.controller;

import com.csyl.cache.CalendarCache;
import com.csyl.deserialization.JsonUtil;
import com.csyl.model.MonthModel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 霖
 */
@RestController
@RequestMapping(value = "/calendar")
public class CalendarController {

    @RequestMapping(value = "/init")
    public String initCalendar() {
        MonthModel monthModel = new MonthModel();
        CalendarCache.cache(monthModel);
        return JsonUtil.objToJson(monthModel);
    }

    @RequestMapping(value = "/sign/{month}/{day}", method = RequestMethod.GET)
    public String sign(@PathVariable("month") Integer month, @PathVariable("day") Integer day) {
        CalendarCache.get(month).sign(day);
        return JsonUtil.objToJson(CalendarCache.get(month));
    }
}

package com.csyl.cache;

import com.csyl.model.MonthModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 霖
 */
@AllArgsConstructor
@Setter
@Getter
public class CalendarCache {

    private final static ConcurrentHashMap<Integer, MonthModel> CALENDAR_MAP = new ConcurrentHashMap<>();

    public static void cache(MonthModel monthModel) {
        CALENDAR_MAP.putIfAbsent(monthModel.getMonthNumber(), monthModel);
    }

    public static MonthModel get(Integer month) {
        CALENDAR_MAP.computeIfAbsent(month, m -> {
            throw new RuntimeException(String.format("%s 不存在数据", m));
        });
        return CALENDAR_MAP.get(month);
    }

    public static ConcurrentHashMap<Integer, MonthModel> getAll() {
        return CALENDAR_MAP;
    }
}

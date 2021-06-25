package com.csyl.stream;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 霖
 */
public class FindRepeat {

    public List<Object> findRepeat(List<Object> objectList) {

        return objectList.stream()
                .collect(Collectors.groupingBy(Object::hashCode, HashMap::new, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}

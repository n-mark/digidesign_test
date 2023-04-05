package com.digdes.school;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class NullRowCleaner {
    public static void clean(List<Map<String, Object>> table) {
        table.removeAll(table.stream()
                .filter(map -> map.values().stream()
                        .allMatch(Objects::isNull))
                .toList());
    }
}

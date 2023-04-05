package com.digdes.school.query;

import java.util.List;
import java.util.Map;

public abstract class Query {
    private final String leftover;

    public Query(String leftover) {
        this.leftover = leftover;
    }

    public abstract List<Map<String, Object>> performOn(List<Map<String, Object>> table);

    public String getLeftover() {
        return leftover;
    }

}

package com.digdes.school.query;

import com.digdes.school.ConditionalSelector;

import java.util.List;
import java.util.Map;

public class SelectQuery extends Query{

    public SelectQuery(String leftover) {
        super(leftover);
    }

    @Override
    public List<Map<String, Object>> performOn(List<Map<String, Object>> table) {
        if (getLeftover().toLowerCase().startsWith("where ")) {
            return ConditionalSelector.select(getLeftover().split(" ", 2)[1], table);
        }
        return table;
    }
}

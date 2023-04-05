package com.digdes.school.query;

import com.digdes.school.ConditionalSelector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DeleteQuery extends Query{

    public DeleteQuery(String leftover) {
        super(leftover);
    }

    @Override
    public List<Map<String, Object>> performOn(List<Map<String, Object>> table) {
        if (getLeftover().toLowerCase().startsWith("where ")) {
            List<Map<String, Object>> selected =
                    ConditionalSelector.select(getLeftover().split(" ", 2)[1], table);
            table.removeAll(selected);
            return selected;
        }
        List<Map<String, Object>> allDeleted = new ArrayList<>(table);
        table.clear();
        return allDeleted;
    }
}

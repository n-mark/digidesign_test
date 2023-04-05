package com.digdes.school.query;

import com.digdes.school.Mapper;
import com.digdes.school.NullRowCleaner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InsertQuery extends Query {
    public InsertQuery(String leftover) {
        super(leftover);
    }

    @Override
    public List<Map<String, Object>> performOn(List<Map<String, Object>> table) {
        table.add(Mapper.toMap(getLeftover()));
        List<Map<String, Object>> temp = new ArrayList<>();
        temp.add(table.get(table.size() - 1));
        NullRowCleaner.clean(table);
        return temp;
    }
}

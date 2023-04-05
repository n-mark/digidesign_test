package com.digdes.school.query;

import com.digdes.school.ConditionalSelector;
import com.digdes.school.Mapper;
import com.digdes.school.NullRowCleaner;
import com.digdes.school.Regex;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;

public class UpdateQuery extends Query {

    public UpdateQuery(String leftover) {
        super(leftover);
    }

    @Override
    public List<Map<String, Object>> performOn(List<Map<String, Object>> table) {
        String[] tempArr = getLeftover().split(Regex.WHERE_SPLIT.getRegex());

        if (tempArr.length == 1) {
            return getMaps(table, tempArr, table);
        } else if (tempArr.length == 2) {
            List<Map<String, Object>> selected = ConditionalSelector.select(tempArr[1], table);
            return getMaps(table, tempArr, selected);
        } else {
            throw new InputMismatchException("Something went wrong during the 'WHERE' split. Check the input");
        }
    }

    private List<Map<String, Object>> getMaps(List<Map<String, Object>> table,
                                              String[] tempArr, List<Map<String, Object>> selected) {
        Map<String, Object> temp = Mapper.toMap(tempArr[0]);
        selected.forEach(m -> {
            temp.forEach((k,v) -> {
                if (temp.get(k) == null) {
                    m.remove(k);
                } else {
                    m.put(k, v);
                }
            });
        });
        NullRowCleaner.clean(table);
        return selected;
    }
}

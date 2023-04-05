package com.digdes.school;

import java.util.*;
import java.util.stream.Collectors;

public class ConditionalSelector {
    public static List<Map<String, Object>> select(String conditions,
                                                   List<Map<String, Object>> target) {
        String[] sArr = conditions.split(Regex.OR_SPLIT.getRegex());
        Set<Map<String, Object>> set = new HashSet<>();

        for (String s : sArr) {
            set.addAll(selectByAnd(s, target));
        }

        return new ArrayList<>(set);
    }

    private static List<Map<String, Object>> selectByAnd(String s, List<Map<String, Object>> target) {
        List<Map<String, Object>> list = new ArrayList<>(target);
        String[] arr = s.split(Regex.AND_SPLIT.getRegex());

        for (String str : arr) {
            Condition c = new Condition(str);
                list.retainAll(target.stream()
                        .filter(map -> {
                            try {
                                return c.getPredicate().test(map.get(c.getField()), c.getValue());
                            } catch (NullPointerException e) {
                                return false;
                            }
                        })
                        .collect(Collectors.toList()));
        }

        return list;
    }
}

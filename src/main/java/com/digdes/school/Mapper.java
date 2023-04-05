package com.digdes.school;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

public class Mapper {
    public static Map<String, Object> toMap(String s) throws InputMismatchException {
        Map<String, Object> tempMap = new HashMap<>();
        String[] commaSplit = s.split(Regex.COMMA_SPLIT.getRegex());

        for (String keyValue : commaSplit) {
            String[] pair = keyValue.split(Regex.EQUAL.getRegex());
            if (pair.length != 2) {
                throw new InputMismatchException("Can't define key/value properly. Probably comma/equal sign/quote(s) missing");
            }

            String key = ColumnValidator.validateName(pair[0]);
            Object value = ColumnValidator.validateType(key, pair[1]);
            tempMap.put(key, value);
        }

        return tempMap;
    }
}

package com.digdes.school;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

public class ColumnValidator {
    private static final Map<String, Class<?>> columnValue = new HashMap<>();

    static {
        columnValue.put("id", Long.class);
        columnValue.put("lastName", String.class);
        columnValue.put("cost", Double.class);
        columnValue.put("age", Long.class);
        columnValue.put("active", Boolean.class);
    }

    public static String validateName(String s) {
        String toValidate = Trimmer.trimQuotes(s);

        for (String str : columnValue.keySet()) {
            if (toValidate.equalsIgnoreCase(str)) {
                return str;
            }
        }
        throw new InputMismatchException("Invalid column name");
    }

    public static Object validateType(String key, String type) {
        Object o = TypeDefiner.define(type);
        if (o == null) {
            return null;
        }
        if (o.getClass() == columnValue.get(key)) {
            return o;
        }
        throw new IllegalArgumentException("the value doesn't correspond to the '" + key + "' column type");
    }
}

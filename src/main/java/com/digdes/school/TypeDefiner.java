package com.digdes.school;

import java.util.InputMismatchException;

public class TypeDefiner {
    public static Object define(String s) {
        String trimmed = s.trim();
        if (trimmed.equalsIgnoreCase("null")) {
            return null;
        } else if (trimmed.startsWith("'")) {
            return Trimmer.trimQuotes(trimmed);
        } else if (trimmed.equalsIgnoreCase("true")
                || trimmed.equalsIgnoreCase("false")) {
            return Boolean.parseBoolean(trimmed);
        } else if (trimmed.matches(Regex.DOUBLE.getRegex())) {
            return Double.parseDouble(trimmed);
        } else {
            try {
                return Long.parseLong(trimmed);
            } catch (NumberFormatException e) {
                throw new InputMismatchException("Can't define \"" + s + "\" value type. Check if the input is correct.");
            }
        }
    }
}

package com.digdes.school;

import java.util.InputMismatchException;

public class Trimmer {
    public static String trimQuotes(String s) {
        if (s.trim().startsWith("'") && s.trim().endsWith("'")) {
            return s.substring(1, s.length() - 1);
        }
        else throw new InputMismatchException("Probably quotes set wrong");
    }
}

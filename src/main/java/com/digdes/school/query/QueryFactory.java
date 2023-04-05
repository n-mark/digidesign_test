package com.digdes.school.query;

import java.util.InputMismatchException;

public class QueryFactory {
    public static Query createQuery(String input) throws InputMismatchException {
        String[] actionAndLeftover = input.split(" ", 2);
        String action = actionAndLeftover[0].trim().toLowerCase();
        String leftover;

        try {
            leftover = actionAndLeftover[1].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            leftover = "undefined";
        }

        return switch (action) {
            case "insert" -> new InsertQuery(validateAndShrink(leftover));
            case "update" -> new UpdateQuery(validateAndShrink(leftover));
            case "select" -> new SelectQuery(leftover);
            case "delete" -> new DeleteQuery(leftover);
            default -> throw new InputMismatchException("An action can't be recognized. " +
                    "Check if the action keyword is present and spelled correctly");
        };
    }

    private static String validateAndShrink(String s) {
        if (!s.toLowerCase().startsWith("values")) {
            throw new InputMismatchException("Check if the 'VALUES' keyword is present and spelled correctly");
        }
        return s.split(" ", 2)[1];
    }
}

package com.digdes.school;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.function.BiPredicate;

public class Condition {
    private final String field;
    private BiPredicate<Object, Object> predicate;
    private final Object value;

    public Condition(String str) {
        String[] arr = null;
        Regex regex = null;

        for (Regex r : PredicateContainer.getPredicates().keySet()) {
            arr = str.split(r.getRegex());
            if (arr.length == 2){
                regex = r;
                break;
            }
        }

        if (arr == null || arr.length != 2) {
            throw new InputMismatchException("Comparison error. Check the input and try again");
        }

        Map<Class<?>, BiPredicate<Object, Object>> map = PredicateContainer.getPredicates().get(regex);

        this.field = ColumnValidator.validateName(arr[0]);
        this.value = ColumnValidator.validateType(field, arr[1]);

        for (Class<?> cl : map.keySet()) {
            if (value == null) {
                throw new IllegalArgumentException("Comparison value cannot be null");
            }
            if (value.getClass() == cl) {
                this.predicate = map.get(cl);
                break;
            }
        }
    }

    public String getField() {
        return field;
    }

    public BiPredicate<Object, Object> getPredicate() {
        return predicate;
    }

    public Object getValue() {
        return value;
    }
}

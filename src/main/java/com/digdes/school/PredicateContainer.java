package com.digdes.school;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiPredicate;

public class PredicateContainer {
    private static final Map<Regex, Map<Class<?>, BiPredicate<Object, Object>>> predicates = new HashMap<>();

    static {
        Map<Class<?>, BiPredicate<Object, Object>> equal = new HashMap<>();
        equal.put(Double.class, Object::equals);
        equal.put(Long.class, Object::equals);
        equal.put(String.class, Object::equals);
        equal.put(Boolean.class, Object::equals);
        predicates.put(Regex.EQUAL, equal);

        Map<Class<?>, BiPredicate<Object, Object>> notEqual = new HashMap<>();
        BiPredicate<Object, Object> notEqualPredicate = (o1, o2) -> {
            if (o1 == null && o2 != null) {
                return true;
            }
            return !o1.equals(o2);
        };
        notEqual.put(Double.class, notEqualPredicate);
        notEqual.put(Long.class, notEqualPredicate);
        notEqual.put(String.class, notEqualPredicate);
        notEqual.put(Boolean.class, notEqualPredicate);
        predicates.put(Regex.NOT_EQUAL, notEqual);

        Map<Class<?>, BiPredicate<Object, Object>> moreOrEqual = new HashMap<>();
        moreOrEqual.put(Double.class, (d1, d2) -> (Double) d1 >= (Double) d2);
        moreOrEqual.put(Long.class, (l1, l2) -> (Long) l1 >= (Long) l2);
        predicates.put(Regex.MORE_EQUAL, moreOrEqual);

        Map<Class<?>, BiPredicate<Object, Object>> lessOrEqual = new HashMap<>();
        lessOrEqual.put(Double.class, (d1, d2) -> (Double) d1 <= (Double) d2);
        lessOrEqual.put(Long.class, (l1, l2) -> (Long) l1 <= (Long) l2);
        predicates.put(Regex.LESS_EQUAL, lessOrEqual);

        Map<Class<?>, BiPredicate<Object, Object>> more = new HashMap<>();
        more.put(Double.class, (d1, d2) -> (Double) d1 > (Double) d2);
        more.put(Long.class, (l1, l2) -> (Long) l1 > (Long) l2);
        predicates.put(Regex.MORE, more);

        Map<Class<?>, BiPredicate<Object, Object>> less = new HashMap<>();
        less.put(Double.class, (d1, d2) -> (Double) d1 < (Double) d2);
        less.put(Long.class, (l1, l2) -> (Long) l1 < (Long) l2);
        predicates.put(Regex.LESS, less);

        Map<Class<?>, BiPredicate<Object, Object>> like = new HashMap<>();
        like.put(String.class, (s1, s2) -> ((String)s1).matches(((String)s2).replaceAll("%", ".*")));
        predicates.put(Regex.LIKE, like);

        Map<Class<?>, BiPredicate<Object, Object>> ilike = new HashMap<>();
        ilike.put(String.class, (s1, s2) -> ((String)s1).toLowerCase()
                .matches(((String)s2).toLowerCase().replaceAll("%", ".*")));
        predicates.put(Regex.ILIKE, ilike);
    }

    public static Map<Regex, Map<Class<?>, BiPredicate<Object, Object>>> getPredicates(){
        return predicates;
    }
}

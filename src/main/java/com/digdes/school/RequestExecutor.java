package com.digdes.school;

import com.digdes.school.query.Query;
import com.digdes.school.query.QueryFactory;

import java.util.List;
import java.util.Map;

public class RequestExecutor {

    public static List<Map<String, Object>> executeRequest(String request,
                                                           List<Map<String, Object>> table) throws Exception {
        Query query = QueryFactory.createQuery(request);
        return query.performOn(table);
    }
}

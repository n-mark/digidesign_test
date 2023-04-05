package com.digdes.school;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JavaSchoolStarter {
    private final List<Map<String, Object>> table = new ArrayList<>();

    public JavaSchoolStarter() {
    }

    public List<Map<String, Object>> execute(String request) throws Exception {
        return RequestExecutor.executeRequest(request, table);
    }
}

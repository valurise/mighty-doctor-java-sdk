package com.valurise.md.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaodong.tan
 */
public class ValueEntry extends UserInput {

    private List<KeyValuePair> answers = new ArrayList<KeyValuePair>();

    public ValueEntry(int questionId, String key, String value) {
        super(questionId);
        KeyValuePair kv = new KeyValuePair(key, value);
        answers.add(kv);
    }

    public String toString() {
        KeyValuePair kv = answers.get(0);
        return String.format("{\"questionId\":%s,\"values\":[{\"key\":\"%s\",\"value\":\"%s\"}]}\n", questionId, kv.getKey(), kv.getValue());
    }
}

package com.valurise.md.model;

import java.util.List;

/**
 * @author xiaodong.tan
 */
public class MultiSelection extends UserInput {

    private String answers;

    public MultiSelection(int questionId, List<String> answerIds) {
        super(questionId);
        StringBuilder sb = new StringBuilder();
        if (answerIds != null && answerIds.size() > 0) {
            boolean firstOne = true;
            for (String answerId : answerIds) {
                if (!firstOne) {
                    sb.append(",");
                }
                sb.append(answerId);
                firstOne = false;
            }
            answers = sb.toString();
        } else {
            answers = "";
        }
    }

    public String toString() {
        return String.format("{\"questionId\":%s,\"answers\":\"%s\"}", questionId, answers);
    }
}

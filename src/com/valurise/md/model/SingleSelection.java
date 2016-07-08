package com.valurise.md.model;

/**
 * @author xiaodong.tan
 */
public class SingleSelection extends UserInput {

    private String answer;

    public SingleSelection(int questionId, String answer) {
        super(questionId);
        this.answer = answer;
    }

    public String toString() {
        return String.format("{\"questionId\":%s,\"answer\":\"%s\"}", questionId, answer);
    }
}

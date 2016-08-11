package com.valurise.md.model;

/**
 * @author xiaodong.tan
 */
public abstract class UserInput {

    protected int questionId;

    public UserInput(int questionId) {
        this.questionId = questionId;
    }
}

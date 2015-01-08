package com.teamdev.arseniuk.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExpressionReader {
    private final Logger logger = LoggerFactory.getLogger(ExpressionReader.class);
    private final String expression;
    private int index;

    public ExpressionReader(String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }

    public String getRemainExpression() {
        logger.info("Getting remain expression.");
        skipWhitespaces();
        return expression.substring(index);
    }

    public void skipWhitespaces() {
        logger.info("skipping white space.");
        while (index < expression.length() && Character.isWhitespace(expression.charAt(index))) {
            index++;
        }
    }

    public boolean isEnd() {
        return index >= expression.length();
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void incrementIndex(int index) {
        this.index += index;
    }

}

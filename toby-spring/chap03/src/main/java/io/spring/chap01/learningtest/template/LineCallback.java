package io.spring.chap01.learningtest.template;

public interface LineCallback<T> {
    T doSomethingWithLine(String line, T value);
}

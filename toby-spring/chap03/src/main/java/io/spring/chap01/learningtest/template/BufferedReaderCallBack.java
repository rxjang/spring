package io.spring.chap01.learningtest.template;

import java.io.BufferedReader;
import java.io.IOException;

public interface BufferedReaderCallBack {
    Integer doSomethingWithReader(BufferedReader br) throws IOException;
}

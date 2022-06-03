package com.example.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(SampleController.class)
class SampleControllerTest {

    @Autowired
    MockMvc mock;

    @Test
    void sayHello() throws Exception {
        MvcResult result = mock.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World"))
                .andReturn();
        
        System.out.println(result.getResponse().getContentAsString());
    }
}
package com.exrate.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class UserControllerTest {
	
	private MockMvc mvc;

    @org.junit.Before
    public void setUp() throws Exception {
    	InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        //viewResolver.setPrefix("/WEB-INF/jsp/view/");
        viewResolver.setSuffix(".html");
        mvc = MockMvcBuilders.standaloneSetup(new UserController()).setViewResolvers(viewResolver).build();
    }
	
    @Test
    public void get() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/registration"))
                .andExpect(status().isOk())
                .andExpect(view().name(equalTo("registration")));
    }

}

package com.restaurant.voting.debugtool;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class SpringMain {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ConfigurableEnvironment env = ctx.getEnvironment();
        ctx.load("spring/spring-db.xml");
        ctx.refresh();
        ctx.close();
    }
}

package com.restaurant.voting.developtools;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class SpringMain {
    public static void main(String[] args) {

        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ConfigurableEnvironment env = ctx.getEnvironment();

        ctx.load("spring/spring-db.xml");
        ctx.refresh();
        /*List.of(ctx.getBeanDefinitionNames()).forEach(System.out::println);*/

        ctx.close();


    }
}

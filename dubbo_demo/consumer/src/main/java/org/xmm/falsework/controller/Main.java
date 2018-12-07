package org.xmm.falsework.controller;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring/applicationContext.xml"});
        context.start();

//        UserController userXXX=context.getBean(UserController.class);
//        User u=userXXX.getUser();
//        System.out.println("---------------"+u);
    }
    
    
}

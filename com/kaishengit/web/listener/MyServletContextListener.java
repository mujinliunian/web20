package com.kaishengit.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        ServletContext application = servletContextEvent.getServletContext();
        String password = application.getInitParameter("password");
        System.out.println("password:" + password);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("ServletContext销毁");
    }
}

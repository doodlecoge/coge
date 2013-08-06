package com.szwx.yht;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created with IntelliJ IDEA.
 * User: hch
 * Date: 13-8-5
 * Time: 下午9:46
 * To change this template use File | Settings | File Templates.
 */
public class AppListener implements ServletContextListener {
    public static String WebRoot;
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        WebRoot = servletContextEvent
                .getServletContext()
                .getRealPath("/");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}

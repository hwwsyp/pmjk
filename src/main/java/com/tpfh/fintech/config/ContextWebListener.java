/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.servlet.ServletContextEvent
 *  javax.servlet.ServletContextListener
 *  javax.servlet.annotation.WebListener
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package com.tpfh.fintech.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebListener
public class ContextWebListener
implements ServletContextListener {
    private static final Logger logger = LoggerFactory.getLogger(ContextWebListener.class);

    public void contextDestroyed(ServletContextEvent arg0) {
    }

    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("lang", (Object)"zh");
        String webRoot = sce.getServletContext().getContextPath();
        logger.info("\u52a0\u8f7d servlet...");
        sce.getServletContext().setAttribute("webRoot", (Object)webRoot);
        logger.info("Web\u6839\u76ee\u5f55:" + sce.getServletContext().getAttribute("webRoot"));
        sce.getServletContext().setAttribute("resRoot", (Object)(String.valueOf(webRoot) + "/statics"));
        logger.info("\u8d44\u6001\u8d44\u6e90\u6839\u76ee\u5f55:" + sce.getServletContext().getAttribute("resRoot"));
    }
}


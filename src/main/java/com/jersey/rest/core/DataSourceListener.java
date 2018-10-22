package com.jersey.rest.core;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

public class DataSourceListener implements ServletContextListener {

	private static final Logger logger = Logger.getLogger(DataSourceListener.class.getName());
	@Resource(name="jdbc/postgresDS")
	private static DataSource postGresDS;
	  
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	    logger.log(Level.INFO, "releasing the reportsDS datasource");
	    postGresDS = null;
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		logger.log(Level.INFO, "DataSourceListener initialized.");
	}

	public static DataSource getPostGresDS() {
		return postGresDS;
	}
}

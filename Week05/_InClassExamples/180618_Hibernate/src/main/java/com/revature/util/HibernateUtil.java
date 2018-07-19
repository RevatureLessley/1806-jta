package com.revature.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	/*
	 * SessionFactory is configured as a singleton.
	 * We call the configure method of the configuration component which,
	 * by default, will use the "configure.cfg.xml" file. 
	 * If named differently, you will ahve to supply teh custom name as a parameter. 
	 */
	private static SessionFactory sessionFactory =
			new Configuration().configure().
			buildSessionFactory();
	
	public static Session getSession(){
		return sessionFactory.openSession();
	}
}

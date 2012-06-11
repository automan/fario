package com.taobao.fario.server.db;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.w3c.dom.Document;

/**
 * commands:
 * 
 * pg_dump -U postgres -Fc --no-acl --no-owner fario > data.dump
 * 
 * PGPASSWORD=8Rv3_OytNkovjUQgSyfQXdWCNT pg_restore --verbose --clean --no-acl
 * --no-owner -h ec2-23-23-234-207.compute-1.amazonaws.com -U ntbblzmivnuzcq -d
 * de0kicucnvgj98 ~/data.dump
 * 
 * @author taichan
 * 
 */
public class HibernateSessionFactory {

	private static String CONFIG_FILE_LOCATION = "src/main/java/hibernate.cfg.xml";
	private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
	private static Configuration configuration = new Configuration();
	private static org.hibernate.SessionFactory sessionFactory;
	private static String configFile = CONFIG_FILE_LOCATION;

	private static String user_configFile = "src/main/java/com/taobao/fario/server/service/User.hbm.xml";
	private static String shopinfo_configFile = "src/main/java/com/taobao/fario/server/service/ShopInfo.hbm.xml";
	private static String localhistory_configFile = "src/main/java/com/taobao/fario/server/service/locationhistory.hbm.xml";

	static {
		try {
			configuration.configure(new File(configFile));
			DocumentBuilder documentBuilder = DocumentBuilderFactory
					.newInstance().newDocumentBuilder();

			Document doc = documentBuilder.parse(new File(user_configFile));
			configuration.addDocument(doc);

			Document docshop = documentBuilder.parse(new File(
					shopinfo_configFile));
			configuration.addDocument(docshop);

			Document dochistory = documentBuilder.parse(new File(
					localhistory_configFile));
			configuration.addDocument(dochistory);

			sessionFactory = configuration.buildSessionFactory();
		} catch (Exception e) {
			System.err.println("%%%% Error Creating SessionFactory %%%%");
			e.printStackTrace();
		}
	}

	private HibernateSessionFactory() {
	}

	/**
	 * Returns the ThreadLocal Session instance. Lazy initialize the
	 * <code>SessionFactory</code> if needed.
	 * 
	 * @return Session
	 * @throws HibernateException
	 */
	public static Session getSession() throws HibernateException {
		Session session = (Session) threadLocal.get();

		if (session == null || !session.isOpen()) {
			if (sessionFactory == null) {
				rebuildSessionFactory();
			}
			session = (sessionFactory != null) ? sessionFactory.openSession()
					: null;
			threadLocal.set(session);
		}

		return session;
	}

	/**
	 * Rebuild hibernate session factory
	 * 
	 */
	public static void rebuildSessionFactory() {
		try {
			configuration.configure(configFile);
			sessionFactory = configuration.buildSessionFactory();
		} catch (Exception e) {
			System.err.println("%%%% Error Creating SessionFactory %%%%");
			e.printStackTrace();
		}
	}

	/**
	 * Close the single hibernate session instance.
	 * 
	 * @throws HibernateException
	 */
	public static void closeSession() throws HibernateException {
		Session session = (Session) threadLocal.get();
		threadLocal.set(null);

		if (session != null) {
			session.close();
		}
	}

	/**
	 * return session factory
	 * 
	 */
	public static org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * return session factory
	 * 
	 * session factory will be rebuilded in the next call
	 */
	public static void setConfigFile(String configFile) {
		HibernateSessionFactory.configFile = configFile;
		sessionFactory = null;
	}

	/**
	 * return hibernate configuration
	 * 
	 */
	public static Configuration getConfiguration() {
		return configuration;
	}

}
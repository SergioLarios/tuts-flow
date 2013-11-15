package org.tutsflow.parent;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/application-context.xml")
public abstract class DatabaseConectionTest {
	
	private static Logger log = Logger.getLogger(DatabaseConectionTest.class);
	
	@BeforeClass
	public static void setUp() {
		try {
			// Set necessary system properties
			System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
			System.setProperty(Context.URL_PKG_PREFIXES,  "org.apache.naming");
			
			// Create initial context
			InitialContext ic = new InitialContext();
			ic.createSubcontext("java:");
			ic.createSubcontext("java:/comp");
			ic.createSubcontext("java:/comp/env");
			ic.createSubcontext("java:/comp/env/jdbc");
			
			// Create data source
			PGSimpleDataSource dataSource = new PGSimpleDataSource();
			dataSource.setUser("tutsflow");
			dataSource.setPassword("root");
			dataSource.setServerName("localhost");
			dataSource.setDatabaseName("tutsflow");
			dataSource.setPortNumber(5432);
			
			// finally bind the datasoruce
			ic.bind("java:/comp/env/jdbc/tutsflow", dataSource);
			
		} catch (Exception e) {
			log.error("Database connection failed", e);
		}
	}
	
}

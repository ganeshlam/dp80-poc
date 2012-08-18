package org.dp80.mongodb.ex1;

import java.util.logging.LogManager;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/META-INF/spring/mongodb-beans.xml"})
public class AbstractIntegrationTest {
	
	static final String COLLECTION_CUSTOMER = "customer";

	@Autowired
	MongoOperations operations;
	
	@Before
	public void setUp() {
		
		LogManager.getLogManager().reset();
		SLF4JBridgeHandler.removeHandlersForRootLogger(); 
		SLF4JBridgeHandler.install();
		java.util.logging.Logger.getLogger("global").setLevel(java.util.logging.Level.FINEST);
		java.util.logging.Logger.getLogger("com.mongodb").setLevel(java.util.logging.Level.ALL);
		
		operations.dropCollection(COLLECTION_CUSTOMER);

	}


}

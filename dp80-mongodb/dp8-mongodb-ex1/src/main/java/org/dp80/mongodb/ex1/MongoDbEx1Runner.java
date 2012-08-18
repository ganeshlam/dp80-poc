
package org.dp80.mongodb.ex1;

import java.util.logging.LogManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * 
 *
 */
public class MongoDbEx1Runner {
	
	/**
	 * logger
	 */
	private static final Logger		logger		= LoggerFactory.getLogger(MongoDbEx1Runner.class);
	
	/**
	 * Spring configuration files
	 */
	private static final String[]	springFiles	= new String[] {
												"src/main/resources/META-INF/spring/mongodb-beans.xml"
												};
	
	/**
	 * Start performance test
	 */
	public static void main(String[] args) {
	
		FileSystemXmlApplicationContext context = null;
		
		try {
			
			LogManager.getLogManager().reset();
			SLF4JBridgeHandler.install();
			java.util.logging.Logger.getLogger("global").setLevel(java.util.logging.Level.FINEST);
						
			//
			// Load spring context
			context = new FileSystemXmlApplicationContext(springFiles);
			
			Thread.sleep(99999999);
			
		} catch ( final Exception ex ) {
			
			logger.error("Error executing MongoDB example 1", ex);
			
		} finally {
			
			logger.info("Stopping context...");
			
			if ( context != null ) {
				
				context.close();
			}
			
		}
	}
}


package org.dp80.jaxrs.ex1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * 
 *
 */
public class JaxrsEx1Runner {
	
	/**
	 * logger
	 */
	private static final Logger		logger		= LoggerFactory.getLogger(JaxrsEx1Runner.class);
	
	/**
	 * Spring configuration files
	 */
	private static final String[]	springFiles	= new String[] {
												"src/main/resources/META-INF/spring/cxf-beans.xml"
												};
	
	/**
	 * Start performance test
	 */
	public static void main(String[] args) {
	
		FileSystemXmlApplicationContext context = null;
		
		try {
			
			//
			// Load spring context
			context = new FileSystemXmlApplicationContext(springFiles);
			
			//
			// 5 min. demo
			Thread.sleep(5 * 60 * 1000);
			
		} catch ( final Exception ex ) {
			
			logger.error("Error executing JAX-RS example 1", ex);
			
		} finally {
			
			logger.info("Stopping context...");
			
			if ( context != null ) {
				
				context.close();
			}
			
		}
	}
}

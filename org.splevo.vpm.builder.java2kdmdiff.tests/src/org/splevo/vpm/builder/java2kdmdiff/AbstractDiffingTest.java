package org.splevo.vpm.builder.java2kdmdiff;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.PatternLayout;
import org.junit.Before;

/**
 * Abstract base class for diffing tests. 
 */
public abstract class AbstractDiffingTest {

	/**
	 * Prepare the test.
	 * Initializes a log4j logging environment.
	 */
	@Before
	public void setUp() {
		// set up a basic logging configuration for the test environment
		BasicConfigurator.resetConfiguration();
		BasicConfigurator.configure(new ConsoleAppender(new PatternLayout("%m%n")));
	}

}
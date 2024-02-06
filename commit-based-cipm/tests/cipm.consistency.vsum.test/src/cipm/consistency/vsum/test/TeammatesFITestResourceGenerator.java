package cipm.consistency.vsum.test;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * The concrete implementation of {@link AbstractFITestResourceGenerator} for
 * Teammates tests.
 * 
 * @author atora
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TeammatesFITestResourceGenerator extends AbstractFITestResourceGenerator {
	private static final Logger LOGGER = Logger.getLogger("cipm." + TeammatesFITestResourceGenerator.class.getSimpleName());
	
	@Disabled("Enable to generate resource")
	@Order(0)
	@Test
	public void testTeammatesIntegration0() throws Exception {
		this.initResGen(0);
		this.generateResourcesFor(0);
	}
	
	@Disabled("Enable to generate resource")
	@Order(1)
	@Test
	public void testTeammatesIntegration1() throws Exception {
		this.initResGen(1);
		this.generateResourcesFor(1);
	}

	@Override
	protected String getTestModelFileIdentifier(Object commitHashIdentifier) {
		return String.valueOf(commitHashIdentifier);
	}

	@Override
	protected HasRepoSettings initRepoSettings() {
		return new TeammatesRepoSettings();
	}
}

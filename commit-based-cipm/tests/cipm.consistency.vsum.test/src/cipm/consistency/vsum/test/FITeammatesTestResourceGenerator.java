package cipm.consistency.vsum.test;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * A test case that is to be used to generate test resources required by FirstInstance tests. It
 * is meant to generate test resources for FirstInstance tests (fitests) and save them at the
 * corresponding path in the target directory of FirstInstance tests fitests.
 * <br><br>
 * Use the {@link #generateResourcesFor(Object)} method from the super class to generate the said
 * test resources. Pass the index of the last commit hash to the aforementioned method, as the resource
 * will only be generated with that commit in mind.
 * <br><br>
 * <b>Note that the size of generated test resources may slightly vary, which is to be expected</b>
 * 
 * @author atora
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FITeammatesTestResourceGenerator extends AbstractFITestResourceGenerator {
	private static final Logger LOGGER = Logger.getLogger("cipm." + FITeammatesTestResourceGenerator.class.getSimpleName());
	
	@Disabled("Enable to generate resource")
	@Order(0)
	@Test
	public void testTeammatesIntegration() throws Exception {
		this.initResGen(0);
		this.generateResourcesFor(0);
	}
	
	@Disabled("Enable to generate resource")
	@Order(1)
	@Test
	public void testTeammatesFirstPropagation() throws Exception {
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

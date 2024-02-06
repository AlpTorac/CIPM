package cipm.consistency.vsum.test;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import cipm.consistency.vsum.test.TeaStoreRepoSettings.TeaStoreCommitTag;
/**
 * The concrete implementation of {@link AbstractFITestResourceGenerator} for
 * TeaStore tests.
 * 
 * @author atora
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TeaStoreFITestResourceGenerator extends AbstractFITestResourceGenerator {
	private static final Logger LOGGER = Logger.getLogger("cipm." + TeaStoreFITestResourceGenerator.class.getSimpleName());
	
	@Disabled("Enable to generate resource")
	@Order(0)
	@Test
	public void testTeaStore1_0Integration() throws Exception {
		this.initResGen(TeaStoreCommitTag.COMMIT_1_0);
		this.generateResourcesFor(TeaStoreCommitTag.COMMIT_1_0);
	}

	@Disabled("Enable to generate resource")
	@Order(1)
	@Test
	public void testTeaStore1_1Integration() throws Exception {
		this.initResGen(TeaStoreCommitTag.COMMIT_1_1);
		this.generateResourcesFor(TeaStoreCommitTag.COMMIT_1_1);
	}

	@Override
	protected String getTestModelFileIdentifier(Object commitHashIdentifier) {
		return ((TeaStoreCommitTag) commitHashIdentifier).getTagName();
	}

	@Override
	protected HasRepoSettings initRepoSettings() {
		return new TeaStoreRepoSettings();
	}
}

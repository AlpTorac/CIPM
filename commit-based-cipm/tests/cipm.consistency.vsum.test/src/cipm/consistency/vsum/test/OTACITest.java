package cipm.consistency.vsum.test;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class OTACITest extends AbstractRepoTest {
	@Override
	protected HasRepoSettings initRepoSettings() {
		return new OTARepoSettings();
	}

//	@Disabled("Only one test case should run at once.")
	@Test
	public void testOTAIntegration() throws Exception {
		executePropagationAndEvaluation(null, this.getCommitHash(5), 0);
	}
}

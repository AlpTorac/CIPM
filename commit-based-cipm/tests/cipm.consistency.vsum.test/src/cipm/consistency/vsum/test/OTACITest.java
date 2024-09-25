package cipm.consistency.vsum.test;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class OTACITest extends AbstractRepoTest {
	@Override
	protected HasRepoSettings initRepoSettings() {
		return new OTARepoSettings();
	}

	@Disabled("Only one test case should run at once.")
	@Test
	public void testOTAIntegration() throws Exception {
		executePropagationAndEvaluation(null, this.getCommitHash(0), 0);
	}
	
	@Disabled("Only one test case should run at once.")
	@Test
	public void testOTAFirstPropagation() throws Exception {
		executePropagationAndEvaluation(this.getCommitHash(0), this.getCommitHash(1), 1);
	}
	
	@Disabled("Only one test case should run at once.")
	@Test
	public void testOTASecondPropagation() throws Exception {
		executePropagationAndEvaluation(this.getCommitHash(1), this.getCommitHash(2), 2);
	}
	
	@Disabled("Only one test case should run at once.")
	@Test
	public void testOTAThirdPropagation() throws Exception {
		executePropagationAndEvaluation(this.getCommitHash(2), this.getCommitHash(3), 3);
	}
	
	@Disabled("Only one test case should run at once.")
	@Test
	public void testOTAFourthPropagation() throws Exception {
		executePropagationAndEvaluation(this.getCommitHash(3), this.getCommitHash(4), 4);
	}
	
	@Disabled("Only one test case should run at once.")
	@Test
	public void testOTAFifthPropagation() throws Exception {
		executePropagationAndEvaluation(this.getCommitHash(4), this.getCommitHash(5), 5);
	}
}

package cipm.consistency.vsum.test;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * A test case that is to be used to generate test resources required by FirstInstance tests. It
 * functions similar to {@link TEAMMATESCITest} but generates an additional Java-Model per test
 * and saves it at the corresponding path in the target directory of FirstInstance tests (fitests).
 * <br><br>
 * <b>Note that the size of generated test resources may slightly vary, which is to be expected</b>
 * 
 * @author atora
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FITeammatesTestResourceGenerator extends AbstractTEAMMATESCITest {
	private static final Logger LOGGER = Logger.getLogger("cipm." + FITeammatesTestResourceGenerator.class.getSimpleName());
	private FITestResourceGenerator resGen;
	
	protected String getTestModelFileName(int commitHashIndex) {
		return "JavaModel-test" + commitHashIndex;
	}
	
	@Disabled("Enable to generate resource")
	@Order(0)
	@Test
	public void testTeammatesIntegration() throws Exception {
		this.resGen = new FITestResourceGenerator();
		this.resGen.init(this.getTestType(), this.getTestModelFileName(0), true);
		
		this.resGen.generateResourceWhile(() -> executePropagationAndEvaluation(null, this.getCommitHash(0), 0));
		this.resGen.finalise();
	}
	
	@Disabled("Enable to generate resource")
	@Order(1)
	@Test
	public void testTeammatesFirstPropagation() throws Exception {
		this.resGen = new FITestResourceGenerator();
		this.resGen.init(this.getTestType(), this.getTestModelFileName(1), true);
		
		this.resGen.generateResourceWhile(() -> executePropagationAndEvaluation(null, this.getCommitHash(1), 0));
		this.resGen.finalise();
	}
}

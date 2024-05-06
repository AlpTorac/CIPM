package cipm.consistency.fitests.similarity.java;

import org.junit.jupiter.api.BeforeEach;
import cipm.consistency.fitests.similarity.java.initialiser.containers.IModuleInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.containers.ModuleInitialiser;

public class ModuleSimilarityTest extends EObjectSimilarityTest {
	private IModuleInitialiser moduleInitialiser;
	
	@BeforeEach
	@Override
	public void setUp() {
		this.setResourceFileTestPrefix(ModuleSimilarityTest.class.getSimpleName());
		this.moduleInitialiser = new ModuleInitialiser();
		super.setUp();
	}
}

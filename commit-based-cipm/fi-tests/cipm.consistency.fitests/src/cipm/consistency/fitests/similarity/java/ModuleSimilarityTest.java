package cipm.consistency.fitests.similarity.java;

import org.emftext.language.java.modifiers.ModifiersFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

package cipm.consistency.fitests.similarity.java;

import org.emftext.language.java.commons.NamedElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.initialiser.INamedElementInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.IInitialiser;
import cipm.consistency.fitests.similarity.java.params.NameTestParams;

public class NamedElementSimilarityTest extends EObjectSimilarityTest {
	private final String name11 = "name11";
	private final String name22 = "name22";
	
	@BeforeEach
	@Override
	public void setUp() {
		this.setResourceFileTestPrefix(NamedElementSimilarityTest.class.getSimpleName());
		super.setUp();
	}
	
	protected <T extends INamedElementInitialiser & IInitialiser<NamedElement>> NamedElement initElement(T initialiser, String name) {
		var result = initialiser.instantiate();
		initialiser.initialiseName(result, name);
		return result;
	}
	
	@ParameterizedTest
	@ArgumentsSource(NameTestParams.class)
	public <T extends INamedElementInitialiser & IInitialiser<NamedElement>> void testSameName(T initialiser) {
		this.setResourceFileTestIdentifier("testSameName");
		
		var objOne = this.initElement(initialiser, name11);
		
		this.sameX(objOne, initialiser);
	}
	
	@ParameterizedTest
	@ArgumentsSource(NameTestParams.class)
	public <T extends INamedElementInitialiser & IInitialiser<NamedElement>> void testDifferentName(T initialiser) {
		this.setResourceFileTestIdentifier("testDifferentName");
		
		var objOne = this.initElement(initialiser, name11);
		var objTwo = this.initElement(initialiser, name22);
		
		this.differentX(objOne, objTwo);
	}
}

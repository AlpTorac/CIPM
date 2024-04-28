package cipm.consistency.fitests.similarity.java;

import org.emftext.language.java.commons.NamespaceAwareElement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.initialiser.INamespaceAwareElementInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.IInitialiser;
import cipm.consistency.fitests.similarity.java.params.NamespaceTestParams;

public class NamespaceAwareElementTest extends EObjectSimilarityTest {
	private final String ns11 = "ns11";
	private final String ns12 = "ns12";
	private final String ns13 = "ns13";
	
	private final String ns21 = "ns21";
	private final String ns22 = "ns22";
	private final String ns23 = "ns23";

	private final String[] nss1 = new String[] {ns11, ns12, ns13};
	private final String[] nss2 = new String[] {ns21, ns22, ns23};
	
	@BeforeEach
	@Override
	public void setUp() {
		this.setResourceFileTestPrefix(NamespaceAwareElementTest.class.getSimpleName());
		super.setUp();
	}
	
	protected <T extends INamespaceAwareElementInitialiser & IInitialiser<NamespaceAwareElement>> NamespaceAwareElement initElement(T initialiser, String[] nss) {
		var result = initialiser.instantiate();
		initialiser.minimalInitialisation(result);
		initialiser.initialiseNamespaces(result, nss);
		return result;
	}
	
	@ParameterizedTest
	@ArgumentsSource(NamespaceTestParams.class)
	public <T extends INamespaceAwareElementInitialiser & IInitialiser<NamespaceAwareElement>> void testSameNamespace(T initialiser) {
		this.setResourceFileTestIdentifier("testSameNamespace");
		
		var objOne = this.initElement(initialiser, nss1);
		
		this.sameX(objOne, initialiser);
	}

	@ParameterizedTest
	@ArgumentsSource(NamespaceTestParams.class)
	public <T extends INamespaceAwareElementInitialiser & IInitialiser<NamespaceAwareElement>> void testDifferentNamespace(T initialiser) {
		this.setResourceFileTestIdentifier("testDifferentNamespace");
		
		var objOne = this.initElement(initialiser, nss1);
		var objTwo = this.initElement(initialiser, nss2);
		
		this.differentX(objOne, objTwo);	
	}
	
	/**
	 * Tests whether nested namespaces are different.
	 */
	@ParameterizedTest
	@ArgumentsSource(NamespaceTestParams.class)
	public <T extends INamespaceAwareElementInitialiser & IInitialiser<NamespaceAwareElement>> void testNamespaceScope(T initialiser) {
		this.setResourceFileTestIdentifier("testNamespaceScope");
		
		for (int i = 0; i < nss1.length; i++) {
			var newNss = new String[i];
			
			for (int j = 0; j < i; j++) {
				newNss[j] = nss1[j];
			}
			
			var objOne = this.initElement(initialiser, newNss);
			var objTwo = this.initElement(initialiser, nss1);
			
			this.differentX(objOne, objTwo);
			this.differentX(initialiser.clone(objTwo), initialiser.clone(objOne));
		}
	}
}

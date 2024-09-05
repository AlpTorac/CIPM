package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.commons.CommonsPackage;
import org.emftext.language.java.commons.NamespaceAwareElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.commons.INamespaceAwareElementInitialiser;

public class NamespaceAwareElementTest extends EObjectSimilarityTest {
	private final String ns11 = "ns11";
	private final String ns12 = "ns12";
	private final String ns13 = "ns13";

	private final String ns21 = "ns21";
	private final String ns22 = "ns22";
	private final String ns23 = "ns23";

	private final String[] nss1 = new String[] { ns11, ns12, ns13 };
	private final String[] nss2 = new String[] { ns21, ns22, ns23 };

	protected NamespaceAwareElement initElement(INamespaceAwareElementInitialiser init, String[] nss) {
		NamespaceAwareElement result = init.instantiate();
		Assertions.assertTrue(init.initialise(result));
		Assertions.assertTrue(init.addNamespaces(result, nss));
		return result;
	}

	@ParameterizedTest
	@ArgumentsSource(NamespaceAwareElementTestParams.class)
	public void testNamespace(INamespaceAwareElementInitialiser init) {
		this.setCurrentInitialiser(init);
		var objOne = this.initElement(init, nss1);
		var objTwo = this.initElement(init, nss2);

		this.testSimilarity(objOne, objTwo, CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES);
	}

	/**
	 * Tests whether longer namespaces with the same prefix are different.
	 */
	@ParameterizedTest
	@ArgumentsSource(NamespaceAwareElementTestParams.class)
	public void testNamespaceScope(INamespaceAwareElementInitialiser init) {
		this.setCurrentInitialiser(init);
		for (int i = 0; i < nss1.length; i++) {
			var newNss = new String[i];

			for (int j = 0; j < i; j++) {
				newNss[j] = nss1[j];
			}

			var objOne = this.initElement(init, newNss);
			var objTwo = this.initElement(init, nss1);

			this.testSimilarity(objOne, objTwo, CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES);
		}
	}

	@ParameterizedTest
	@ArgumentsSource(NamespaceAwareElementTestParams.class)
	public void testNamespaceNullCheck(INamespaceAwareElementInitialiser init) {
		this.setCurrentInitialiser(init);

		this.testSimilarityNullCheck(this.initElement(init, nss1), init, true,
				CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES);
	}

}

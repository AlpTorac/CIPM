package cipm.consistency.fitests.similarity.jamopp.unittests.complextests;

import java.util.stream.Stream;

import org.emftext.language.java.commons.CommonsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.initialisers.jamopp.commons.INamespaceAwareElementInitialiser;

/**
 * A test class that contains tests for namespaces of
 * {@link NamespaceAwareElement} that have certain relations.
 * 
 * @author Alp Torac Genc
 */
public class NamespaceScopeTest extends AbstractJaMoPPSimilarityTest {
	private static Stream<Arguments> provideArguments() {
		return AbstractJaMoPPSimilarityTest.getAllInitialiserArgumentsFor(INamespaceAwareElementInitialiser.class);
	}

	/**
	 * @param nss   The full namespace
	 * @param begin Begin index (inclusive)
	 * @param end   End index (inclusive)
	 * @return The desired part of nss
	 */
	private String[] getSubNamespace(String[] nss, int begin, int end) {
		var result = new String[end + 1 - begin];
		for (int i = 0; i < result.length; i++) {
			result[i] = nss[i + begin];
		}
		return result;
	}

	/**
	 * Tests whether namespaces with the same prefix are handled accordingly, such
	 * as "ns1" and "ns1.ns2". <br>
	 * <br>
	 * Accounts for all possible (non-empty) prefixes of "ns1.ns2.ns3".
	 */
	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testNamespace_CommonPrefix(INamespaceAwareElementInitialiser init, String displayName) {
		var doNamespacesNotMatter = this.getExpectedSimilarityResult(init.getInstanceClassOfInitialiser(),
				CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES);
		var nss = new String[] { "ns1", "ns2", "ns3" };

		// Similarity checking method here is symmetric
		// no need to swap nae1 and nae2
		for (int i = 0; i < nss.length; i++) {
			var nae1 = init.instantiate();
			Assertions.assertTrue(init.initialise(nae1));
			init.addNamespaces(nae1, this.getSubNamespace(nss, 0, i));

			// More specific namespace: nae1's namespace + more of nss
			for (int j = i; j < nss.length; j++) {
				var nae2 = init.instantiate();
				Assertions.assertTrue(init.initialise(nae2));
				init.addNamespaces(nae2, this.getSubNamespace(nss, 0, j));
				this.testSimilarity(nae1, nae2, doNamespacesNotMatter || i == j);
			}
		}
	}

	/**
	 * Tests whether namespaces with the same suffix are handled accordingly, such
	 * as "ns2" and "ns1.ns2". <br>
	 * <br>
	 * Accounts for all possible (non-empty) suffixes of "ns1.ns2.ns3".
	 */
	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testNamespace_CommonSuffix(INamespaceAwareElementInitialiser init, String displayName) {
		var doNamespacesNotMatter = this.getExpectedSimilarityResult(init.getInstanceClassOfInitialiser(),
				CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES);
		var nss = new String[] { "ns1", "ns2", "ns3" };

		// Similarity checking method here is symmetric
		// no need to swap nae1 and nae2
		for (int i = 0; i < nss.length; i++) {
			var nae1 = init.instantiate();
			Assertions.assertTrue(init.initialise(nae1));
			init.addNamespaces(nae1, this.getSubNamespace(nss, 0, i));

			for (int j = 0; j <= i; j++) {
				var nae2 = init.instantiate();
				Assertions.assertTrue(init.initialise(nae2));
				init.addNamespaces(nae2, this.getSubNamespace(nss, i - j, i));
				this.testSimilarity(nae1, nae2, doNamespacesNotMatter || i == j);
			}
		}
	}

	/**
	 * Tests whether namespaces with the same suffix are handled accordingly, such
	 * as "ns1.ns2.ns3" and "ns1.ns4.ns3".
	 */
	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testNamespace_CommonPrefixAndSuffix(INamespaceAwareElementInitialiser init, String displayName) {
		var doNamespacesNotMatter = this.getExpectedSimilarityResult(init.getInstanceClassOfInitialiser(),
				CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES);

		var ns1 = "ns1";
		var ns2 = "ns2";
		var ns3 = "ns3";
		var ns4 = "ns4";

		var nae1 = init.instantiate();
		Assertions.assertTrue(init.initialise(nae1));
		init.addNamespaces(nae1, new String[] { ns1, ns2, ns3 });

		var nae2 = init.instantiate();
		Assertions.assertTrue(init.initialise(nae2));
		init.addNamespaces(nae2, new String[] { ns1, ns4, ns3 });

		this.testSimilarity(nae1, nae2, doNamespacesNotMatter);
	}

	/**
	 * Tests whether namespaces with same parts are handled accordingly, such as
	 * "ns" and "ns.ns".
	 */
	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testNamespace_SameNamespacePart(INamespaceAwareElementInitialiser init, String displayName) {
		var doNamespacesNotMatter = this.getExpectedSimilarityResult(init.getInstanceClassOfInitialiser(),
				CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES);

		var ns = "ns";

		var nae1 = init.instantiate();
		Assertions.assertTrue(init.initialise(nae1));
		init.addNamespaces(nae1, new String[] { ns });

		var nae2 = init.instantiate();
		Assertions.assertTrue(init.initialise(nae2));
		init.addNamespaces(nae2, new String[] { ns, ns });

		this.testSimilarity(nae1, nae2, doNamespacesNotMatter);
	}

	/**
	 * Tests whether namespaces with duplicated parts are handled accordingly, such
	 * as "ns1.ns1.ns1" and "ns1.ns2.ns1".
	 */
	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testNamespace_DuplicatedNamespaceParts(INamespaceAwareElementInitialiser init, String displayName) {
		var doNamespacesNotMatter = this.getExpectedSimilarityResult(init.getInstanceClassOfInitialiser(),
				CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES);

		var ns1 = "ns1";
		var ns2 = "ns2";

		var nae1 = init.instantiate();
		Assertions.assertTrue(init.initialise(nae1));
		init.addNamespaces(nae1, new String[] { ns1, ns1, ns1 });

		var nae2 = init.instantiate();
		Assertions.assertTrue(init.initialise(nae2));
		init.addNamespaces(nae2, new String[] { ns1, ns2, ns1 });

		this.testSimilarity(nae1, nae2, doNamespacesNotMatter);
	}
}
package cipm.consistency.fitests.similarity.eobject.java.unittests.interfacetests;

import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.imports.Import;
import org.emftext.language.java.imports.ImportsPackage;
import org.emftext.language.java.imports.PackageImport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.eobject.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.classifiers.IClassifierInitialiser;
import cipm.consistency.fitests.similarity.eobject.java.unittests.UsesImports;
import cipm.consistency.fitests.similarity.eobject.java.unittests.UsesPackageImports;

/**
 * Classifier has no modifiable attributes on its own.
 * 
 * @author atora
 */
public class ClassifierTest extends EObjectSimilarityTest implements UsesImports, UsesPackageImports {
	protected Classifier initElement(IClassifierInitialiser init, Import[] imps, PackageImport[] pImps) {

		var result = init.instantiate();
		Assertions.assertTrue(init.initialise(result));

		var addImportsRes = init.addImports(result, imps);
		var addPackageImportsRes = init.addPackageImports(result, pImps);

		Assertions.assertEquals(init.canAddImports(result) || imps == null, addImportsRes);
		Assertions.assertEquals(init.canAddPackageImports(result) || pImps == null, addPackageImportsRes);

		return result;
	}

	@ParameterizedTest
	@ArgumentsSource(ClassifierTestParams.class)
	public void testImports(IClassifierInitialiser init) {
		var objOne = this.initElement(init, new Import[] { this.createMinimalClsImport("cls1") }, null);
		var objTwo = this.initElement(init, new Import[] { this.createMinimalClsImport("cls2") }, null);

		this.testSimilarity(objOne, objTwo, CompilationUnit.class,
				this.getExpectedSimilarityResult(ImportsPackage.Literals.IMPORTING_ELEMENT__IMPORTS).booleanValue()
						|| (!init.canAddImports(objOne) && !init.canAddImports(objTwo)));
	}

	@ParameterizedTest
	@ArgumentsSource(ClassifierTestParams.class)
	public void testImportsSize(IClassifierInitialiser init) {
		var objOne = this.initElement(init,
				new Import[] { this.createMinimalClsImport("cls1"), this.createMinimalClsImport("cls2") }, null);
		var objTwo = this.initElement(init, new Import[] { this.createMinimalClsImport("cls1") }, null);

		this.testSimilarity(objOne, objTwo, CompilationUnit.class,
				this.getExpectedSimilarityResult(ImportsPackage.Literals.IMPORTING_ELEMENT__IMPORTS).booleanValue()
						|| (!init.canAddImports(objOne) && !init.canAddImports(objTwo)));
	}

	@ParameterizedTest
	@ArgumentsSource(ClassifierTestParams.class)
	public void testImportsNullCheck(IClassifierInitialiser init) {
		var objOne = this.initElement(init, new Import[] { this.createMinimalClsImport("cls1") }, null);

		this.testSimilarityNullCheck(objOne, init, true, CompilationUnit.class,
				this.getExpectedSimilarityResult(ImportsPackage.Literals.IMPORTING_ELEMENT__IMPORTS).booleanValue()
						|| (!init.canAddImports(objOne)));
	}

	/**
	 * Package import differences do not break similarity
	 */
	@ParameterizedTest
	@ArgumentsSource(ClassifierTestParams.class)
	public void testPackageImports(IClassifierInitialiser init) {
		var objOne = this.initElement(init, null,
				new PackageImport[] { this.createMinimalPackageImport(new String[] { "ns1", "ns2" }) });
		var objTwo = this.initElement(init, null,
				new PackageImport[] { this.createMinimalPackageImport(new String[] { "ns3", "ns4" }) });

		this.testSimilarity(objOne, objTwo, CompilationUnit.class,
				this.getExpectedSimilarityResult(ImportsPackage.Literals.IMPORTING_ELEMENT__IMPORTS).booleanValue()
						|| (!init.canAddImports(objOne) && !init.canAddImports(objTwo)));
	}

	@ParameterizedTest
	@ArgumentsSource(ClassifierTestParams.class)
	public void testPackageImportsSize(IClassifierInitialiser init) {
		var objOne = this.initElement(init, null,
				new PackageImport[] { this.createMinimalPackageImport(new String[] { "ns1", "ns2" }),
						this.createMinimalPackageImport(new String[] { "ns3", "ns4" }) });
		var objTwo = this.initElement(init, null,
				new PackageImport[] { this.createMinimalPackageImport(new String[] { "ns1", "ns2" }) });

		this.testSimilarity(objOne, objTwo, CompilationUnit.class,
				this.getExpectedSimilarityResult(ImportsPackage.Literals.IMPORTING_ELEMENT__IMPORTS).booleanValue()
						|| (!init.canAddImports(objOne) && !init.canAddImports(objTwo)));
	}

	@ParameterizedTest
	@ArgumentsSource(ClassifierTestParams.class)
	public void testPackageImportsNullCheck(IClassifierInitialiser init) {
		var objOne = this.initElement(init, null,
				new PackageImport[] { this.createMinimalPackageImport(new String[] { "ns1", "ns2" }) });

		this.testSimilarityNullCheck(objOne, init, true, CompilationUnit.class,
				this.getExpectedSimilarityResult(ImportsPackage.Literals.IMPORTING_ELEMENT__IMPORTS).booleanValue()
						|| (!init.canAddImports(objOne)));
	}
}

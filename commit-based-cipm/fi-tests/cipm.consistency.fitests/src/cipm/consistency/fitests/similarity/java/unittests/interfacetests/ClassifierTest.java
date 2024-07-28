package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.imports.Import;
import org.emftext.language.java.imports.ImportsPackage;
import org.emftext.language.java.imports.PackageImport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.classifiers.IClassifierInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesImports;
import cipm.consistency.fitests.similarity.java.unittests.UsesPackageImports;

/**
 * Classifier has no modifiable attributes on its own.
 * 
 * @author atora
 */
public class ClassifierTest extends EObjectSimilarityTest implements UsesImports, UsesPackageImports {
	protected Classifier initElement(IClassifierInitialiser initialiser,
			Import[] imps, PackageImport[] pImps) {
		
		// FIXME: Move adaptation to test params
		
		var result = initialiser.instantiate();
		Assertions.assertTrue(initialiser.initialise(result));
		Assertions.assertTrue(initialiser.addImports(result, imps));
		Assertions.assertTrue(initialiser.addPackageImports(result, pImps));
		return result;
	}
	
	@ParameterizedTest
	@ArgumentsSource(ClassifierTestParams.class)
	public void testImports(IClassifierInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testImports");
		
		var objOne = this.initElement(initialiser,
				new Import[] {this.createMinimalClsImport("cls1")}, null);
		var objTwo = this.initElement(initialiser,
				new Import[] {this.createMinimalClsImport("cls2")}, null);
		
		this.testSimilarity(objOne, objTwo, CompilationUnit.class, ImportsPackage.Literals.IMPORTING_ELEMENT__IMPORTS);
	}
	
	/**
	 * Package import differences do not break similarity
	 */
	@ParameterizedTest
	@ArgumentsSource(ClassifierTestParams.class)
	public void testPackageImports(IClassifierInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testPackageImports");
		
		var objOne = this.initElement(initialiser,
				null, new PackageImport[] {
						this.createMinimalPackageImport(new String[] {"ns1", "ns2"})});
		var objTwo = this.initElement(initialiser,
				null, new PackageImport[] {this.createMinimalPackageImport(new String[] {"ns3", "ns4"})});
		
		this.testSimilarity(objOne, objTwo, CompilationUnit.class, ImportsPackage.Literals.IMPORTING_ELEMENT__IMPORTS);
	}
}

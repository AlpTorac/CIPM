package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.containers.Package;
import org.emftext.language.java.imports.Import;
import org.emftext.language.java.imports.PackageImport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.classifiers.ClassInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.containers.PackageInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.imports.ClassifierImportInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.imports.PackageImportInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IClassifierInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesImports;
import cipm.consistency.fitests.similarity.java.unittests.UsesPackageImports;
import cipm.consistency.fitests.similarity.java.unittests.UsesPackages;

public class ClassifierTest extends EObjectSimilarityTest implements UsesImports, UsesPackageImports {
	protected Classifier initElement(IClassifierInitialiser initialiser,
			Import[] imps, PackageImport[] pImps) {
		Classifier result = initialiser.instantiate();
		initialiser.minimalInitialisationWithContainer(result);
		initialiser.addImports(result, imps);
		initialiser.addPackageImports(result, pImps);
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
		
		this.testX(objOne, objTwo, false);
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
		
		this.testX(objOne, objTwo, false);
	}
}

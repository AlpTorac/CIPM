package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import java.util.Collection;

import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.containers.Package;
import org.emftext.language.java.imports.Import;
import org.emftext.language.java.imports.PackageImport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.generators.ClassifierGenerator;
import cipm.consistency.fitests.similarity.java.generators.CompilationUnitGenerator;
import cipm.consistency.fitests.similarity.java.generators.ImportGenerator;
import cipm.consistency.fitests.similarity.java.generators.PackageImportGenerator;
import cipm.consistency.fitests.similarity.java.initialiser.classifiers.ClassInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.containers.PackageInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.imports.ClassifierImportInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.imports.PackageImportInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IClassifierInitialiser;

public class ClassifierTest extends EObjectSimilarityTest {
	private ImportGenerator<?> impGen;
	private PackageImportGenerator pImpGen;
	private ClassifierGenerator<?> clsGen;
	
	@BeforeEach
	@Override
	public void setUp() {
		this.clsGen = new ClassifierGenerator<>();
		this.clsGen.setDefaultCUGen();
		
		this.impGen = new ImportGenerator<>();
		this.pImpGen = new PackageImportGenerator();
		
		this.registerGenerator(clsGen);
		this.registerGenerator(impGen);
		this.registerGenerator(pImpGen);
		
		super.setUp();
	}
	
	protected Classifier initElement(IClassifierInitialiser initialiser,
			Collection<? extends Import> imps, Collection<? extends PackageImport> pImps) {
		this.clsGen.setInitialiser(initialiser);
		Classifier result = this.clsGen.generateElement();
		
		initialiser.addImports(result, imps);
		initialiser.addPackageImports(result, pImps);
		
		return result;
	}
	
	// TODO: Figure out how to add imports with import strings
	
	@ParameterizedTest
	@ArgumentsSource(ClassifierTestParams.class)
	public void testImports(IClassifierInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testImports");
		
		var objOne = this.initElement(initialiser,
				this.impGen.generateElements(2), null);
		var objTwo = this.initElement(initialiser,
				this.impGen.generateElements(1), null);
		
		// TODO: Replace last parameter
		this.testX(objOne, objTwo, false);
	}
	
	@ParameterizedTest
	@ArgumentsSource(ClassifierTestParams.class)
	public void testPackageImports(IClassifierInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testPackageImports");
		
		var objOne = this.initElement(initialiser,
				null, this.pImpGen.generateElements(2));
		var objTwo = this.initElement(initialiser,
				null, this.pImpGen.generateElements(1));
		
		// TODO: Replace last parameter
		this.testX(objOne, objTwo, false);
	}
}

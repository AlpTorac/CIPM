package cipm.consistency.fitests.similarity.java.unittests;

import java.math.BigInteger;
import java.util.Collection;

import org.emftext.language.java.JavaClasspath;
import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.containers.CompilationUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IClassifierInitialiser;
import cipm.consistency.fitests.similarity.java.params.ClassifierTestParams;

public class ClassifierTest extends EObjectSimilarityTest {
	private String is1;
	private String is2;
	private String is3;
	
	private String ips1;
	private String ips2;
	private String ips3;
	
	@BeforeEach
	@Override
	public void setUp() {
		this.setResourceFileTestPrefix(ClassifierTest.class.getSimpleName());
		
		JavaClasspath.get().registerStdLib();
		
		this.is1 = String.class.getSimpleName();
		this.is2 = Collection.class.getSimpleName();
		this.is3 = BigInteger.class.getSimpleName();
		
		this.ips1 = String.class.getPackageName();
		this.ips2 = Collection.class.getPackageName();
		this.ips3 = BigInteger.class.getPackageName();
		
		super.setUp();
	}
	
	protected Classifier initElement(IClassifierInitialiser initialiser,
			String[] importStrings, String[] importPackageStrings) {
		Classifier result = initialiser.instantiate();
		CompilationUnit cu = (CompilationUnit) initialiser.minimalInitialisationWithContainer(result);
		JavaClasspath.get(cu).registerStdLib();
		JavaClasspath.get(result).registerStdLib();
		
		if (importStrings != null) {
			for (var s : importStrings) {
				initialiser.addImport(result, s);
			}
		}
		
		if (importPackageStrings != null) {
			for (var s : importPackageStrings) {
				initialiser.addPackageImport(result, s);
			}
		}
		
		return result;
	}
	
	// TODO: Figure out how to add imports with import strings
	
	@Disabled("Not functional")
	@ParameterizedTest
	@ArgumentsSource(ClassifierTestParams.class)
	public void testSameImports(IClassifierInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testSameImports");
		
		var objOne = this.initElement(initialiser,
				new String[] {this.is1, this.is2}, null);
		
		this.sameX(objOne, initialiser);
	}
	
	@Disabled("Not functional")
	@ParameterizedTest
	@ArgumentsSource(ClassifierTestParams.class)
	public void testDifferentImports(IClassifierInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testDifferentImports");
		
		var objOne = this.initElement(initialiser,
				new String[] {this.is1, this.is2}, null);
		var objTwo = this.initElement(initialiser,
				new String[] {this.is1, this.is3}, null);
		
		this.differentX(objOne, objTwo);
	}
	
	@ParameterizedTest
	@ArgumentsSource(ClassifierTestParams.class)
	public void testSamePackageImports(IClassifierInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testSamePackageImports");
		
		var objOne = this.initElement(initialiser,
				null, new String [] {this.ips1});
		
		this.sameX(objOne, initialiser);
	}
	
	/**
	 * Package import differences do not break similarity
	 */
	@ParameterizedTest
	@ArgumentsSource(ClassifierTestParams.class)
	public void testDifferentPackageImports(IClassifierInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testDifferentPackageImports");
		
		var objOne = this.initElement(initialiser,
				null, new String [] {this.ips1, this.ips3});
		var objTwo = this.initElement(initialiser,
				null, new String [] {this.ips1, this.ips2});
		
		this.compareX(objOne, objTwo, Boolean.TRUE);
	}
}

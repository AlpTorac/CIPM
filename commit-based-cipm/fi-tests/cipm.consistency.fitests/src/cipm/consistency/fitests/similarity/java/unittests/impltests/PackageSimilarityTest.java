package cipm.consistency.fitests.similarity.java.unittests.impltests;

import java.util.ArrayList;
import java.util.List;

import org.emftext.language.java.containers.Module;
import org.emftext.language.java.containers.Package;
import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.generators.ConcreteClassifierGenerator;
import cipm.consistency.fitests.similarity.java.generators.ModuleGenerator;
import cipm.consistency.fitests.similarity.java.initialiser.classifiers.ClassInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.containers.IModuleInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.containers.IPackageInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.containers.ModuleInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.containers.PackageInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IConcreteClassifierInitialiser;

public class PackageSimilarityTest extends EObjectSimilarityTest {
	private IPackageInitialiser pacInit;
	
	private ConcreteClassifierGenerator ccGen;
	private ModuleGenerator modGen;
	
	@BeforeEach
	@Override
	public void setUp() {
		this.pacInit = new PackageInitialiser();
		
		this.ccGen = new ConcreteClassifierGenerator();
		this.registerGenerator(ccGen);
		
		this.modGen = new ModuleGenerator();
		this.registerGenerator(modGen);
		
		super.setUp();
	}
	
	protected ConcreteClassifier generateCC() {
		return this.ccGen.generateDefaultElement();
	}
	
	protected Module generateMod() {
		return this.modGen.generateDefaultElement();
	}
	
	protected Package initElement(IPackageInitialiser initialiser, Module mod, ConcreteClassifier[] clss) {
		Package pac = initialiser.instantiate();
		initialiser.minimalInitialisation(pac);
		
		initialiser.initialiseModuleField(pac, mod);
		
		if (clss != null) {
			for (var cls : clss) {
				initialiser.addClassifier(pac, cls);
			}
		}
		
		return pac;
	}
	
	@Test
	public void testModule() {
		this.setResourceFileTestIdentifier("testModule");
		
		var objOne = this.initElement(pacInit, this.generateMod(), null);
		var objTwo = this.initElement(pacInit, this.generateMod(), null);
		
		// TODO: Replace last parameter
		this.testX(objOne, objTwo, pacInit, false);
	}
	
	@Test
	public void testClassifiers() {
		this.setResourceFileTestIdentifier("testClassifiers");
		
		var objOne = this.initElement(pacInit, null, new ConcreteClassifier[] {
				this.generateCC()
		});
		var objTwo = this.initElement(pacInit, null, new ConcreteClassifier[] {
				this.generateCC()
		});
		
		// TODO: Replace last parameter
		this.testX(objOne, objTwo, pacInit, false);
	}
}

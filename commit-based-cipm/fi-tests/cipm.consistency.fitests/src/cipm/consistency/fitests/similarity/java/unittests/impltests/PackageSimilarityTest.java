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
import cipm.consistency.fitests.similarity.java.initialiser.classifiers.ClassInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.containers.IModuleInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.containers.IPackageInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.containers.ModuleInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.containers.PackageInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IConcreteClassifierInitialiser;

public class PackageSimilarityTest extends EObjectSimilarityTest {
	private IPackageInitialiser pacInit;
	private IConcreteClassifierInitialiser clsInit;
	private IModuleInitialiser modInit;
	
	private List<ConcreteClassifier> clsList;
	private List<Module> modList;
	
	@BeforeEach
	@Override
	public void setUp() {
		this.setResourceFileTestPrefix(PackageSimilarityTest.class.getSimpleName());
		
		this.pacInit = new PackageInitialiser();
		modInit = new ModuleInitialiser();
		clsInit = new ClassInitialiser();
		
		this.clsList = new ArrayList<ConcreteClassifier>();
		this.modList = new ArrayList<Module>();
		
		var modNames = new String[] {"mod1", "mod2"};
		var clsNames = new String[] {"cls1", "cls2"};
		
		for (var mn : modNames) {
			Module mod = modInit.instantiate();
			modInit.minimalInitialisation(mod);
			modInit.initialiseName(mod, mn);
			
			this.modList.add(mod);
		}
		
		for (var cn : clsNames) {
			ConcreteClassifier cls = clsInit.instantiate();
			clsInit.minimalInitialisation(cls);
			clsInit.initialiseName(cls, cn);
			
			this.clsList.add(cls);
		}
		
		super.setUp();
	}
	
	protected ConcreteClassifier getCCAt(int index) {
		return this.clsInit.clone(this.clsList.get(index));
	}
	
	protected Module getModAt(int index) {
		return this.modInit.clone(this.modList.get(index));
	}
	
	protected Package initElement(IPackageInitialiser initialiser, Module mod, ConcreteClassifier[] clss) {
		Package pac = initialiser.instantiate();
		initialiser.minimalInitialisation(pac);
		
		initialiser.initialiseModuleField(pac, mod);
		initialiser.addClassifiers(pac, clss);
		
		return pac;
	}
	
	@Test
	public void testSameModule() {
		this.setResourceFileTestIdentifier("testSameModule");
		
		var objOne = this.initElement(pacInit, this.getModAt(0), null);
		
		this.sameX(objOne);
	}
	
	@Test
	public void testDifferentModule() {
		this.setResourceFileTestIdentifier("testDifferentModule");
		
		var objOne = this.initElement(pacInit, this.getModAt(0), null);
		var objTwo = this.initElement(pacInit, this.getModAt(1), null);
		
		this.differentX(objOne, objTwo);
	}
	
	@Test
	public void testSameClassifiers() {
		this.setResourceFileTestIdentifier("testSameClassifiers");
		
		var objOne = this.initElement(pacInit, null, new ConcreteClassifier[] {
				this.getCCAt(0), this.getCCAt(1)
		});
		
		this.sameX(objOne);
	}
	
	@Test
	public void testDifferentClassifiers() {
		this.setResourceFileTestIdentifier("testDifferentClassifiers");
		
		var objOne = this.initElement(pacInit, null, new ConcreteClassifier[] {
				this.getCCAt(0)
		});
		var objTwo = this.initElement(pacInit, null, new ConcreteClassifier[] {
				this.getCCAt(1)
		});
		
		this.differentX(objOne, objTwo);
	}
}

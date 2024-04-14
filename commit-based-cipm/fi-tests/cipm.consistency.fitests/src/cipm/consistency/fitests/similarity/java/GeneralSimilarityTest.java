package cipm.consistency.fitests.similarity.java;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import org.emftext.language.java.containers.Module;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.ModuleInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.PackageInitialiser;

/**
 * A test class dedicated to test the general control flow of
 * similarity checking, regardless of the given elements.
 * 
 * @author atora
 */
public class GeneralSimilarityTest extends AbstractSimilarityTest {
	@BeforeEach
	@Override
	public void setUp() {
		this.setResourceFileTestPrefix(GeneralSimilarityTest.class.getSimpleName());
		super.setUp();
	}
	
	@ParameterizedTest
	@ArgumentsSource(InitialiserProvider.class)
	public void testSameReference(EObjectInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testSameReference");
		
		var obj11 = initialiser.instantiate();
		
		var list1 = List.of(obj11);
		
		var resOne = this.createResource(list1);
		
		Assertions.assertTrue(this.areSimilar(resOne.getContents(), resOne.getContents()));
	}

	@ParameterizedTest
	@ArgumentsSource(InitialiserProvider.class)
	public void testListSameReference(EObjectInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testListSameReference");
		
		var obj11 = initialiser.instantiate();
		var obj12 = initialiser.instantiate();
		
		var list1 = List.of(obj11, obj12);
		
		var resOne = this.createResource(list1);
		
		Assertions.assertTrue(this.areSimilar(resOne.getContents(), resOne.getContents()));
	}
	
	@ParameterizedTest
	@ArgumentsSource(InitialiserProvider.class)
	public void testListLeftSubset(EObjectInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testListLeftSubset");
		
		var obj11 = initialiser.instantiate();
		var obj21 = initialiser.clone(obj11);
		
		var obj12 = initialiser.instantiate();
		
		var list1 = List.of(obj11);
		var list2 = List.of(obj21, obj12);
		
		var resOne = this.createResource(list1);
		var resTwo = this.createResource(list2);
		
		Assertions.assertFalse(this.areSimilar(resOne.getContents(), resTwo.getContents()));
	}
	
	@ParameterizedTest
	@ArgumentsSource(InitialiserProvider.class)
	public void testListRightSubset(EObjectInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testListRightSubset");
		
		var obj11 = initialiser.instantiate();
		var obj21 = initialiser.clone(obj11);
		
		var obj12 = initialiser.instantiate();
		
		var list1 = List.of(obj11, obj12);
		var list2 = List.of(obj21);
		
		var resOne = this.createResource(list1);
		var resTwo = this.createResource(list2);
		
		Assertions.assertFalse(this.areSimilar(resOne.getContents(), resTwo.getContents()));
	}
	
	@ParameterizedTest
	@ArgumentsSource(InitialiserProvider.class)
	public void testOneListEmpty(EObjectInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testOneListEmpty");
		
		var obj11 = initialiser.instantiate();
		
		List<EObject> list1 = List.of();
		var list2 = List.of(obj11);
		
		var resOne = this.createResource(list1);
		var resTwo = this.createResource(list2);
		
		Assertions.assertFalse(this.areSimilar(resOne.getContents(), resTwo.getContents()));
		Assertions.assertFalse(this.areSimilar(resTwo.getContents(), resOne.getContents()));
	}
	
	@Test
	public void testClassMismatch() {
		this.setResourceFileTestIdentifier("testClassMismatch");
		
		var mod = new ModuleInitialiser().instantiate();
		var pac = new PackageInitialiser().instantiate();
		
		var list1 = List.of(mod);
		var list2 = List.of(pac);
		
		var resOne = this.createResource(list1);
		var resTwo = this.createResource(list2);
		
		Assertions.assertFalse(this.areSimilar(resOne.getContents(), resTwo.getContents()));
		Assertions.assertFalse(this.areSimilar(resTwo.getContents(), resOne.getContents()));
	}
	
	@Test
	public void testBothListsEmpty() {
		this.setResourceFileTestIdentifier("testBothListsEmpty");
		
		List<EObject> list1 = List.of();
		List<EObject> list2 = List.of();
		
		var resOne = this.createResource(list1);
		var resTwo = this.createResource(list2);
		
		Assertions.assertTrue(this.areSimilar(resOne.getContents(), resTwo.getContents()));
	}
	
	/**
	 * Tests whether a mismatch in the list actually leads to
	 * the similarity check returning false.
	 */
	@Test
	public void testListCheckStop() {
		this.setResourceFileTestIdentifier("testListCheckStop");
		
		var modInit = new ModuleInitialiser();
		
		var modName1 = "mod1";
		var modName2 = "mod2";
		var modName3 = "mod3";
		var modMismatchName = "mod4";
		
		var mod1 = modInit.instantiate();
		modInit.initialiseName(mod1, modName1);
		
		var mod2 = modInit.instantiate();
		modInit.initialiseName(mod2, modName2);
		
		var mod3 = modInit.instantiate();
		modInit.initialiseName(mod3, modName3);
		
		var modMismatch = modInit.instantiate();
		modInit.initialiseName(modMismatch, modMismatchName);
		
		var mods = new Module[] {mod1, mod2, mod3};
		
		for (int i = 0; i < mods.length; i++) {
			var copiedMods1 = new Module[mods.length];
			var copiedMods2 = new Module[mods.length];
			
			// Clone the Module-instances so that they
			// do not jump between resources
			for (int j = 0; j < mods.length; j++) {
				copiedMods1[j] = modInit.clone(mods[j]);
				copiedMods2[j] = modInit.clone(mods[j]);
			}
			
			copiedMods1[i] = modInit.clone(modMismatch);
			
			var list1 = List.of(copiedMods1);
			var list2 = List.of(copiedMods2);
			
			var resOne = this.createResource(list1);
			var resTwo = this.createResource(list2);
			
			Assertions.assertFalse(this.areSimilar(resOne.getContents(), resTwo.getContents()));
			Assertions.assertFalse(this.areSimilar(resTwo.getContents(), resOne.getContents()));
		}
	}
	
	/**
	 * Minimal test that checks, whether lists with the same size,
	 * same elements (clones of them) in the same order are successfully
	 * identified as similar.
	 * <br><br>
	 * Implemented by cloning and comparing 2
	 * {@link org.emftext.language.java.containers.Module}
	 * instances with only their name set, due to the mentioned modules
	 * being expected to always have a name and being easy to compare.
	 */
	@Test
	public void testListSameSizeSameElementsSameOrder() {
		this.setResourceFileTestIdentifier("testListSameSizeSameElementsSameOrder");
		
		var modInit = new ModuleInitialiser();
		
		var modName1 = "mod1";
		var modName2 = "mod2";
		
		var mod1 = modInit.instantiate();
		modInit.initialiseName(mod1, modName1);
		
		var mod2 = modInit.instantiate();
		modInit.initialiseName(mod2, modName2);
		
		var modCopy1 = modInit.clone(mod1);
		var modCopy2 = modInit.clone(mod2);
		
		var list1 = List.of(mod1, mod2);
		var list2 = List.of(modCopy1, modCopy2);
		
		var resOne = this.createResource(list1);
		var resTwo = this.createResource(list2);
		
		Assertions.assertTrue(this.areSimilar(resOne.getContents(), resTwo.getContents()));
	}
}

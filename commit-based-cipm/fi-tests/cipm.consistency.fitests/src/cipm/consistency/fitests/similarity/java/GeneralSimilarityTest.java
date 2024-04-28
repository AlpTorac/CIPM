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
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.ModuleInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.PackageInitialiser;
import cipm.consistency.fitests.similarity.java.params.GeneralTestParams;

/**
 * A test class dedicated to test the general control flow of
 * similarity checking.
 * <br><br>
 * Also contains some tests for lists, from which some abstract
 * from {@link EObject} instances, whereas others make use of
 * {@link Module} instances, as they can easily be compared
 * after their name (module.getName()).
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
	@ArgumentsSource(GeneralTestParams.class)
	public void testIsSimilarSameReference(EObjectInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testIsSimilarSameReference");
		
		var obj11 = initialiser.instantiate();
		
		Assertions.assertTrue(this.isSimilar(obj11, obj11));
	}
	
	@ParameterizedTest
	@ArgumentsSource(GeneralTestParams.class)
	public void testAreSimilarSameReference(EObjectInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testAreSimilarSameReference");
		
		var obj11 = initialiser.instantiate();
		
		var list1 = List.of(obj11);
		
		var resOne = this.createResource(list1);
		
		Assertions.assertTrue(this.areSimilar(resOne.getContents(), resOne.getContents()));
	}

	@ParameterizedTest
	@ArgumentsSource(GeneralTestParams.class)
	public void testListSameReference(EObjectInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testListSameReference");
		
		var obj11 = initialiser.instantiate();
		var obj12 = initialiser.instantiate();
		
		var list1 = List.of(obj11, obj12);
		
		var resOne = this.createResource(list1);
		
		Assertions.assertTrue(this.areSimilar(resOne.getContents(), resOne.getContents()));
	}
	
	@ParameterizedTest
	@ArgumentsSource(GeneralTestParams.class)
	public void testOneListSubList(EObjectInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testListSubList");
		
		var obj11 = initialiser.instantiate();
		var obj11Copy = initialiser.clone(obj11);
		
		var obj12 = initialiser.instantiate();
		
		var list1 = List.of(obj11);
		var list2 = List.of(obj11Copy, obj12);
		
		var resOne = this.createResource(list1);
		var resTwo = this.createResource(list2);
		
		Assertions.assertFalse(this.areSimilar(resOne.getContents(), resTwo.getContents()));
		Assertions.assertFalse(this.areSimilar(resTwo.getContents(), resOne.getContents()));
	}
	
	@ParameterizedTest
	@ArgumentsSource(GeneralTestParams.class)
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
	 * Checks whether lists with the same size but
	 * different elements can be distinguished.
	 * <br><br>
	 * Implemented by cloning and comparing 2
	 * {@link org.emftext.language.java.containers.Module}.
	 */
	@Test
	public void testListSameSizeDifferentElements() {
		this.setResourceFileTestIdentifier("testListSameSizeDifferentElement");
		
		var modInit = new ModuleInitialiser();
		
		var modName1 = "mod1";
		var modName2 = "mod2";
		var modName3 = "mod3";
		
		var modMismatch1Name = "mod4";
		var modMismatch2Name = "mod5";
		var modMismatch3Name = "mod6";
		
		var mod1 = modInit.instantiate();
		modInit.initialiseName(mod1, modName1);
		
		var mod2 = modInit.instantiate();
		modInit.initialiseName(mod2, modName2);
		
		var mod3 = modInit.instantiate();
		modInit.initialiseName(mod3, modName3);
		
		var modMismatch1 = modInit.instantiate();
		modInit.initialiseName(modMismatch1, modMismatch1Name);
		
		var modMismatch2 = modInit.instantiate();
		modInit.initialiseName(modMismatch2, modMismatch2Name);
		
		var modMismatch3 = modInit.instantiate();
		modInit.initialiseName(modMismatch3, modMismatch3Name);
		
		var mods = new Module[] {mod1, mod2, mod3};
		var mismatchMods = new Module[] {modMismatch1, modMismatch2, modMismatch3};
		
		/*
		 * All positions of mismatch for each
		 * mismatch possibility.
		 */
		boolean[][] mismatchEntries = new boolean[][] {
			{true, false, false},
			{false, true, false},
			{false, false, true},
			
			{false, true, true},
			{true, false, true},
			{true, true, false},
			
			{true, true, true}
		};
		
		for (int i = 0; i < mismatchEntries.length; i++) {
			var copiedMods1 = new Module[mods.length];
			var copiedMods2 = new Module[mods.length];
			
			// Clone the Module-instances so that they
			// do not jump between resources
			for (int j = 0; j < mods.length; j++) {
				copiedMods1[j] = modInit.clone(mods[j]);
				copiedMods2[j] = modInit.clone(mods[j]);
			}
			
			var differentMods = copiedMods1.clone();
			
			// Replace elements that are supposed to mismatch
			for (int z = 0; z < mismatchEntries[i].length; z++) {
				if (mismatchEntries[i][z]) {
					var mismatchClone = modInit.clone(mismatchMods[z]);
					
					// Make sure that the replacements are mismatching
					Assertions.assertFalse(this.isSimilar(differentMods[z], mismatchClone),
							"Module that is supposed to be different is not different");
					
					differentMods[z] = mismatchClone;
				}
			}
			
			var list1 = List.of(differentMods);
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
	 * {@link org.emftext.language.java.containers.Module}.
	 */
	@Test
	public void testListSameElementsSameOrder() {
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
	
	/**
	 * Minimal test that checks, whether lists with the same size,
	 * same elements (clones of them) but in different order are successfully
	 * identified as different.
	 * <br><br>
	 * Implemented by cloning and comparing 2
	 * {@link org.emftext.language.java.containers.Module}.
	 */
	@Test
	public void testListSameElementsDifferentOrder() {
		this.setResourceFileTestIdentifier("testListSameElementsDifferentOrder");
		
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
		var list2 = List.of(modCopy2, modCopy1);
		
		var resOne = this.createResource(list1);
		var resTwo = this.createResource(list2);
		
		Assertions.assertFalse(this.areSimilar(resOne.getContents(), resTwo.getContents()));
	}
	
	/**
	 * Checks for potential control flow issues in
	 * {@link #isSimilar(EObject, EObject)}
	 */
	@Test
	public void testIsSimilarEqual() {
		this.setResourceFileTestIdentifier("testIsSimilarEqual");
		
		var initialiser = new ModuleInitialiser();
		
		var obj11 = initialiser.instantiate();
		initialiser.minimalInitialisation(obj11);
		
		var obj11Copy = initialiser.clone(obj11);
		
		Assertions.assertTrue(this.isSimilar(obj11, obj11Copy));
	}
	
	/**
	 * Checks for potential control flow issues in
	 * {@link #areSimilar(java.util.Collection, java.util.Collection)}
	 */
	@Test
	public void testAreSimilarEqual() {
		this.setResourceFileTestIdentifier("testAreSimilarEqual");
		
		var initialiser = new ModuleInitialiser();
		
		var obj11 = initialiser.instantiate();
		initialiser.minimalInitialisation(obj11);
		
		var obj11Copy = initialiser.clone(obj11);
		
		var list1 = List.of(obj11);
		var list2 = List.of(obj11Copy);
		
		var resOne = this.createResource(list1);
		var resTwo = this.createResource(list2);
		
		Assertions.assertTrue(this.areSimilar(resOne.getContents(), resTwo.getContents()));
	}
}

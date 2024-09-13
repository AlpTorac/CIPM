package cipm.consistency.fitests.similarity.eobject.java;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.eobject.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.containers.ModuleInitialiser;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.containers.PackageInitialiser;

import org.emftext.language.java.containers.Module;

/**
 * A test class dedicated to test the general control flow of similarity
 * checking. <br>
 * <br>
 * Also contains some tests for lists, from which some abstract from
 * {@link EObject} instances, whereas others make use of {@link Module}
 * instances, as they can easily be compared after their name
 * (module.getName()).
 * 
 * @author atora
 */
public class GeneralSimilarityTest extends AbstractEObjectJavaSimilarityTest {
	@ParameterizedTest
	@ArgumentsSource(GeneralTestParams.class)
	public void testIsSimilarUninitialisedObject(EObjectInitialiser initialiser) {
		var obj1 = initialiser.instantiate();
		var obj2 = initialiser.instantiate();

		var res = this.isSimilar(obj1, obj2);

		if (res != null) {
			Assertions.assertTrue(res);
		}
	}

	@ParameterizedTest
	@ArgumentsSource(GeneralTestParams.class)
	public void testAreSimilarUninitialisedSingleObject(EObjectInitialiser initialiser) {
		var obj1 = initialiser.instantiate();
		var obj2 = initialiser.instantiate();

		var res = this.areSimilar(List.of(obj1), List.of(obj2));

		if (res != null) {
			Assertions.assertTrue(res);
		}
	}

	@ParameterizedTest
	@ArgumentsSource(GeneralTestParams.class)
	public void testAreSimilarUninitialisedMultipleObjects(EObjectInitialiser initialiser) {
		var obj11 = initialiser.instantiate();
		var obj12 = initialiser.instantiate();
		var obj21 = initialiser.instantiate();
		var obj22 = initialiser.instantiate();

		var res = this.areSimilar(List.of(obj11, obj12), List.of(obj21, obj22));

		if (res != null) {
			Assertions.assertTrue(res);
		}
	}

	/**
	 * Checks if the same {@link EObject} instance is similar to itself.
	 */
	@ParameterizedTest
	@ArgumentsSource(GeneralTestParams.class)
	public void testIsSimilarSameReference(EObjectInitialiser initialiser) {
		var obj11 = initialiser.instantiate();

		Assertions.assertTrue(this.isSimilar(obj11, obj11));
	}

	/**
	 * Checks if the same {@link EObject} instance is similar to itself, if it is
	 * alone in a list.
	 */
	@ParameterizedTest
	@ArgumentsSource(GeneralTestParams.class)
	public void testAreSimilarSameReference(EObjectInitialiser initialiser) {
		var obj11 = initialiser.instantiate();

		var list1 = List.of(obj11);

		var resOne = this.createResource(list1);

		Assertions.assertTrue(this.areSimilar(resOne.getContents(), resOne.getContents()));
	}

	/**
	 * Checks if two lists containing the same {@link EObject} instances are
	 * detected as similar.
	 */
	@ParameterizedTest
	@ArgumentsSource(GeneralTestParams.class)
	public void testListSameReference(EObjectInitialiser initialiser) {
		var obj11 = initialiser.instantiate();
		var obj12 = initialiser.instantiate();

		var list1 = List.of(obj11, obj12);

		var resOne = this.createResource(list1);

		Assertions.assertTrue(this.areSimilar(resOne.getContents(), resOne.getContents()));
	}

	/**
	 * Checks if two lists, where one of them is a sublist of another, are not
	 * similar.
	 */
	@ParameterizedTest
	@ArgumentsSource(GeneralTestParams.class)
	public void testOneListSubList(EObjectInitialiser initialiser) {
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

	/**
	 * Checks if two lists, where one list is empty and the other one is not, are
	 * not similar.
	 */
	@ParameterizedTest
	@ArgumentsSource(GeneralTestParams.class)
	public void testOneListEmpty(EObjectInitialiser initialiser) {
		var obj11 = initialiser.instantiate();

		List<EObject> list1 = List.of();
		var list2 = List.of(obj11);

		var resOne = this.createResource(list1);
		var resTwo = this.createResource(list2);

		Assertions.assertFalse(this.areSimilar(resOne.getContents(), resTwo.getContents()));
		Assertions.assertFalse(this.areSimilar(resTwo.getContents(), resOne.getContents()));
	}

	/**
	 * Checks if two {@link EObject} instances that implement different interfaces
	 * are not similar.
	 */
	@Test
	public void testClassMismatch() {
		var mod = new ModuleInitialiser().instantiate();
		var pac = new PackageInitialiser().instantiate();

		var list1 = List.of(mod);
		var list2 = List.of(pac);

		var resOne = this.createResource(list1);
		var resTwo = this.createResource(list2);

		Assertions.assertFalse(this.areSimilar(resOne.getContents(), resTwo.getContents()));
		Assertions.assertFalse(this.areSimilar(resTwo.getContents(), resOne.getContents()));
	}

	/**
	 * Checks if two empty lists are similar.
	 */
	@Test
	public void testBothListsEmpty() {
		List<EObject> list1 = List.of();
		List<EObject> list2 = List.of();

		var resOne = this.createResource(list1);
		var resTwo = this.createResource(list2);

		Assertions.assertTrue(this.areSimilar(resOne.getContents(), resTwo.getContents()));
	}

	/**
	 * Checks whether lists with the same size but different elements can be
	 * distinguished. <br>
	 * <br>
	 * Implemented by cloning and comparing 2
	 * {@link org.emftext.language.java.containers.Module}.
	 */
	@Test
	public void testListSameSizeDifferentElements() {
		var modInit = new ModuleInitialiser();

		var modName1 = "mod1";
		var modName2 = "mod2";
		var modName3 = "mod3";

		var modMismatch1Name = "mod4";
		var modMismatch2Name = "mod5";
		var modMismatch3Name = "mod6";

		var mod1 = modInit.instantiate();
		modInit.setName(mod1, modName1);

		var mod2 = modInit.instantiate();
		modInit.setName(mod2, modName2);

		var mod3 = modInit.instantiate();
		modInit.setName(mod3, modName3);

		var modMismatch1 = modInit.instantiate();
		modInit.setName(modMismatch1, modMismatch1Name);

		var modMismatch2 = modInit.instantiate();
		modInit.setName(modMismatch2, modMismatch2Name);

		var modMismatch3 = modInit.instantiate();
		modInit.setName(modMismatch3, modMismatch3Name);

		var mods = new Module[] { mod1, mod2, mod3 };
		var mismatchMods = new Module[] { modMismatch1, modMismatch2, modMismatch3 };

		/*
		 * All positions of mismatch for each mismatch possibility.
		 */
		boolean[][] mismatchEntries = new boolean[][] { { true, false, false }, { false, true, false },
				{ false, false, true },

				{ false, true, true }, { true, false, true }, { true, true, false },

				{ true, true, true } };

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
	 * Checks whether lists with the same size, same elements (clones of them) in
	 * the same order are successfully identified as similar. <br>
	 * <br>
	 * Implemented by cloning and comparing 2
	 * {@link org.emftext.language.java.containers.Module}.
	 */
	@Test
	public void testListSameElementsSameOrder() {
		var modInit = new ModuleInitialiser();

		var modName1 = "mod1";
		var modName2 = "mod2";

		var mod1 = modInit.instantiate();
		modInit.setName(mod1, modName1);

		var mod2 = modInit.instantiate();
		modInit.setName(mod2, modName2);

		var modCopy1 = modInit.clone(mod1);
		var modCopy2 = modInit.clone(mod2);

		var list1 = List.of(mod1, mod2);
		var list2 = List.of(modCopy1, modCopy2);

		var resOne = this.createResource(list1);
		var resTwo = this.createResource(list2);

		Assertions.assertTrue(this.areSimilar(resOne.getContents(), resTwo.getContents()));
	}

	/**
	 * Checks whether lists with the same size, same elements (clones of them) but
	 * in different order are successfully identified as different. <br>
	 * <br>
	 * Implemented by cloning and comparing 2
	 * {@link org.emftext.language.java.containers.Module}.
	 */
	@Test
	public void testListSameElementsDifferentOrder() {
		var modInit = new ModuleInitialiser();

		var modName1 = "mod1";
		var modName2 = "mod2";

		var mod1 = modInit.instantiate();
		modInit.setName(mod1, modName1);

		var mod2 = modInit.instantiate();
		modInit.setName(mod2, modName2);

		var modCopy1 = modInit.clone(mod1);
		var modCopy2 = modInit.clone(mod2);

		var list1 = List.of(mod1, mod2);
		var list2 = List.of(modCopy2, modCopy1);

		var resOne = this.createResource(list1);
		var resTwo = this.createResource(list2);

		Assertions.assertFalse(this.areSimilar(resOne.getContents(), resTwo.getContents()));
	}

	/**
	 * Checks if an {@link EObject} instance is similar to its clone.
	 */
	@Test
	public void testIsSimilarEqual() {
		var initialiser = new ModuleInitialiser();

		var obj11 = initialiser.instantiate();
		Assertions.assertTrue(initialiser.setName(obj11, "mod1"));

		var obj11Copy = initialiser.clone(obj11);

		Assertions.assertTrue(this.isSimilar(obj11, obj11Copy));
	}

	/**
	 * Checks if a list containing only an {@link EObject} instance is similar to
	 * another list that contains only a clone of that {@link EObject} instance.
	 */
	@Test
	public void testAreSimilarEqual() {
		var initialiser = new ModuleInitialiser();

		var obj11 = initialiser.instantiate();
		Assertions.assertTrue(initialiser.setName(obj11, "mod2"));

		var obj11Copy = initialiser.clone(obj11);

		var list1 = List.of(obj11);
		var list2 = List.of(obj11Copy);

		var resOne = this.createResource(list1);
		var resTwo = this.createResource(list2);

		Assertions.assertTrue(this.areSimilar(resOne.getContents(), resTwo.getContents()));
	}

	@Test
	public void testIsSimilarOneNull() {
		var initialiser = new ModuleInitialiser();
		var obj = initialiser.instantiate();

		Assertions.assertFalse(this.isSimilar(null, obj));
		Assertions.assertFalse(this.isSimilar(obj, null));
	}

	@Test
	public void testIsSimilarBothNull() {
		Assertions.assertTrue(this.isSimilar(null, null));
	}

	@Test
	public void testAreSimilarOneNull() {
		var initialiser = new ModuleInitialiser();
		var obj = initialiser.instantiate();

		Assertions.assertFalse(this.areSimilar(null, List.of(obj)));
		Assertions.assertFalse(this.areSimilar(List.of(obj), null));
	}

	@Test
	public void testAreSimilarBothNull() {
		Assertions.assertTrue(this.areSimilar(null, null));
	}
}

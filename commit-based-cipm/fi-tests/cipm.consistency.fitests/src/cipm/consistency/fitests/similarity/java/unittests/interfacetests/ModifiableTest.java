package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.modifiers.Modifiable;
import org.emftext.language.java.modifiers.Modifier;
import org.emftext.language.java.modifiers.ModifiersPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.modifiers.IModifiableInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesModifiers;

public class ModifiableTest extends EObjectSimilarityTest implements UsesModifiers {
	protected Modifiable initElement(IModifiableInitialiser init, Modifier[] modifs) {
		Modifiable result = init.instantiate();
		Assertions.assertTrue(init.addModifiers(result, modifs));
		return result;
	}

	@ParameterizedTest
	@ArgumentsSource(ModifiableTestParams.class)
	public void testModifier(IModifiableInitialiser init) {
		this.setCurrentInitialiser(init);
		this.setResourceFileTestIdentifier("testModifier");

		var objOne = this.initElement(init, new Modifier[] { this.createFinal() });
		var objTwo = this.initElement(init, new Modifier[] { this.createAbstract() });

		this.testSimilarity(objOne, objTwo, ModifiersPackage.Literals.MODIFIABLE__MODIFIERS);
	}

	@ParameterizedTest
	@ArgumentsSource(ModifiableTestParams.class)
	public void testModifierSize(IModifiableInitialiser init) {
		this.setCurrentInitialiser(init);
		this.setResourceFileTestIdentifier("testModifierSize");

		var objOne = this.initElement(init, new Modifier[] { this.createFinal(), this.createAbstract() });
		var objTwo = this.initElement(init, new Modifier[] { this.createFinal() });

		this.testSimilarity(objOne, objTwo, ModifiersPackage.Literals.MODIFIABLE__MODIFIERS);
	}

	@ParameterizedTest
	@ArgumentsSource(ModifiableTestParams.class)
	public void testModifierNullCheck(IModifiableInitialiser init) {
		this.setCurrentInitialiser(init);
		this.setResourceFileTestIdentifier("testModifierNullCheck");

		var objOne = this.initElement(init, new Modifier[] { this.createFinal() });
		var objTwo = init.instantiate();

		this.testSimilarity(objOne, objTwo, ModifiersPackage.Literals.MODIFIABLE__MODIFIERS);
	}
}

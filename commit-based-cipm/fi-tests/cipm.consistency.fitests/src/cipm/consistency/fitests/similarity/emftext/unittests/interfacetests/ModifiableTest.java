package cipm.consistency.fitests.similarity.emftext.unittests.interfacetests;

import org.emftext.language.java.modifiers.Modifiable;
import org.emftext.language.java.modifiers.Modifier;
import org.emftext.language.java.modifiers.ModifiersPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.emftext.AbstractEMFTextSimilarityTest;
import cipm.consistency.fitests.similarity.emftext.unittests.UsesModifiers;
import cipm.consistency.initialisers.emftext.modifiers.IModifiableInitialiser;

public class ModifiableTest extends AbstractEMFTextSimilarityTest implements UsesModifiers {
	protected Modifiable initElement(IModifiableInitialiser init, Modifier[] modifs) {
		Modifiable result = init.instantiate();
		Assertions.assertTrue(init.addModifiers(result, modifs));
		return result;
	}

	@ParameterizedTest
	@ArgumentsSource(ModifiableTestParams.class)
	public void testModifier(IModifiableInitialiser init) {
		var objOne = this.initElement(init, new Modifier[] { this.createFinal() });
		var objTwo = this.initElement(init, new Modifier[] { this.createAbstract() });

		this.testSimilarity(objOne, objTwo, ModifiersPackage.Literals.MODIFIABLE__MODIFIERS);
	}

	@ParameterizedTest
	@ArgumentsSource(ModifiableTestParams.class)
	public void testModifierSize(IModifiableInitialiser init) {
		var objOne = this.initElement(init, new Modifier[] { this.createFinal(), this.createAbstract() });
		var objTwo = this.initElement(init, new Modifier[] { this.createFinal() });

		this.testSimilarity(objOne, objTwo, ModifiersPackage.Literals.MODIFIABLE__MODIFIERS);
	}

	@ParameterizedTest
	@ArgumentsSource(ModifiableTestParams.class)
	public void testModifierNullCheck(IModifiableInitialiser init) {
		var objOne = this.initElement(init, new Modifier[] { this.createFinal() });
		var objTwo = init.instantiate();

		this.testSimilarity(objOne, objTwo, ModifiersPackage.Literals.MODIFIABLE__MODIFIERS);
	}
}

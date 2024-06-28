package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.modifiers.Modifiable;
import org.emftext.language.java.modifiers.Modifier;
import org.emftext.language.java.modifiers.ModifiersPackage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.modifiers.IModifiableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.params.ModifierFactory;

public class ModifiableTest extends EObjectSimilarityTest {
	protected Modifiable initElement(IModifiableInitialiser init, Modifier[] modifs) {
		Modifiable result = init.instantiate();
		init.addModifiers(result, modifs);
		return result;
	}
	
	@ParameterizedTest
	@ArgumentsSource(ModifiableTestParams.class)
	public void testModifier(IModifiableInitialiser init) {
		this.setResourceFileTestIdentifier("testModifier");
		
		var objOne = this.initElement(init, new Modifier[] {new ModifierFactory().createFinal()});
		var objTwo = this.initElement(init, null);
		
		this.testX(objOne, objTwo, ModifiersPackage.Literals.MODIFIABLE__MODIFIERS);
	}
}

package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.classifiers.ClassifiersPackage;
import org.emftext.language.java.classifiers.Implementor;
import org.emftext.language.java.types.TypeReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.classifiers.IImplementorInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesTypeReferences;

public class ImplementorTest extends EObjectSimilarityTest implements UsesTypeReferences {
	protected Implementor initElement(IImplementorInitialiser init, TypeReference[] trefs) {
		Implementor result = init.instantiate();
		Assertions.assertTrue(init.minimalInitialisation(result));
		Assertions.assertTrue(init.addImplements(result, trefs));
		return result;
	}
	
	@ParameterizedTest
	@ArgumentsSource(ImplementorTestParams.class)
	public void testImplements(IImplementorInitialiser init) {
		this.setResourceFileTestIdentifier("testImplements");
		
		var objOne = this.initElement(init, new TypeReference[] {
				this.createMinimalClsRef("cls1")
		});
		var objTwo = this.initElement(init, new TypeReference[] {
				this.createMinimalClsRef("cls2")
		});
		
		this.testX(objOne, objTwo, ClassifiersPackage.Literals.IMPLEMENTOR__IMPLEMENTS);
	}
}

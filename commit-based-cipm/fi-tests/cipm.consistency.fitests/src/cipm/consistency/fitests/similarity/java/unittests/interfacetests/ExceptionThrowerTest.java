package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.members.ExceptionThrower;
import org.emftext.language.java.members.MembersPackage;
import org.emftext.language.java.types.NamespaceClassifierReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.members.IExceptionThrowerInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesTypeReferences;

public class ExceptionThrowerTest extends EObjectSimilarityTest implements UsesTypeReferences {
	protected ExceptionThrower initElement(IExceptionThrowerInitialiser init, NamespaceClassifierReference[] refs) {
		ExceptionThrower result = init.instantiate();
		Assertions.assertTrue(init.minimalInitialisation(result));
		Assertions.assertTrue(init.addExceptions(result, refs));
		return result;
	}
	
	@ParameterizedTest
	@ArgumentsSource(ExceptionThrowerTestParams.class)
	public void testExceptions(IExceptionThrowerInitialiser init) {
		this.setResourceFileTestIdentifier("testExceptions");
		
		var objOne = this.initElement(init, new NamespaceClassifierReference[] {
				this.createMinimalCNR("cls1")	
		});
		var objTwo = this.initElement(init, new NamespaceClassifierReference[] {
				this.createMinimalCNR("cls2")	
		});
		
		this.testX(objOne, objTwo, MembersPackage.Literals.EXCEPTION_THROWER__EXCEPTIONS);
	}
}

package cipm.consistency.fitests.similarity.jamopp.unittests.interfacetests;

import java.util.stream.Stream;

import org.emftext.language.java.members.ExceptionThrower;
import org.emftext.language.java.members.MembersPackage;
import org.emftext.language.java.types.NamespaceClassifierReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesTypeReferences;
import cipm.consistency.initialisers.jamopp.members.IExceptionThrowerInitialiser;

public class ExceptionThrowerTest extends AbstractJaMoPPSimilarityTest implements UsesTypeReferences {
	private NamespaceClassifierReference exc1;
	private NamespaceClassifierReference exc2;

	private static Stream<Arguments> provideArguments() {
		return AbstractJaMoPPSimilarityTest.getAllInitialiserArgumentsFor(IExceptionThrowerInitialiser.class);
	}

	protected ExceptionThrower initElement(IExceptionThrowerInitialiser init,
			NamespaceClassifierReference[] exceptions) {
		ExceptionThrower result = init.instantiate();
		Assertions.assertTrue(init.initialise(result));
		Assertions.assertTrue(init.addExceptions(result, exceptions));
		return result;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		exc1 = this.createMinimalCNR("cls1");
		exc2 = this.createMinimalCNR("cls2");
		Assertions.assertFalse(this.isSimilar(exc1, exc2));
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testExceptions(IExceptionThrowerInitialiser init, String displayName) {
		var objOne = this.initElement(init, new NamespaceClassifierReference[] { this.cloneEObjWithContainers(exc1) });
		var objTwo = this.initElement(init, new NamespaceClassifierReference[] { this.cloneEObjWithContainers(exc2) });

		this.testSimilarity(objOne, objTwo, MembersPackage.Literals.EXCEPTION_THROWER__EXCEPTIONS);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testExceptionsSize(IExceptionThrowerInitialiser init, String displayName) {
		var objOne = this.initElement(init, new NamespaceClassifierReference[] { this.cloneEObjWithContainers(exc1),
				this.cloneEObjWithContainers(exc2) });
		var objTwo = this.initElement(init, new NamespaceClassifierReference[] { this.cloneEObjWithContainers(exc1) });

		this.testSimilarity(objOne, objTwo, MembersPackage.Literals.EXCEPTION_THROWER__EXCEPTIONS);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testExceptionsPosition(IExceptionThrowerInitialiser init, String displayName) {
		var objOne = this.initElement(init, new NamespaceClassifierReference[] { this.cloneEObjWithContainers(exc1),
				this.cloneEObjWithContainers(exc2) });
		var objTwo = this.initElement(init, new NamespaceClassifierReference[] { this.cloneEObjWithContainers(exc2),
				this.cloneEObjWithContainers(exc1) });

		this.testSimilarity(objOne, objTwo, MembersPackage.Literals.EXCEPTION_THROWER__EXCEPTIONS);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testExceptionsDuplication(IExceptionThrowerInitialiser init, String displayName) {
		var objOne = this.initElement(init, new NamespaceClassifierReference[] { this.cloneEObjWithContainers(exc1),
				this.cloneEObjWithContainers(exc1) });
		var objTwo = this.initElement(init, new NamespaceClassifierReference[] { this.cloneEObjWithContainers(exc1) });

		this.testSimilarity(objOne, objTwo, MembersPackage.Literals.EXCEPTION_THROWER__EXCEPTIONS);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testExceptionsNullCheck(IExceptionThrowerInitialiser init, String displayName) {
		this.testSimilarityNullCheck(
				this.initElement(init, new NamespaceClassifierReference[] { this.cloneEObjWithContainers(exc1) }), init,
				true, MembersPackage.Literals.EXCEPTION_THROWER__EXCEPTIONS);
	}
}

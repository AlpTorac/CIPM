package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.variables.AdditionalLocalVariable;
import org.emftext.language.java.variables.LocalVariable;
import org.emftext.language.java.variables.VariablesPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesAdditionalLocalVariables;
import cipm.consistency.initialisers.jamopp.variables.LocalVariableInitialiser;

public class LocalVariableTest extends AbstractJaMoPPSimilarityTest implements UsesAdditionalLocalVariables {
	private AdditionalLocalVariable alv1;
	private AdditionalLocalVariable alv2;

	protected LocalVariable initElement(AdditionalLocalVariable[] alvs) {
		var lvInit = new LocalVariableInitialiser();
		var lv = lvInit.instantiate();
		Assertions.assertTrue(lvInit.addAdditionalLocalVariables(lv, alvs));
		return lv;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		alv1 = this.createMinimalALV("alv1");
		alv2 = this.createMinimalALV("alv2");
		Assertions.assertFalse(this.isSimilar(alv1, alv2));
	}

	@Test
	public void testLocalVariable() {
		var objOne = this.initElement(new AdditionalLocalVariable[] { this.cloneEObjWithContainers(alv1) });
		var objTwo = this.initElement(new AdditionalLocalVariable[] { this.cloneEObjWithContainers(alv2) });

		this.testSimilarity(objOne, objTwo, VariablesPackage.Literals.LOCAL_VARIABLE__ADDITIONAL_LOCAL_VARIABLES);
	}

	@Test
	public void testLocalVariableSize() {
		var objOne = this.initElement(new AdditionalLocalVariable[] { this.cloneEObjWithContainers(alv1),
				this.cloneEObjWithContainers(alv2) });
		var objTwo = this.initElement(new AdditionalLocalVariable[] { this.cloneEObjWithContainers(alv1) });

		this.testSimilarity(objOne, objTwo, VariablesPackage.Literals.LOCAL_VARIABLE__ADDITIONAL_LOCAL_VARIABLES);
	}

	@Test
	public void testLocalVariablePosition() {
		var objOne = this.initElement(new AdditionalLocalVariable[] { this.cloneEObjWithContainers(alv1),
				this.cloneEObjWithContainers(alv2) });
		var objTwo = this.initElement(new AdditionalLocalVariable[] { this.cloneEObjWithContainers(alv2),
				this.cloneEObjWithContainers(alv1) });

		this.testSimilarity(objOne, objTwo, VariablesPackage.Literals.LOCAL_VARIABLE__ADDITIONAL_LOCAL_VARIABLES);
	}

	@Test
	public void testLocalVariableSizeDuplication() {
		var objOne = this.initElement(new AdditionalLocalVariable[] { this.cloneEObjWithContainers(alv1),
				this.cloneEObjWithContainers(alv1) });
		var objTwo = this.initElement(new AdditionalLocalVariable[] { this.cloneEObjWithContainers(alv1) });

		this.testSimilarity(objOne, objTwo, VariablesPackage.Literals.LOCAL_VARIABLE__ADDITIONAL_LOCAL_VARIABLES);
	}

	@Test
	public void testLocalVariableNullCheck() {
		this.testSimilarityNullCheck(
				this.initElement(new AdditionalLocalVariable[] { this.cloneEObjWithContainers(alv1) }),
				new LocalVariableInitialiser(), false,
				VariablesPackage.Literals.LOCAL_VARIABLE__ADDITIONAL_LOCAL_VARIABLES);
	}
}

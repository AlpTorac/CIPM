package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.statements.LocalVariableStatement;
import org.emftext.language.java.statements.StatementsPackage;
import org.emftext.language.java.variables.LocalVariable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesLocalVariables;
import cipm.consistency.initialisers.jamopp.statements.LocalVariableStatementInitialiser;

public class LocalVariableStatementTest extends AbstractJaMoPPSimilarityTest implements UsesLocalVariables {
	private LocalVariable lv1;
	private LocalVariable lv2;

	protected LocalVariableStatement initElement(LocalVariable lv) {
		var lvsInit = new LocalVariableStatementInitialiser();
		var lvs = lvsInit.instantiate();
		Assertions.assertTrue(lvsInit.setVariable(lvs, lv));
		return lvs;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		lv1 = this.createMinimalLV("lv1");
		lv2 = this.createMinimalLV("lv2");
		Assertions.assertFalse(this.isSimilar(lv1, lv2));
	}

	@Test
	public void testVariable() {
		var objOne = this.initElement(this.cloneEObjWithContainers(lv1));
		var objTwo = this.initElement(this.cloneEObjWithContainers(lv2));

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.LOCAL_VARIABLE_STATEMENT__VARIABLE);
	}

	@Test
	public void testVariableNullCheck() {
		this.testSimilarityNullCheck(this.initElement(this.cloneEObjWithContainers(lv1)),
				new LocalVariableStatementInitialiser(), false,
				StatementsPackage.Literals.LOCAL_VARIABLE_STATEMENT__VARIABLE);
	}
}

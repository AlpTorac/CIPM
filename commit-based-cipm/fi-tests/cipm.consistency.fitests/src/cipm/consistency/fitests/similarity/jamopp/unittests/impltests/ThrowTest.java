package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.StatementsPackage;
import org.emftext.language.java.statements.Throw;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesExpressions;
import cipm.consistency.initialisers.jamopp.statements.ThrowInitialiser;

public class ThrowTest extends AbstractJaMoPPSimilarityTest implements UsesExpressions {
	private Expression thr1;
	private Expression thr2;

	protected Throw initElement(Expression throwable) {
		var thInit = new ThrowInitialiser();
		var th = thInit.instantiate();
		Assertions.assertTrue(thInit.setThrowable(th, throwable));
		return th;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		thr1 = this.createMinimalSR("str1");
		thr2 = this.createMinimalSR("str2");
		Assertions.assertFalse(this.isSimilar(thr1, thr2));
	}

	@Test
	public void testThrowable() {
		var objOne = this.initElement(this.cloneEObjWithContainers(thr1));
		var objTwo = this.initElement(this.cloneEObjWithContainers(thr2));

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.THROW__THROWABLE);
	}

	@Test
	public void testThrowableNullCheck() {
		this.testSimilarityNullCheck(this.initElement(this.cloneEObjWithContainers(thr1)), new ThrowInitialiser(),
				false, StatementsPackage.Literals.THROW__THROWABLE);
	}
}

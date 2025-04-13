package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.StatementsPackage;
import org.emftext.language.java.statements.SynchronizedBlock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesExpressions;
import cipm.consistency.initialisers.jamopp.statements.SynchronizedBlockInitialiser;

public class SynchronizedBlockTest extends AbstractJaMoPPSimilarityTest implements UsesExpressions {
	private Expression lp1;
	private Expression lp2;

	protected SynchronizedBlock initElement(Expression lockProvider) {
		var sbInit = new SynchronizedBlockInitialiser();
		var sb = sbInit.instantiate();
		Assertions.assertTrue(sbInit.setLockProvider(sb, lockProvider));
		return sb;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		lp1 = this.createDecimalIntegerLiteral(1);
		lp2 = this.createDecimalIntegerLiteral(2);
		Assertions.assertFalse(this.isSimilar(lp1, lp2));
	}

	@Test
	public void testLockProvider() {
		var objOne = this.initElement(this.cloneEObjWithContainers(lp1));
		var objTwo = this.initElement(this.cloneEObjWithContainers(lp2));

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.SYNCHRONIZED_BLOCK__LOCK_PROVIDER);
	}

	@Test
	public void testLockProviderNullCheck() {
		this.testSimilarityNullCheck(this.initElement(this.cloneEObjWithContainers(lp1)),
				new SynchronizedBlockInitialiser(), false,
				StatementsPackage.Literals.SYNCHRONIZED_BLOCK__LOCK_PROVIDER);
	}
}

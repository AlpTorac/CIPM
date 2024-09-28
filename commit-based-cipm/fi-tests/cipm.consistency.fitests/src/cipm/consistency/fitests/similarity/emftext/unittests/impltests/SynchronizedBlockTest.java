package cipm.consistency.fitests.similarity.emftext.unittests.impltests;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.StatementsPackage;
import org.emftext.language.java.statements.SynchronizedBlock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.emftext.AbstractEMFTextSimilarityTest;
import cipm.consistency.fitests.similarity.emftext.unittests.UsesExpressions;
import cipm.consistency.initialisers.emftext.statements.SynchronizedBlockInitialiser;

public class SynchronizedBlockTest extends AbstractEMFTextSimilarityTest implements UsesExpressions {
	protected SynchronizedBlock initElement(Expression lockProvider) {
		var sbInit = new SynchronizedBlockInitialiser();
		var sb = sbInit.instantiate();
		Assertions.assertTrue(sbInit.setLockProvider(sb, lockProvider));
		return sb;
	}

	@Test
	public void testLockProvider() {
		var objOne = this.initElement(this.createDecimalIntegerLiteral(1));
		var objTwo = this.initElement(this.createDecimalIntegerLiteral(2));

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.SYNCHRONIZED_BLOCK__LOCK_PROVIDER);
	}

	@Test
	public void testLockProviderNullCheck() {
		this.testSimilarityNullCheck(this.initElement(this.createDecimalIntegerLiteral(1)),
				new SynchronizedBlockInitialiser(), false,
				StatementsPackage.Literals.SYNCHRONIZED_BLOCK__LOCK_PROVIDER);
	}
}
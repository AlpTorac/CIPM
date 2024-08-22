package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.StatementsPackage;
import org.emftext.language.java.statements.SynchronizedBlock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.statements.SynchronizedBlockInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesExpressions;

public class SynchronizedBlockTest extends EObjectSimilarityTest implements UsesExpressions {
	protected SynchronizedBlock initElement(Expression lockProvider) {
		var sbInit = new SynchronizedBlockInitialiser();
		var sb = sbInit.instantiate();
		Assertions.assertTrue(sbInit.setLockProvider(sb, lockProvider));
		return sb;
	}

	@Test
	public void testLockProvider() {
		this.setResourceFileTestIdentifier("testLockProvider");

		var objOne = this.initElement(this.createDecimalIntegerLiteral(1));
		var objTwo = this.initElement(this.createDecimalIntegerLiteral(2));

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.SYNCHRONIZED_BLOCK__LOCK_PROVIDER);
	}

	@Test
	public void testLockProviderNullCheck() {
		this.setResourceFileTestIdentifier("testLockProviderNullCheck");

		var objOne = this.initElement(this.createDecimalIntegerLiteral(1));
		var objTwo = new SynchronizedBlockInitialiser().instantiate();

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.SYNCHRONIZED_BLOCK__LOCK_PROVIDER);
	}
}

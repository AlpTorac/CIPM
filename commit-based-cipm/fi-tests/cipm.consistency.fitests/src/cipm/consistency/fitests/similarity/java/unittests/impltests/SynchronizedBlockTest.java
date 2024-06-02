package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.SynchronizedBlock;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.statements.SynchronizedBlockInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesExpressions;

public class SynchronizedBlockTest extends EObjectSimilarityTest implements UsesExpressions {
	protected SynchronizedBlock initElement(Expression expr) {
		var sbInit = new SynchronizedBlockInitialiser();
		var sb = sbInit.instantiate();
		sbInit.minimalInitialisation(sb);
		sbInit.setLockProvider(sb, expr);
		return sb;
	}
	
	@Test
	public void testLockProvider() {
		this.setResourceFileTestIdentifier("testLockProvider");
		
		var objOne = this.initElement(this.createInteger(1));
		var objTwo = this.initElement(this.createInteger(2));
		
		this.compareX(objOne, objTwo, false);
	}
}

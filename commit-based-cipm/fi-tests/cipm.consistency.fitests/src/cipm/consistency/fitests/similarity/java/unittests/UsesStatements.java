package cipm.consistency.fitests.similarity.java.unittests;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.Assert;
import org.emftext.language.java.statements.Return;

import cipm.consistency.fitests.similarity.java.initialiser.params.LiteralFactory;
import cipm.consistency.fitests.similarity.java.initialiser.statements.AssertInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.statements.ReturnInitialiser;

public interface UsesStatements {
	public default Return createMinimalNullReturn() {
		return this.createMinimalReturn(new LiteralFactory().createNullLiteral());
	}
	
	public default Return createMinimalReturn(Expression returnExpr) {
		var init = new ReturnInitialiser();
		Return result = init.instantiate();
		init.minimalInitialisation(result);
		init.setReturnValue(result, returnExpr);
		return result;
	}
	
	public default Assert createMinimalTrivialAssert() {
		var init = new AssertInitialiser();
		Assert result = init.instantiate();
		init.minimalInitialisation(result);
		init.setCondition(result, new LiteralFactory().createBooleanLiteral(true));
		return result;
	}
}

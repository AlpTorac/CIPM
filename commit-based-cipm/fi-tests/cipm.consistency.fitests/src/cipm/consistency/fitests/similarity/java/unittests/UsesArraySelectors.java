package cipm.consistency.fitests.similarity.java.unittests;

import org.emftext.language.java.arrays.ArraySelector;
import org.emftext.language.java.expressions.Expression;

import cipm.consistency.fitests.similarity.java.initialiser.arrays.ArraySelectorInitialiser;

public interface UsesArraySelectors extends UsesExpressions {
	public default ArraySelector createAS(Expression pos) {
		var init = new ArraySelectorInitialiser();
		var as = init.instantiate();
		init.setPosition(as, pos);
		return as;
	}
	
	public default ArraySelector createMinimalAS(int idx) {
		return this.createAS(this.createDecimalIntegerLiteral(idx));
	}
}

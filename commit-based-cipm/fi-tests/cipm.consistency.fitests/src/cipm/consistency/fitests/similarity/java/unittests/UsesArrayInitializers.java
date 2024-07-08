package cipm.consistency.fitests.similarity.java.unittests;

import org.emftext.language.java.arrays.ArrayInitializationValue;
import org.emftext.language.java.arrays.ArrayInitializer;
import org.emftext.language.java.expressions.Expression;

import cipm.consistency.fitests.similarity.java.initialiser.arrays.ArrayInitializerInitialiser;

public interface UsesArrayInitializers {
	public default ArrayInitializer createMinimalArrayInitializer(ArrayInitializationValue[] aivs) {
		var init = new ArrayInitializerInitialiser();
		ArrayInitializer result = init.instantiate();
		init.addInitialValues(result, aivs);
		return result;
	}
	
	public default ArrayInitializer createMinimalArrayInitializer(Expression expr) {
		return this.createMinimalArrayInitializer(new ArrayInitializationValue[] {expr});
	}
}

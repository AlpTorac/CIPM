package cipm.consistency.fitests.similarity.java.initialiser.instantiations;

import org.emftext.language.java.instantiations.ExplicitConstructorCall;
import org.emftext.language.java.literals.Self;

public interface IExplicitConstructorCallInitialiser extends IInstantiationInitialiser {
	public default void setCallTarget(ExplicitConstructorCall ecc, Self self) {
		if (self != null) {
			ecc.setCallTarget(self);
			assert ecc.getCallTarget().equals(self);
		}
	}
}

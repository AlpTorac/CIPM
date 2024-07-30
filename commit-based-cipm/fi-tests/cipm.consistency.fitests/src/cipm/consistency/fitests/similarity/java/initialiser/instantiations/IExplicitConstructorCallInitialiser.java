package cipm.consistency.fitests.similarity.java.initialiser.instantiations;

import org.emftext.language.java.instantiations.ExplicitConstructorCall;
import org.emftext.language.java.literals.Self;

public interface IExplicitConstructorCallInitialiser extends IInstantiationInitialiser {
	@Override
	public ExplicitConstructorCall instantiate();

	public default boolean setCallTarget(ExplicitConstructorCall ecc, Self self) {
		if (self != null) {
			ecc.setCallTarget(self);
			return ecc.getCallTarget().equals(self);
		}
		return true;
	}
}

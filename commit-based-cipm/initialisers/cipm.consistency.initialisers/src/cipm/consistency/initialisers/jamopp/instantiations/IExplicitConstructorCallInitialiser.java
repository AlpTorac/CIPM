package cipm.consistency.initialisers.jamopp.instantiations;

import org.emftext.language.java.instantiations.ExplicitConstructorCall;
import org.emftext.language.java.literals.Self;

public interface IExplicitConstructorCallInitialiser extends IInstantiationInitialiser {
	@Override
	public ExplicitConstructorCall instantiate();

	public default boolean setCallTarget(ExplicitConstructorCall ecc, Self callTarget) {
		if (callTarget != null) {
			ecc.setCallTarget(callTarget);
			return ecc.getCallTarget().equals(callTarget);
		}
		return true;
	}
}

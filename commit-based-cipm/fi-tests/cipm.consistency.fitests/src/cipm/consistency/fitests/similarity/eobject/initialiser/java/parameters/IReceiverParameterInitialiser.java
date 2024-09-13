package cipm.consistency.fitests.similarity.eobject.initialiser.java.parameters;

import org.emftext.language.java.literals.This;
import org.emftext.language.java.parameters.ReceiverParameter;
import org.emftext.language.java.types.TypeReference;

import cipm.consistency.fitests.similarity.eobject.initialiser.java.annotations.IAnnotableInitialiser;

public interface IReceiverParameterInitialiser extends IAnnotableInitialiser, IParameterInitialiser {
	@Override
	public ReceiverParameter instantiate();

	public default boolean setOuterTypeReference(ReceiverParameter rp, TypeReference otRef) {
		if (otRef != null) {
			rp.setOuterTypeReference(otRef);
			return rp.getOuterTypeReference().equals(otRef);
		}
		return true;
	}

	public default boolean setThisReference(ReceiverParameter rp, This thisRef) {
		if (thisRef != null) {
			rp.setThisReference(thisRef);
			return rp.getThisReference().equals(thisRef);
		}
		return true;
	}
}

package cipm.consistency.fitests.similarity.java.initialiser.parameters;

import org.emftext.language.java.literals.This;
import org.emftext.language.java.parameters.ReceiverParameter;
import org.emftext.language.java.types.TypeReference;

import cipm.consistency.fitests.similarity.java.initialiser.ModificationMethod;
import cipm.consistency.fitests.similarity.java.initialiser.annotations.IAnnotableInitialiser;

public interface IReceiverParameterInitialiser extends IAnnotableInitialiser, IParameterInitialiser {
    @Override
    public ReceiverParameter instantiate();
    @ModificationMethod
	public default boolean setOuterTypeReference(ReceiverParameter rp, TypeReference tref) {
		if (tref != null) {
			rp.setOuterTypeReference(tref);
			return rp.getOuterTypeReference().equals(tref);
		}
		return true;
	}
    @ModificationMethod
	public default boolean setThisReference(ReceiverParameter rp, This th) {
		if (th != null) {
			rp.setThisReference(th);
			return rp.getThisReference().equals(th);
		}
		return true;
	}
}

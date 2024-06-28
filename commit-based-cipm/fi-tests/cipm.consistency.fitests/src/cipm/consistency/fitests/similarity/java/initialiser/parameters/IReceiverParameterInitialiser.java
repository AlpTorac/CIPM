package cipm.consistency.fitests.similarity.java.initialiser.parameters;

import org.emftext.language.java.parameters.ReceiverParameter;
import org.emftext.language.java.types.TypeReference;

import cipm.consistency.fitests.similarity.java.initialiser.annotations.IAnnotableInitialiser;

public interface IReceiverParameterInitialiser extends IAnnotableInitialiser, IParameterInitialiser {
	public default void setOuterTypeReference(ReceiverParameter rp, TypeReference tref) {
		if (tref != null) {
			rp.setOuterTypeReference(tref);
			assert rp.getOuterTypeReference().equals(tref);
		}
	}
}

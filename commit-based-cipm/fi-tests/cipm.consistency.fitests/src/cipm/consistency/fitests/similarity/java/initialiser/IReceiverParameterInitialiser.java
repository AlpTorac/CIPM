package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.parameters.Parametrizable;
import org.emftext.language.java.parameters.ReceiverParameter;
import org.emftext.language.java.types.TypeReference;

public interface IReceiverParameterInitialiser extends IAnnotableInitialiser, IParameterInitialiser {
	@Override
	public ReceiverParameter instantiate();
	
	public default void setOuterTypeReference(ReceiverParameter rp, TypeReference tref) {
		if (tref != null) {
			rp.setOuterTypeReference(tref);
			assert rp.getOuterTypeReference().equals(tref);
		}
	}
}

package cipm.consistency.fitests.similarity.java.initialiser.parameters;

import org.emftext.language.java.parameters.CatchParameter;
import org.emftext.language.java.types.TypeReference;

public interface ICatchParameterInitialiser extends IOrdinaryParameterInitialiser {
    @Override
    public CatchParameter instantiate();
	public default boolean addTypeReference(CatchParameter cp, TypeReference tref) {
		if (tref != null) {
			cp.getTypeReferences().add(tref);
			return cp.getTypeReferences().contains(tref);
		}
		return true;
	}
	
	public default boolean addTypeReferences(CatchParameter cp, TypeReference[] trefs) {
		return this.doMultipleModifications(cp, trefs, this::addTypeReference);
	}
}

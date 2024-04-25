package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.parameters.CatchParameter;
import org.emftext.language.java.types.TypeReference;

public interface ICatchParameterInitialiser extends IOrdinaryParameterInitialiser {
	@Override
	public CatchParameter instantiate();
	
	@Override
	public default CatchParameter minimalInstantiation() {
		return (CatchParameter) IOrdinaryParameterInitialiser.super.minimalInstantiation();
	}
	
	public default void addTypeReference(CatchParameter cp, TypeReference tref) {
		if (tref != null) {
			cp.getTypeReferences().add(tref);
			assert cp.getTypeReferences().contains(tref);
		}
	}
}

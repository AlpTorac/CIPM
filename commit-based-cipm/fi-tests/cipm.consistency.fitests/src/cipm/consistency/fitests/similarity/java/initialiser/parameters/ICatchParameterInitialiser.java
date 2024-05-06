package cipm.consistency.fitests.similarity.java.initialiser.parameters;

import org.emftext.language.java.parameters.CatchParameter;
import org.emftext.language.java.types.TypeReference;

public interface ICatchParameterInitialiser extends IOrdinaryParameterInitialiser {
	public default void addTypeReference(CatchParameter cp, TypeReference tref) {
		if (tref != null) {
			cp.getTypeReferences().add(tref);
			assert cp.getTypeReferences().contains(tref);
		}
	}
}

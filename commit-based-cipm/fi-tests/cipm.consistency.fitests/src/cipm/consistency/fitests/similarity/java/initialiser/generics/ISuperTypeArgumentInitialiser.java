package cipm.consistency.fitests.similarity.java.initialiser.generics;

import org.emftext.language.java.generics.SuperTypeArgument;
import org.emftext.language.java.types.TypeReference;

import cipm.consistency.fitests.similarity.java.initialiser.ITypeArgumentInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IAnnotableInitialiser;

public interface ISuperTypeArgumentInitialiser extends IAnnotableInitialiser,
	ITypeArgumentInitialiser {
	public default void setSuperType(SuperTypeArgument sta, TypeReference tref) {
		if (tref != null) {
			sta.setSuperType(tref);
			assert sta.getSuperType().equals(tref);
		}
	}
}

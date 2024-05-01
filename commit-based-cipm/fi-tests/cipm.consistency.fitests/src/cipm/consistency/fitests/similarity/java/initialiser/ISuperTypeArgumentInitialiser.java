package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.generics.SuperTypeArgument;
import org.emftext.language.java.types.TypeReference;

public interface ISuperTypeArgumentInitialiser extends IAnnotableInitialiser,
	ITypeArgumentInitialiser {
	public default void setSuperType(SuperTypeArgument sta, TypeReference tref) {
		if (tref != null) {
			sta.setSuperType(tref);
			assert sta.getSuperType().equals(tref);
		}
	}
}

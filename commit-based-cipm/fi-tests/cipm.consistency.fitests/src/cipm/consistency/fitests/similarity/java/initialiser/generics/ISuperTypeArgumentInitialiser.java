package cipm.consistency.fitests.similarity.java.initialiser.generics;

import org.emftext.language.java.generics.SuperTypeArgument;
import org.emftext.language.java.types.TypeReference;

import cipm.consistency.fitests.similarity.java.initialiser.ModificationMethod;
import cipm.consistency.fitests.similarity.java.initialiser.annotations.IAnnotableInitialiser;

public interface ISuperTypeArgumentInitialiser extends IAnnotableInitialiser,
	ITypeArgumentInitialiser {
	@Override
	public SuperTypeArgument instantiate();
	@ModificationMethod
	public default boolean setSuperType(SuperTypeArgument sta, TypeReference tref) {
		if (tref != null) {
			sta.setSuperType(tref);
			return sta.getSuperType().equals(tref);
		}
		return true;
	}
}

package cipm.consistency.initialisers.eobject.java.generics;

import org.emftext.language.java.generics.SuperTypeArgument;
import org.emftext.language.java.types.TypeReference;

import cipm.consistency.initialisers.eobject.java.annotations.IAnnotableInitialiser;

public interface ISuperTypeArgumentInitialiser extends IAnnotableInitialiser, ITypeArgumentInitialiser {
	@Override
	public SuperTypeArgument instantiate();

	public default boolean setSuperType(SuperTypeArgument sta, TypeReference superType) {
		if (superType != null) {
			sta.setSuperType(superType);
			return sta.getSuperType().equals(superType);
		}
		return true;
	}
}

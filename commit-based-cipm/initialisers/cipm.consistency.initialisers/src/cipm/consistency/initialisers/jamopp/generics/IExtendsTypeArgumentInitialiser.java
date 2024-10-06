package cipm.consistency.initialisers.jamopp.generics;

import org.emftext.language.java.generics.ExtendsTypeArgument;
import org.emftext.language.java.types.TypeReference;

import cipm.consistency.initialisers.jamopp.annotations.IAnnotableInitialiser;

public interface IExtendsTypeArgumentInitialiser extends IAnnotableInitialiser, ITypeArgumentInitialiser {
	@Override
	public ExtendsTypeArgument instantiate();

	public default boolean setExtendType(ExtendsTypeArgument eta, TypeReference extType) {
		if (extType != null) {
			eta.setExtendType(extType);
			return eta.getExtendType().equals(extType) && eta.getExtendTypes().contains(extType);
		}
		return true;
	}
}
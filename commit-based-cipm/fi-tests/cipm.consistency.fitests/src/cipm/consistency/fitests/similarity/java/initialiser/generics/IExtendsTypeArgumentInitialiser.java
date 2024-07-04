package cipm.consistency.fitests.similarity.java.initialiser.generics;

import org.emftext.language.java.generics.ExtendsTypeArgument;
import org.emftext.language.java.types.TypeReference;

import cipm.consistency.fitests.similarity.java.initialiser.annotations.IAnnotableInitialiser;

public interface IExtendsTypeArgumentInitialiser extends IAnnotableInitialiser,
	ITypeArgumentInitialiser {
	@Override
	public ExtendsTypeArgument instantiate();
	
	public default boolean setExtendType(ExtendsTypeArgument eta, TypeReference tref) {
		if (tref != null) {
			eta.setExtendType(tref);
			return eta.getExtendType().equals(tref) &&
					eta.getExtendTypes().contains(tref);
		}
		return true;
	}
}

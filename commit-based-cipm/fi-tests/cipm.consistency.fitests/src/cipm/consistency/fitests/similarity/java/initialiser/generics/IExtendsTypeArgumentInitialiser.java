package cipm.consistency.fitests.similarity.java.initialiser.generics;

import org.emftext.language.java.generics.ExtendsTypeArgument;
import org.emftext.language.java.types.TypeReference;

import cipm.consistency.fitests.similarity.java.initialiser.IAnnotableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.ITypeArgumentInitialiser;

public interface IExtendsTypeArgumentInitialiser extends IAnnotableInitialiser,
	ITypeArgumentInitialiser {
	public default void setExtendType(ExtendsTypeArgument eta, TypeReference tref) {
		if (tref != null) {
			eta.setExtendType(tref);
			assert eta.getExtendType().equals(tref);
			assert eta.getExtendTypes().contains(tref);
		}
	}
}

package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.generics.ExtendsTypeArgument;
import org.emftext.language.java.types.TypeReference;

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

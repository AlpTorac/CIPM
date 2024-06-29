package cipm.consistency.fitests.similarity.java.initialiser.generics;

import org.emftext.language.java.generics.TypeParameter;
import org.emftext.language.java.types.TypeReference;

import cipm.consistency.fitests.similarity.java.initialiser.annotations.IAnnotableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.classifiers.IClassifierInitialiser;

public interface ITypeParameterInitialiser extends IClassifierInitialiser, IAnnotableInitialiser {
	public default boolean addExtendType(TypeParameter tp, TypeReference tref) {
		if (tref != null) {
			tp.getExtendTypes().add(tref);
			return tp.getExtendTypes().contains(tref);
		}
		return false;
	}
	
	public default boolean addExtendTypes(TypeParameter tp, TypeReference[] trefs) {
		return this.addXs(tp, trefs, this::addExtendType);
	}
}

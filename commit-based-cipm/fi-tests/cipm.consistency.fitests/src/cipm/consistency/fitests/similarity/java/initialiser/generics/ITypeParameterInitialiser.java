package cipm.consistency.fitests.similarity.java.initialiser.generics;

import org.emftext.language.java.generics.TypeParameter;
import org.emftext.language.java.types.TypeReference;

import cipm.consistency.fitests.similarity.java.initialiser.testable.IAnnotableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IClassifierInitialiser;

public interface ITypeParameterInitialiser extends IClassifierInitialiser, IAnnotableInitialiser {
	public default void addExtendType(TypeParameter tp, TypeReference tref) {
		if (tref != null) {
			tp.getExtendTypes().add(tref);
			assert tp.getExtendTypes().contains(tref);
		}
	}
	
	public default void addExtendTypes(TypeParameter tp, TypeReference[] trefs) {
		this.addXs(tp, trefs, this::addExtendType);
	}
}

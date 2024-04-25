package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.generics.TypeParameter;
import org.emftext.language.java.types.TypeReference;

public interface ITypeParameterInitialiser extends IClassifierInitialiser, IAnnotableInitialiser {
	@Override
	public TypeParameter instantiate();
	
	@Override
	public default TypeParameter minimalInstantiation() {
		return (TypeParameter) IClassifierInitialiser.super.minimalInstantiation();
	}
	
	public default void addExtendType(TypeParameter tp, TypeReference tref) {
		if (tref != null) {
			tp.getExtendTypes().add(tref);
			assert tp.getExtendTypes().contains(tref);
		}
	}
}

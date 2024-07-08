package cipm.consistency.fitests.similarity.java.initialiser.generics;

import org.emftext.language.java.generics.TypeParameter;

import org.emftext.language.java.generics.GenericsFactory;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class TypeParameterInitialiser extends AbstractInitialiserBase implements ITypeParameterInitialiser {
	@Override
	public TypeParameter instantiate() {
		return GenericsFactory.eINSTANCE.createTypeParameter();
	}

	@Override
	public ITypeParameterInitialiser newInitialiser() {
		return new TypeParameterInitialiser();
	}
}

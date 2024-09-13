package cipm.consistency.fitests.similarity.java.eobject.initialiser.generics;

import org.emftext.language.java.generics.TypeParameter;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

import org.emftext.language.java.generics.GenericsFactory;

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

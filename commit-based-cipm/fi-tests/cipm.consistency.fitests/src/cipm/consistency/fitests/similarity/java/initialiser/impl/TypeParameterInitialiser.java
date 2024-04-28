package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.generics.TypeParameter;
import org.emftext.language.java.generics.GenericsFactory;

import cipm.consistency.fitests.similarity.java.initialiser.ITypeParameterInitialiser;

public class TypeParameterInitialiser implements ITypeParameterInitialiser, IInitialiser<TypeParameter> {
	@Override
	public TypeParameter instantiate() {
		return GenericsFactory.eINSTANCE.createTypeParameter();
	}
}

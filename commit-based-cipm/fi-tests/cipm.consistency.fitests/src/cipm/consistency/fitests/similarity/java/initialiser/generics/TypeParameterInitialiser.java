package cipm.consistency.fitests.similarity.java.initialiser.generics;

import org.emftext.language.java.generics.TypeParameter;

import cipm.consistency.fitests.similarity.java.initialiser.ConcreteClassifierInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;

import org.emftext.language.java.generics.GenericsFactory;

public class TypeParameterInitialiser implements ITypeParameterInitialiser {
	@Override
	public TypeParameter instantiate() {
		return GenericsFactory.eINSTANCE.createTypeParameter();
	}

	@Override
	public ITypeParameterInitialiser newInitialiser() {
		return new TypeParameterInitialiser();
	}
}

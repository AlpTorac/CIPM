package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.arrays.ArrayInstantiationBySize;
import org.emftext.language.java.arrays.ArraysFactory;
import org.emftext.language.java.references.ElementReference;

import cipm.consistency.fitests.similarity.java.initialiser.IArrayInstantiationBySizeInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IElementReferenceInitialiser;

public class ArrayInstantiationBySizeInitialiser implements IArrayInstantiationBySizeInitialiser {
	@Override
	public IArrayInstantiationBySizeInitialiser newInitialiser() {
		return new ArrayInstantiationBySizeInitialiser();
	}

	@Override
	public ArrayInstantiationBySize instantiate() {
		return ArraysFactory.eINSTANCE.createArrayInstantiationBySize();
	}
}
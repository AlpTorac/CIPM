package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.containers.ContainersFactory;
import org.emftext.language.java.containers.EmptyModel;

import cipm.consistency.fitests.similarity.java.initialiser.IEmptyModelInitialiser;

public class EmptyModelInitialiser implements IEmptyModelInitialiser {
	@Override
	public IEmptyModelInitialiser newInitialiser() {
		return new EmptyModelInitialiser();
	}

	@Override
	public EmptyModel instantiate() {
		return ContainersFactory.eINSTANCE.createEmptyModel();
	}
}
package cipm.consistency.fitests.similarity.java.initialiser.containers;

import org.emftext.language.java.containers.ContainersFactory;
import org.emftext.language.java.containers.EmptyModel;

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
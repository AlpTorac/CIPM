package cipm.consistency.initialisers.emftext.containers;

import org.emftext.language.java.containers.EmptyModel;

public interface IEmptyModelInitialiser extends IJavaRootInitialiser {
	@Override
	public EmptyModel instantiate();

}

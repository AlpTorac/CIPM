package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.classifiers.ClassifiersFactory;
import org.emftext.language.java.classifiers.Interface;

public class InterfaceInitialiser implements IInterfaceInitialiser {
	@Override
	public void shouldSetDefaultName(boolean setDefaultName) {}

	@Override
	public boolean isSetDefaultName() {
		return false;
	}

	@Override
	public Interface instantiate() {
		var fac = ClassifiersFactory.eINSTANCE;
		return fac.createInterface();
	}
}

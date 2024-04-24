package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.classifiers.ClassifiersFactory;
import org.emftext.language.java.classifiers.Enumeration;

public class EnumerationInitialiser implements IEnumerationInitialiser {
	@Override
	public void shouldSetDefaultName(boolean setDefaultName) {}

	@Override
	public boolean isSetDefaultName() {
		return false;
	}

	@Override
	public Enumeration instantiate() {
		var fac = ClassifiersFactory.eINSTANCE;
		return fac.createEnumeration();
	}
}

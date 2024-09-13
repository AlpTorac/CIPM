package cipm.consistency.fitests.similarity.eobject.initialiser.java.classifiers;

import org.emftext.language.java.classifiers.ClassifiersFactory;
import org.emftext.language.java.classifiers.Enumeration;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class EnumerationInitialiser extends AbstractInitialiserBase implements IEnumerationInitialiser {
	@Override
	public Enumeration instantiate() {
		var fac = ClassifiersFactory.eINSTANCE;
		return fac.createEnumeration();
	}

	@Override
	public EnumerationInitialiser newInitialiser() {
		return new EnumerationInitialiser();
	}
}

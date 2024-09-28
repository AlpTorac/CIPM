package cipm.consistency.initialisers.eobject.java.classifiers;

import org.emftext.language.java.classifiers.AnonymousClass;

import cipm.consistency.initialisers.eobject.java.members.IMemberContainerInitialiser;
import cipm.consistency.initialisers.eobject.java.types.ITypeInitialiser;

public interface IAnonymousClassInitialiser extends IMemberContainerInitialiser, ITypeInitialiser {
	@Override
	public AnonymousClass instantiate();
}

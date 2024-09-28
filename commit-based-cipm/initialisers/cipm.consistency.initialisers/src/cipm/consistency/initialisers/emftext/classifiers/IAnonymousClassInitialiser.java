package cipm.consistency.initialisers.emftext.classifiers;

import org.emftext.language.java.classifiers.AnonymousClass;

import cipm.consistency.initialisers.emftext.members.IMemberContainerInitialiser;
import cipm.consistency.initialisers.emftext.types.ITypeInitialiser;

public interface IAnonymousClassInitialiser extends IMemberContainerInitialiser, ITypeInitialiser {
	@Override
	public AnonymousClass instantiate();
}

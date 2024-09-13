package cipm.consistency.fitests.similarity.eobject.initialiser.java.classifiers;

import org.emftext.language.java.classifiers.AnonymousClass;

import cipm.consistency.fitests.similarity.eobject.initialiser.java.members.IMemberContainerInitialiser;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.types.ITypeInitialiser;

public interface IAnonymousClassInitialiser extends IMemberContainerInitialiser, ITypeInitialiser {
	@Override
	public AnonymousClass instantiate();
}

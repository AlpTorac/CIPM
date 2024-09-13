package cipm.consistency.fitests.similarity.java.eobject.initialiser.classifiers;

import org.emftext.language.java.classifiers.AnonymousClass;

import cipm.consistency.fitests.similarity.java.eobject.initialiser.members.IMemberContainerInitialiser;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.types.ITypeInitialiser;

public interface IAnonymousClassInitialiser extends IMemberContainerInitialiser, ITypeInitialiser {
	@Override
	public AnonymousClass instantiate();
}

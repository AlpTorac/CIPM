package cipm.consistency.fitests.similarity.java.initialiser.classifiers;

import org.emftext.language.java.classifiers.AnonymousClass;

import cipm.consistency.fitests.similarity.java.initialiser.members.IMemberContainerInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.types.ITypeInitialiser;

public interface IAnonymousClassInitialiser extends	IMemberContainerInitialiser,
	ITypeInitialiser {
	@Override
	public AnonymousClass instantiate();
}

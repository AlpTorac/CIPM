package cipm.consistency.fitests.similarity.java.initialiser.instantiations;

import org.emftext.language.java.classifiers.AnonymousClass;
import org.emftext.language.java.instantiations.NewConstructorCall;

import cipm.consistency.fitests.similarity.java.initialiser.ModificationMethod;
import cipm.consistency.fitests.similarity.java.initialiser.types.ITypedElementInitialiser;

public interface INewConstructorCallInitialiser extends
	IInstantiationInitialiser,
	ITypedElementInitialiser {

	@Override
	public NewConstructorCall instantiate();
	@ModificationMethod
	public default boolean setAnonymousClass(NewConstructorCall ncc, AnonymousClass ac) {
		if (ac != null) {
			ncc.setAnonymousClass(ac);
			return ncc.getAnonymousClass().equals(ac);
		}
		return true;
	}
}

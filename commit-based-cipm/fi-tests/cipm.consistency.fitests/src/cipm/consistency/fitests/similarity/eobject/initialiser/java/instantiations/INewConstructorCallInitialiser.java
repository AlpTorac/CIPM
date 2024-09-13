package cipm.consistency.fitests.similarity.eobject.initialiser.java.instantiations;

import org.emftext.language.java.classifiers.AnonymousClass;
import org.emftext.language.java.instantiations.NewConstructorCall;

import cipm.consistency.fitests.similarity.eobject.initialiser.java.types.ITypedElementInitialiser;

public interface INewConstructorCallInitialiser extends IInstantiationInitialiser, ITypedElementInitialiser {

	@Override
	public NewConstructorCall instantiate();

	public default boolean setAnonymousClass(NewConstructorCall ncc, AnonymousClass anonymousCls) {
		if (anonymousCls != null) {
			ncc.setAnonymousClass(anonymousCls);
			return ncc.getAnonymousClass().equals(anonymousCls);
		}
		return true;
	}
}

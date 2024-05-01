package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.classifiers.AnonymousClass;
import org.emftext.language.java.instantiations.NewConstructorCall;

public interface INewConstructorCallInitialiser extends
	IInstantiationInitialiser,
	ITypedElementInitialiser {

	public default void setAnonymousClass(NewConstructorCall ncc, AnonymousClass ac) {
		if (ac != null) {
			ncc.setAnonymousClass(ac);
			assert ncc.getAnonymousClass().equals(ac);
		}
	}
}

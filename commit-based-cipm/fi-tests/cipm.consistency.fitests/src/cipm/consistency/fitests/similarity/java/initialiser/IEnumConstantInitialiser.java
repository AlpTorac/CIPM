package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.classifiers.AnonymousClass;
import org.emftext.language.java.members.EnumConstant;

public interface IEnumConstantInitialiser extends IArgumentableInitialiser, IAnnotableInitialiser, IReferenceableElementInitialiser {
	public default void setAnonymousClass(EnumConstant ec, AnonymousClass cls) {
		if (cls != null) {
			ec.setAnonymousClass(cls);
			assert ec.getAnonymousClass().equals(cls);
		}
	}
}

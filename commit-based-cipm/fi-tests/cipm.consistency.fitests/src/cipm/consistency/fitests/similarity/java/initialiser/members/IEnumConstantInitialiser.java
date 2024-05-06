package cipm.consistency.fitests.similarity.java.initialiser.members;

import org.emftext.language.java.classifiers.AnonymousClass;
import org.emftext.language.java.members.EnumConstant;

import cipm.consistency.fitests.similarity.java.initialiser.IReferenceableElementInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IAnnotableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IArgumentableInitialiser;

public interface IEnumConstantInitialiser extends IArgumentableInitialiser, IAnnotableInitialiser, IReferenceableElementInitialiser {
	public default void setAnonymousClass(EnumConstant ec, AnonymousClass cls) {
		if (cls != null) {
			ec.setAnonymousClass(cls);
			assert ec.getAnonymousClass().equals(cls);
		}
	}
}

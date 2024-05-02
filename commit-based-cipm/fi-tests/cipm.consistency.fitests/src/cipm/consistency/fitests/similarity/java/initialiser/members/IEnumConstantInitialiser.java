package cipm.consistency.fitests.similarity.java.initialiser.members;

import org.emftext.language.java.classifiers.AnonymousClass;
import org.emftext.language.java.members.Constructor;
import org.emftext.language.java.members.EnumConstant;

import cipm.consistency.fitests.similarity.java.initialiser.IAnnotableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IArgumentableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IReferenceableElementInitialiser;

public interface IEnumConstantInitialiser extends IArgumentableInitialiser, IAnnotableInitialiser, IReferenceableElementInitialiser {
	public default void setAnonymousClass(EnumConstant ec, AnonymousClass cls) {
		if (cls != null) {
			ec.setAnonymousClass(cls);
			assert ec.getAnonymousClass().equals(cls);
		}
	}
}

package cipm.consistency.fitests.similarity.java.initialiser.members;

import org.emftext.language.java.classifiers.AnonymousClass;
import org.emftext.language.java.members.EnumConstant;

import cipm.consistency.fitests.similarity.java.initialiser.ModificationMethod;
import cipm.consistency.fitests.similarity.java.initialiser.annotations.IAnnotableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.references.IArgumentableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.references.IReferenceableElementInitialiser;

public interface IEnumConstantInitialiser extends IArgumentableInitialiser, IAnnotableInitialiser, IReferenceableElementInitialiser {
    @Override
    public EnumConstant instantiate();
    @ModificationMethod
	public default boolean setAnonymousClass(EnumConstant ec, AnonymousClass cls) {
		if (cls != null) {
			ec.setAnonymousClass(cls);
			return ec.getAnonymousClass().equals(cls);
		}
		return true;
	}
}

package cipm.consistency.initialisers.emftext.members;

import org.emftext.language.java.classifiers.AnonymousClass;
import org.emftext.language.java.members.EnumConstant;

import cipm.consistency.initialisers.emftext.annotations.IAnnotableInitialiser;
import cipm.consistency.initialisers.emftext.references.IArgumentableInitialiser;
import cipm.consistency.initialisers.emftext.references.IReferenceableElementInitialiser;

public interface IEnumConstantInitialiser
		extends IArgumentableInitialiser, IAnnotableInitialiser, IReferenceableElementInitialiser {
	@Override
	public EnumConstant instantiate();

	public default boolean setAnonymousClass(EnumConstant ec, AnonymousClass anonymousCls) {
		if (anonymousCls != null) {
			ec.setAnonymousClass(anonymousCls);
			return ec.getAnonymousClass().equals(anonymousCls);
		}
		return true;
	}
}

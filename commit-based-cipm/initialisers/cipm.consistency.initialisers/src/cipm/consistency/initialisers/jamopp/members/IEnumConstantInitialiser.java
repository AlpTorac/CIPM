package cipm.consistency.initialisers.jamopp.members;

import org.emftext.language.java.classifiers.AnonymousClass;
import org.emftext.language.java.members.EnumConstant;

import cipm.consistency.initialisers.jamopp.annotations.IAnnotableInitialiser;
import cipm.consistency.initialisers.jamopp.references.IArgumentableInitialiser;
import cipm.consistency.initialisers.jamopp.references.IReferenceableElementInitialiser;

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

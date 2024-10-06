package cipm.consistency.initialisers.jamopp.types;

import org.emftext.language.java.types.TypeReference;
import org.emftext.language.java.types.TypedElementExtension;

import cipm.consistency.initialisers.jamopp.commons.ICommentableInitialiser;

public interface ITypedElementExtensionInitialiser extends ICommentableInitialiser {
	@Override
	public TypedElementExtension instantiate();

	public default boolean addActualTarget(TypedElementExtension tee, TypeReference actualTarget) {
		if (actualTarget != null) {
			tee.getActualTargets().add(actualTarget);
			return tee.getActualTargets().contains(actualTarget);
		}
		return true;
	}
}
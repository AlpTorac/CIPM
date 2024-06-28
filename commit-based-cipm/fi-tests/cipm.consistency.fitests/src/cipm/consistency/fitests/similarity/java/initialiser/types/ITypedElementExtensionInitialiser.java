package cipm.consistency.fitests.similarity.java.initialiser.types;

import org.emftext.language.java.types.TypeReference;
import org.emftext.language.java.types.TypedElementExtension;

import cipm.consistency.fitests.similarity.java.initialiser.commons.ICommentableInitialiser;

public interface ITypedElementExtensionInitialiser extends ICommentableInitialiser {
	public default void addActualTarget(TypedElementExtension tee, TypeReference tref) {
		if (tref != null) {
			tee.getActualTargets().add(tref);
			assert tee.getActualTargets().contains(tref);
		}
	}
}

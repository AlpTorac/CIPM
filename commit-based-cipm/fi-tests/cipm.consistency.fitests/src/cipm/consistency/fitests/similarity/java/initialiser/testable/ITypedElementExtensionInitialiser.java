package cipm.consistency.fitests.similarity.java.initialiser.testable;

import org.emftext.language.java.types.TypeReference;
import org.emftext.language.java.types.TypedElementExtension;

import cipm.consistency.fitests.similarity.java.initialiser.ICommentableInitialiser;

public interface ITypedElementExtensionInitialiser extends ICommentableInitialiser {
	public default void addActualTarget(TypedElementExtension tee, TypeReference tref) {
		if (tref != null) {
			tee.getActualTargets().add(tref);
			assert tee.getActualTargets().contains(tref);
		}
	}
}

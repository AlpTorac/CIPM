package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.types.TypeReference;
import org.emftext.language.java.types.TypedElementExtension;

public interface ITypedElementExtensionInitialiser extends ICommentableInitialiser {
	public default void addActualTarget(TypedElementExtension tee, TypeReference tref) {
		if (tref != null) {
			tee.getActualTargets().add(tref);
			assert tee.getActualTargets().contains(tref);
		}
	}
}

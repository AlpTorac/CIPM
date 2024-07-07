package cipm.consistency.fitests.similarity.java.initialiser.types;

import org.emftext.language.java.types.TypeReference;
import org.emftext.language.java.types.TypedElementExtension;

import cipm.consistency.fitests.similarity.java.initialiser.ModificationMethod;
import cipm.consistency.fitests.similarity.java.initialiser.commons.ICommentableInitialiser;

import org.emftext.language.java.types.TypedElementExtension;

public interface ITypedElementExtensionInitialiser extends ICommentableInitialiser {
    @Override
    public TypedElementExtension instantiate();
    @ModificationMethod
	public default boolean addActualTarget(TypedElementExtension tee, TypeReference tref) {
		if (tref != null) {
			tee.getActualTargets().add(tref);
			return tee.getActualTargets().contains(tref);
		}
		return true;
	}
}

package cipm.consistency.fitests.similarity.java.initialiser.types;

import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.types.ClassifierReference;
import org.emftext.language.java.types.NamespaceClassifierReference;
import org.emftext.language.java.types.TypeReference;

import cipm.consistency.fitests.similarity.java.initialiser.commons.INamespaceAwareElementInitialiser;

public interface INamespaceClassifierReferenceInitialiser
		extends INamespaceAwareElementInitialiser, ITypeReferenceInitialiser {
	@Override
	public NamespaceClassifierReference instantiate();

	@Override
	public default boolean setTarget(TypeReference tref, Classifier target) {
		boolean result = ITypeReferenceInitialiser.super.setTarget(tref, target);

		if (target != null) {
			var castedTref = (NamespaceClassifierReference) tref;

			/*
			 * Assertions for the implementation at
			 * https://github.com/DevBoost/JaMoPP/blob/master/Core/org.emftext.language.java
			 * /src/org/emftext/language/java/extensions/types/TypeReferenceExtension.java
			 */
			return result && castedTref.getNamespaces().size() == target.getContainingContainerName().size()
					&& castedTref.getNamespaces().containsAll(target.getContainingContainerName())
					&& castedTref.getClassifierReferences().size() == 1
					&& castedTref.getClassifierReferences().stream().anyMatch((cr) -> cr.getTarget().equals(target));
		}
		return true;
	}

	public default boolean addClassifierReference(NamespaceClassifierReference ncr, ClassifierReference clsRef) {
		if (clsRef != null) {
			ncr.getClassifierReferences().add(clsRef);
			return ncr.getClassifierReferences().contains(clsRef);
		}
		return true;
	}

	public default boolean addClassifierReferences(NamespaceClassifierReference ncr, ClassifierReference[] clsRefs) {
		return this.doMultipleModifications(ncr, clsRefs, this::addClassifierReference);
	}
}

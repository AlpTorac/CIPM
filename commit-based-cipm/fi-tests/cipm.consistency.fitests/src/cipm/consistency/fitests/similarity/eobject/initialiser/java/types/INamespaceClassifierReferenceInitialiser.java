package cipm.consistency.fitests.similarity.eobject.initialiser.java.types;

import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.types.ClassifierReference;
import org.emftext.language.java.types.NamespaceClassifierReference;
import org.emftext.language.java.types.TypeReference;

import cipm.consistency.fitests.similarity.eobject.initialiser.java.commons.INamespaceAwareElementInitialiser;

public interface INamespaceClassifierReferenceInitialiser
		extends INamespaceAwareElementInitialiser, ITypeReferenceInitialiser {
	@Override
	public NamespaceClassifierReference instantiate();

	@Override
	public default boolean setTargetAssertion(TypeReference tref, Classifier target) {
		var castedTref = (NamespaceClassifierReference) tref;
		var containerName = target.getContainingContainerName();
		var nss = castedTref.getNamespaces();
		var clsRefs = castedTref.getClassifierReferences();

		/*
		 * Assertions for the implementation at
		 * https://github.com/DevBoost/JaMoPP/blob/master/Core/org.emftext.language.java
		 * /src/org/emftext/language/java/extensions/types/TypeReferenceExtension.java
		 */
		return ITypeReferenceInitialiser.super.setTargetAssertion(tref, target) && nss != null && containerName != null
				&& nss.size() == containerName.size() && nss.containsAll(containerName) && clsRefs != null
				&& clsRefs.size() == 1 && clsRefs.stream().anyMatch((cr) -> cr.getTarget().equals(target));
	}

	public default boolean addClassifierReference(NamespaceClassifierReference ncr, ClassifierReference clsRef) {
		if (clsRef != null) {
			ncr.getClassifierReferences().add(clsRef);
			return ncr.getClassifierReferences().contains(clsRef);
		}
		return true;
	}

	@Override
	public default boolean canSetTarget(TypeReference tref) {
		return true;
	}

	@Override
	default boolean canSetTargetTo(TypeReference tref, Classifier target) {
		return ITypeReferenceInitialiser.super.canSetTargetTo(tref, target)
				&& target.getContainingContainerName() != null;
	}

	public default boolean addClassifierReferences(NamespaceClassifierReference ncr, ClassifierReference[] clsRefs) {
		return this.doMultipleModifications(ncr, clsRefs, this::addClassifierReference);
	}
}

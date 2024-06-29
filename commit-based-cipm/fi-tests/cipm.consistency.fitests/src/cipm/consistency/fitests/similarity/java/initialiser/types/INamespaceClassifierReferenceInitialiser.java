package cipm.consistency.fitests.similarity.java.initialiser.types;

import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.types.ClassifierReference;
import org.emftext.language.java.types.NamespaceClassifierReference;
import org.emftext.language.java.types.TypeReference;

import cipm.consistency.fitests.similarity.java.initialiser.commons.INamespaceAwareElementInitialiser;

public interface INamespaceClassifierReferenceInitialiser extends
	INamespaceAwareElementInitialiser,
	ITypeReferenceInitialiser {

	@Override
	public default boolean setTarget(TypeReference tref, Classifier cls) {
		boolean result = ITypeReferenceInitialiser.super.setTarget(tref, cls);
		
		if (cls != null) {
			var castedTref = (NamespaceClassifierReference) tref;
			
			/*
			 * Assertions for the implementation at
			 * https://github.com/DevBoost/JaMoPP/blob/master/Core/org.emftext.language.java/src/org/emftext/language/java/extensions/types/TypeReferenceExtension.java
			 */
			return result &&
				castedTref.getNamespaces().size() == cls.getContainingContainerName().size() &&
				 castedTref.getNamespaces().containsAll(cls.getContainingContainerName()) &&
				 castedTref.getClassifierReferences().size() == 1 &&
				 castedTref.getClassifierReferences().stream()
				.anyMatch((cr) -> cr.getTarget().equals(cls));
		}
		return true;
	}
	
	public default boolean addClassifierReference(NamespaceClassifierReference ncr, ClassifierReference cref) {
		if (cref != null) {
			ncr.getClassifierReferences().add(cref);
			return ncr.getClassifierReferences().contains(cref);
		}
		return true;
	}
	
	public default boolean addClassifierReferences(NamespaceClassifierReference ncr, ClassifierReference[] crefs) {
		return this.addXs(ncr, crefs, this::addClassifierReference);
	}
}

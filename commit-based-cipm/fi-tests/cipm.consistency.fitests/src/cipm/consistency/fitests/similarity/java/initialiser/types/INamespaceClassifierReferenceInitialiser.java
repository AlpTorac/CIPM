package cipm.consistency.fitests.similarity.java.initialiser.types;

import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.types.ClassifierReference;
import org.emftext.language.java.types.NamespaceClassifierReference;
import org.emftext.language.java.types.TypeReference;

import cipm.consistency.fitests.similarity.java.initialiser.testable.INamespaceAwareElementInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.ITypeReferenceInitialiser;

public interface INamespaceClassifierReferenceInitialiser extends
	INamespaceAwareElementInitialiser,
	ITypeReferenceInitialiser {

	@Override
	public default void setTarget(TypeReference tref, Classifier cls) {
		ITypeReferenceInitialiser.super.setTarget(tref, cls);
		
		if (cls != null) {
			var castedTref = (NamespaceClassifierReference) tref;
			
			/*
			 * Assertions for the implementation at
			 * https://github.com/DevBoost/JaMoPP/blob/master/Core/org.emftext.language.java/src/org/emftext/language/java/extensions/types/TypeReferenceExtension.java
			 */
			assert castedTref.getNamespaces().size() == cls.getContainingContainerName().size();
			assert castedTref.getNamespaces().containsAll(cls.getContainingContainerName());
			assert castedTref.getClassifierReferences().size() == 1;
			assert castedTref.getClassifierReferences().stream()
				.anyMatch((cr) -> cr.getTarget().equals(cls));
		}
	}
	
	public default void addClassifierReference(NamespaceClassifierReference ncr, ClassifierReference cref) {
		if (cref != null) {
			ncr.getClassifierReferences().add(cref);
			assert ncr.getClassifierReferences().contains(cref);
		}
	}
	
	public default void addClassifierReferences(NamespaceClassifierReference ncr, ClassifierReference[] crefs) {
		this.addXs(ncr, crefs, this::addClassifierReference);
	}
}

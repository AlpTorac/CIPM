package cipm.consistency.fitests.similarity.java.initialiser.types;

import org.emftext.language.java.types.ClassifierReference;
import org.emftext.language.java.types.NamespaceClassifierReference;

import cipm.consistency.fitests.similarity.java.initialiser.testable.INamespaceAwareElementInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.ITypeReferenceInitialiser;

public interface INamespaceClassifierReferenceInitialiser extends
	INamespaceAwareElementInitialiser,
	ITypeReferenceInitialiser {

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

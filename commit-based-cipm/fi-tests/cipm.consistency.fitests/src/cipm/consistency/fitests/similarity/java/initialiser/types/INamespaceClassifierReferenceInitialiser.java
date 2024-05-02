package cipm.consistency.fitests.similarity.java.initialiser.types;

import org.emftext.language.java.types.ClassifierReference;
import org.emftext.language.java.types.NamespaceClassifierReference;

import cipm.consistency.fitests.similarity.java.initialiser.INamespaceAwareElementInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.ITypeReferenceInitialiser;

public interface INamespaceClassifierReferenceInitialiser extends
	INamespaceAwareElementInitialiser,
	ITypeReferenceInitialiser {

	public default void addClassifierReference(NamespaceClassifierReference ncr, ClassifierReference cref) {
		if (cref != null) {
			ncr.getClassifierReferences().add(cref);
			assert ncr.getClassifierReferences().contains(cref);
		}
	}
}

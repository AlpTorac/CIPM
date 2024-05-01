package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.types.ClassifierReference;
import org.emftext.language.java.types.NamespaceClassifierReference;

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

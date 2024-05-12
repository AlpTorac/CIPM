package cipm.consistency.fitests.similarity.java.initialiser.testable;

import org.emftext.language.java.arrays.ArraySelector;
import org.emftext.language.java.references.Reference;

import cipm.consistency.fitests.similarity.java.initialiser.IPrimaryExpressionInitialiser;

public interface IReferenceInitialiser extends IPrimaryExpressionInitialiser,
	ITypeArgumentableInitialiser,
	ITypedElementExtensionInitialiser {

	public default void addArraySelector(Reference ref, ArraySelector as) {
		if (as != null) {
			ref.getArraySelectors().add(as);
			assert ref.getArraySelectors().contains(as);
		}
	}
	
	public default void setNext(Reference ref, Reference next) {
		if (next != null) {
			ref.setNext(next);
			assert ref.getNext().equals(next);
			assert ref.getNext().getPrevious().equals(ref);
			assert next.getPrevious().equals(ref);
			assert next.getPrevious().getNext().equals(next);
		}
	}
}

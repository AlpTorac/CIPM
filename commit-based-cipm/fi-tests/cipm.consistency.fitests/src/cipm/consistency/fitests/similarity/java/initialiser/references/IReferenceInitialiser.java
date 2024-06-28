package cipm.consistency.fitests.similarity.java.initialiser.references;

import org.emftext.language.java.arrays.ArraySelector;
import org.emftext.language.java.references.Reference;

import cipm.consistency.fitests.similarity.java.initialiser.expressions.IPrimaryExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.generics.ITypeArgumentableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.types.ITypedElementExtensionInitialiser;

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

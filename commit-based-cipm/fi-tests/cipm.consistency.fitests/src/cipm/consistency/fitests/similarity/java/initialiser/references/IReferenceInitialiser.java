package cipm.consistency.fitests.similarity.java.initialiser.references;

import org.emftext.language.java.arrays.ArraySelector;
import org.emftext.language.java.references.Reference;

import cipm.consistency.fitests.similarity.java.initialiser.expressions.IPrimaryExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.generics.ITypeArgumentableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.types.ITypedElementExtensionInitialiser;

public interface IReferenceInitialiser extends IPrimaryExpressionInitialiser,
	ITypeArgumentableInitialiser,
	ITypedElementExtensionInitialiser {

	@Override
	public Reference instantiate();
	public default boolean addArraySelector(Reference ref, ArraySelector as) {
		if (as != null) {
			ref.getArraySelectors().add(as);
			return ref.getArraySelectors().contains(as);
		}
		return true;
	}
	
	public default boolean addArraySelectors(Reference ref, ArraySelector[] asArr) {
		return this.addXs(ref, asArr, this::addArraySelector);
	}
	public default boolean setNext(Reference ref, Reference next) {
		if (next != null) {
			ref.setNext(next);
			return ref.getNext().equals(next) &&
				ref.getNext().getPrevious().equals(ref) &&
				next.getPrevious().equals(ref) &&
				next.getPrevious().getNext().equals(next);
		}
		return true;
	}
}

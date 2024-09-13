package cipm.consistency.fitests.similarity.eobject.initialiser.java.references;

import org.emftext.language.java.arrays.ArraySelector;
import org.emftext.language.java.references.Reference;

import cipm.consistency.fitests.similarity.eobject.initialiser.java.expressions.IPrimaryExpressionInitialiser;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.generics.ITypeArgumentableInitialiser;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.types.ITypedElementExtensionInitialiser;

public interface IReferenceInitialiser
		extends IPrimaryExpressionInitialiser, ITypeArgumentableInitialiser, ITypedElementExtensionInitialiser {

	@Override
	public Reference instantiate();

	public default boolean addArraySelector(Reference ref, ArraySelector arrSel) {
		if (arrSel != null) {
			ref.getArraySelectors().add(arrSel);
			return ref.getArraySelectors().contains(arrSel);
		}
		return true;
	}

	public default boolean addArraySelectors(Reference ref, ArraySelector[] arrSels) {
		return this.doMultipleModifications(ref, arrSels, this::addArraySelector);
	}

	public default boolean setNext(Reference ref, Reference next) {
		if (next != null) {
			ref.setNext(next);
			return ref.getNext().equals(next) && ref.getNext().getPrevious().equals(ref)
					&& next.getPrevious().equals(ref) && next.getPrevious().getNext().equals(next);
		}
		return true;
	}
}

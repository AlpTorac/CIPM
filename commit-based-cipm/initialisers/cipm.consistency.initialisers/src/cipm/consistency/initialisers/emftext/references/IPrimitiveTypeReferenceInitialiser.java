package cipm.consistency.initialisers.emftext.references;

import org.emftext.language.java.references.PrimitiveTypeReference;
import org.emftext.language.java.types.PrimitiveType;

import cipm.consistency.initialisers.emftext.arrays.IArrayTypeableInitialiser;

public interface IPrimitiveTypeReferenceInitialiser extends IArrayTypeableInitialiser, IReferenceInitialiser {
	@Override
	public PrimitiveTypeReference instantiate();

	public default boolean setPrimitiveType(PrimitiveTypeReference ptr, PrimitiveType pt) {
		if (pt != null) {
			ptr.setPrimitiveType(pt);
			return ptr.getPrimitiveType().equals(pt);
		}
		return true;
	}
}
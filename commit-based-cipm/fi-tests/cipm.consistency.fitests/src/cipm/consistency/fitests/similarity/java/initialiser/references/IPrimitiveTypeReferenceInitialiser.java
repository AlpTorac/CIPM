package cipm.consistency.fitests.similarity.java.initialiser.references;

import org.emftext.language.java.references.PrimitiveTypeReference;
import org.emftext.language.java.types.PrimitiveType;

import cipm.consistency.fitests.similarity.java.initialiser.ModificationMethod;
import cipm.consistency.fitests.similarity.java.initialiser.arrays.IArrayTypeableInitialiser;

public interface IPrimitiveTypeReferenceInitialiser extends IArrayTypeableInitialiser,
	IReferenceInitialiser {
	@Override
	public PrimitiveTypeReference instantiate();
	@ModificationMethod
	public default boolean setPrimitiveType(PrimitiveTypeReference ptr, PrimitiveType pt) {
		if (pt != null) {
			ptr.setPrimitiveType(pt);
			return ptr.getPrimitiveType().equals(pt);
		}
		return true;
	}
}

package cipm.consistency.fitests.similarity.java.initialiser.references;

import org.emftext.language.java.references.PrimitiveTypeReference;
import org.emftext.language.java.types.PrimitiveType;

import cipm.consistency.fitests.similarity.java.initialiser.IArrayTypeableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IReferenceInitialiser;

public interface IPrimitiveTypeReferenceInitialiser extends IArrayTypeableInitialiser,
	IReferenceInitialiser {
	public default void setPrimitiveType(PrimitiveTypeReference ptr, PrimitiveType pt) {
		if (pt != null) {
			ptr.setPrimitiveType(pt);
			assert ptr.getPrimitiveType().equals(pt);
		}
	}
}

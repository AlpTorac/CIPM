package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.references.PrimitiveTypeReference;
import org.emftext.language.java.types.PrimitiveType;

public interface IPrimitiveTypeReferenceInitialiser extends IArrayTypeableInitialiser,
	IReferenceInitialiser {
	public default void setPrimitiveType(PrimitiveTypeReference ptr, PrimitiveType pt) {
		if (pt != null) {
			ptr.setPrimitiveType(pt);
			assert ptr.getPrimitiveType().equals(pt);
		}
	}
}

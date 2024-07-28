package cipm.consistency.fitests.similarity.java.unittests;

import org.emftext.language.java.annotations.AnnotationValue;

public interface UsesAnnotationValues extends UsesReferences, UsesLiterals {
	public default AnnotationValue createNullVal() {
		return this.createNullLiteral();
	}
	
	public default AnnotationValue createStringRefVal(String val) {
		return this.createMinimalSR(val);
	}
}

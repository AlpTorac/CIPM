package cipm.consistency.fitests.similarity.java.unittests;

import org.emftext.language.java.annotations.AnnotationValue;

import cipm.consistency.fitests.similarity.java.initialiser.params.LiteralFactory;

public interface UsesAnnotationValues extends UsesReferences {
	public default AnnotationValue createNullVal() {
		return new LiteralFactory().createNullLiteral();
	}
	
	public default AnnotationValue createStringRefVal(String val) {
		return this.createMinimalSR(val);
	}
}

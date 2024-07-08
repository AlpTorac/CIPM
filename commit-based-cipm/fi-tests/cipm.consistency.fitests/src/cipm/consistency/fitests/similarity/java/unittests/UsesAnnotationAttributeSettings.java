package cipm.consistency.fitests.similarity.java.unittests;

import org.emftext.language.java.annotations.AnnotationAttributeSetting;
import org.emftext.language.java.annotations.AnnotationValue;
import org.emftext.language.java.members.InterfaceMethod;

import cipm.consistency.fitests.similarity.java.initialiser.annotations.AnnotationAttributeSettingInitialiser;

public interface UsesAnnotationAttributeSettings extends UsesAnnotationValues {
	public default AnnotationAttributeSetting createAAS(InterfaceMethod im, AnnotationValue val) {
		var aasInit = new AnnotationAttributeSettingInitialiser();
		var aas = aasInit.instantiate();
		aasInit.initialise(aas);
		aasInit.setAttribute(aas, im);
		aasInit.setValue(aas, val);
		return aas;
	}
	
	public default AnnotationAttributeSetting createEmptyAAS() {
		return this.createAAS(null, null);
	}
	
	public default AnnotationAttributeSetting createNullAAS() {
		return this.createAAS(null, this.createNullVal());
	}
	
	public default AnnotationAttributeSetting createStringAAS(String val) {
		return this.createAAS(null, this.createStringRefVal(val));
	}
}

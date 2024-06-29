package cipm.consistency.fitests.similarity.java.initialiser.annotations;

import org.emftext.language.java.annotations.AnnotationAttributeSetting;
import org.emftext.language.java.annotations.AnnotationValue;
import org.emftext.language.java.members.InterfaceMethod;

import cipm.consistency.fitests.similarity.java.initialiser.commons.ICommentableInitialiser;

public interface IAnnotationAttributeSettingInitialiser extends ICommentableInitialiser {
	public default boolean setAttribute(AnnotationAttributeSetting aas, InterfaceMethod im) {
		if (im != null) {
			aas.setAttribute(im);
			return aas.getAttribute().equals(im);
		}
		return true;
	}
	
	public default boolean setValue(AnnotationAttributeSetting aas, AnnotationValue val) {
		if (val != null) {
			aas.setValue(val);
			return aas.getValue().equals(val);
		}
		return true;
	}
}

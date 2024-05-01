package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.annotations.AnnotationAttributeSetting;
import org.emftext.language.java.annotations.AnnotationValue;
import org.emftext.language.java.members.InterfaceMethod;

public interface IAnnotationAttributeSettingInitialiser extends ICommentableInitialiser {
	public default void setAttribute(AnnotationAttributeSetting aas, InterfaceMethod im) {
		if (im != null) {
			aas.setAttribute(im);
			assert aas.getAttribute().equals(im);
		}
	}
	
	public default void setValue(AnnotationAttributeSetting aas, AnnotationValue val) {
		if (val != null) {
			aas.setValue(val);
			assert aas.getValue().equals(val);
		}
	}
}

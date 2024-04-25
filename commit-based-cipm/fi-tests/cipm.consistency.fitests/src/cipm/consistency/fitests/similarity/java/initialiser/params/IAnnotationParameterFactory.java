package cipm.consistency.fitests.similarity.java.initialiser.params;

import java.util.Collection;

import org.emftext.language.java.annotations.AnnotationAttributeSetting;
import org.emftext.language.java.annotations.AnnotationParameterList;
import org.emftext.language.java.annotations.AnnotationValue;
import org.emftext.language.java.annotations.SingleAnnotationParameter;
import org.emftext.language.java.members.InterfaceMethod;

public interface IAnnotationParameterFactory {
	public AnnotationParameterList createParamList();
	
	public default AnnotationParameterList createParamList(Collection<AnnotationAttributeSetting> settings) {
		var result = this.createParamList();
		
		if (settings != null) {
			result.getSettings().addAll(settings);
			assert result.getSettings().containsAll(settings);
		}
		
		return result;
	}
	
	public SingleAnnotationParameter createParam();
	
	public default SingleAnnotationParameter createParam(AnnotationValue val) {
		var result = this.createParam();
		
		if (val != null) {
			result.setValue(val);
			assert result.getValue().equals(val);
		}
		
		return result;
	}
	
	public AnnotationAttributeSetting createSetting();
	
	public default AnnotationAttributeSetting createSetting(InterfaceMethod im, AnnotationValue val) {
		var result = this.createSetting();
		
		if (im != null) {
			result.setAttribute(im);
			assert result.getAttribute().equals(im);
		}
		
		if (val != null) {
			result.setValue(val);
			assert result.getValue().equals(val);
		}
		
		return result;
	}
}

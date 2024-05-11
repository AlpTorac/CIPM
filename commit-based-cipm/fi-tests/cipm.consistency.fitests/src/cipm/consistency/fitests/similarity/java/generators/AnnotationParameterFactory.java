package cipm.consistency.fitests.similarity.java.generators;

import org.emftext.language.java.annotations.AnnotationsFactory;
import org.emftext.language.java.annotations.SingleAnnotationParameter;
import org.emftext.language.java.annotations.AnnotationAttributeSetting;
import org.emftext.language.java.annotations.AnnotationParameterList;

public class AnnotationParameterFactory implements IAnnotationParameterFactory {
	protected AnnotationsFactory getFactory() {
		return AnnotationsFactory.eINSTANCE;
	}
	
	@Override
	public AnnotationParameterList createParamList() {
		return this.getFactory().createAnnotationParameterList();
	}
	
	@Override
	public SingleAnnotationParameter createParam() {
		return this.getFactory().createSingleAnnotationParameter();
	}

	@Override
	public AnnotationAttributeSetting createSetting() {
		return this.getFactory().createAnnotationAttributeSetting();
	}
}

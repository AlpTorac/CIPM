package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.annotations.AnnotationsFactory;
import org.emftext.language.java.annotations.AnnotationAttributeSetting;
import org.emftext.language.java.annotations.AnnotationValue;
import org.emftext.language.java.expressions.ExpressionsFactory;

import cipm.consistency.fitests.similarity.java.initialiser.IAnnotationInstanceInitialiser;

import org.emftext.language.java.annotations.AnnotationInstance;
import org.emftext.language.java.annotations.AnnotationParameter;

public class AnnotationInstanceInitialiser implements IAnnotationInstanceInitialiser, IInitialiser {
	@Override
	public AnnotationInstance instantiate() {
		var fac = AnnotationsFactory.eINSTANCE;
//		fac.createAnnotationAttributeSetting()
//		fac.createAnnotationParameterList()
//		fac.createSingleAnnotationParameter()
		
		return fac.createAnnotationInstance();
	}
}

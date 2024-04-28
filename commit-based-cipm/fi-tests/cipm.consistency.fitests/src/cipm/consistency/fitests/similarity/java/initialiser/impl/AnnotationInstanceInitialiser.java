package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.annotations.AnnotationsFactory;

import cipm.consistency.fitests.similarity.java.initialiser.IAnnotationInstanceInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;

import org.emftext.language.java.annotations.AnnotationInstance;

public class AnnotationInstanceInitialiser implements IAnnotationInstanceInitialiser {
	@Override
	public AnnotationInstance instantiate() {
		var fac = AnnotationsFactory.eINSTANCE;
//		fac.createAnnotationAttributeSetting()
//		fac.createAnnotationParameterList()
//		fac.createSingleAnnotationParameter()
		
		return fac.createAnnotationInstance();
	}
}

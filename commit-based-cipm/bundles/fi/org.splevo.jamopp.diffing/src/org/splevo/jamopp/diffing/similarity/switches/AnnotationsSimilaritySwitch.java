package org.splevo.jamopp.diffing.similarity.switches;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.annotations.AnnotationAttributeSetting;
import org.emftext.language.java.annotations.AnnotationInstance;
import org.emftext.language.java.annotations.util.AnnotationsSwitch;
import org.emftext.language.java.classifiers.Classifier;
import org.splevo.jamopp.diffing.similarity.ILoggableSwitch;
import org.splevo.jamopp.diffing.similarity.ISimilaritySwitch;

/**
 * Similarity decisions for annotation elements.
 */
public class AnnotationsSimilaritySwitch extends AnnotationsSwitch<Boolean> implements ILoggableSwitch {
	private final ISimilaritySwitch similaritySwitch;

	/**
	 * @param similaritySwitch
	 */
	public AnnotationsSimilaritySwitch(ISimilaritySwitch similaritySwitch) {
		this.similaritySwitch = similaritySwitch;
	}

	@Override
    public Boolean caseAnnotationInstance(AnnotationInstance instance1) {
        AnnotationInstance instance2 = (AnnotationInstance) this.similaritySwitch.getCompareElement();
        this.logComparison(
        		instance1.getAnnotation().getName(),
        		instance2.getAnnotation().getName(),
        		AnnotationInstance.class.getSimpleName());

        Classifier class1 = instance1.getAnnotation();
        Classifier class2 = instance2.getAnnotation();
        Boolean classifierSimilarity = this.similaritySwitch.isSimilar(class1, class2);
        this.logResult(classifierSimilarity, Classifier.class.getSimpleName());
        if (classifierSimilarity == Boolean.FALSE) {
            return Boolean.FALSE;
        }
        
        String namespace1 = instance1.getNamespacesAsString();
        String namespace2 = instance2.getNamespacesAsString();
        this.logComparison(namespace1, namespace2, "namespace");
        if (namespace1 == null) {
        	this.logResult(namespace2 == null, "namespace");
            return (namespace2 == null);
        } else {
        	this.logResult(namespace1.equals(namespace2), "namespace");
            return (namespace1.equals(namespace2));
        }
    }

    @Override
    public Boolean caseAnnotationAttributeSetting(AnnotationAttributeSetting setting1) {
        AnnotationAttributeSetting setting2 = (AnnotationAttributeSetting) this.similaritySwitch.getCompareElement();
        this.logComparison(setting1.getAttribute().getName(), setting2.getAttribute().getName(), AnnotationAttributeSetting.class.getSimpleName());
        Boolean similarity = this.similaritySwitch.isSimilar(setting1.getAttribute(), setting2.getAttribute());
        this.logResult(similarity, AnnotationAttributeSetting.class.getSimpleName());
        if (similarity == Boolean.FALSE) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean defaultCase(EObject object) {
    	this.logMessage("Default annotation comparing case (" + object.eClass().getName() +"), similarity: true");
        return Boolean.TRUE;
    }
}
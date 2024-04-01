package org.splevo.jamopp.diffing.similarity.switches;

import org.emftext.language.java.classifiers.AnonymousClass;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.classifiers.util.ClassifiersSwitch;
import org.splevo.diffing.util.NormalizationUtil;
import org.splevo.jamopp.diffing.similarity.SimilaritySwitch;

import com.google.common.base.Strings;

/**
 * Similarity decisions for classifier elements.
 */
public class ClassifiersSimilaritySwitch extends ClassifiersSwitch<Boolean> {
	private final SimilaritySwitch similaritySwitch;

    /**
     * Constructor to set the required configurations.
     * 
     * @param classifierNormalizationPatterns
     *            A list of patterns replace any match in a classifier name with the defined
     *            replacement string.
     * @param similaritySwitch TODO
     */
    public ClassifiersSimilaritySwitch(SimilaritySwitch similaritySwitch) {
        this.similaritySwitch = similaritySwitch;
    }

    /**
     * Concrete classifiers such as classes and interface are identified by their location and
     * name. The location is considered implicitly.
     * 
     * @param classifier1
     *            the classifier to compare with the compareelement
     * @return True or false whether they are similar or not.
     */
    @Override
    public Boolean caseConcreteClassifier(ConcreteClassifier classifier1) {

        ConcreteClassifier classifier2 = (ConcreteClassifier) this.similaritySwitch.getCompareElement();

        String name1 = NormalizationUtil.normalize(classifier1.getQualifiedName(),
        		this.similaritySwitch.getClassifierNormalizations());
        String name2 = Strings.nullToEmpty(classifier2.getQualifiedName());

        return (name1.equals(name2));
    }
    
    /**
     * Anonymous classes are considered to be similar.
     * 
     * @param anon the anonymous class to compare with the compare element.
     * @return true.
     */
    @Override
    public Boolean caseAnonymousClass(AnonymousClass anon) {
    	return Boolean.TRUE;
    }

}
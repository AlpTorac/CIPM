package org.splevo.jamopp.diffing.similarity.switches.classifiers;

import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.splevo.diffing.util.NormalizationUtil;
import org.splevo.jamopp.diffing.similarity.SimilarityChecker;
import org.splevo.jamopp.diffing.similarity.switches.AbstractSimilaritySwitch;

import com.google.common.base.Strings;

public class ConcreteClassifiersSimilaritySwitch extends AbstractSimilaritySwitch {

    /**
     * A list of patterns replace any match in a classifier name with the defined replacement
     * string.
     */
    private Map<Pattern, String> classifierNormalizationPatterns = null;

    /**
     * Constructor to set the required configurations.
     * 
     * @param classifierNormalizationPatterns
     *            A list of patterns replace any match in a classifier name with the defined
     *            replacement string.
     */
    public ConcreteClassifiersSimilaritySwitch(Map<Pattern, String> classifierNormalizationPatterns) {
        this.classifierNormalizationPatterns = classifierNormalizationPatterns;
    }

	@Override
	public Boolean defaultCase(EObject eo1, EObject eo2) {
		return null;
	}

	@Override
	public Class<?> getComparisonSubjectType() {
		return ConcreteClassifier.class;
	}

	@Override
	public Boolean handle(EObject eo1, EObject eo2, SimilarityChecker sc) {
		ConcreteClassifier classifier1 = (ConcreteClassifier) eo1;
	            ConcreteClassifier classifier2 = (ConcreteClassifier) eo2;
	
	            String name1 = NormalizationUtil.normalize(classifier1.getQualifiedName(), this.getClassifierNormalizationPatterns());
	            String name2 = Strings.nullToEmpty(classifier2.getQualifiedName());
	
	            return (name1.equals(name2));
	}

	public Map<Pattern, String> getClassifierNormalizationPatterns() {
		return this.classifierNormalizationPatterns;
	}
}

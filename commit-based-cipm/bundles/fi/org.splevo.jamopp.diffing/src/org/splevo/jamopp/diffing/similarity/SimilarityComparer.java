package org.splevo.jamopp.diffing.similarity;

import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.commons.NamespaceAwareElement;
import org.splevo.diffing.util.NormalizationUtil;

public class SimilarityComparer extends AbstractSimilarityComparer {
    private final Map<Pattern, String> classifierNormalizations;
    private final Map<Pattern, String> compilationUnitNormalizations;
    private final Map<Pattern, String> packageNormalizations;
    
    public SimilarityComparer(Map<Pattern, String> classifierNormalizations,
    		Map<Pattern, String> compilationUnitNormalizations,
    		Map<Pattern, String> packageNormalizations) {
    	
    	super();
    	
        this.classifierNormalizations = classifierNormalizations;
        this.compilationUnitNormalizations = compilationUnitNormalizations;
        this.packageNormalizations = packageNormalizations;
    }
    
    public String normalizeCompilationUnit(String original) {
    	return NormalizationUtil.normalize(original, this.compilationUnitNormalizations);
    }
    
    public String normalizePackage(String original) {
    	return NormalizationUtil.normalize(original, this.packageNormalizations);
    }
    
    public String normalizeClassifier(String original) {
    	return NormalizationUtil.normalize(original, this.classifierNormalizations);
    }
    
    public String normalizeNamespace(String namespace) {
    	return NormalizationUtil.normalizeNamespace(namespace, this.packageNormalizations);
    }

    /**
     * Compares the namespaces of two elements by comparing each part of the namespaces.
     * 
     * @param ele1 the first element.
     * @param ele2 the second element to compare to the first element.
     * @return true if the number of parts of the namespaces and each part in both namespaces are equal. false otherwise.
     */
    public boolean compareNamespacesByPart(NamespaceAwareElement ele1, NamespaceAwareElement ele2) {
    	if (ele1.getNamespaces().size() != ele2.getNamespaces().size()) {
    		return false;
    	}
    	for (int idx = 0; idx < ele1.getNamespaces().size(); idx++) {
    		if (!ele1.getNamespaces().get(idx).equals(ele2.getNamespaces().get(idx))) {
    			return false;
    		}
    	}
    	return true;
    }
    
    public Boolean checkSimilarityForResolvedAndSameType(EObject element1, EObject element2, boolean checkStatementPosition) {
    	var clone = this.clone(checkStatementPosition);
    	return new SimilaritySwitch(clone).compare(element1, element2);
    }
    
    protected Map<Pattern, String> getClassifierNormalizations() {
		return this.classifierNormalizations;
	}

	protected Map<Pattern, String> getCompilationUnitNormalizations() {
		return this.compilationUnitNormalizations;
	}

	protected Map<Pattern, String> getPackageNormalizations() {
		return this.packageNormalizations;
	}

	@Override
    public SimilarityComparer clone(boolean checkStatementPosition) {
    	var clone = new SimilarityComparer(
    			this.getClassifierNormalizations(),
    			this.getCompilationUnitNormalizations(),
    			this.getPackageNormalizations());
    	
    	clone.setChecksStatementPositionOnDefault(checkStatementPosition);
    	return clone;
    }
}

package org.splevo.jamopp.diffing.similarity.switches.containers;

import java.util.LinkedHashMap;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.containers.CompilationUnit;
import org.splevo.diffing.util.NormalizationUtil;
import org.splevo.jamopp.diffing.similarity.SimilarityChecker;
import org.splevo.jamopp.diffing.similarity.switches.AbstractSimilaritySwitch;

import com.google.common.base.Strings;

public class CompilationUnitSimilaritySwitch extends AbstractSimilaritySwitch {

    private LinkedHashMap<Pattern, String> compilationUnitNormalizations = null;
    private LinkedHashMap<Pattern, String> packageNormalizations = null;

    /**
     * Constructor to set the required configurations.
     * 
     * @param compilationUnitNormalizations
     *            A list of patterns replace any match in a classifier name with the defined
     *            replacement string.
     * @param packageNormalizations
     *            A list of package normalization patterns.
     */
    public CompilationUnitSimilaritySwitch(LinkedHashMap<Pattern, String> compilationUnitNormalizations,
            LinkedHashMap<Pattern, String> packageNormalizations) {
        this.compilationUnitNormalizations = compilationUnitNormalizations;
        this.packageNormalizations = packageNormalizations;
    }
    
	@Override
	public Class<?> getComparisonSubjectType() {
		return CompilationUnit.class;
	}

    /**
     * Check the similarity of two CompilationUnits.<br>
     * Similarity is checked by
     * <ul>
     * <li>Comparing their names (including renamings)</li>
     * <li>Comparing their namespaces' values (including renamings)</li>
     * </ul>
     * Note: CompilationUnit names are full qualified. So it is important to apply classifier as
     * well as package renaming normalizations to them.
     * 
     * @param unit1
     *            The compilation unit to compare with the compareElement.
     * @return True/False whether they are similar or not.
     */
	@Override
	public Boolean handle(EObject eo1, EObject eo2, SimilarityChecker sc) {
		CompilationUnit unit1 = (CompilationUnit) eo1;
        CompilationUnit unit2 = (CompilationUnit) eo2;
        this.logComparison(unit1.getName(), unit2.getName());
        this.logComparison(unit1.eClass().getName(), unit2.eClass().getName());
        
        String name1 = NormalizationUtil.normalize(unit1.getName(), compilationUnitNormalizations);
        name1 = NormalizationUtil.normalize(name1, packageNormalizations);
        String name2 = unit2.getName();
        
        this.logMessage("Comparing compilation unit name");
        this.logComparison(name1, name2);
        this.logResult(name1.equals(name2));
        if (!name1.equals(name2)) {
            return Boolean.FALSE;
        }

        this.logMessage("Comparing compilation unit namespace");
        
        String namespaceString1 = NormalizationUtil.normalizeNamespace(unit1.getNamespacesAsString(),
                packageNormalizations);
        String namespaceString2 = Strings.nullToEmpty(unit2.getNamespacesAsString());
        
        this.logComparison(namespaceString1, namespaceString2);
        this.logResult(namespaceString1.equals(namespaceString2));
        
        if (!namespaceString1.equals(namespaceString2)) {
            return Boolean.FALSE;
        }
        
        this.logResult(true);
        return Boolean.TRUE;
	}

	@Override
	public Boolean defaultCase(EObject eo1, EObject eo2) {
		// TODO Auto-generated method stub
		return null;
	}

}

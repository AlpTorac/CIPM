package org.splevo.jamopp.diffing.similarity.switches.containers;

import java.util.LinkedHashMap;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.containers.Package;
import org.splevo.diffing.util.NormalizationUtil;
import org.splevo.jamopp.diffing.similarity.SimilarityChecker;
import org.splevo.jamopp.diffing.similarity.switches.AbstractSimilaritySwitch;
import org.splevo.jamopp.diffing.util.JaMoPPModelUtil;

public class PackageSimilaritySwitch extends AbstractSimilaritySwitch {
    private LinkedHashMap<Pattern, String> packageNormalizations = null;

    /**
     * Constructor to set the required configurations.
     * 
     * @param packageNormalizations
     *            A list of package normalization patterns.
     */
    public PackageSimilaritySwitch(LinkedHashMap<Pattern, String> packageNormalizations) {
        this.packageNormalizations = packageNormalizations;
    }

	@Override
	public Class<?> getComparisonSubjectType() {
		return Package.class;
	}

    /**
     * Check package similarity.<br>
     * Similarity is checked by
     * <ul>
     * <li>full qualified package path</li>
     * </ul>
     * 
     * @param package1
     *            The package to compare with the compare element.
     * @return True/False if the packages are similar or not.
     */
	@Override
	public Boolean handle(EObject eo1, EObject eo2, SimilarityChecker sc) {
		Package package1 = (Package) eo1;
        Package package2 = (Package) eo2;
        this.logComparison(package1.getName(), package2.getName());
        this.logComparison(package1.getNamespacesAsString(), package2.getNamespacesAsString());
        this.logMessage("Comparing package path");
        
        String packagePath1 = JaMoPPModelUtil.buildNamespacePath(package1);
        packagePath1 = NormalizationUtil.normalizeNamespace(packagePath1, packageNormalizations);
        String packagePath2 = JaMoPPModelUtil.buildNamespacePath(package2);
        
        this.logComparison(packagePath1, packagePath2);
        this.logResult(packagePath1.equals(packagePath2));
        if (!packagePath1.equals(packagePath2)) {
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

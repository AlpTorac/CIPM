package org.splevo.jamopp.diffing.similarity.switches;

import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.containers.Package;
import org.emftext.language.java.containers.util.ContainersSwitch;
import org.splevo.diffing.util.NormalizationUtil;
import org.splevo.jamopp.diffing.similarity.ILoggableSwitch;
import org.splevo.jamopp.diffing.similarity.SimilaritySwitch;
import org.splevo.jamopp.diffing.util.JaMoPPModelUtil;

import com.google.common.base.Strings;

/**
 * Similarity decisions for container elements.
 */
public class ContainersSimilaritySwitch extends ContainersSwitch<Boolean> implements ILoggableSwitch {
	private final SimilaritySwitch similaritySwitch;

    /**
     * Constructor to set the required configurations.
     * 
     * @param compilationUnitNormalizations
     *            A list of patterns replace any match in a classifier name with the defined
     *            replacement string.
     * @param packageNormalizations
     *            A list of package normalization patterns.
     * @param similaritySwitch TODO
     */
    public ContainersSimilaritySwitch(SimilaritySwitch similaritySwitch) {
        this.similaritySwitch = similaritySwitch;
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
    public Boolean caseCompilationUnit(CompilationUnit unit1) {
        CompilationUnit unit2 = (CompilationUnit) this.similaritySwitch.getCompareElement();
        this.logComparison(unit1.getName(), unit2.getName(), CompilationUnit.class.getSimpleName());
        this.logComparison(unit1.eClass().getName(), unit2.eClass().getName(), "compilation unit class");
        
        String name1 = NormalizationUtil.normalize(unit1.getName(), this.similaritySwitch.getCompilationUnitNormalizations());
        name1 = NormalizationUtil.normalize(name1, this.similaritySwitch.getPackageNormalizations());
        String name2 = unit2.getName();
        
        this.logResult(name1.equals(name2), "compilation unit name");
        if (!name1.equals(name2)) {
            return Boolean.FALSE;
        }
        
        String namespaceString1 = NormalizationUtil.normalizeNamespace(unit1.getNamespacesAsString(),
        		this.similaritySwitch.getPackageNormalizations());
        String namespaceString2 = Strings.nullToEmpty(unit2.getNamespacesAsString());
        
        this.logResult(namespaceString1.equals(namespaceString2), "compilation unit namespace");
        
        if (!namespaceString1.equals(namespaceString2)) {
            return Boolean.FALSE;
        }
        
        return Boolean.TRUE;
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
    public Boolean casePackage(Package package1) {
        Package package2 = (Package) this.similaritySwitch.getCompareElement();
        this.logComparison(package1.getName(), package2.getName(), Package.class.getSimpleName());
        this.logComparison(package1.getNamespacesAsString(), package2.getNamespacesAsString(), "package namespace");
        
        String packagePath1 = JaMoPPModelUtil.buildNamespacePath(package1);
        packagePath1 = NormalizationUtil.normalizeNamespace(packagePath1, this.similaritySwitch.getPackageNormalizations());
        String packagePath2 = JaMoPPModelUtil.buildNamespacePath(package2);
        
        this.logResult(packagePath1.equals(packagePath2), "package path");
        
        if (!packagePath1.equals(packagePath2)) {
            return Boolean.FALSE;
        }
        
        return Boolean.TRUE;
    }
    
    /**
     * Check module similarity.<br>
     * Similarity is checked by
     * <ul>
     * <li>module names</li>
     * </ul>
     * 
     * @param module1 The module to compare with the compare element.
     * @return True/False if the modules are similar or not.
     */
    @Override
    public Boolean caseModule(org.emftext.language.java.containers.Module module1) {
    	org.emftext.language.java.containers.Module module2 =
    			(org.emftext.language.java.containers.Module) this.similaritySwitch.getCompareElement();
    	
    	this.logResult(module1.getName().equals(module2.getName()), Module.class.getSimpleName());
    	if (!module1.getName().equals(module2.getName())) {
    		return Boolean.FALSE;
    	}
    	return Boolean.TRUE;
    }
}
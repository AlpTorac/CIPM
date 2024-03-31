package org.splevo.jamopp.diffing.similarity.switches.containers;

import org.eclipse.emf.ecore.EObject;
import org.splevo.jamopp.diffing.similarity.SimilarityChecker;
import org.splevo.jamopp.diffing.similarity.switches.AbstractSimilaritySwitch;

public class ModuleSimilaritySwitch extends AbstractSimilaritySwitch {

	@Override
	public Class<?> getComparisonSubjectType() {
		return org.emftext.language.java.containers.Module.class;
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
	public Boolean handle(EObject eo1, EObject eo2, SimilarityChecker sc) {
		org.emftext.language.java.containers.Module module1 =
				(org.emftext.language.java.containers.Module) eo1;
    	org.emftext.language.java.containers.Module module2 =
    			(org.emftext.language.java.containers.Module) eo2;
    	this.logComparison(module1.getName(), module2.getName());
    	this.logResult(module1.getName().equals(module2.getName()));
    	if (!module1.getName().equals(module2.getName())) {
    		return Boolean.FALSE;
    	}
    	return Boolean.TRUE;
	}

	@Override
	public Boolean defaultCase(EObject eo1, EObject eo2) {
		// TODO Auto-generated method stub
		return null;
	}

}

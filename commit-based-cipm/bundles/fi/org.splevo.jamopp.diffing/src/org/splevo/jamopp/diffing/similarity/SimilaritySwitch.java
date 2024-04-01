/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt - initial API and implementation and/or initial documentation
 *    Martin Armbruster - extension for expanded JaMoPP
 *******************************************************************************/
package org.splevo.jamopp.diffing.similarity;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.ComposedSwitch;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.emftext.language.java.commons.NamespaceAwareElement;
import org.splevo.jamopp.diffing.similarity.switches.AnnotationsSimilaritySwitch;
import org.splevo.jamopp.diffing.similarity.switches.ArraysSimilaritySwitch;
import org.splevo.jamopp.diffing.similarity.switches.ClassifiersSimilaritySwitch;
import org.splevo.jamopp.diffing.similarity.switches.CommonsSimilaritySwitch;
import org.splevo.jamopp.diffing.similarity.switches.ContainersSimilaritySwitch;
import org.splevo.jamopp.diffing.similarity.switches.ExpressionsSimilaritySwitch;
import org.splevo.jamopp.diffing.similarity.switches.GenericsSimilaritySwitch;
import org.splevo.jamopp.diffing.similarity.switches.ImportsSimilaritySwitch;
import org.splevo.jamopp.diffing.similarity.switches.InstantiationsSimilaritySwitch;
import org.splevo.jamopp.diffing.similarity.switches.LayoutSimilaritySwitch;
import org.splevo.jamopp.diffing.similarity.switches.LiteralsSimilaritySwitch;
import org.splevo.jamopp.diffing.similarity.switches.MembersSimilaritySwitch;
import org.splevo.jamopp.diffing.similarity.switches.ModifiersSimilaritySwitch;
import org.splevo.jamopp.diffing.similarity.switches.ModulesSimilaritySwitch;
import org.splevo.jamopp.diffing.similarity.switches.OperatorsSimilaritySwitch;
import org.splevo.jamopp.diffing.similarity.switches.ParametersSimilaritySwitch;
import org.splevo.jamopp.diffing.similarity.switches.ReferencesSimilaritySwitch;
import org.splevo.jamopp.diffing.similarity.switches.StatementsSimilaritySwitch;
import org.splevo.jamopp.diffing.similarity.switches.TypesSimilaritySwitch;
import org.splevo.jamopp.diffing.similarity.switches.VariablesSimilaritySwitch;

/**
 * Internal switch class to prove element similarity.
 * 
 * <p>
 * The similarity case methods do not need to check for null values. It is assumed that the calling
 * class does a null value check for the elements to compare in advanced, such as done by the
 * SimilarityChecker class.
 * </p>
 * 
 * <p>
 * Check strategy:<br>
 * First all "not-similar"-criteria are checked. If none hits, true will be returned.
 * </p>
 */
public class SimilaritySwitch extends ComposedSwitch<Boolean> implements ISimilaritySwitch, ILoggableSwitch {

    /** The logger for this class. */
    Logger logger = Logger.getLogger(SimilaritySwitch.class);

    /** The object to compare the switched element with. */
    private EObject compareElement = null;

    private Map<Pattern, String> classifierNormalizations = null;
	private Map<Pattern, String> compilationUnitNormalizations = null;
    private Map<Pattern, String> packageNormalizations = null;
    private boolean defaultCheckStatementPosition = true;
    
    /**
     * Constructor requiring the element to compare with.
     * 
     * @param defaultCheckStatementPosition
     *            Flag if the similarity check should consider the position of a statement or not.
     * @param classifierNormalizations
     *            A list of patterns replace any match in a classifier name with the defined
     *            replacement string.
     * @param compilationUnitNormalizations
     *            A list of patterns replace any match in a compilation unit name with the defined
     *            replacement string.
     * @param packageNormalizations
     *            A list of package normalization patterns.
     */
    public SimilaritySwitch(boolean defaultCheckStatementPosition,
    		Map<Pattern, String> classifierNormalizations,
    		Map<Pattern, String> compilationUnitNormalizations,
    		Map<Pattern, String> packageNormalizations) {
    	
    	this.classifierNormalizations = classifierNormalizations;
    	this.compilationUnitNormalizations = compilationUnitNormalizations;
    	this.packageNormalizations = packageNormalizations;
    	this.defaultCheckStatementPosition = defaultCheckStatementPosition;

        addSwitch(new AnnotationsSimilaritySwitch(this));
        addSwitch(new ArraysSimilaritySwitch());
        addSwitch(new ClassifiersSimilaritySwitch(this));
        addSwitch(new CommonsSimilaritySwitch(this));
        addSwitch(new ContainersSimilaritySwitch(this));
        addSwitch(new ExpressionsSimilaritySwitch(this));
        addSwitch(new GenericsSimilaritySwitch(this));
        addSwitch(new ImportsSimilaritySwitch(this));
        addSwitch(new InstantiationsSimilaritySwitch(this));
        addSwitch(new LiteralsSimilaritySwitch(this));
        addSwitch(new MembersSimilaritySwitch(this));
        addSwitch(new ModifiersSimilaritySwitch());
        addSwitch(new OperatorsSimilaritySwitch());
        addSwitch(new ParametersSimilaritySwitch(this));
        addSwitch(new ReferencesSimilaritySwitch(this));
        addSwitch(new StatementsSimilaritySwitch(this));
        addSwitch(new TypesSimilaritySwitch(this));
        addSwitch(new VariablesSimilaritySwitch(this));
        addSwitch(new LayoutSimilaritySwitch());
        addSwitch(new ModulesSimilaritySwitch(this));
    }

    @Override
	public Map<Pattern, String> getClassifierNormalizations() {
		return classifierNormalizations;
	}

	@Override
	public Map<Pattern, String> getCompilationUnitNormalizations() {
		return compilationUnitNormalizations;
	}

	@Override
	public Map<Pattern, String> getPackageNormalizations() {
		return packageNormalizations;
	}
	
	@Override
	public EObject getCompareElement() {
		return this.compareElement;
	}
    
    public Boolean compare(EObject eo1, EObject eo2) {
    	this.compareElement = eo2;
    	return this.doSwitch(eo1);
    }
    
    /**
     * Check two object lists if they are similar.
     *
     * The elements is compared pairwise and it is the responsibility of the provided list
     * implementations to return them in an appropriate order by calling get(i) with a increasing
     * index i.
     *
     * @param elements1
     *            The first list of elements to check.
     * @param elements2
     *            The second list of elements to check.
     * @return TRUE, if they are all similar; FALSE if a different number of elements is submitted or at least one pair of elements is not similar to each other.
     */
    @Override
	public Boolean areSimilar(final List<? extends EObject> elements1, final List<? extends EObject> elements2) {
        if (elements1.size() != elements2.size()) {
            return Boolean.FALSE;
        }
        for (int i = 0; i < elements1.size(); i++) {
            Boolean childSimilarity = isSimilar(elements1.get(i), elements2.get(i));
            if (childSimilarity == Boolean.FALSE) {
                return Boolean.FALSE;
            }
        }

        return Boolean.TRUE;
    }

    public boolean getDefaultCheckStatementPosition() {
		return defaultCheckStatementPosition;
	}
    
    public void setDefaultCheckStatementPosition(boolean defaultCheckStatementPosition) {
    	this.defaultCheckStatementPosition = defaultCheckStatementPosition;
    }

	/**
     * Check two objects if they are similar.
     *
     * @param element1
     *            The first element to check.
     * @param element2
     *            The second element to check.
     * @param checkStatementPosition
     *            Flag if the position of statement elements should be considered or not.
     * @return TRUE, if they are similar; FALSE if not, NULL if it can't be decided.
     */
    public Boolean isSimilar(EObject element1, EObject element2, boolean checkStatementPosition) {

        // check that either both or none of them is null
        if (element1 == element2) {
            return Boolean.TRUE;
        }

        if (onlyOneIsNull(element1, element2)) {
            return Boolean.FALSE;
        }

        // if a proxy is present try to resolve it
        // the other element is used as a context.
        // TODO Clarify why it can happen that one proxy is resolved and the other is not
        // further notes available with the issue
        // https://sdqbuild.ipd.kit.edu/jira/browse/SPLEVO-279
        if (element2.eIsProxy() && !element1.eIsProxy()) {
            element2 = EcoreUtil.resolve(element2, element1);
        } else if (element1.eIsProxy() && !element2.eIsProxy()) {
            element1 = EcoreUtil.resolve(element1, element2);
        }

        // check the elements to be of the same type
        if (!element1.getClass().equals(element2.getClass())) {
            return Boolean.FALSE;
        }

        // check type specific similarity
        return this.checkSimilarityForResolvedAndSameType(element1, element2, checkStatementPosition);
    }
    
    /**
     * Checks the similarity of two EObjects where both EObjects are resolved and have the same type.
     * 
     * @param element1 the first EObject.
     * @param element2 the second EObject.
     * @param checkStatementPosition true if the position of statements should be checked. false otherwise.
     *                               If no statements are involved, the flag can be ignored.
     * @return true if the EObjects are similar. null if they cannot be compared. false otherwise.
     */
    protected Boolean checkSimilarityForResolvedAndSameType(EObject element1, EObject element2,
    		boolean checkStatementPosition) {
    	return new SimilaritySwitch(checkStatementPosition, this.classifierNormalizations,
    			this.compilationUnitNormalizations, this.packageNormalizations).compare(element1, element2);
    }

    /**
     * Method to check if only one of the provided elements is null.
     *
     * @param element1
     *            The first element.
     * @param element2
     *            The second element.
     * @return True if only one element is null and the other is not.
     */
    private Boolean onlyOneIsNull(final EObject element1, final EObject element2) {
        Boolean onlyOneIsNull = false;
        if (element1 != null && element2 == null) {
            onlyOneIsNull = Boolean.TRUE;
        } else if (element1 == null && element2 != null) {
            onlyOneIsNull = Boolean.TRUE;
        }
        return onlyOneIsNull;
    }

    
    /**
     * Compares the namespaces of two elements by comparing each part of the namespaces.
     * 
     * @param ele1 the first element.
     * @param ele2 the second element to compare to the first element.
     * @return true if the number of parts of the namespaces and each part in both namespaces are equal. false otherwise.
     */
    @Override
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

    /**
     * The default case for not explicitly handled elements always returns null to identify the open
     * decision.
     * 
     * @param object
     *            The object to compare with the compare element.
     * @return null
     */
    @Override
    public Boolean defaultCase(EObject object) {
        return null;
    }
}

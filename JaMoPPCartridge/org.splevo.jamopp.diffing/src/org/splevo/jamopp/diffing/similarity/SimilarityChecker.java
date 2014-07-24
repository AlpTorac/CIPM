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
 *******************************************************************************/
package org.splevo.jamopp.diffing.similarity;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.google.common.collect.Maps;

/**
 * Checker for the similarity of two elements specific for the java application model.
 *
 * TODO: Check caching for this similarity checker. Would require to pass this to the similarity
 * switch as well!
 *
 */
public class SimilarityChecker {

    /** The logger for this class. */
    @SuppressWarnings("unused")
    private Logger logger = Logger.getLogger(SimilarityChecker.class);

    private LinkedHashMap<Pattern, String> classifierNormalizations = null;
    private LinkedHashMap<Pattern, String> compilationUnitNormalizations = null;
    private LinkedHashMap<Pattern, String> packageNormalizations = null;

    /**
     * Constructor to set the required configurations.
     *
     * @param classifierNormalizations
     *            A list of patterns replace any match in a classifier name with the defined
     *            replacement string.
     * @param compilationUnitNormalizations
     *            A list of patterns replace any match in a compilation unit name with the defined
     *            replacement string.
     * @param packageNormalizations
     *            The normalizations to replace expressions.
     */
    public SimilarityChecker(LinkedHashMap<Pattern, String> classifierNormalizations,
            LinkedHashMap<Pattern, String> compilationUnitNormalizations, LinkedHashMap<Pattern, String> packageNormalizations) {
        this.classifierNormalizations = classifierNormalizations;
        this.compilationUnitNormalizations = compilationUnitNormalizations;
        this.packageNormalizations = packageNormalizations;
    }

    /**
     * Default constructor for a similarity checker without any normalization configurations.
     */
    public SimilarityChecker() {
        this.classifierNormalizations = Maps.newLinkedHashMap();
        this.compilationUnitNormalizations = Maps.newLinkedHashMap();
        this.packageNormalizations = Maps.newLinkedHashMap();
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

    /**
     * Check two objects if they are similar.
     *
     * @param element1
     *            The first element to check.
     * @param element2
     *            The second element to check.
     * @return TRUE, if they are similar; FALSE if not, NULL if it can't be decided.
     */
    public Boolean isSimilar(final EObject element1, final EObject element2) {
        return isSimilar(element1, element2, true);
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
        SimilaritySwitch similaritySwitch = new SimilaritySwitch(element2, checkStatementPosition,
                classifierNormalizations, compilationUnitNormalizations, packageNormalizations);
        Boolean similar = similaritySwitch.doSwitch(element1);
        return similar;
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

}

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
 *    Martin Armbruster - enable change of default behavior for statement position check.
 *******************************************************************************/
package org.splevo.jamopp.diffing.similarity;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;

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
    private boolean defaultCheckStatementPositionFlag = true;

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
     * Indicates if the SimilarityChecker instance checks the position of statements in its default behavior.
     * 
     * @return true if the statement positions are checked. false otherwise.
     */
    public boolean checksStatementPositionOnDefault() {
    	return this.defaultCheckStatementPositionFlag;
    }
    
    /**
     * Controls if the SimilarityChecker checks the position of statements in the default behavior.
     * 
     * @param defaultCheckStatementPositionFlag true if the statement positions are checked. false otherwise.
     */
    public void setCheckStatementPositionOnDefault(boolean defaultCheckStatementPositionFlag) {
    	this.defaultCheckStatementPositionFlag = defaultCheckStatementPositionFlag;
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
        return this.makeSwitch().areSimilar(elements1, elements2);
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
    	return this.isSimilar(element1, element2, this.checksStatementPositionOnDefault());
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
    	return this.makeSwitch(checkStatementPosition).isSimilar(element1, element2, checkStatementPosition);
    }
    
    protected ISimilaritySwitch makeSwitch(boolean defaultCheckStatementPositionFlag) {
    	return new SimilaritySwitch(defaultCheckStatementPositionFlag, classifierNormalizations,
    			compilationUnitNormalizations, packageNormalizations);
    }
    
    protected ISimilaritySwitch makeSwitch() {
    	return this.makeSwitch(this.checksStatementPositionOnDefault());
    }
}

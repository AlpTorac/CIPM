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
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.google.common.collect.Maps;

/**
 * Checker for the similarity of two elements specific for the java application model.
 *
 * TODO: Check caching for this similarity checker. Would require to pass this to the similarity
 * switch as well!
 *
 */
public class SimilarityChecker implements ISimilarityChecker {

    /** The logger for this class. */
    @SuppressWarnings("unused")
    private Logger logger = Logger.getLogger(SimilarityChecker.class);

    private LinkedHashMap<Pattern, String> classifierNormalizations = null;
    private LinkedHashMap<Pattern, String> compilationUnitNormalizations = null;
    private LinkedHashMap<Pattern, String> packageNormalizations = null;

    private SimilarityComparer sc;
    
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
        
        this.sc = this.createSimilarityComparer();
    }

    /**
     * Default constructor for a similarity checker without any normalization configurations.
     */
    public SimilarityChecker() {
        this.classifierNormalizations = Maps.newLinkedHashMap();
        this.compilationUnitNormalizations = Maps.newLinkedHashMap();
        this.packageNormalizations = Maps.newLinkedHashMap();
        
        this.sc = this.createSimilarityComparer();
    }
    
    @Override
    public SimilarityComparer createSimilarityComparer() {
    	SimilarityComparer sc = new SimilarityComparer(classifierNormalizations,
    			compilationUnitNormalizations,
    			packageNormalizations);
    	
    	return sc;
    }

	@Override
	public SimilarityComparer getSimilarityComparer() {
		return this.sc;
	}
}

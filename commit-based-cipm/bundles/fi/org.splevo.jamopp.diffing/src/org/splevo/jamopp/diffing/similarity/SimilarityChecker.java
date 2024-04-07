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

import java.util.Map;
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

    private final Map<Pattern, String> classifierNormalizations;
    private final Map<Pattern, String> compilationUnitNormalizations;
    private final Map<Pattern, String> packageNormalizations;

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
    public SimilarityChecker(Map<Pattern, String> classifierNormalizations,
    		Map<Pattern, String> compilationUnitNormalizations, Map<Pattern, String> packageNormalizations) {
        this.classifierNormalizations = classifierNormalizations;
        this.compilationUnitNormalizations = compilationUnitNormalizations;
        this.packageNormalizations = packageNormalizations;
        
        this.sc = this.createSimilarityComparer();
    }

    /**
     * Default constructor for a similarity checker without any normalization configurations.
     */
    public SimilarityChecker() {
    	this(Maps.newLinkedHashMap(),
    			Maps.newLinkedHashMap(),
    			Maps.newLinkedHashMap());
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
    public SimilarityComparer createSimilarityComparer() {
    	SimilarityComparer sc = new SimilarityComparer(
    			this.getClassifierNormalizations(),
    			this.getCompilationUnitNormalizations(),
    			this.getPackageNormalizations());
    	
    	return sc;
    }

	@Override
	public SimilarityComparer getSimilarityComparer() {
		return this.sc;
	}
}

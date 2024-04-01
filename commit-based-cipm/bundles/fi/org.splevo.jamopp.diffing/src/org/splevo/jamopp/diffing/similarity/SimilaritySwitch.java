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

import java.util.Map;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
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
public class SimilaritySwitch extends AbstractSimilaritySwitch {
    private Map<Pattern, String> classifierNormalizations = null;
	private Map<Pattern, String> compilationUnitNormalizations = null;
    private Map<Pattern, String> packageNormalizations = null;
    
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
    public SimilaritySwitch(ISimilarityChecker sc,
    		boolean defaultCheckStatementPosition,
    		Map<Pattern, String> classifierNormalizations,
    		Map<Pattern, String> compilationUnitNormalizations,
    		Map<Pattern, String> packageNormalizations) {
    	
    	super(sc, defaultCheckStatementPosition);
    	
    	this.classifierNormalizations = classifierNormalizations;
    	this.compilationUnitNormalizations = compilationUnitNormalizations;
    	this.packageNormalizations = packageNormalizations;

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
    
	public Map<Pattern, String> getClassifierNormalizations() {
		return classifierNormalizations;
	}

	public Map<Pattern, String> getCompilationUnitNormalizations() {
		return compilationUnitNormalizations;
	}

	public Map<Pattern, String> getPackageNormalizations() {
		return packageNormalizations;
	}
	
	protected ISimilaritySwitch makeSwitch(boolean checkStatementPosition) {
		return new SimilaritySwitch(this.getSimilarityChecker(), checkStatementPosition, this.classifierNormalizations,
    			this.compilationUnitNormalizations, this.packageNormalizations);
	}
}

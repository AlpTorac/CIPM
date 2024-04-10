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

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.splevo.jamopp.diffing.similarity.base.AbstractSimilarityChecker;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityToolbox;
import org.splevo.jamopp.diffing.similarity.base.ecore.SingleSimilarityCheckRequest;
import org.splevo.jamopp.diffing.similarity.requests.NewSimilaritySwitchRequest;

/**
 * Checker for the similarity of two elements specific for the java application model.
 *
 * TODO: Check caching for this similarity checker. Would require to pass this to the similarity
 * switch as well!
 *
 */
public class JavaSimilarityChecker extends AbstractSimilarityChecker {

    /** The logger for this class. */
    @SuppressWarnings("unused")
    private Logger logger = Logger.getLogger(JavaSimilarityChecker.class);

    public JavaSimilarityChecker(ISimilarityToolbox st) {
        super(st);
    }

	@Override
	protected JavaSimilarityComparer createSimilarityComparer(ISimilarityToolbox st) {
		return new JavaSimilarityComparer(st);
	}

	@Override
	public Boolean isSimilar(Object element1, Object element2) {
		IJavaSimilaritySwitch ss = (IJavaSimilaritySwitch) 
				this.getSimilarityComparer().handleSimilarityRequest(
							new NewSimilaritySwitchRequest(true)
						);
		
		return (Boolean) ss.handleSimilarityRequest(new SingleSimilarityCheckRequest((EObject) element1, (EObject) element2, ss));
	}
}

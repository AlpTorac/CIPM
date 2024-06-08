package org.splevo.jamopp.diffing.similarity.base.ecore;

import org.splevo.jamopp.diffing.similarity.base.AbstractSimilarityComparer;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityToolbox;

/**
 * An abstract class provided for integrating future constructs that may
 * complement {@link AbstractSimilarityComparer}.
 * 
 * @author atora
 */
public abstract class AbstractComposedSimilaritySwitchComparer extends AbstractSimilarityComparer {
	public AbstractComposedSimilaritySwitchComparer(ISimilarityToolbox st) {
		super(st);
	}
}

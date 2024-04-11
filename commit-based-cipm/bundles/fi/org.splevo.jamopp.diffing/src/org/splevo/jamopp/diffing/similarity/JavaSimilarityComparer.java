package org.splevo.jamopp.diffing.similarity;

import org.splevo.jamopp.diffing.similarity.base.ISimilarityToolbox;
import org.splevo.jamopp.diffing.similarity.base.ecore.AbstractComposedSimilaritySwitchComparer;

public class JavaSimilarityComparer extends AbstractComposedSimilaritySwitchComparer {
    public JavaSimilarityComparer(ISimilarityToolbox st) {
    	super(st);
    }
}

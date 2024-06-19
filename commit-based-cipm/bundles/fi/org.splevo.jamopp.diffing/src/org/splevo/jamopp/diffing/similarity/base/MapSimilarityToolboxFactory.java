package org.splevo.jamopp.diffing.similarity.base;

/**
 * A factory that creates {@link ISimilarityToolbox} instances, which internally
 * use a {@link Map}.
 * 
 * @see {@link MapSimilarityToolbox}
 * @author atora
 */
public class MapSimilarityToolboxFactory implements ISimilarityToolboxFactory {
	@Override
	public MapSimilarityToolbox createSimilarityToolbox() {
		return new MapSimilarityToolbox();
	}
}

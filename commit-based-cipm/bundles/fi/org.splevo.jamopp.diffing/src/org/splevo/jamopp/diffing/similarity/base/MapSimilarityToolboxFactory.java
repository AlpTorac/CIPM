package org.splevo.jamopp.diffing.similarity.base;

public class MapSimilarityToolboxFactory implements ISimilarityToolboxFactory {
	@Override
	public MapSimilarityToolbox createSimilarityToolbox() {
		return new MapSimilarityToolbox();
	}
}

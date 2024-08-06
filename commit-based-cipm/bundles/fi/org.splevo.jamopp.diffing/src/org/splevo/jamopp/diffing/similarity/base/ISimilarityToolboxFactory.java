package org.splevo.jamopp.diffing.similarity.base;

/**
 * An interface for {@link ISimilarityToolbox} factories. <br>
 * <br>
 * The purpose of the implementors of this interface is to set the data
 * structure that will be used in the {@link ISimilarityToolbox} to contain the
 * pairs added to them. <br>
 * <br>
 * It is recommended to use {@link ISimilarityToolboxBuilder} and its
 * implementors to build {@link ISimilarityToolbox} instances.
 * 
 * @author atora
 */
public interface ISimilarityToolboxFactory {
	/**
	 * Constructs and returns an {@link ISimilarityToolbox} instance without any
	 * pairs.
	 */
	public ISimilarityToolbox createSimilarityToolbox();
}

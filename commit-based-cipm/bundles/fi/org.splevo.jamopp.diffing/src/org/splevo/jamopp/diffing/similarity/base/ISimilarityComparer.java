package org.splevo.jamopp.diffing.similarity.base;

/**
 * An interface for similarity comparers, which serve as a layer of indirection
 * between {@link ISimilarityChecker} and {@link ISimilarityToolbox}. This
 * allows to integrate additional similarity checking related constructs without
 * bloating the implementors of {@link ISimilarityChecker}.
 * 
 * @author atora
 */
public interface ISimilarityComparer extends ISimilarityRequestHandler {

}
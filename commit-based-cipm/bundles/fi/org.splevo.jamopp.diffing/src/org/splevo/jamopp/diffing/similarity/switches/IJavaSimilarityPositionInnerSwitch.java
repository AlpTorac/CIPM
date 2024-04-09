package org.splevo.jamopp.diffing.similarity.switches;

import org.splevo.jamopp.diffing.similarity.IJavaSimilaritySwitch;
import org.splevo.jamopp.diffing.similarity.base.ecore.IPositionInnerSwitch;
import org.splevo.jamopp.diffing.similarity.requests.NewSimilaritySwitchRequest;

public interface IJavaSimilarityPositionInnerSwitch extends IJavaSimilarityInnerSwitch, IPositionInnerSwitch {
	public default IJavaSimilaritySwitch requestNewSwitch(boolean checkStatementPosition) {
		return (IJavaSimilaritySwitch) this.handleSimilarityRequest(new NewSimilaritySwitchRequest(checkStatementPosition));
	}
}

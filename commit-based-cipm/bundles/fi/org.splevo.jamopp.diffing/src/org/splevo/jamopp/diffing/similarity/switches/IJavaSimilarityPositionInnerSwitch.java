package org.splevo.jamopp.diffing.similarity.switches;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.splevo.jamopp.diffing.similarity.IJavaSimilaritySwitch;
import org.splevo.jamopp.diffing.similarity.requests.MultipleSimilarityCheckRequest;
import org.splevo.jamopp.diffing.similarity.requests.NewSimilaritySwitchRequest;
import org.splevo.jamopp.diffing.similarity.requests.SingleSimilarityCheckRequest;

public interface IJavaSimilarityPositionInnerSwitch extends IJavaSimilarityInnerSwitch {
	public boolean shouldCheckStatementPosition();
	
	public default Boolean isSimilar(EObject eo1, EObject eo2) {
		return this.isSimilar(eo1, eo2, this.shouldCheckStatementPosition());
	}
	
	public default Boolean isSimilar(EObject eo1, EObject eo2, boolean checkStatementPosition) {
		return (Boolean) this.getSimilarityRequestHandler().handleSimilarityRequest(
					new SingleSimilarityCheckRequest(eo1, eo2,
							(IJavaSimilaritySwitch) this.getSimilarityRequestHandler().handleSimilarityRequest(new NewSimilaritySwitchRequest(checkStatementPosition))
						)
				);
	}
	
	public default Boolean areSimilar(
			Collection<? extends EObject> eos1,
			Collection<? extends EObject> eos2,
			Collection<? extends IJavaSimilaritySwitch> sss) {
		return (Boolean) this.getSimilarityRequestHandler().handleSimilarityRequest(
					new MultipleSimilarityCheckRequest(eos1, eos2, sss)
				);
	}
	
	public default Boolean areSimilar(
			Collection<? extends EObject> eos1,
			Collection<? extends EObject> eos2,
			List<Boolean> csps) {
		
		Collection<IJavaSimilaritySwitch> sss = new ArrayList<IJavaSimilaritySwitch>();
		
		csps.forEach((csp) -> sss.add((IJavaSimilaritySwitch) this.requestNewSwitch(csp)));
		
		return this.areSimilar(eos1, eos2,
				sss);
	}
	
	public default Boolean areSimilar(
			Collection<? extends EObject> eos1,
			Collection<? extends EObject> eos2) {
		
		var csps = new ArrayList<Boolean>();
		
		for (int i = 0; i < eos1.size(); i++) {
			csps.add(this.shouldCheckStatementPosition());
		}
		
		return this.areSimilar(eos1, eos2, csps);
	}
	
	public default IJavaSimilaritySwitch requestNewSwitch(boolean checkStatementPosition) {
		return (IJavaSimilaritySwitch) this.getSimilarityRequestHandler()
				.handleSimilarityRequest(new NewSimilaritySwitchRequest(checkStatementPosition));
	}
}

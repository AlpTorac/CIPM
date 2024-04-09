package org.splevo.jamopp.diffing.similarity.base.ecore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

public interface IPositionInnerSwitch extends IInnerSwitch {
	public boolean shouldCheckStatementPosition();
	
	public default Boolean isSimilar(EObject eo1, EObject eo2) {
		return this.isSimilar(eo1, eo2, this.shouldCheckStatementPosition());
	}
	
	public default Boolean isSimilar(EObject eo1, EObject eo2, boolean checkStatementPosition) {
		return (Boolean) this.handleSimilarityRequest(
					new SingleSimilarityCheckRequest(eo1, eo2,
							this.requestNewSwitch(checkStatementPosition)
						)
				);
	}
	
	public default Boolean areSimilar(
			Collection<? extends EObject> eos1,
			Collection<? extends EObject> eos2,
			Collection<? extends IComposedSwitchWrapper> sss) {
		return (Boolean) this.handleSimilarityRequest(
					new MultipleSimilarityCheckRequest(eos1, eos2, sss)
				);
	}
	
	public default Boolean areSimilar(
			Collection<? extends EObject> eos1,
			Collection<? extends EObject> eos2,
			List<Boolean> csps) {
		
		Collection<IComposedSwitchWrapper> sss = new ArrayList<IComposedSwitchWrapper>();
		
		csps.forEach((csp) -> sss.add((IComposedSwitchWrapper) this.requestNewSwitch(csp)));
		
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
	
	public IComposedSwitchWrapper requestNewSwitch(boolean checkStatementPosition);
}

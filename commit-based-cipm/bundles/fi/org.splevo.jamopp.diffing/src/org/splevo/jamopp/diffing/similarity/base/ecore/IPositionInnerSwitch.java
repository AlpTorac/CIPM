package org.splevo.jamopp.diffing.similarity.base.ecore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

/**
 * An interface that extends {@link IInnerSwitch} with methods, which are mutual
 * among the implementors of {@link IInnerSwitch} that additionally use a flag
 * to check statement positions in EObject instances they compare.
 * 
 * @author atora
 */
public interface IPositionInnerSwitch extends IInnerSwitch {
	/**
	 * @return Whether this switch should take statement positions in EObjects it
	 *         compares into account, while comparing them.
	 */
	public boolean shouldCheckStatementPosition();

	/**
	 * @return Whether the EObject instances are similar.
	 * @see {@link ISimilarityChecker}
	 */
	public default Boolean isSimilar(EObject eo1, EObject eo2) {
		return this.isSimilar(eo1, eo2, this.shouldCheckStatementPosition());
	}

	/**
	 * @return Whether the EObject instances are similar, given the
	 *         checkStatementPosition flag.
	 * @see {@link #shouldCheckStatementPosition()}, {@link ISimilarityChecker}
	 */
	public default Boolean isSimilar(EObject eo1, EObject eo2, boolean checkStatementPosition) {
		return (Boolean) this.handleSimilarityRequest(
				new SingleSimilarityCheckRequest(eo1, eo2, this.requestNewSwitch(checkStatementPosition)));
	}

	/**
	 * @return Whether the given lists are pairwise similar, using the given list of
	 *         {@link IComposedSwitchWrapper}.
	 * @see {@link ISimilarityChecker}
	 */
	public default Boolean areSimilar(Collection<? extends EObject> eos1, Collection<? extends EObject> eos2,
			Collection<? extends IComposedSwitchWrapper> sss) {
		return (Boolean) this.handleSimilarityRequest(new MultipleSimilarityCheckRequest(eos1, eos2, sss));
	}

	/**
	 * A version of {@link #areSimilar(Collection, Collection)}
	 * 
	 * @return Whether the given lists are pairwise similar, using the given list of
	 *         checkStatementPosition flags.
	 * 
	 * @see {@link #shouldCheckStatementPosition()},
	 *      {@link #isSimilar(EObject, EObject, boolean)},
	 *      {@link ISimilarityChecker}
	 */
	public default Boolean areSimilar(Collection<? extends EObject> eos1, Collection<? extends EObject> eos2,
			List<Boolean> csps) {

		Collection<IComposedSwitchWrapper> sss = new ArrayList<IComposedSwitchWrapper>();

		csps.forEach((csp) -> sss.add((IComposedSwitchWrapper) this.requestNewSwitch(csp)));

		return this.areSimilar(eos1, eos2, sss);
	}

	/**
	 * @return Whether the given lists are pairwise similar.
	 * @see {@link ISimilarityChecker}
	 */
	public default Boolean areSimilar(Collection<? extends EObject> eos1, Collection<? extends EObject> eos2) {

		var csps = new ArrayList<Boolean>();

		for (int i = 0; i < eos1.size(); i++) {
			csps.add(this.shouldCheckStatementPosition());
		}

		return this.areSimilar(eos1, eos2, csps);
	}

	/**
	 * @return A new switch with the given checkStatementPosition.
	 * @see {@link #shouldCheckStatementPosition()}
	 */
	public IComposedSwitchWrapper requestNewSwitch(boolean checkStatementPosition);
}

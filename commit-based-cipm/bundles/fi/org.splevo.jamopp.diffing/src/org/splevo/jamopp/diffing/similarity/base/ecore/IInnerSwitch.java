package org.splevo.jamopp.diffing.similarity.base.ecore;

import org.eclipse.emf.ecore.EObject;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequest;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequestHandler;

/**
 * An interface for the classes to implement, which extend
 * {@link org.eclipse.emf.ecore.util.Switch} and are nested in
 * {@link org.eclipse.emf.ecore.util.ComposedSwitch}. This interface contains
 * getters and delegation methods that are mutual among its implementors.
 * 
 * @author atora
 */
public interface IInnerSwitch extends ISimilarityRequestHandler {
	/**
	 * @return The {@link ISimilarityRequestHandler}, to which all incoming
	 *         {@link ISimilarityRequest} instances will be delegated.
	 */
	public ISimilarityRequestHandler getSimilarityRequestHandler();

	/**
	 * @return The object containing this switch.
	 */
	public IComposedSwitchWrapper getContainingSwitch();

	/**
	 * @return The current compare element.
	 * @see {@link IComposedSwitchWrapper}
	 */
	public default EObject getCompareElement() {
		return this.getContainingSwitch().getCompareElement();
	}

	/**
	 * {@inheritDoc} <br>
	 * <br>
	 * Here, the incoming requests are delegated to
	 * {@link #getSimilarityRequestHandler()}.
	 */
	@Override
	public default Object handleSimilarityRequest(ISimilarityRequest req) {
		return this.getSimilarityRequestHandler().handleSimilarityRequest(req);
	}
}

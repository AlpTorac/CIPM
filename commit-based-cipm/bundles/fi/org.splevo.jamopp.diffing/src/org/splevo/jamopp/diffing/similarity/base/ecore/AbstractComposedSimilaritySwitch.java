package org.splevo.jamopp.diffing.similarity.base.ecore;

import java.util.Collection;

import org.eclipse.emf.ecore.util.Switch;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequest;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequestHandler;

/**
 * An abstract class that complements {@link AbstractComposedSwitchWrapper} with
 * an attribute to store an {@link ISimilarityRequestHandler} instance. This
 * attribute is then used during similarity checking to delegate
 * {@link ISimilarityRequest} instances to their proper
 * {@link ISimilarityRequestHandler} instance.
 * 
 * @author atora
 */
public abstract class AbstractComposedSimilaritySwitch extends AbstractComposedSwitchWrapper
		implements ISimilarityRequestHandler {

	/**
	 * The {@link ISimilarityRequestHandler}, to which incoming
	 * {@link ISimilarityRequest} instances will be delegated.
	 */
	private ISimilarityRequestHandler srh;

	/**
	 * Constructs an instance with the given {@link ISimilarityRequestHandler}
	 * 
	 * @param srh The request handler, to which all incoming {@link ISimilarityRequest}
	 * instances will be delegated.
	 */
	public AbstractComposedSimilaritySwitch(ISimilarityRequestHandler srh) {
		super();
		this.srh = srh;
	}

	/**
	 * Constructs an instance with the given switches.
	 * 
	 * @see {@link AbstractComposedSwitchWrapper#AbstractComposedSwitchWrapper(Collection)}
	 */
	protected AbstractComposedSimilaritySwitch(ISimilarityRequestHandler srh, Collection<Switch<Boolean>> switches) {
		super(switches);
		this.srh = srh;
	}

	/**
	 * Constructs an instance with the given switches.
	 * 
	 * @see {@link AbstractComposedSwitchWrapper#AbstractComposedSwitchWrapper(Switch[])}
	 */
	protected AbstractComposedSimilaritySwitch(ISimilarityRequestHandler srh, Switch<Boolean>[] switches) {
		super(switches);
		this.srh = srh;
	}

	/**
	 * @return {@link #srh}
	 */
	protected ISimilarityRequestHandler getSimilarityRequestHandler() {
		return this.srh;
	}

	/**
	 * {@inheritDoc}
	 * <br><br>
	 * Here, it delegates the incoming {@link ISimilarityRequest} to its {@link #srh}.
	 */
	@Override
	public Object handleSimilarityRequest(ISimilarityRequest req) {
		return this.getSimilarityRequestHandler().handleSimilarityRequest(req);
	}

}

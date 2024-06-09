package org.splevo.jamopp.diffing.similarity.handlers;

import org.splevo.jamopp.diffing.similarity.JavaSimilaritySwitch;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequest;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequestHandler;
import org.splevo.jamopp.diffing.similarity.requests.NewSimilaritySwitchRequest;

/**
 * An {@link ISimilarityRequestHandler} that processes incoming
 * {@link NewSimilaritySwitchRequest} instances.
 * 
 * @author atora
 */
public class NewSimilaritySwitchHandler implements ISimilarityRequestHandler {
	/**
	 * The {@link ISimilarityRequestHandler}, which will be passed onto the
	 * similarity switches created in
	 * {@link #handleSimilarityRequest(ISimilarityRequest)}.
	 */
	private ISimilarityRequestHandler srh;

	/**
	 * Constructs an instance with the given {@link ISimilarityRequestHandler}.
	 * 
	 * @param srh {@link #srh}
	 */
	public NewSimilaritySwitchHandler(ISimilarityRequestHandler srh) {
		this.srh = srh;
	}

	/**
	 * {@inheritDoc} <br>
	 * <br>
	 * Constructs a new {@link JavaSimilaritySwitch} (with its {@link #srh}) and
	 * returns it.
	 */
	@Override
	public Object handleSimilarityRequest(ISimilarityRequest req) {
		NewSimilaritySwitchRequest castedR = (NewSimilaritySwitchRequest) req;
		Boolean csp = (Boolean) castedR.getParams();
		return new JavaSimilaritySwitch(this.srh, csp);
	}
}

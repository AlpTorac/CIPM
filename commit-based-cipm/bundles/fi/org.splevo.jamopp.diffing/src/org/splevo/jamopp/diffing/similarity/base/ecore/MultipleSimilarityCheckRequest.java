package org.splevo.jamopp.diffing.similarity.base.ecore;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequest;

/**
 * An {@link ISimilarityRequest} for checking the similarity of 2 lists of
 * {@link EObject} instances.
 * 
 * @author atora
 */
public class MultipleSimilarityCheckRequest implements ISimilarityRequest {
	private Collection<? extends EObject> elements1;
	private Collection<? extends EObject> elements2;
	private Collection<? extends IComposedSwitchWrapper> sss;

	/**
	 * Constructs a request that encapsulates 2 EObject lists and a list of
	 * switches.
	 * 
	 * @param elements1 The first element list.
	 * @param elements2 The second element list.
	 * @param sss       Switches that will be used for comparing the elements.
	 * 
	 * @see {@link MultipleSimilarityCheckHandler#handleSimilarityRequest(ISimilarityRequest)}
	 */
	public MultipleSimilarityCheckRequest(Collection<? extends EObject> elements1,
			Collection<? extends EObject> elements2, Collection<? extends IComposedSwitchWrapper> sss) {
		this.elements1 = elements1;
		this.elements2 = elements2;
		this.sss = sss;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @return { The first element list, The second element list, Switches that will
	 *         be used for comparing the elements }
	 * @see {@link #MultipleSimilarityCheckRequest(Collection, Collection, Collection)}
	 */
	@Override
	public Object getParams() {
		return new Object[] { this.elements1, this.elements2, this.sss };
	}
}

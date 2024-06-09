package cipm.consistency.commitintegration.diff.util.pcm;

import java.util.Collection;

import org.eclipse.emf.ecore.util.Switch;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequestHandler;
import org.splevo.jamopp.diffing.similarity.base.ecore.AbstractComposedSimilaritySwitch;

import cipm.consistency.commitintegration.diff.util.pcm.switches.SimilarityRepositorySwitch;
import cipm.consistency.commitintegration.diff.util.pcm.switches.SimilaritySeffSwitch;

/**
 * Concrete implementation of {@link AbstractComposedSimilaritySwitch} for
 * computing the similarity of Palladio Component Model (PCM) repositories.
 * 
 * @author atora
 */
public class PCMRepositorySimilaritySwitch extends AbstractComposedSimilaritySwitch implements IPCMRepositorySimilaritySwitch {
	/**
	 * Constructs an instance with the given request handler and the flag. Adds default
	 * inner switches to the constructed instance.
	 * 
	 * @param srh The request handler, to which all incoming {@link ISimilarityRequest}
	 * instances will be delegated.
	 * @param checkStatementPosition The flag, which denotes whether this switch should
	 * take positions of statements while comparing.
	 */
	public PCMRepositorySimilaritySwitch(ISimilarityRequestHandler srh, boolean checkStatementPosition) {
		super(srh);
		
		this.addSwitch(new SimilarityRepositorySwitch(this, checkStatementPosition));
		this.addSwitch(new SimilaritySeffSwitch(this, checkStatementPosition));
	}
	
    /**
     * @see {@link AbstractComposedSimilaritySwitch}
     * @see {@link IInnerSwitch}
     */
	protected PCMRepositorySimilaritySwitch(ISimilarityRequestHandler srh) {
		super(srh);
	}
	
    /**
     * @see {@link AbstractComposedSimilaritySwitch}
     */
    protected PCMRepositorySimilaritySwitch(ISimilarityRequestHandler srh, Collection<Switch<Boolean>> switches) {
    	super(srh, switches);
    }
    
    /**
     * @see {@link AbstractComposedSimilaritySwitch}
     */
    protected PCMRepositorySimilaritySwitch(ISimilarityRequestHandler srh, Switch<Boolean>[] switches) {
    	super(srh, switches);
    }
}
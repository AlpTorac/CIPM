package cipm.consistency.commitintegration.diff.util.pcm;

import java.util.Collection;

import org.eclipse.emf.ecore.util.Switch;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequestHandler;
import org.splevo.jamopp.diffing.similarity.base.ecore.AbstractComposedSimilaritySwitch;

import cipm.consistency.commitintegration.diff.util.pcm.switches.SimilarityRepositorySwitch;
import cipm.consistency.commitintegration.diff.util.pcm.switches.SimilaritySeffSwitch;

public class PCMRepositorySimilaritySwitch extends AbstractComposedSimilaritySwitch implements IPCMRepositorySimilaritySwitch {
	public PCMRepositorySimilaritySwitch(ISimilarityRequestHandler srh, boolean checkStatementPosition) {
		super(srh);
		
		this.addSwitch(new SimilarityRepositorySwitch(this, checkStatementPosition));
		this.addSwitch(new SimilaritySeffSwitch(this, checkStatementPosition));
	}
	
    protected PCMRepositorySimilaritySwitch(ISimilarityRequestHandler srh, Collection<Switch<Boolean>> switches) {
    	super(srh, switches);
    }
    
    protected PCMRepositorySimilaritySwitch(ISimilarityRequestHandler srh, Switch<Boolean>[] switches) {
    	super(srh, switches);
    }
}
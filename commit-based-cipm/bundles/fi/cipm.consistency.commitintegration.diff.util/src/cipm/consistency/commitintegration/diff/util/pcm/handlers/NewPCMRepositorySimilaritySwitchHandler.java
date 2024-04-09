package cipm.consistency.commitintegration.diff.util.pcm.handlers;

import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequest;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequestHandler;

import cipm.consistency.commitintegration.diff.util.pcm.PCMRepositorySimilaritySwitch;
import cipm.consistency.commitintegration.diff.util.pcm.requests.NewPCMRepositorySimilaritySwitchRequest;

public class NewPCMRepositorySimilaritySwitchHandler implements ISimilarityRequestHandler {
	private ISimilarityRequestHandler srh;
	
	public NewPCMRepositorySimilaritySwitchHandler(ISimilarityRequestHandler srh) {
		this.srh = srh;
	}
	
	@Override
	public PCMRepositorySimilaritySwitch handleSimilarityRequest(ISimilarityRequest req) {
		NewPCMRepositorySimilaritySwitchRequest castedR = (NewPCMRepositorySimilaritySwitchRequest) req;
		var csp = castedR.getParams();
		return new PCMRepositorySimilaritySwitch(this.srh, csp);
	}
}

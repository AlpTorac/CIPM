package cipm.consistency.commitintegration.diff.util.pcm.switches;

import org.splevo.jamopp.diffing.similarity.base.ecore.IPositionInnerSwitch;

import cipm.consistency.commitintegration.diff.util.pcm.IPCMRepositorySimilaritySwitch;
import cipm.consistency.commitintegration.diff.util.pcm.requests.NewPCMRepositorySimilaritySwitchRequest;

public interface IPCMPositionInnerSwitch extends IPCMInnerSwitch, IPositionInnerSwitch {
	public default IPCMRepositorySimilaritySwitch requestNewSwitch(boolean checkStatementPosition) {
		return (IPCMRepositorySimilaritySwitch) this.handleSimilarityRequest(
				new NewPCMRepositorySimilaritySwitchRequest(checkStatementPosition));
	}
}

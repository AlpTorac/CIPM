package cipm.consistency.vsum.test.changedetection;

import java.util.ArrayList;
import java.util.List;

import tools.vitruv.change.atomic.EChange;

public class CompositeChangeCandidate {
	private final List<EChange> changes = new ArrayList<>();
	private final String changeType;
	
	public CompositeChangeCandidate(String changeType) {
		this.changeType = changeType;
	}

	public String getChangeType() {
		return this.changeType;
	}

	public void addChange(EChange change) {
		changes.add(change);
	}

	public List<EChange> getChanges() {
		return List.copyOf(changes);
	}
}

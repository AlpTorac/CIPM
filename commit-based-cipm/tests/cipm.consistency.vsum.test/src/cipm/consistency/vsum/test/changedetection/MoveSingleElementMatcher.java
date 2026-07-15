package cipm.consistency.vsum.test.changedetection;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;

import tools.vitruv.change.atomic.EChange;
import tools.vitruv.change.atomic.feature.reference.AdditiveReferenceEChange;
import tools.vitruv.change.atomic.feature.reference.SubtractiveReferenceEChange;

public class MoveSingleElementMatcher implements ICompositeChangeMatcher {
	private List<EChange> changeSeq;

	private SubtractiveReferenceEChange sr;
	private AdditiveReferenceEChange ar;

	public MoveSingleElementMatcher(EChange sr, List<EChange> changeSeq) {
		this.changeSeq = changeSeq;
		if (sr instanceof SubtractiveReferenceEChange) {
			this.sr = (SubtractiveReferenceEChange) sr;
		}
	}

	public List<EChange> getAtomicChanges() {
		var list = new ArrayList<EChange>();
		if (sr != null)
			list.add(sr);
		if (ar != null)
			list.add(ar);
		return list;
	}

	public boolean match() {
		var idx = this.changeSeq.indexOf(sr);
		return idx + 1 < changeSeq.size() && matchAdditiveChange(changeSeq.get(idx + 1));
	}

	private boolean matchAdditiveChange(EChange ar) {
		if (ar instanceof AdditiveReferenceEChange) {
			var castedAR = (AdditiveReferenceEChange) ar;
			var typeAR = (EClass) castedAR.getAffectedFeature().getEType();
			var typeSR = (EClass) sr.getAffectedFeature().getEType();

			if (typeAR.isSuperTypeOf(typeSR) || typeSR.isSuperTypeOf(typeAR)) {
				this.ar = castedAR;
				return true;
			}
		}
		return false;
	}
}

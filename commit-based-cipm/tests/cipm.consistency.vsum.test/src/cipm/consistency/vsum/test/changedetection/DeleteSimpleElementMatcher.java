package cipm.consistency.vsum.test.changedetection;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;

import tools.vitruv.change.atomic.EChange;
import tools.vitruv.change.atomic.eobject.DeleteEObject;
import tools.vitruv.change.atomic.feature.reference.SubtractiveReferenceEChange;

public class DeleteSimpleElementMatcher implements ICompositeChangeMatcher {
	private List<EChange> changeSeq;

	private SubtractiveReferenceEChange sr;
	private DeleteEObject dc;

	public DeleteSimpleElementMatcher(EChange sr, List<EChange> changeSeq) {
		this.changeSeq = changeSeq;
		if (sr instanceof SubtractiveReferenceEChange) {
			this.sr = (SubtractiveReferenceEChange) sr;
		}
	}

	public List<EChange> getAtomicChanges() {
		var list = new ArrayList<EChange>();
		if (sr != null)
			list.add(sr);
		if (dc != null)
			list.add(dc);
		return list;
	}

	public boolean match() {
		var idx = this.changeSeq.indexOf(sr);
		return idx + 1 < changeSeq.size() && matchDeleteChange(changeSeq.get(idx + 1));
	}

	private boolean matchDeleteChange(EChange dc) {
		if (dc instanceof DeleteEObject) {
			var castedDC = (DeleteEObject) dc;
			var type = (EClass) sr.getAffectedFeature().getEType();

			if (castedDC.getAffectedEObjectID().equals(sr.getOldValueID())
					&& type.isSuperTypeOf(castedDC.getAffectedEObjectType())) {
				this.dc = castedDC;
				return true;
			}
		}
		return false;
	}
}

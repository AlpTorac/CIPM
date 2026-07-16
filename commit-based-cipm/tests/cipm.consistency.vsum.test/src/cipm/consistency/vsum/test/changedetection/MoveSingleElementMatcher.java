package cipm.consistency.vsum.test.changedetection;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.util.EcoreUtil;

import cipm.consistency.cpr.pcmjava.preprocessing.ChangeUtil;
import tools.vitruv.change.atomic.EChange;
import tools.vitruv.change.atomic.feature.reference.AdditiveReferenceEChange;
import tools.vitruv.change.atomic.feature.reference.SubtractiveReferenceEChange;

/**
 * 
 * 4 possibilities in total:
 * <ul>
 * <li>ReplaceSingleValuedEReference E newVal -> ReplaceSingleValuedEReference
 * oldVal E (E.ID is a cache ID)
 * <li>ReplaceSingleValuedEReference E newVal -> InsertEReference E (E.ID is a
 * cache ID)
 * <li>RemoveEReference E ->ReplaceSingleValuedEReference oldVal E (E.ID is a
 * cache ID)
 * <li>RemoveEReference E -> InsertEReference E (E.ID is a cache ID)
 * </ul>
 * 
 * => If no EObjects are present within EChanges (i.e. they are unresolved), the
 * only thing to check for is: (1) is a SubtractiveReferenceEChange followed by
 * a AdditiveReferenceEChange, (2) is AdditiveReferenceEChange.newValueID a
 * cache ID.
 * 
 * => Assume that E will be stored in the same feature, so that its purpose
 * remains the same and therefore moving it is less prone to side-effects
 * 
 * @author Alp Torac Genc
 */
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
			if (
			// Make sure that the affected features are the same
			EcoreUtil.equals(sr.getAffectedFeature(), castedAR.getAffectedFeature()) &&
			// Check whether the newValueID is a cache ID
					castedAR.getNewValueID() != null && ChangeUtil.isCacheURI(castedAR.getNewValueID())) {
				this.ar = castedAR;
				return true;
			}
		}
		return false;
	}
}

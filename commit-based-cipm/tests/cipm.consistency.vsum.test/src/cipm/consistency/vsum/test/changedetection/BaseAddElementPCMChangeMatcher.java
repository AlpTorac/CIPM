package cipm.consistency.vsum.test.changedetection;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;

import de.uka.ipd.sdq.identifier.IdentifierPackage;
import tools.vitruv.change.atomic.EChange;
import tools.vitruv.change.atomic.eobject.CreateEObject;
import tools.vitruv.change.atomic.feature.attribute.ReplaceSingleValuedEAttribute;
import tools.vitruv.change.atomic.feature.reference.AdditiveReferenceEChange;

public class BaseAddElementPCMChangeMatcher implements ICompositeChangeMatcher {
	private List<EChange> changeSeq;

	private CreateEObject cc;
	private AdditiveReferenceEChange ar;
	private ReplaceSingleValuedEAttribute rsva;

	public BaseAddElementPCMChangeMatcher(EChange cc, List<EChange> changeSeq) {
		this.changeSeq = changeSeq;
		if (cc instanceof CreateEObject) {
			this.cc = (CreateEObject) cc;
		}
	}

	public EClass getAddedElementType() {
		return cc != null ? cc.getAffectedEObjectType() : null;
	}

	public List<EChange> getAtomicChanges() {
		var list = new ArrayList<EChange>();
		if (cc != null)
			list.add(cc);
		if (ar != null)
			list.add(ar);
		if (rsva != null)
			list.add(rsva);
		return list;
	}

	public boolean match() {
		var idx = this.changeSeq.indexOf(cc);
		return idx + 2 < changeSeq.size() && matchAdditiveChange(changeSeq.get(idx + 1))
				&& matchIDChange(changeSeq.get(idx + 2));
	}

	private boolean matchAdditiveChange(EChange ar) {
		if (ar instanceof AdditiveReferenceEChange) {
			var castedAR = (AdditiveReferenceEChange) ar;
			var type = (EClass) castedAR.getAffectedFeature().getEType();

			if (castedAR.getNewValueID().equals(cc.getAffectedEObjectID())
					&& type.isSuperTypeOf(cc.getAffectedEObjectType())) {
				this.ar = castedAR;
				return true;
			}
		}
		return false;
	}

	private boolean matchIDChange(EChange rsva) {
		if (rsva instanceof ReplaceSingleValuedEAttribute) {
			var castedRSVA = (ReplaceSingleValuedEAttribute) rsva;
			if (castedRSVA.getAffectedFeature().getName().equals(IdentifierPackage.Literals.IDENTIFIER__ID.getName())
					&& castedRSVA.getNewValue().equals(cc.getIdAttributeValue())) {
				this.rsva = castedRSVA;
				return true;
			}
		}
		return false;
	}
}

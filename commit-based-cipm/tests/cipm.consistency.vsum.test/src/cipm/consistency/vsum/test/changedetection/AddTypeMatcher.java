package cipm.consistency.vsum.test.changedetection;

import java.util.List;

import org.palladiosimulator.pcm.core.entity.EntityPackage;
import org.palladiosimulator.pcm.repository.RepositoryPackage;

import tools.vitruv.change.atomic.EChange;
import tools.vitruv.change.atomic.eobject.CreateEObject;
import tools.vitruv.change.atomic.feature.attribute.ReplaceSingleValuedEAttribute;

public class AddTypeMatcher implements ICompositeChangeMatcher {
	private List<EChange> changeSeq;

	private BaseAddElementChangeMatcher dataTypeCreation;
	private ReplaceSingleValuedEAttribute renameDataType;
	private BaseAddElementChangeMatcher interfaceCreation;
	private ReplaceSingleValuedEAttribute renameInterface;

	private int idx;

	public AddTypeMatcher(EChange cc, List<EChange> changeSeq) {
		this.changeSeq = changeSeq;

		if (cc instanceof CreateEObject) {
			idx = changeSeq.indexOf(cc);
		} else {
			idx = -1;
		}
	}

	@Override
	public boolean match() {
		if (idx == -1) {
			return false;
		}

		// Attempt to get the first element creation change, if it fails, no valid
		// change candidate
		if (!matchDataTypeCreation() && !matchInterfaceCreation()) {
			return false;
		}

		// First match successful
		var firstMatch = dataTypeCreation != null ? dataTypeCreation : interfaceCreation;
		idx = idx + firstMatch.getAtomicChanges().size();

		if (!lookForNameChange(firstMatch)) {
			return false;
		}

		// Name change found
		idx++;

		// Attempt to get the second element creation change
		while (idx < changeSeq.size() && (dataTypeCreation == null || interfaceCreation == null)) {
			if (matchDataTypeCreation() || matchInterfaceCreation()) {
				// Second match successful too
				var secondMatch = firstMatch == dataTypeCreation ? interfaceCreation : dataTypeCreation;
				idx = idx + secondMatch.getAtomicChanges().size();

				if (lookForNameChange(secondMatch)) {
					return true;
				}
			}
			idx++;
		}

		return false;
	}

	private boolean lookForNameChange(BaseAddElementChangeMatcher match) {
		while (!matchNameSet(match) && idx < changeSeq.size()) {
			idx++;
		}
		return idx < changeSeq.size();
	}

	private boolean matchDataTypeCreation() {
		if (dataTypeCreation != null)
			return false;

		var addElem = new BaseAddElementChangeMatcher(changeSeq.get(idx), changeSeq);
		if (addElem.match()) {
			var type = addElem.getAddedElementType();
			if (RepositoryPackage.Literals.DATA_TYPE.isSuperTypeOf(type)) {
				this.dataTypeCreation = addElem;
				return true;
			}
		}
		return false;
	}

	private boolean matchInterfaceCreation() {
		if (interfaceCreation != null)
			return false;

		var addElem = new BaseAddElementChangeMatcher(changeSeq.get(idx), changeSeq);
		if (addElem.match()) {
			var type = addElem.getAddedElementType();
			if (RepositoryPackage.Literals.INTERFACE.isSuperTypeOf(type)) {
				this.interfaceCreation = addElem;
				return true;
			}
		}
		return false;
	}

	private boolean matchNameSet(BaseAddElementChangeMatcher match) {
		if (idx >= changeSeq.size()) {
			return false;
		}

		var change = changeSeq.get(idx);
		if (change instanceof ReplaceSingleValuedEAttribute) {
			var castedC = ((ReplaceSingleValuedEAttribute) change);
			if (castedC.getAffectedFeature().equals(EntityPackage.Literals.NAMED_ELEMENT__ENTITY_NAME)) {
				var name = (String) castedC.getNewValue();
				if (doNamesMatch(name)) {
					if (dataTypeCreation == match) {
						renameDataType = castedC;
					} else {
						renameInterface = castedC;
					}
					return true;
				}
			}
		}

		return false;
	}

	private boolean doNamesMatch(String name) {
		return (renameDataType == null || name.equals(renameDataType.getNewValue()))
				&& (renameInterface == null || name.equals(renameInterface.getNewValue()));
	}
}

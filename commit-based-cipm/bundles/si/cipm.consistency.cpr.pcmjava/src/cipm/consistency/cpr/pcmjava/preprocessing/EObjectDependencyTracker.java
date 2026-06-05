package cipm.consistency.cpr.pcmjava.preprocessing;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.palladiosimulator.pcm.core.entity.EntityPackage;

import de.uka.ipd.sdq.identifier.IdentifierPackage;
import tools.vitruv.change.atomic.EChange;
import tools.vitruv.change.atomic.eobject.CreateEObject;
import tools.vitruv.change.atomic.eobject.DeleteEObject;
import tools.vitruv.change.atomic.eobject.EObjectExistenceEChange;
import tools.vitruv.change.atomic.eobject.EobjectPackage;
import tools.vitruv.change.atomic.feature.FeaturePackage;
import tools.vitruv.change.atomic.feature.UnsetFeature;
import tools.vitruv.change.atomic.feature.attribute.InsertEAttributeValue;
import tools.vitruv.change.atomic.feature.attribute.RemoveEAttributeValue;
import tools.vitruv.change.atomic.feature.attribute.ReplaceSingleValuedEAttribute;
import tools.vitruv.change.atomic.feature.list.UpdateSingleListEntryEChange;
import tools.vitruv.change.atomic.feature.reference.InsertEReference;
import tools.vitruv.change.atomic.feature.reference.RemoveEReference;
import tools.vitruv.change.atomic.feature.reference.ReplaceSingleValuedEReference;
import tools.vitruv.change.atomic.root.InsertRootEObject;
import tools.vitruv.change.atomic.root.RemoveRootEObject;
import tools.vitruv.change.atomic.root.RootEChange;

/**
 * Assumes that the given change sequence is valid and resembles the change
 * sequences that are computed by the state-based model comparison:
 * <ul>
 * <li>Staging area of Resource are only trivially used: The only allowed cache
 * URI is "cache:/0"
 * </ul>
 */
public class EObjectDependencyTracker {
	private Resource resource;
	private String resourceUri;
//	private EObject replacedEObject;
	private EClass eobjectType;

	private IDAdjustingStrategy idStrat;

	/**
	 * The change sequence to operate on
	 */
	private List<EChange> changeSequence;

	/**
	 * The current ID of the wrapped EObject as the wrapped EObject is being used as
	 * a placeholder.
	 */
	private String currentID;

	/**
	 * ID of the wrapped EObject in individual changes. Note that the ID can change
	 * after the individual changes happen, hence {@link #currentID} is used.
	 */
	private final Map<EChange, String> changeToIDMap = new LinkedHashMap<>();
//	/**
//	 * Index of the wrapped EObject in individual changes
//	 */
//	private final Map<EChange, Integer> changeToIndexMap = new LinkedHashMap<>();
	/**
	 * The feature of the individual changes that hold the wrapped EObject
	 */
	private final Map<EChange, EStructuralFeature> changeToFeatMap = new LinkedHashMap<>();

	public EObjectDependencyTracker() {
		super();
		/*
		 * TODO Alternative (potentially easier) approach, similar idea as RIS model
		 * integration:
		 * 
		 * Consider utilising parts of
		 * ChangePreprocessingTestAssertions.assertChangeSequencesHaveSameEffect:
		 * 
		 * Pass a Resource instance to this EObjectWrapper, perform the relevant changes
		 * to the resource one by one, track all the attributes of EObjects
		 * 
		 * Note: May prove counter-intuitive, since the idea is to pre-process without a
		 * "preview" / "pre-computation of the model"
		 */
	}

	public Resource getResource() {
		return resource;
	}

	public String getResourceUri() {
		return resourceUri;
	}

//	public EObject getReplacedEObject() {
//		return replacedEObject;
//	}

	public EClass getEobjectType() {
		return eobjectType;
	}

	public IDAdjustingStrategy getIdStrat() {
		return idStrat;
	}

	public List<EChange> getChangeSequence() {
		return changeSequence;
	}

	public void setIDAdjustingStrategy(IDAdjustingStrategy idStrat) {
		this.idStrat = idStrat;
	}

	public String getIDInChange(EChange change) {
		return this.changeToIDMap.getOrDefault(change, null);
	}

	public EStructuralFeature getContainingFeatInChange(EChange change) {
		return this.changeToFeatMap.getOrDefault(change, null);
	}

//	public Integer getIndexInChange(EChange change) {
//		return this.changeToIndexMap.getOrDefault(change, null);
//	}

	/**
	 * Marks the first occurrence (within the changeSequence) of the EObject to be
	 * wrapped. Assumes that the EObject to be wrapped does not occur prior to the
	 * given EChange, and that this EObjectWrapper will have adjusted the
	 * changeSequence, such that the EObject's occurrences are clear.
	 * <p>
	 * <p>
	 * Sets the fields of this class accordingly.
	 * 
	 * @param changeSequence The complete sequence of changes the given change is in
	 * @param initialChange  The initial change that is the first occurrence of the
	 *                       EObject
	 * @param changeFeat     The EObject in the change, which this will replace
	 */
	public void setInitialChange(List<EChange> changeSequence, EChange initialChange, EStructuralFeature changeFeat) {
		if (!changeSequence.contains(initialChange))
			throw new IllegalArgumentException("The given change sequence does not contain the given change");

		this.changeSequence = new ArrayList<>(changeSequence);

		// No need to add if-blocks for these attributes, since they will be null if
		// change is not eligible
		setEObjectType(initialChange);
		setResource(initialChange);

		reportChangeToFeatMapping(initialChange, changeFeat);

		if (changeFeat == EobjectPackage.Literals.EOBJECT_EXISTENCE_ECHANGE__AFFECTED_EOBJECT) {
			// Created / Deleted EObject
//			this.replacedEObject = ChangeUtil.getAffectedEObject(initialChange);
			reportChangeToIDMapping(initialChange, ChangeUtil.getAffectedEObjectID(initialChange));
		}
		if (changeFeat == FeaturePackage.Literals.FEATURE_ECHANGE__AFFECTED_EOBJECT) {
			// EObject whose feature is changed
//			this.replacedEObject = ChangeUtil.getAffectedEObject(initialChange);
			reportChangeToIDMapping(initialChange, ChangeUtil.getAffectedEObjectID(initialChange));
		}
		if (changeFeat == EobjectPackage.Literals.EOBJECT_ADDED_ECHANGE__NEW_VALUE) {
			// EObject as new value to an EReference
//			this.replacedEObject = (EObject) ChangeUtil.getNewValue(initialChange);
			reportChangeToIDMapping(initialChange, ChangeUtil.getNewValueID(initialChange));
		}
		if (changeFeat == EobjectPackage.Literals.EOBJECT_SUBTRACTED_ECHANGE__OLD_VALUE) {
			// EObject as old value of an EReference
//			this.replacedEObject = (EObject) ChangeUtil.getOldValue(initialChange);
			reportChangeToIDMapping(initialChange, ChangeUtil.getOldValueID(initialChange));
		}

		this.currentID = this.changeToIDMap.get(initialChange);

		// Get root indices, i.e. at which index in a Resource the EObject is to be put
		if (initialChange instanceof RootEChange) {
//			reportChangeToIndexMapping(initialChange, ChangeUtil.getRootIndex(initialChange));
		}

		// Get the list index, i.e. at which index in an EList the EObject is to be put
		if (initialChange instanceof UpdateSingleListEntryEChange) {
//			reportChangeToIndexMapping(initialChange, ChangeUtil.getIndex(initialChange));
		}

		trackDependencies(initialChange, changeFeat);
	}

	private void setResource(EChange change) {
		this.resourceUri = ChangeUtil.getRootChangeURI(change);
		this.resource = ChangeUtil.getRootChangeResource(change);
	}

	private void setEObjectType(EChange change) {
		this.eobjectType = ChangeUtil.getEObjectType(change);
	}
	
	/**
	 * Identify occurrences of the same EObject this wrapper is supposed to wrap,
	 * then put the wrapper into the individual changes.
	 */
	private void trackDependencies(EChange initialChange, EStructuralFeature changeFeat) {
		var changeIdxInSequence = this.changeSequence.indexOf(initialChange);

		// Adjust following EChanges
		for (int i = changeIdxInSequence + 1; i < this.changeSequence.size(); i++) {
			var currentChange = this.changeSequence.get(i);
			if (!trackDependencies(currentChange)) return;
			if (!this.changeToIDMap.containsKey(currentChange))
				reportChangeToIDMapping(currentChange, currentID);
		}
	}

	private boolean trackDependencies(EChange change) {
		if (change instanceof CreateEObject)
			return trackDependencies((CreateEObject<?>) change);
		else if (change instanceof DeleteEObject)
			return trackDependencies((DeleteEObject<?>) change);
		else if (change instanceof InsertRootEObject)
			return trackDependencies((InsertRootEObject<?>) change);
		else if (change instanceof RemoveRootEObject)
			return trackDependencies((RemoveRootEObject<?>) change);
		else if (change instanceof UnsetFeature)
			return trackDependencies((UnsetFeature<?, ?>) change);
		else if (change instanceof InsertEAttributeValue)
			return trackDependencies((InsertEAttributeValue<?, ?>) change);
		else if (change instanceof RemoveEAttributeValue)
			return trackDependencies((RemoveEAttributeValue<?, ?>) change);
		else if (change instanceof InsertEReference)
			return trackDependencies((InsertEReference<?, ?>) change);
		else if (change instanceof RemoveEReference)
			return trackDependencies((RemoveEReference<?, ?>) change);
		else if (change instanceof ReplaceSingleValuedEAttribute)
			return trackDependencies((ReplaceSingleValuedEAttribute<?, ?>) change);
		else if (change instanceof ReplaceSingleValuedEReference)
			return trackDependencies((ReplaceSingleValuedEReference<?, ?>) change);
		else {
			throw new IllegalArgumentException("Unknown change type: " + change.getClass().getName());
		}
	}

	private void reportChangeToFeatMapping(EChange change, EStructuralFeature feat) {
		this.changeToFeatMap.put(change, feat);
	}

	private void reportChangeToIDMapping(EChange change, String idInChange) {
		this.changeToIDMap.put(change, idInChange);
	}

//	private void reportChangeToIndexMapping(EChange change, int index) {
//		this.changeToIndexMap.put(change, index);
//	}

	private boolean trackDependencies(CreateEObject<?> change) {
		if (change.getAffectedEObjectID().equals(this.currentID)) {
			// Creates object: Existential Affected ID
			setEObjectType(change);
			reportChangeToFeatMapping(change, EobjectPackage.Literals.EOBJECT_EXISTENCE_ECHANGE__AFFECTED_EOBJECT);
			reportChangeToIDMapping(change, this.currentID);
			this.currentID = idStrat.moveToStagedArea(currentID);
		}
		return true;
	}

	private boolean trackDependencies(DeleteEObject<?> change) {
		if (change.getAffectedEObjectID().equals(this.currentID)) {
			// Deletes object: Existential Affected ID
			reportChangeToFeatMapping(change, EobjectPackage.Literals.EOBJECT_EXISTENCE_ECHANGE__AFFECTED_EOBJECT);
			reportChangeToIDMapping(change, this.currentID);
			this.currentID = idStrat.deleteFromStagedArea(currentID);
			return false;
		}
		return true;
	}

	private boolean trackDependencies(InsertRootEObject<?> change) {
		if (change.getNewValueID().equals(this.currentID)) {
			// Inserts object to resource: From newID (cache:/X) to resourceURI/idx
			// TODO Properly append the index
			setResource(change);
			reportChangeToFeatMapping(change, EobjectPackage.Literals.EOBJECT_ADDED_ECHANGE__NEW_VALUE);
			reportChangeToIDMapping(change, this.currentID);
//			reportChangeToIndexMapping(change, change.getIndex());
			this.currentID = idStrat.addToResource(change.getUri(), change.getIndex());
		} else if (change.getNewValueID().startsWith(this.currentID)) {
			// Inserts another object to resource at smaller index: From resourceURI/idx to
			// resourceURI/idx+1
			// TODO Properly adjust the index in currentID
		}
		return true;
	}

	private boolean trackDependencies(RemoveRootEObject<?> change) {
		if (change.getOldValueID().equals(this.currentID)) {
			// Removes object from resource: From oldID (resourceURI/idx) to cache:/X
			setResource(change);
			reportChangeToFeatMapping(change, EobjectPackage.Literals.EOBJECT_SUBTRACTED_ECHANGE__OLD_VALUE);
			reportChangeToIDMapping(change, this.currentID);
//			reportChangeToIndexMapping(change, change.getIndex());
			// TODO Properly append the index
			this.currentID = idStrat.moveToStagedArea(currentID);
		} else if (modifiesSmallerIndexInCommonParent(change.getOldValueID())) {
			// Removes another object from resource at a smaller index: From resourceURI/idx
			// to resourceURI/idx-1
			// TODO Properly adjust the index in currentID
		}
		return true;
	}

	private boolean modifiesSmallerIndexInCommonParent(String targetID) {
		// this.currentID.removeIndex.startsWith(change.getOldValueID())
		// TODO Remove the index from currentID during comparison
		// TODO Implement
		// TODO Make sure to null check this.currentID
		return false;
	}

	private boolean trackDependencies(UnsetFeature<?, ?> change) {
		if (change.getAffectedEObjectID().equals(this.currentID)) {
			// Unsets object's feature: Affected ID
			reportChangeToFeatMapping(change, FeaturePackage.Literals.FEATURE_ECHANGE__AFFECTED_EOBJECT);
			reportChangeToIDMapping(change, this.currentID);
		}
		return true;
	}

	private boolean trackDependencies(InsertEAttributeValue<?, ?> change) {
		if (change.getAffectedEObjectID().equals(this.currentID)) {
			// Inserts a primitive to object's feature: Affected ID
			reportChangeToFeatMapping(change, FeaturePackage.Literals.FEATURE_ECHANGE__AFFECTED_EOBJECT);
			reportChangeToIDMapping(change, this.currentID);
		}
		return true;
	}

	private boolean trackDependencies(RemoveEAttributeValue<?, ?> change) {
		if (change.getAffectedEObjectID().equals(this.currentID)) {
			// Removes a primitive to object's feature: Affected ID
			reportChangeToFeatMapping(change, FeaturePackage.Literals.FEATURE_ECHANGE__AFFECTED_EOBJECT);
			reportChangeToIDMapping(change, this.currentID);
		}
		return true;
	}

	private boolean trackDependencies(InsertEReference<?, ?> change) {
		if (change.getNewValueID().equals(this.currentID)) {
			// Inserts object to parent: From newID to parent.feat/idx
			reportChangeToFeatMapping(change, EobjectPackage.Literals.EOBJECT_ADDED_ECHANGE__NEW_VALUE);
			reportChangeToIDMapping(change, this.currentID);
//			reportChangeToIndexMapping(change, change.getIndex());
			// TODO Properly append the index
			this.currentID = idStrat.insertIntoFeature(change.getAffectedEObjectID(), change.getAffectedFeature(),
					change.getIndex());
		} else if (change.getAffectedEObjectID().equals(this.currentID)) {
			// Inserts another object to object's feature: Affected ID
			reportChangeToFeatMapping(change, FeaturePackage.Literals.FEATURE_ECHANGE__AFFECTED_EOBJECT);
			reportChangeToIDMapping(change, this.currentID);
		} else if (modifiesSmallerIndexInCommonParent(change.getNewValueID())) {
			// Inserts another object to parent at smaller index: From parent.feat/idx to
			// parent.feat/idx+1
			// TODO Properly adjust the index
		}
		return true;
	}

	private boolean trackDependencies(RemoveEReference<?, ?> change) {
		if (change.getOldValueID().equals(this.currentID)) {
			// Removes object from parent: From oldID to cache:/X
			reportChangeToFeatMapping(change, EobjectPackage.Literals.EOBJECT_SUBTRACTED_ECHANGE__OLD_VALUE);
			reportChangeToIDMapping(change, this.currentID);
//			reportChangeToIndexMapping(change, change.getIndex());
			this.currentID = idStrat.moveToStagedArea(currentID);
		} else if (change.getAffectedEObjectID().equals(this.currentID)) {
			// Removes another object from object's feature: Affected ID
			reportChangeToFeatMapping(change, FeaturePackage.Literals.FEATURE_ECHANGE__AFFECTED_EOBJECT);
			reportChangeToIDMapping(change, this.currentID);
		} else if (modifiesSmallerIndexInCommonParent(change.getOldValueID())) {
			// Removes another object from parent at smaller index: From parent.feat/idx to
			// parent.feat/idx-1
			// TODO Properly adjust the index
		}
		return true;
	}

	private boolean trackDependencies(ReplaceSingleValuedEAttribute<?, ?> change) {
		if (change.getAffectedEObjectID().equals(this.currentID)) {
			// Sets objects's feature: Affected ID
			reportChangeToFeatMapping(change, FeaturePackage.Literals.FEATURE_ECHANGE__AFFECTED_EOBJECT);
			reportChangeToIDMapping(change, this.currentID);
			// FIXME Enable once fixed
//			if (change.getAffectedFeature() == IdentifierPackage.Literals.IDENTIFIER__ID) {
//				// Sets object's ID
//				this.currentID = (String) change.getNewValue();
//			}
		}
		return true;
	}

	private boolean trackDependencies(ReplaceSingleValuedEReference<?, ?> change) {
		if (change.getNewValueID().equals(this.currentID)) {
			// Sets object as parent's feature: From newID to parent.feat
			reportChangeToFeatMapping(change, EobjectPackage.Literals.EOBJECT_ADDED_ECHANGE__NEW_VALUE);
			reportChangeToIDMapping(change, this.currentID);
			// TODO Properly append feature name
			this.currentID = idStrat.setAsValueOfFeature(change.getAffectedEObjectID(), change.getAffectedFeature());
		} else if (change.getOldValueID().equals(this.currentID)) {
			// Unsets object as parent's feature: From oldID to cache:/X
			reportChangeToFeatMapping(change, EobjectPackage.Literals.EOBJECT_SUBTRACTED_ECHANGE__OLD_VALUE);
			reportChangeToIDMapping(change, this.currentID);
			this.currentID = idStrat.moveToStagedArea(currentID);
		} else if (change.getAffectedEObjectID().equals(this.currentID)) {
			// Sets another object as object's feature: Affected ID
			reportChangeToFeatMapping(change, FeaturePackage.Literals.FEATURE_ECHANGE__AFFECTED_EOBJECT);
			reportChangeToIDMapping(change, this.currentID);
		}
		return true;
	}

//	private String getIDForChange(EChange change) {
//		if (!changeSequence.contains(change))
//			return null;
//
//		if (changeToIDMap.containsKey(change))
//			return changeToIDMap.get(change);
//
//		var mostRecentIDAffectingChange = getPreviousMostRecentIDAffectingChange(change);
//		if (mostRecentIDAffectingChange != null)
//			return changeToIDMap.get(mostRecentIDAffectingChange);
//
//		return null;
//	}
//
//	private EChange getPreviousMostRecentIDAffectingChange(EChange change) {
//		var changeIdx = changeSequence.indexOf(change);
//		for (int i = changeIdx - 1; i > -1; i--) {
//			var currentChange = changeSequence.get(i);
//			if (changeToIDMap.containsKey(currentChange))
//				return currentChange;
//		}
//		return null;
//	}
}

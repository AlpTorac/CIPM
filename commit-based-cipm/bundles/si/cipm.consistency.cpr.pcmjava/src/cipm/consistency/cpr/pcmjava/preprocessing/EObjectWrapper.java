package cipm.consistency.cpr.pcmjava.preprocessing;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;

import tools.vitruv.change.atomic.EChange;
import tools.vitruv.change.atomic.eobject.CreateEObject;
import tools.vitruv.change.atomic.eobject.DeleteEObject;
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
public class EObjectWrapper extends EObjectImpl {
	private static final String cachedEObjectURIPrefix = "cache:/";
	private static final String cachedEObjectURI = cachedEObjectURIPrefix + 0;

	private Resource resource;
	private String resourceUri;
	private EObject replacedEObject;
	private EClass eobjectType;

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
	/**
	 * Index of the wrapped EObject in individual changes
	 */
	private final Map<EChange, Integer> changeToIndexMap = new LinkedHashMap<>();
	/**
	 * The feature of the individual changes that hold the wrapped EObject
	 */
	private final Map<EChange, EStructuralFeature> changeToFeatMap = new LinkedHashMap<>();

	public EObjectWrapper() {
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
		this.eobjectType = ChangeUtil.getEObjectType(initialChange);
		this.resourceUri = ChangeUtil.getRootChangeURI(initialChange);
		this.resource = ChangeUtil.getRootChangeResource(initialChange);

		this.changeToFeatMap.put(initialChange, changeFeat);

		if (changeFeat == EobjectPackage.Literals.EOBJECT_EXISTENCE_ECHANGE__AFFECTED_EOBJECT) {
			// Created / Deleted EObject
			this.replacedEObject = ChangeUtil.getAffectedEObject(initialChange);
			this.changeToIDMap.put(initialChange, ChangeUtil.getAffectedEObjectID(initialChange));
		}
		if (changeFeat == FeaturePackage.Literals.FEATURE_ECHANGE__AFFECTED_EOBJECT) {
			// EObject whose feature is changed
			this.replacedEObject = ChangeUtil.getAffectedEObject(initialChange);
			this.changeToIDMap.put(initialChange, ChangeUtil.getAffectedEObjectID(initialChange));
		}
		if (changeFeat == EobjectPackage.Literals.EOBJECT_ADDED_ECHANGE__NEW_VALUE) {
			// EObject as new value to an EReference
			this.replacedEObject = (EObject) ChangeUtil.getNewValue(initialChange);
			this.changeToIDMap.put(initialChange, ChangeUtil.getNewValueID(initialChange));
		}
		if (changeFeat == EobjectPackage.Literals.EOBJECT_SUBTRACTED_ECHANGE__OLD_VALUE) {
			// EObject as old value of an EReference
			this.replacedEObject = (EObject) ChangeUtil.getOldValue(initialChange);
			this.changeToIDMap.put(initialChange, ChangeUtil.getOldValueID(initialChange));
		}

		this.currentID = this.changeToIDMap.get(initialChange);

		// Get root indices, i.e. at which index in a Resource the EObject is to be put
		if (initialChange instanceof RootEChange) {
			this.changeToIndexMap.put(initialChange, ChangeUtil.getRootIndex(initialChange));
		}

		// Get the list index, i.e. at which index in an EList the EObject is to be put
		if (initialChange instanceof UpdateSingleListEntryEChange) {
			this.changeToIndexMap.put(initialChange, ChangeUtil.getIndex(initialChange));
		}

		replaceEObjectWithWrapperIn(initialChange, changeFeat);
		adjustForChange(initialChange, changeFeat);
	}

	private void replaceEObjectWithWrapperIn(EChange change, EStructuralFeature changeFeat) {
		change.eSet(changeFeat, this);
	}

	public void undoWrapper() {
		// TODO Change the EChanges back
	}

	/**
	 * Identify occurrences of the same EObject this wrapper is supposed to wrap,
	 * then put the wrapper into the individual changes.
	 */
	private void adjustForChange(EChange initialChange, EStructuralFeature changeFeat) {
		var changeIdxInSequence = this.changeSequence.indexOf(initialChange);

		// Adjust following EChanges
		for (int i = changeIdxInSequence + 1; i < this.changeSequence.size(); i++) {
			var currentChange = this.changeSequence.get(i);
			adjustForChangeSequence(currentChange);
		}
	}

	private void adjustForChangeSequence(EChange change) {
		if (change instanceof CreateEObject)
			adjustForChangeSequence((CreateEObject<?>) change);
		else if (change instanceof DeleteEObject)
			adjustForChangeSequence((DeleteEObject<?>) change);
		else if (change instanceof InsertRootEObject)
			adjustForChangeSequence((InsertRootEObject<?>) change);
		else if (change instanceof RemoveRootEObject)
			adjustForChangeSequence((RemoveRootEObject<?>) change);
		else if (change instanceof UnsetFeature)
			adjustForChangeSequence((UnsetFeature<?, ?>) change);
		else if (change instanceof InsertEAttributeValue)
			adjustForChangeSequence((InsertEAttributeValue<?, ?>) change);
		else if (change instanceof RemoveEAttributeValue)
			adjustForChangeSequence((RemoveEAttributeValue<?, ?>) change);
		else if (change instanceof InsertEReference)
			adjustForChangeSequence((InsertEReference<?, ?>) change);
		else if (change instanceof RemoveEReference)
			adjustForChangeSequence((RemoveEReference<?, ?>) change);
		else if (change instanceof ReplaceSingleValuedEAttribute)
			adjustForChangeSequence((ReplaceSingleValuedEAttribute<?, ?>) change);
		else if (change instanceof ReplaceSingleValuedEReference)
			adjustForChangeSequence((ReplaceSingleValuedEReference<?, ?>) change);
		else {
			throw new IllegalArgumentException("Unknown change type: " + change.getClass().getName());
		}
	}

	private void adjustForChangeSequence(CreateEObject<?> change) {
		if (change.getAffectedEObjectID().equals(this.currentID)) {
			// Creates object: Existential Affected ID
			replaceEObjectWithWrapperIn(change, EobjectPackage.Literals.EOBJECT_EXISTENCE_ECHANGE__AFFECTED_EOBJECT);
			this.currentID = cachedEObjectURI;
		}
	}

	private void adjustForChangeSequence(DeleteEObject<?> change) {
		if (change.getAffectedEObjectID().equals(this.currentID)) {
			// Deletes object: Existential Affected ID
			replaceEObjectWithWrapperIn(change, EobjectPackage.Literals.EOBJECT_EXISTENCE_ECHANGE__AFFECTED_EOBJECT);
			this.currentID = null;
		}
	}

	private void adjustForChangeSequence(InsertRootEObject<?> change) {
		if (change.getNewValueID().equals(this.currentID)) {
			// Inserts object to resource: From newID (cache:/X) to resourceURI/idx
			replaceEObjectWithWrapperIn(change, EobjectPackage.Literals.EOBJECT_ADDED_ECHANGE__NEW_VALUE);
			// TODO Properly append the index
			this.currentID = change.getUri() + change.getIndex();
		} else if (change.getNewValueID().startsWith(this.currentID)) {
			// Inserts another object to resource at smaller index: From resourceURI/idx to
			// resourceURI/idx+1
			// TODO Properly adjust the index in currentID
		}
	}

	private void adjustForChangeSequence(RemoveRootEObject<?> change) {
		if (change.getOldValueID().equals(this.currentID)) {
			// Removes object from resource: From oldID (resourceURI/idx) to cache:/X
			replaceEObjectWithWrapperIn(change, EobjectPackage.Literals.EOBJECT_SUBTRACTED_ECHANGE__OLD_VALUE);
			// TODO Properly append the index
			this.currentID = cachedEObjectURI;
		} else if (modifiesSmallerIndexInCommonParent(change.getOldValueID())) {
			// Removes another object from resource at a smaller index: From resourceURI/idx
			// to resourceURI/idx-1
			// TODO Properly adjust the index in currentID
		}
	}

	private boolean modifiesSmallerIndexInCommonParent(String targetID) {
		// this.currentID.removeIndex.startsWith(change.getOldValueID())
		// TODO Remove the index from currentID during comparison
		// TODO Implement
		// TODO Make sure to null check this.currentID
		return false;
	}

	private void adjustForChangeSequence(UnsetFeature<?, ?> change) {
		if (change.getAffectedEObjectID().equals(this.currentID)) {
			// Unsets object's feature: Affected ID
			replaceEObjectWithWrapperIn(change, FeaturePackage.Literals.FEATURE_ECHANGE__AFFECTED_EOBJECT);
		}
	}

	private void adjustForChangeSequence(InsertEAttributeValue<?, ?> change) {
		if (change.getAffectedEObjectID().equals(this.currentID)) {
			// Inserts a primitive to object's feature: Affected ID
			replaceEObjectWithWrapperIn(change, FeaturePackage.Literals.FEATURE_ECHANGE__AFFECTED_EOBJECT);
		}
	}

	private void adjustForChangeSequence(RemoveEAttributeValue<?, ?> change) {
		if (change.getAffectedEObjectID().equals(this.currentID)) {
			// Removes a primitive to object's feature: Affected ID
			replaceEObjectWithWrapperIn(change, FeaturePackage.Literals.FEATURE_ECHANGE__AFFECTED_EOBJECT);
		}
	}

	private void adjustForChangeSequence(InsertEReference<?, ?> change) {
		if (change.getNewValueID().equals(this.currentID)) {
			// Inserts object to parent: From newID to parent.feat/idx
			replaceEObjectWithWrapperIn(change, EobjectPackage.Literals.EOBJECT_ADDED_ECHANGE__NEW_VALUE);
			// TODO Properly append the index
			this.currentID = change.getAffectedEObjectID() + change.getAffectedFeature().getName() + change.getIndex();
		} else if (change.getAffectedEObjectID().equals(this.currentID)) {
			// Inserts another object to object's feature: Affected ID
			replaceEObjectWithWrapperIn(change, FeaturePackage.Literals.FEATURE_ECHANGE__AFFECTED_EOBJECT);
		} else if (modifiesSmallerIndexInCommonParent(change.getNewValueID())) {
			// Inserts another object to parent at smaller index: From parent.feat/idx to
			// parent.feat/idx+1
			// TODO Properly adjust the index
		}
	}

	private void adjustForChangeSequence(RemoveEReference<?, ?> change) {
		if (change.getOldValueID().equals(this.currentID)) {
			// Removes object from parent: From oldID to cache:/X
			replaceEObjectWithWrapperIn(change, EobjectPackage.Literals.EOBJECT_SUBTRACTED_ECHANGE__OLD_VALUE);
			this.currentID = cachedEObjectURI;
		} else if (change.getAffectedEObjectID().equals(this.currentID)) {
			// Removes another object from object's feature: Affected ID
			replaceEObjectWithWrapperIn(change, FeaturePackage.Literals.FEATURE_ECHANGE__AFFECTED_EOBJECT);
		} else if (modifiesSmallerIndexInCommonParent(change.getOldValueID())) {
			// Removes another object from parent at smaller index: From parent.feat/idx to
			// parent.feat/idx-1
			// TODO Properly adjust the index
		}
	}

	private void adjustForChangeSequence(ReplaceSingleValuedEAttribute<?, ?> change) {
		if (change.getAffectedEObjectID().equals(this.currentID)) {
			// Sets objects's feature: Affected ID
			replaceEObjectWithWrapperIn(change, FeaturePackage.Literals.FEATURE_ECHANGE__AFFECTED_EOBJECT);
		}
	}

	private void adjustForChangeSequence(ReplaceSingleValuedEReference<?, ?> change) {
		if (change.getNewValueID().equals(this.currentID)) {
			// Sets object as parent's feature: From newID to parent.feat
			replaceEObjectWithWrapperIn(change, EobjectPackage.Literals.EOBJECT_ADDED_ECHANGE__NEW_VALUE);
			// TODO Properly append feature name
			this.currentID = change.getAffectedEObjectID() + change.getAffectedFeature().getName();
		} else if (change.getOldValueID().equals(this.currentID)) {
			// Unsets object as parent's feature: From oldID to cache:/X
			replaceEObjectWithWrapperIn(change, EobjectPackage.Literals.EOBJECT_SUBTRACTED_ECHANGE__OLD_VALUE);
			this.currentID = cachedEObjectURI;
		} else if (change.getAffectedEObjectID().equals(this.currentID)) {
			// Sets another object as object's feature: Affected ID
			replaceEObjectWithWrapperIn(change, FeaturePackage.Literals.FEATURE_ECHANGE__AFFECTED_EOBJECT);
		}
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

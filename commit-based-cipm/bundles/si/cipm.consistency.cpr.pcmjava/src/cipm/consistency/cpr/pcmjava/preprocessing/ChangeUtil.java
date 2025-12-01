package cipm.consistency.cpr.pcmjava.preprocessing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.URIUtil;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.net4j.util.collection.Pair;

import de.uka.ipd.sdq.identifier.Identifier;
import tools.vitruv.change.atomic.AdditiveEChange;
import tools.vitruv.change.atomic.EChange;
import tools.vitruv.change.atomic.SubtractiveEChange;
import tools.vitruv.change.atomic.eobject.CreateEObject;
import tools.vitruv.change.atomic.eobject.DeleteEObject;
import tools.vitruv.change.atomic.eobject.EObjectAddedEChange;
import tools.vitruv.change.atomic.eobject.EObjectExistenceEChange;
import tools.vitruv.change.atomic.eobject.EObjectSubtractedEChange;
import tools.vitruv.change.atomic.feature.FeatureEChange;
import tools.vitruv.change.atomic.feature.UnsetFeature;
import tools.vitruv.change.atomic.feature.list.InsertInListEChange;
import tools.vitruv.change.atomic.feature.list.RemoveFromListEChange;
import tools.vitruv.change.atomic.feature.list.UpdateSingleListEntryEChange;
import tools.vitruv.change.atomic.feature.reference.InsertEReference;
import tools.vitruv.change.atomic.feature.reference.RemoveEReference;
import tools.vitruv.change.atomic.feature.reference.ReplaceSingleValuedEReference;
import tools.vitruv.change.atomic.root.InsertRootEObject;
import tools.vitruv.change.atomic.root.RemoveRootEObject;
import tools.vitruv.change.atomic.root.RootEChange;

public final class ChangeUtil {
	private static final String cacheIDPrefix = "cache:/";

	/**
	 * Check if any EObject ID is a prefix of another one. If it is, there is a
	 * dependency
	 * 
	 * @return
	 *         <ul>
	 *         <li>TRUE: changeBefore -> changeAfter
	 *         <li>FALSE: changeAfter -> changeBefore
	 *         <li>NULL: Order does not matter
	 *         </ul>
	 */
	public static Boolean shouldChangesHappenInOrder(EChange changeBefore, EChange changeAfter) {
		var affectedIDBefore = getAffectedEObjectID(changeBefore);
		var oldIDBefore = getOldValueID(changeBefore);
		var newIDBefore = getNewValueID(changeBefore);
		var listBefore = List.of(affectedIDBefore, oldIDBefore, newIDBefore).stream().filter((i) -> i != null)
				.collect(Collectors.toList());

		var affectedIDAfter = getAffectedEObjectID(changeAfter);
		var oldIDAfter = getOldValueID(changeAfter);
		var newIDAfter = getNewValueID(changeAfter);
		var listAfter = List.of(affectedIDAfter, oldIDAfter, newIDAfter).stream().filter((i) -> i != null)
				.collect(Collectors.toList());

		var idPairs = new ArrayList<Pair<String, String>>();

		listBefore
				.forEach((idBefore) -> listAfter.stream().filter((idAfter) -> areIDsDependantInOrder(idBefore, idAfter))
						.forEach((idAfter) -> idPairs.add(new Pair<>(idBefore, idAfter))));

		if (!idPairs.isEmpty()) {

			return changeAffectsModelStructure(changeBefore);
		}

		return false;
	}

	public static boolean areIDsDependantInOrder(String idBefore, String idAfter) {
		return idAfter.startsWith(idBefore);
	}

	/**
	 * @return Whether change inserts an element to model (CreateEObject does not
	 *         insert an element to a model)
	 */
	public static boolean doesChangeInsertModelElement(EChange change) {
		if (!changeAffectsModelStructure(change))
			return false;

		if (change instanceof InsertEReference)
			return true;

		if (change instanceof ReplaceSingleValuedEReference && getOldValueID(change) == null
				&& getNewValueID(change) != null)
			return true;

		return false;
	}

	public static String getInsertedModelElementID(EChange change) {
		if (!doesChangeInsertModelElement(change))
			return null;

		return getNewValueID(change);
	}

	public static String getRemovedModelElementID(EChange change) {
		if (!doesChangeRemoveModelElement(change))
			return null;

		return getOldValueID(change);
	}

	/**
	 * @return Whether change removes an element to model (DeleteEObject does not
	 *         remove an element from a model)
	 */
	public static boolean doesChangeRemoveModelElement(EChange change) {
		if (!changeAffectsModelStructure(change))
			return false;

		// UnsetFeature is not used here, since ID of the removed element is unclear
		if (change instanceof RemoveEReference)
			return true;

		if (change instanceof ReplaceSingleValuedEReference && getOldValueID(change) != null
				&& getNewValueID(change) == null)
			return true;

		return false;
	}

	public static boolean changeAffectsModelStructure(EChange change) {
		return change instanceof RootEChange || isContainmentChange(change);
	}

	public static boolean isContainmentChange(EChange change) {
		var feat = ChangeUtil.getAffectedFeature(change);
		var isFeatERef = feat instanceof EReference;
		return isFeatERef && ((EReference) feat).isContainment();
	}

	public static boolean createsEObjectOfType(EChange change, EClass eCls) {
		return change instanceof CreateEObject
				&& eCls.getInstanceClass().isAssignableFrom(getCreatedEObjectType(change).getInstanceClass());
	}

	public static boolean involvesFeature(EChange change, EStructuralFeature feat) {
		var featName = feat.getName();
		return feat.equals(getAffectedFeature(change)) ||

				(getOldValueID(change) != null && getOldValueID(change).contains(featName))

				|| (getNewValueID(change) != null && getNewValueID(change).contains(featName))

				|| (getAffectedEObjectID(change) != null && getAffectedEObjectID(change).contains(featName));
	}

	public static void replaceInAllIDs(EChange change, String regexInOldID, String replacement) {
		var affectedID = getAffectedEObjectID(change);
		if (affectedID != null) {
			setAffectedEObjectID(change, affectedID.replaceAll(regexInOldID, replacement));
		}
		var oldID = getOldValueID(change);
		if (oldID != null) {
			setOldValueID(change, oldID.replaceAll(regexInOldID, replacement));
		}
		var newID = getNewValueID(change);
		if (newID != null) {
			setNewValueID(change, newID.replaceAll(regexInOldID, replacement));
		}
	}

	public static String changeIndexInID(String idToAdjust, String idTillIndexToReplace, int changedIdx,
			boolean wouldChangedIndexBeRemoved) {
		var uri = URI.createURI(idTillIndexToReplace);
		var ls = uri.lastSegment();
		var idxInUri = Integer.valueOf(ls.replaceAll("\\\\D", "")).intValue();
		var newIndex = idxInUri;

		// Assume [..., X-1, X, X+1, ...], where X is the changed index

		// If element's index < X, unaffected
		if (changedIdx <= idxInUri) {

			// If X would normally be removed but actually will not, increment all indices >
			// X
			// I.e. X+N becomes X+N+1
			if (wouldChangedIndexBeRemoved) {
				newIndex = idxInUri + 1;
			} else {
				// If X would normally be added but actually will not, decrement all indices > X
				// I.e. X+N becomes X+N-1
				newIndex = idxInUri - 1;
			}

			return idToAdjust.replaceAll(idTillIndexToReplace,
					uri.trimSegments(1).appendSegment(ls.replaceAll("\\\\D+", String.valueOf(newIndex))).toString());
		} else {
			return idToAdjust;
		}
	}

	public static void changeIndexInIDs(EChange change, String idTillIndexToReplace, int changedIdx,
			boolean wouldChangedIndexBeRemoved) {
		var affectedID = getAffectedEObjectID(change);
		if (affectedID != null && affectedID.startsWith(idTillIndexToReplace)) {
			setAffectedEObjectID(change,
					changeIndexInID(affectedID, idTillIndexToReplace, changedIdx, wouldChangedIndexBeRemoved).toString());
		}
		var oldID = getOldValueID(change);
		if (oldID != null && oldID.startsWith(idTillIndexToReplace)) {
			setOldValueID(change,
					changeIndexInID(oldID, idTillIndexToReplace, changedIdx, wouldChangedIndexBeRemoved).toString());
		}
		var newID = getNewValueID(change);
		if (newID != null && newID.startsWith(idTillIndexToReplace)) {
			setNewValueID(change,
					changeIndexInID(newID, idTillIndexToReplace, changedIdx, wouldChangedIndexBeRemoved).toString());
		}
	}

	public static boolean isCacheURI(URI uri) {
		return isCacheURI(uri.toString());
	}

	public static boolean isCacheURI(String uri) {
		return uri.startsWith(cacheIDPrefix);
	}

	public static void adaptChangeURIs(Resource changeResource, Resource targetModelResource,
			List<String> uriPrefixesToSkip) {
		for (var change : changeResource.getContents()) {
			if (change instanceof EChange)
				adaptChangeURIs((EChange) change, targetModelResource, uriPrefixesToSkip);
		}
	}

	public static void adaptChangeURIs(Resource changeResource, Resource targetModelResource) {
		adaptChangeURIs(changeResource, targetModelResource, List.of());
	}

	public static void adaptChangeURIs(EChange change, Resource targetModelResource) {
		adaptChangeURIs(change, targetModelResource, List.of());
	}

	public static boolean uriStartsWith(String uri, List<String> prefixes) {
		return prefixes.stream().anyMatch((p) -> uriStartsWith(uri, p));
	}

	public static boolean uriStartsWith(String uri, String prefix) {
		return uri.startsWith(prefix);
	}

	public static void adaptChangeURIs(EChange change, Resource targetModelResource, List<String> uriPrefixesToSkip) {
		var affectedID = getAffectedEObjectID(change);
		if (affectedID != null && !uriStartsWith(affectedID, uriPrefixesToSkip)) {
			setAffectedEObjectID(change, adaptURI(affectedID, targetModelResource));
		}
		var oldID = getOldValueID(change);
		if (oldID != null && !uriStartsWith(oldID, uriPrefixesToSkip)) {
			setOldValueID(change, adaptURI(oldID, targetModelResource));
		}
		var newID = getNewValueID(change);
		if (newID != null && !uriStartsWith(newID, uriPrefixesToSkip)) {
			setNewValueID(change, adaptURI(newID, targetModelResource));
		}
		var uri = getRootChangeURI(change);
		if (uri != null && !uriStartsWith(uri, uriPrefixesToSkip)) {
			setRootChangeURI(change, targetModelResource.getURI().toString());
		}
	}

	public static void replaceChangeIDs(EChange change, String idToReplace, String replacementID) {
		var affectedID = getAffectedEObjectID(change);
		if (affectedID != null && affectedID.equals(idToReplace)) {
			setAffectedEObjectID(change, replacementID);
		}
		var oldID = getOldValueID(change);
		if (oldID != null && oldID.equals(idToReplace)) {
			setOldValueID(change, replacementID);
		}
		var newID = getNewValueID(change);
		if (newID != null && newID.equals(idToReplace)) {
			setNewValueID(change, replacementID);
		}
	}

	private static String adaptURI(String uri, Resource res) {
		if (isCacheURI(uri))
			return uri;

		var fragment = URI.createURI(uri).fragment();
		return res.getURI().appendFragment(fragment).toString();
	}

	public static boolean eObjectsNonNullAndEqual(EObject obj1, EObject obj2) {
		if (obj1 == null || obj2 == null)
			return false;

		if (obj1 == obj2)
			return true;

		var res1 = obj1.eResource();
		var res2 = obj2.eResource();
		if (res1 != null && res2 != null && !res1.getURIFragment(obj1).equals(res2.getURIFragment(obj2)))
			return false;

		return EcoreUtil.equals(obj1, obj2);
	}

	public static boolean affectedFeatureValueTypeIsEObject(EChange change) {
		var feat = getAffectedFeature(change);
		if (feat == null)
			return false;
		return EObject.class.isAssignableFrom(feat.getEType().getInstanceClass());
	}

	public static boolean affectedFeatureSupportsValueType(EChange change, Class<?> valueType) {
		var feat = getAffectedFeature(change);
		if (feat == null)
			return false;
		return feat.getEType().getInstanceClass().isAssignableFrom(valueType);
	}

	public static boolean affectedFeaturesPresentAndEqual(EChange change1, EChange change2) {
		var feat1 = getAffectedFeature(change1);
		var feat2 = getAffectedFeature(change2);

		if (feat1 == null || feat2 == null)
			return false;

		return feat1 == feat2;
	}

	public static boolean affectedEObjectsPresentAndEqual(EChange change1, EChange change2) {
		var affectedObj1 = getAffectedEObject(change1);
		var affectedObj2 = getAffectedEObject(change2);

		if (affectedObj1 == null || affectedObj2 == null)
			return false;

		return eObjectsNonNullAndEqual(affectedObj1, affectedObj2);
	}

	public static boolean affectedEObjectFeaturesPresentAndEqual(EChange change1, EChange change2) {
		return affectedFeaturesPresentAndEqual(change1, change2) && affectedEObjectsPresentAndEqual(change1, change2);
	}

	public static boolean newFeatureChangeNegatesOldFeatureChange(EChange oldChange, EChange newChange) {
		// Ensure that the same EObject's same feature is changed
		if (!affectedEObjectFeaturesPresentAndEqual(oldChange, newChange))
			return false;

		// New UnsetFeature negates old FeatureChange
		if (newChange instanceof UnsetFeature)
			return true;

		// New SubtractiveEChange negates old AdditiveEChange
		// Assumption: If there are multiple AdditiveEChanges on the same EObject (and
		// its same feature), there is one SubtractiveEChange between them, which
		// "unsets" the old value
		if (newAndOldValuesPresentAndEqual(oldChange, newChange))
			return true;

		return false;
	}

	public static boolean newAndOldValuesPresentAndEqual(EChange additiveChange, EChange subtractiveChange) {
		if (additiveChange instanceof AdditiveEChange && subtractiveChange instanceof SubtractiveEChange) {
			var newVal = getNewValue(additiveChange);
			var oldVal = getOldValue(subtractiveChange);
			if (newVal == null || oldVal == null)
				return false;
			if (newVal instanceof EObject && oldVal instanceof EObject)
				return eObjectsNonNullAndEqual((EObject) newVal, (EObject) oldVal);
			return oldVal.equals(newVal);
		}
		return false;
	}

	public static boolean newAndOldValuesPresentAndEqual(EChange additiveAndSubractiveChange) {
		return newAndOldValuesPresentAndEqual(additiveAndSubractiveChange, additiveAndSubractiveChange);
	}

	public static String getRootChangeURI(EChange change) {
		if (change instanceof RootEChange) {
			return ((RootEChange) change).getUri();
		}
		return null;
	}

	public static void setRootChangeURI(EChange change, String newURI) {
		if (change instanceof RootEChange) {
			((RootEChange) change).setUri(newURI);
		}
	}

	public static EObject getAffectedEObject(EChange change) {
		if (change instanceof EObjectExistenceEChange)
			return ((EObjectExistenceEChange<?>) change).getAffectedEObject();
		if (change instanceof FeatureEChange)
			return ((FeatureEChange<?, ?>) change).getAffectedEObject();
		return null;
	}

	public static String getAffectedEObjectID(EChange change) {
		if (change instanceof EObjectExistenceEChange)
			return ((EObjectExistenceEChange<?>) change).getAffectedEObjectID();
		if (change instanceof FeatureEChange)
			return ((FeatureEChange<?, ?>) change).getAffectedEObjectID();
		return null;
	}

	public static void setAffectedEObjectID(EChange change, String newID) {
		if (change instanceof EObjectExistenceEChange)
			((EObjectExistenceEChange<?>) change).setAffectedEObjectID(newID);
		if (change instanceof FeatureEChange)
			((FeatureEChange<?, ?>) change).setAffectedEObjectID(newID);
	}

	public static EStructuralFeature getAffectedFeature(EChange change) {
		if (change instanceof FeatureEChange)
			return ((FeatureEChange<?, ?>) change).getAffectedFeature();
		return null;
	}

	public static Object getNewValue(EChange change) {
		if (change instanceof AdditiveEChange)
			return ((AdditiveEChange<?>) change).getNewValue();
		return null;
	}

	public static Object getOldValue(EChange change) {
		if (change instanceof SubtractiveEChange)
			return ((SubtractiveEChange<?>) change).getOldValue();
		return null;
	}

	public static int getIndexOfValue(EChange change) {
		if (change instanceof UpdateSingleListEntryEChange)
			return ((UpdateSingleListEntryEChange<?, ?>) change).getIndex();
		return -1;
	}

	public static String getNewValueID(EChange change) {
		if (change instanceof EObjectAddedEChange)
			return ((EObjectAddedEChange<?>) change).getNewValueID();
		return null;
	}

	public static String getOldValueID(EChange change) {
		if (change instanceof EObjectSubtractedEChange)
			return ((EObjectSubtractedEChange<?>) change).getOldValueID();
		return null;
	}

	public static void setNewValueID(EChange change, String newID) {
		if (change instanceof EObjectAddedEChange)
			((EObjectAddedEChange<?>) change).setNewValueID(newID);
	}

	public static void setOldValueID(EChange change, String newID) {
		if (change instanceof EObjectSubtractedEChange)
			((EObjectSubtractedEChange<?>) change).setOldValueID(newID);
	}

	public static EObject getDeletedEObject(EChange change) {
		if (change instanceof DeleteEObject)
			return ((DeleteEObject<?>) change).getAffectedEObject();
		return null;
	}

	public static EObject getRemovedEObject(EChange change) {
		var oldVal = getOldValue(change);
		if (oldVal instanceof EObject)
			return (EObject) oldVal;
		return null;
	}

	public static EObject getInsertedEObject(EChange change) {
		var newVal = getNewValue(change);
		if (newVal instanceof EObject)
			return (EObject) newVal;
		return null;
	}

	public static EClass getCreatedEObjectType(EChange change) {
		if (change instanceof CreateEObject)
			return ((CreateEObject<?>) change).getAffectedEObjectType();
		return null;
	}

	public static EObject getCreatedEObject(EChange change) {
		if (change instanceof CreateEObject)
			return ((CreateEObject<?>) change).getAffectedEObject();
		return null;
	}

	public static List<EObject> getInvolvedEObjects(EChange change) {
		var objs = new ArrayList<EObject>();
		if (change instanceof AdditiveEChange && ((AdditiveEChange<?>) change).getNewValue() instanceof EObject)
			objs.add((EObject) ((AdditiveEChange<?>) change).getNewValue());
		if (change instanceof SubtractiveEChange && ((SubtractiveEChange<?>) change).getOldValue() instanceof EObject)
			objs.add((EObject) ((SubtractiveEChange<?>) change).getOldValue());
		if (change instanceof EObjectExistenceEChange)
			objs.add(((EObjectExistenceEChange<?>) change).getAffectedEObject());
		if (change instanceof FeatureEChange)
			objs.add(((FeatureEChange<?, ?>) change).getAffectedEObject());
		return objs;
	}

	public static boolean isEObjectInvolvedIn(EChange change, EObject obj) {
		return containsEObject(getInvolvedEObjects(change), obj);
	}

	public static boolean areMatchingEObjectExistenceChanges(EChange createChange, EChange insertRootChange,
			EChange removeRootChange, EChange deleteChange) {
		if (!(createChange instanceof CreateEObject && deleteChange instanceof DeleteEObject
				&& insertRootChange instanceof InsertRootEObject && removeRootChange instanceof RemoveRootEObject))
			return false;

		var idVal1 = ((EObjectExistenceEChange<?>) createChange).getIdAttributeValue();
		var idVal2 = ((EObjectExistenceEChange<?>) deleteChange).getIdAttributeValue();
		if (idVal1 != idVal2 && ((idVal1 == null ^ idVal2 == null) || !idVal1.equals(idVal2)))
			return false;

		if (!affectedEObjectsPresentAndEqual(createChange, deleteChange))
			return false;

		return areMatchingRootEChanges(insertRootChange, removeRootChange);
	}

	public static boolean areMatchingRootEChanges(EChange insertChange, EChange removeChange) {
		if (!(insertChange instanceof InsertRootEObject && removeChange instanceof RemoveRootEObject))
			return false;

		if (!newAndOldValuesPresentAndEqual(insertChange, removeChange))
			return false;

		var castedIC = (InsertRootEObject<?>) insertChange;
		var castedRC = (RemoveRootEObject<?>) removeChange;
		var icNewVal = (EObject) castedIC.getNewValue();
		var rcOldVal = (EObject) castedRC.getOldValue();

		var icURIWithIdx = URI.createURI(castedIC.getUri()).appendFragment("/" + String.valueOf(castedIC.getIndex()))
				.toString();

		if (icURIWithIdx.equals(castedRC.getOldValueID()))
			return true;

		if (icNewVal instanceof Identifier && rcOldVal instanceof Identifier) {
			return URI.createURI(castedIC.getUri()).appendFragment("/" + ((Identifier) icNewVal).getId()).toString()
					.equals(castedRC.getOldValueID());
		}

		return false;
	}

	public static boolean areMatchingSingleListEntryEChanges(EChange insertChange, EChange removeChange) {
		if (!(insertChange instanceof InsertInListEChange && removeChange instanceof RemoveFromListEChange))
			return false;
		return eObjectsNonNullAndEqual(getAffectedEObject(insertChange), getAffectedEObject(removeChange))
				&& newAndOldValuesPresentAndEqual(insertChange, removeChange)
				&& getAffectedFeature(insertChange) == getAffectedFeature(removeChange);
	}

	public static boolean containsEObject(Collection<EObject> col, EObject objToSeek) {
		if (col == null)
			return false;

		return col.stream().anyMatch((o) -> eObjectsNonNullAndEqual(o, objToSeek));
	}

	public static boolean isRootEObject(EObject obj) {
		if (obj.eResource() == null)
			return false;
		return obj.eResource().getContents().contains(obj);
	}
}

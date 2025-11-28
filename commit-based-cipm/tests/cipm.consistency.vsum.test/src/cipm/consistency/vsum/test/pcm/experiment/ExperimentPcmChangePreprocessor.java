package cipm.consistency.vsum.test.pcm.experiment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.net4j.util.collection.Pair;
import org.palladiosimulator.pcm.core.entity.EntityPackage;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.repository.RepositoryPackage;
import org.palladiosimulator.pcm.seff.SeffPackage;
import org.palladiosimulator.pcm.seff.ServiceEffectSpecification;

import cipm.consistency.cpr.pcmjava.preprocessing.ChangeUtil;
import tools.vitruv.change.atomic.EChange;
import tools.vitruv.change.atomic.eobject.CreateEObject;
import tools.vitruv.change.atomic.eobject.DeleteEObject;
import tools.vitruv.change.atomic.feature.reference.InsertEReference;
import tools.vitruv.change.atomic.feature.reference.RemoveEReference;
import tools.vitruv.change.atomic.feature.reference.ReplaceSingleValuedEReference;
import tools.vitruv.change.atomic.root.RemoveRootEObject;

public class ExperimentPcmChangePreprocessor {

	public int getMaxDepth(EChange change) {
		var aID = ChangeUtil.getAffectedEObjectID(change);
		var oID = ChangeUtil.getOldValueID(change);
		var nID = ChangeUtil.getNewValueID(change);

		var aDepth = 0;
		var oDepth = 0;
		var nDepth = 0;

		if (aID != null) {
			aDepth = getDepth(URI.createURI(aID));
		}
		if (oID != null) {
			oDepth = getDepth(URI.createURI(oID));
		}
		if (nID != null) {
			nDepth = getDepth(URI.createURI(nID));
		}

		return Math.max(aDepth, Math.max(oDepth, nDepth));
	}

	public int getDepth(URI uri) {
		if (uri == null || !uri.hasFragment())
			return 0;
		var depth = uri.fragment().split("/").length;
		return depth == 0 ? depth : depth - 2;
	}

	public int getCreatedObjectDepth(InsertEReference<?, ?> ir) {
		return URI.createURI(ir.getAffectedEObjectID()).fragment().split("/").length;
	}

	public boolean isContainer(InsertEReference<?, ?> ir, String containerURIFragment) {
		return URI.createURI(ir.getAffectedEObjectID()).fragment().equals(containerURIFragment);
	}

	public boolean isContainerRepository(InsertEReference<?, ?> ir, Repository repo) {
		return isContainer(ir, repo.eResource().getURIFragment(repo));
	}

	private final static String cachedEObjectURIPattern = "cache:/\\d+";
	private final static String cachedEObjectURI = "cache:/0";

	public List<EChange> orderPCMchanges(List<EChange> changeSequence) {
		var newChangeList = new ArrayList<EChange>();

		var changes = new HashMap<Integer, ArrayList<EChange>>();
		var maxDepth = 0;

//		var createPairs = new ArrayList<Pair<CreateEObject<?>, EChange>>();
//		var deletePairs = new ArrayList<Pair<EChange, DeleteEObject<?>>>();

		for (int i = 0; i < changeSequence.size(); i++) {
			var currentChange = changeSequence.get(i);
			ChangeUtil.replaceInAllIDs(currentChange, cachedEObjectURIPattern, cachedEObjectURI);

			var nextChange = changeSequence.size() > i + 1 ? changeSequence.get(i + 1) : null;
			if (nextChange != null) {
				ChangeUtil.replaceInAllIDs(nextChange, cachedEObjectURIPattern, cachedEObjectURI);
			}

			var currentChangeDepth = getMaxDepth(currentChange);
			var nextChangeDepth = nextChange != null ? getMaxDepth(nextChange) : -1;

			if (maxDepth < currentChangeDepth)
				maxDepth = currentChangeDepth;
			if (maxDepth < nextChangeDepth)
				maxDepth = nextChangeDepth;

			if (!changes.containsKey(currentChangeDepth)) {
				changes.put(currentChangeDepth, new ArrayList<EChange>());
			}
			if (!changes.containsKey(nextChangeDepth)) {
				changes.put(nextChangeDepth, new ArrayList<EChange>());
			}

			if (currentChange instanceof CreateEObject) {
//				createPairs.add(new Pair<CreateEObject<?>, EChange>((CreateEObject<?>) currentChange, nextChange));
				changes.get(nextChangeDepth).add(currentChange);
				changes.get(nextChangeDepth).add(nextChange);

				// Skip both changes, since they are already handled
				i++;
				continue;
			}

			if (nextChange instanceof DeleteEObject) {
//				deletePairs.add(new Pair<EChange, DeleteEObject<?>>(currentChange, (DeleteEObject<?>) nextChange));
				changes.get(currentChangeDepth).add(currentChange);
				changes.get(currentChangeDepth).add(nextChange);

				// Skip both changes, since they are already handled
				i++;
				continue;
			}

			changes.get(currentChangeDepth).add(currentChange);
		}

		// Add PCM elements in Breadth-First order, as this will ensure that all PCM
		// elements are known
		for (int i = 0; i <= maxDepth; i++) {
			if (changes.containsKey(i)) {
				newChangeList.addAll(changes.get(i));
			}
		}

		return newChangeList;
	}

	private void fixIDsUponDeleteChangeRemoval(EChange prevChange, EChange delChange, List<EChange> changeSeq) {
		String idToReplace = ChangeUtil.getOldValueID(prevChange);
		int idx = -1;
		if (prevChange instanceof RemoveRootEObject) {
			idx = ((RemoveRootEObject<?>) prevChange).getIndex();
		} else if (prevChange instanceof RemoveEReference) {
			idx = ((RemoveEReference<?,?>) prevChange).getIndex();
		} else if (prevChange instanceof ReplaceSingleValuedEReference) {
			idx = 0;
		} else {
			throw new IllegalStateException("Unexpected Change -> Delete occurrence");
		}
	}
	
}

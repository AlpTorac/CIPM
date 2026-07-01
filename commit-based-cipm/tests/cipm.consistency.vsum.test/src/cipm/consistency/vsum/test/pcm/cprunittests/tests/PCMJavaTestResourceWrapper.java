package cipm.consistency.vsum.test.pcm.cprunittests.tests;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

import cipm.consistency.cpr.pcmjava.preprocessing.ChangeUtil;
import cipm.consistency.vsum.test.pcm.experiment.PcmToJavaChangePropagationDirLayoutConstants;
import cipm.consistency.vsum.test.pcm.experiment.ResourceOperationsUtil;
import tools.vitruv.change.correspondence.Correspondences;

public class PCMJavaTestResourceWrapper {
	private static final String oldPCMResourceName = "oldPCM.repository";
	private static final String oldJavaResourceName = "oldJava.javaxmi";
	private static final String oldPCMJavaCorrespondencesName = "oldCors.correspondence";

	private static final String pcmChangesResourceName = "oldPcmChanges.changes";

	private static final String newPCMResourceName = "newPCM.repository";
	private static final String newJavaResourceName = "newJava.javaxmi";
	private static final String newPCMJavaCorrespondencesName = "newCors.correspondence";

	private static final String propagatedPCMChangesName = "propPCMChanges.changes";
	private static final String propagatedPCMResourceName = "propPCM.repository";
	private static final String propagatedJavaResourceName = "propJava.javaxmi";
	private static final String propagatedPCMJavaCorrespondencesName = "propCors.correspondence";

	private final URI testDirURI;
	private final File testDir;

	private ResourceSet resSet;

	// Initial models in original vsum test
	private Resource initialJavaModel;
	private Resource initialPcmRepository;
	private Resource initialCorrespondences;

	// Post Java -> PCM propagation models in original vsum test
	private Resource targetJavaModel;
	private Resource targetPcmRepository;
	private Resource targetCorrespondences;

	// Changes propagated in original vsum test (Java -> PCM propagation)
	private Resource originalPcmChanges;

	// Models propagated during experiment (PCM -> Java propagation)
	private Resource propagatedJavaModel;
	private Resource propagatedPcmRepository;
	private Resource propagatedCorrespondences;

	// Changes propagated during experiment (PCM -> Java propagation)
	private Resource propagatedPcmChanges;

	/**
	 * @param resSet           The resource set that will be used throughout the
	 *                         experiment
	 * @param experimentLayout The file layout of the experiment
	 */
	public PCMJavaTestResourceWrapper(ResourceSet resSet, File testDir) {
		this.resSet = resSet;
		this.testDir = testDir;
		this.testDirURI = URI.createFileURI(testDir.getAbsolutePath());
	}

	/**
	 * Loads and prepares all Resource instances that will be needed for the
	 * experiment
	 */
	public void initialise() {
		loadTargetModels();
		loadInitialModels();
		loadOriginalChanges();
		initialiseExperimentTestResources();
		adaptURIsInCorrespondences();
		adaptURIsInChanges();
	}

	private void loadTargetModels() {
		targetJavaModel = ResourceOperationsUtil.loadResource(resSet, testDirURI.appendSegment(newJavaResourceName));
		targetPcmRepository = ResourceOperationsUtil.loadResource(resSet, testDirURI.appendSegment(newPCMResourceName));
		targetCorrespondences = ResourceOperationsUtil.loadResource(resSet,
				testDirURI.appendSegment(newPCMJavaCorrespondencesName));
	}

	private void loadOriginalChanges() {
		originalPcmChanges = ResourceOperationsUtil.loadResource(resSet,
				testDirURI.appendSegment(pcmChangesResourceName));
	}

	private void loadInitialModels() {
		initialJavaModel = ResourceOperationsUtil.loadResource(resSet, testDirURI.appendSegment(oldJavaResourceName));
		initialPcmRepository = ResourceOperationsUtil.loadResource(resSet,
				testDirURI.appendSegment(oldPCMResourceName));
		initialCorrespondences = ResourceOperationsUtil.loadResource(resSet,
				testDirURI.appendSegment(oldPCMJavaCorrespondencesName));
	}

	private void initialiseExperimentTestResources() {
		propagatedJavaModel = ResourceOperationsUtil.copyAndSaveResource(resSet, initialJavaModel,
				testDirURI.appendSegment(propagatedJavaResourceName));
		propagatedPcmRepository = ResourceOperationsUtil.copyAndSaveResource(resSet, initialPcmRepository,
				testDirURI.appendSegment(propagatedPCMResourceName));
		propagatedCorrespondences = ResourceOperationsUtil.copyAndSaveResource(resSet, initialCorrespondences,
				testDirURI.appendSegment(propagatedPCMJavaCorrespondencesName));
		propagatedPcmChanges = ResourceOperationsUtil.copyAndSaveResource(resSet, originalPcmChanges,
				testDirURI.appendSegment(propagatedPCMChangesName));
	}

	private void adaptURIsInChanges() {
		var uriPrefixesToSkip = List
				.of(PcmToJavaChangePropagationDirLayoutConstants.getPcmprimitivetypesrepositoryuri());
		ChangeUtil.adaptChangeURIs(propagatedPcmChanges, propagatedPcmRepository, uriPrefixesToSkip);
		ResourceOperationsUtil.saveResource(propagatedPcmChanges);
	}

	private void adaptURIsInCorrespondences() {
		var propRess = List.of(propagatedJavaModel, propagatedPcmRepository);

		var cors = (Correspondences) propagatedCorrespondences.getContents().get(0);
		cors.getCorrespondences().forEach((c) -> {
			// Replace all EObjects in correspondences with their correspondents from
			// the propagatedX Resources. This fixes their URIs.
			for (var originalList : List.of(c.getLeftEObjects(), c.getRightEObjects())) {
				var iterationList = List.copyOf(originalList);
				for (int i = 0; i < iterationList.size(); i++) {
					final var idx = i;
					var original = iterationList.get(idx);

					// Since there may be correspondences to Ecore Literals too,
					// only replace EObjects, if they actually have a replacement
					// in propagatedX Resources
					if (original.eResource() == null || !original.eResource().getURI().isFile())
						continue;

					var replacement = propRess.stream()
							.map((r) -> r.getEObject(original.eResource().getURIFragment(original)))
							.filter((r) -> r != null).findFirst().get();

					originalList.add(i, replacement);
					originalList.remove(original);
				}
			}
		});

		ResourceOperationsUtil.saveResource(propagatedCorrespondences);
	}

	public void reloadPropagatedResources() {
		ResourceOperationsUtil.reload(propagatedJavaModel);
		ResourceOperationsUtil.reload(propagatedPcmRepository);
		ResourceOperationsUtil.reload(propagatedCorrespondences);
	}

//	public PcmToJavaChangePropagationDirLayout getExperimentLayout() {
//		return experimentLayout;
//	}

	public ResourceSet getResSet() {
		return resSet;
	}

	public Resource getInitialJavaModel() {
		return initialJavaModel;
	}

	public Resource getInitialPcmRepository() {
		return initialPcmRepository;
	}

	public Resource getInitialCorrespondences() {
		return initialCorrespondences;
	}

	public Resource getTargetJavaModel() {
		return targetJavaModel;
	}

	public Resource getTargetPcmRepository() {
		return targetPcmRepository;
	}

	public Resource getTargetCorrespondences() {
		return targetCorrespondences;
	}

	public Resource getOriginalPcmChanges() {
		return originalPcmChanges;
	}

	public Resource getPropagatedJavaModel() {
		return propagatedJavaModel;
	}

	public Resource getPropagatedPcmRepository() {
		return propagatedPcmRepository;
	}

	public Resource getPropagatedCorrespondences() {
		return propagatedCorrespondences;
	}

	public Resource getPropagatedPcmChanges() {
		return propagatedPcmChanges;
	}

	public void close() {
		resSet.getResources().forEach((r) -> {
			r.unload();
		});

		List.of(propagatedJavaModel, propagatedPcmRepository, propagatedCorrespondences, propagatedPcmChanges)
				.forEach((r) -> {
					try {
						r.delete(null);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
	}
}

package cipm.consistency.vsum.test.pcm.cprunittests.tests;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.jupiter.api.Assertions;

import cipm.consistency.cpr.pcmjava.CommitIntegrationPCMJavaChangePropagationSpecification;
import cipm.consistency.cpr.pcmjava.JavaModelAccess;
import cipm.consistency.cpr.pcmjava.userinteraction.PcmUserInteractionManager;

import cipm.consistency.vsum.Propagation;
import cipm.consistency.vsum.test.pcm.PcmVsumFacade;
import cipm.consistency.vsum.test.pcm.PcmVsumFacadeImpl;
import cipm.consistency.vsum.test.pcm.cprunittests.dummy.PcmCprAssertions;
import tools.vitruv.change.atomic.EChange;
import tools.vitruv.change.propagation.ChangePropagationSpecification;

public class PCMJavaTestBody {
	private CRSConfig crsConfig;
	private File testDir;

	private PcmVsumFacade vsumFacade;
	private MinimalPCMFacade pcmFacade;
	private MinimalJavaFacade javaFacade;

	private PCMJavaTestResourceWrapper resWrapper;

	public PCMJavaTestBody(CRSConfig crsConfig, File testDir) {
		this.crsConfig = crsConfig;
		this.testDir = testDir;
	}

	private MinimalPCMFacade getPcmFacade() {
		return this.pcmFacade;
	}

	private PcmVsumFacade getPcmVsumFacade() {
		return this.vsumFacade;
	}

	private Propagation propagateChangesToResource(Resource res, Collection<EChange> changes) {
		this.getPcmVsumFacade().addChanges(changes);
		var prop = this.getPcmVsumFacade().propagateResource(res);
		Assertions.assertNull(prop.getException());
		return prop;
	}

	private MinimalJavaFacade getJavaFacade() {
		return this.javaFacade;
	}

	private void initialiseResources() {
		this.resWrapper = new PCMJavaTestResourceWrapper(new ResourceSetImpl(), testDir);
		this.resWrapper.initialise();

		pcmFacade = this.setupPcmFacade();
		javaFacade = this.setupJavaFacade();
		vsumFacade = this.setupVsumFacade();
	}

	private void tearDown() {
		pcmFacade.close();
		javaFacade.close();
		vsumFacade.close();

		resWrapper.close();

		for (var dir : testDir.listFiles(
				(f) -> f.isDirectory() && (f.getName().equals("consistencymetadata") || f.getName().equals("vsum")))) {
			try {
				FileUtils.deleteDirectory(dir);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		PcmUserInteractionManager.reset();
	}

	private MinimalPCMFacade setupPcmFacade() {
		var pcmFacade = new MinimalPCMFacade();
		pcmFacade.setPCMRepositoryResource(resWrapper.getPropagatedPcmRepository());
		return pcmFacade;
	}

	/**
	 * Use {@link #getRootPath()} as the root directory of the PcmVsumFacade.<br>
	 * <br>
	 * It is not recommended to call the super method from the concrete classes
	 * while overriding this method, in order to keep the construction clear and to
	 * avoid possible side effects. If only a minimal PCM without correspondences is
	 * desired, the super method can be used.
	 * 
	 * @return The VSUM facade for the PCM that will be used in this test.
	 */
	private PcmVsumFacade setupVsumFacade() {
		var vsumFacade = new PcmVsumFacadeImpl(testDir.getAbsoluteFile().toPath(), List.of(pcmFacade, javaFacade),
				this.getCPRs());
		vsumFacade.initialise(testDir.getAbsoluteFile().toPath());
		return vsumFacade;
	}

	private List<EChange> getPcmChanges(Resource res) {
		var pcmChangeRes = res;
		var pcmChangeList = new ArrayList<EChange>();
		for (var c : pcmChangeRes.getContents()) {
			pcmChangeList.add((EChange) c);
		}
		return pcmChangeList;
	}

	private void addCRSs() {
		this.crsConfig.setWrapper(resWrapper);
		this.crsConfig.getCRSs().forEach((crs) -> PcmUserInteractionManager.addConflictResolutionStrategy(crs));
	}

	/**
	 * The method that runs the experiment for the given file layout.
	 */
	public void testBody() {
		this.initialiseResources();

		var changeList = getPcmChanges(resWrapper.getPropagatedPcmChanges());

		var newPcmRepoRes = pcmFacade.getResources().stream()
				.filter((r) -> r.getURI().fileExtension().equals("repository")).findFirst().get();

		addCRSs();

		// Propagate PCM changes

		this.propagateChangesToResource(newPcmRepoRes, changeList);

		// Propagated change Resources must be reloaded anew, if they are to be used

		resWrapper.reloadPropagatedResources();

		PcmCprAssertions.assertAllContentsEqual(resWrapper.getPropagatedJavaModel(), resWrapper.getTargetJavaModel());
		PcmCprAssertions.assertAllContentsEqual(resWrapper.getPropagatedPcmRepository(),
				resWrapper.getPropagatedPcmRepository());
		PcmCprAssertions.assertCorrespondencesEqual(resWrapper.getPropagatedCorrespondences(),
				resWrapper.getTargetCorrespondences());

		this.tearDown();
	}

	private MinimalJavaFacade setupJavaFacade() {
		var model = new MinimalJavaFacade();
		model.setJavaResource(resWrapper.getPropagatedJavaModel());
		var modelRes = model.getResource();
		JavaModelAccess.setJavaModel(modelRes);
		return model;
	}

	private List<ChangePropagationSpecification> getCPRs() {
		List<ChangePropagationSpecification> changeSpecs = new ArrayList<>();
		changeSpecs.add(new CommitIntegrationPCMJavaChangePropagationSpecification());
		return changeSpecs;
	}
}

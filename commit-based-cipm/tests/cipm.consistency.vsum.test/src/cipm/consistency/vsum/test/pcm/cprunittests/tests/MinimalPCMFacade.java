package cipm.consistency.vsum.test.pcm.cprunittests.tests;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.emf.ecore.resource.Resource;
import org.palladiosimulator.pcm.repository.Repository;
import cipm.consistency.base.shared.pcm.InMemoryPCM;
import cipm.consistency.models.ModelDirLayout;
import cipm.consistency.models.ModelFacade;

public class MinimalPCMFacade implements ModelFacade {
	private Resource repoRes;
	private InMemoryPCM pcm;

	public MinimalPCMFacade() {
		pcm = new InMemoryPCM();
	}

	@Override
	public void initialize(Path rootPath) {}

	@Override
	public void reload() {
		this.repoRes.unload();
		try {
			this.repoRes.load(null);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
		// Make sure to update the repository instance
		setPCMRepositoryResource(repoRes);
	}

	public void setPCMRepositoryResource(Resource repoRes) {
		this.repoRes = repoRes;
		pcm.setRepository((Repository) this.repoRes.getContents().get(0));
	}

	@Override
	public List<Resource> getResources() {
		if (pcm == null)
			return List.of();

		var resources = new ArrayList<Resource>();
		if (pcm.getSystem() != null && pcm.getSystem().eResource() != null)
			resources.add(pcm.getSystem().eResource());

		if (pcm.getRepository() != null && pcm.getRepository().eResource() != null)
			resources.add(pcm.getRepository().eResource());

		if (pcm.getResourceEnvironmentModel() != null && pcm.getResourceEnvironmentModel().eResource() != null)
			resources.add(pcm.getResourceEnvironmentModel().eResource());

		if (pcm.getUsageModel() != null && pcm.getUsageModel().eResource() != null)
			resources.add(pcm.getUsageModel().eResource());

		if (pcm.getAllocationModel() != null && pcm.getAllocationModel().eResource() != null)
			resources.add(pcm.getAllocationModel().eResource());

		return resources;
	}

	@Override
	public Resource getResource() {
		return null;
	}

	@Override
	public ModelDirLayout getDirLayout() {
		throw new UnsupportedOperationException();
	}
}

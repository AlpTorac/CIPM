package cipm.consistency.models.pcm;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.palladiosimulator.pcm.allocation.Allocation;
import org.palladiosimulator.pcm.allocation.AllocationFactory;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.repository.RepositoryFactory;
import org.palladiosimulator.pcm.resourceenvironment.ResourceEnvironment;
import org.palladiosimulator.pcm.resourceenvironment.ResourceenvironmentFactory;
import org.palladiosimulator.pcm.system.System;
import org.palladiosimulator.pcm.system.SystemFactory;
import org.palladiosimulator.pcm.usagemodel.UsageModel;
import org.palladiosimulator.pcm.usagemodel.UsagemodelFactory;

import cipm.consistency.base.shared.ModelUtil;
import cipm.consistency.base.shared.pcm.InMemoryPCM;
import cipm.consistency.models.ModelFacade;

public class PcmFacade implements ModelFacade {
	private static final Logger LOGGER = Logger.getLogger(PcmFacade.class.getName());

	private InMemoryPCM pcm;
	private PcmDirLayout fileLayout;

	private ResourceSet pcmResourceSet;

	public PcmFacade() {
		fileLayout = new PcmDirLayout();
	}

	@Override
	public void initialize(Path rootPath) {
		fileLayout.initialize(rootPath);
		loadOrCreateModelResources();
	}

	@Override
	public void reload() {
		loadOrCreateModelResources();
	}

	private void prepareFacade(System systemModel, Repository repoModel, ResourceEnvironment resourceEnvModel,
			UsageModel usageModel, Allocation allocationModel) {
		if (pcmResourceSet == null) {
			pcmResourceSet = new ResourceSetImpl();
		}

		// Unload and remove potential past resources
		pcmResourceSet.getResources().forEach((r) -> r.unload());
		pcmResourceSet.getResources().clear();

		// Add resources for PCM elements
		var sysRes = pcmResourceSet.createResource(fileLayout.getPcmSystemURI());
		sysRes.getContents().add(systemModel);
		var repoRes = pcmResourceSet.createResource(fileLayout.getPcmRepositoryURI());
		repoRes.getContents().add(repoModel);
		var resEnvRes = pcmResourceSet.createResource(fileLayout.getPcmResourceEnvironmentURI());
		resEnvRes.getContents().add(resourceEnvModel);
		var usageRes = pcmResourceSet.createResource(fileLayout.getPcmUsageModelURI());
		usageRes.getContents().add(usageModel);
		var allocRes = pcmResourceSet.createResource(fileLayout.getPcmAllocationURI());
		allocRes.getContents().add(allocationModel);

		pcmResourceSet.getResources().add(sysRes);
		pcmResourceSet.getResources().add(repoRes);
		pcmResourceSet.getResources().add(resEnvRes);
		pcmResourceSet.getResources().add(usageRes);
		pcmResourceSet.getResources().add(allocRes);

		pcm = new InMemoryPCM(repoModel, systemModel, usageModel, allocationModel, resourceEnvModel);
	}

	private void loadOrCreateModelResources() {
		if (!existsOnDisk()) {
			createModelResources();
		} else {
			loadFromDisk();
		}
	}

	public void createModelResources() {
		LOGGER.info("Creating new PCM");
		this.prepareFacade(SystemFactory.eINSTANCE.createSystem(), RepositoryFactory.eINSTANCE.createRepository(),
				ResourceenvironmentFactory.eINSTANCE.createResourceEnvironment(),
				UsagemodelFactory.eINSTANCE.createUsageModel(), AllocationFactory.eINSTANCE.createAllocation());

		// Create files and resources before binding the allocation
		saveToDisk();

		// Bind the allocation
		// This needs to occur after pcm.saveToFile
//        allocationModel.setSystem_Allocation(systemModel);
//        allocationModel.setTargetResourceEnvironment_Allocation(resourceEnvModel);
//
//        // save again for the allocation model
//        saveToDisk();
//        try {
//            allocationModel.eResource()
//                .save(null);
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
	}

	private boolean existsOnDisk() {
		return !List.of(fileLayout.getPcmRepositoryPath(), fileLayout.getPcmResourceEnvironmentPath(),
				fileLayout.getPcmUsageModelPath(), fileLayout.getPcmAllocationPath(), fileLayout.getPcmSystemPath())
				.stream().map(p -> p.toFile().isFile()).collect(Collectors.toList()).contains(false);
	}

	private void loadFromDisk() {
		// using createFromFilesystem causes strange errors when propagating the
		// resource
		// -> so we don't use it
		// pcm = InMemoryPCM.createFromFilesystem(filePcm);

		LOGGER.debug("Loading PCM from disk");

		var files = fileLayout.getFilePCM();
		this.prepareFacade(ModelUtil.readFromFile(files.getSystemFile(), System.class),
				ModelUtil.readFromFile(files.getRepositoryFile(), Repository.class),
				ModelUtil.readFromFile(files.getResourceEnvironmentFile(), ResourceEnvironment.class),
				ModelUtil.readFromFile(files.getUsageModelFile(), UsageModel.class),
				ModelUtil.readFromFile(files.getAllocationModelFile(), Allocation.class));

		// TODO Is saving to disk necessary, even if we freshly load the model?
		// saveToDisk();
	}

	public void saveToDisk() {
		pcm.saveToFilesystem(fileLayout.getFilePCM());
	}

	@Override
	public ResourceSet getResourceSet() {
		return pcmResourceSet;
	}

	public PcmDirLayout getDirLayout() {
		return fileLayout;
	}

	public InMemoryPCM getInMemoryPCM() {
		return pcm;
	}

	public Path createNamedCopyOfRepositoryModel(String name) throws IOException {
		var path = getDirLayout().getPcmRepositoryPath();
		var copyPath = path.resolveSibling(String.format("%s-%s%s", PcmDirLayout.getPcmRepositoryFileName(), name,
				PcmDirLayout.getPcmRepositoryFileExtension()));

		FileUtils.copyFile(path.toFile(), copyPath.toFile());

		return copyPath;
	}

	public Path createNamedCopyOfSystemModel(String name) throws IOException {
		var path = getDirLayout().getPcmSystemPath();
		var copyPath = path.resolveSibling(String.format("%s-%s%s", PcmDirLayout.getPcmSystemFileName(), name,
				PcmDirLayout.getPcmSystemFileExtension()));

		FileUtils.copyFile(path.toFile(), copyPath.toFile());

		return copyPath;
	}

	public Path createNamedCopyOfResourceEnvironmentModel(String name) throws IOException {
		var path = getDirLayout().getPcmResourceEnvironmentPath();
		var copyPath = path.resolveSibling(String.format("%s-%s%s", PcmDirLayout.getPcmResourceEnvironmentFileName(),
				name, PcmDirLayout.getPcmResourceEnvironmentFileExtension()));

		FileUtils.copyFile(path.toFile(), copyPath.toFile());

		return copyPath;
	}

	public Path createNamedCopyOfAllocationModel(String name) throws IOException {
		var path = getDirLayout().getPcmAllocationPath();
		var copyPath = path.resolveSibling(String.format("%s-%s%s", PcmDirLayout.getPcmAllocationFileName(), name,
				PcmDirLayout.getPcmAllocationFileExtension()));

		FileUtils.copyFile(path.toFile(), copyPath.toFile());

		return copyPath;
	}

	public Path createNamedCopyOfUsageModel(String name) throws IOException {
		var path = getDirLayout().getPcmUsageModelPath();
		var copyPath = path.resolveSibling(String.format("%s-%s%s", PcmDirLayout.getPcmUsageModelFileName(), name,
				PcmDirLayout.getPcmUsageModelFileExtension()));

		FileUtils.copyFile(path.toFile(), copyPath.toFile());

		return copyPath;
	}

	@Override
	public List<Path> createNamedCopy(String name) throws IOException {
		return List.of(this.createNamedCopyOfRepositoryModel(name), this.createNamedCopyOfSystemModel(name),
				this.createNamedCopyOfResourceEnvironmentModel(name), this.createNamedCopyOfAllocationModel(name),
				this.createNamedCopyOfUsageModel(name));
	}

	@Override
	public ResourceSet parseModel(Path modelDirPath) {
		// using createFromFilesystem causes strange errors when propagating the
		// resource
		// -> so we don't use it
		// pcm = InMemoryPCM.createFromFilesystem(filePcm);

		LOGGER.debug("Parsing PCM");

		fileLayout.initialize(modelDirPath);
		var files = fileLayout.getFilePCM();
		this.prepareFacade(ModelUtil.readFromFile(files.getSystemFile(), System.class),
				ModelUtil.readFromFile(files.getRepositoryFile(), Repository.class),
				ModelUtil.readFromFile(files.getResourceEnvironmentFile(), ResourceEnvironment.class),
				ModelUtil.readFromFile(files.getUsageModelFile(), UsageModel.class),
				ModelUtil.readFromFile(files.getAllocationModelFile(), Allocation.class));

		// TODO Is saving to disk necessary, even if we freshly parse the model?
		// saveToDisk();

		return this.pcmResourceSet;
	}
}

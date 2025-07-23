package cipm.consistency.models.im;

import java.io.IOException;
import java.nio.file.Path;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import cipm.consistency.base.models.instrumentation.InstrumentationModel.InstrumentationModel;
import cipm.consistency.base.models.instrumentation.InstrumentationModel.InstrumentationModelFactory;

import cipm.consistency.base.shared.FileBackedModelUtil;
import cipm.consistency.base.shared.ModelUtil;
import cipm.consistency.models.ModelFacade;

public class ImFacade implements ModelFacade {

	private ResourceSet imResourceSet;
	private ImDirLayout dirLayout;

	public ImFacade() {
		dirLayout = new ImDirLayout();
	}

	@Override
	public void initialize(Path rootPath) {
		dirLayout.initialize(rootPath);
		loadOrCreateModelResources();
	}

	@Override
	public void reload() {
		loadOrCreateModelResources();
	}

	public void saveToDisk() {
		FileBackedModelUtil.synchronize(this.getModel(), dirLayout.getImFilePath().toFile(),
				InstrumentationModel.class);
		try {
			this.getModel().eResource().save(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Persistently deactivates all instrumentation points in the underlying model.
	 */
	public void deactivateAllActionIPs() {
		this.getModel().getPoints()
				.forEach(sip -> sip.getActionInstrumentationPoints().forEach(aip -> aip.setActive(false)));
		this.saveToDisk();
	}

	private Resource getResourceInResourceSet() {
		return this.imResourceSet.getResources().get(0);
	}

	private void prepareFacade(InstrumentationModel im) {
		if (imResourceSet == null) {
			imResourceSet = new ResourceSetImpl();
		}

		// Unload and remove potential past resources
		imResourceSet.getResources().forEach((r) -> r.unload());
		imResourceSet.getResources().clear();

		// Add a new resource
		var imRes = imResourceSet.createResource(dirLayout.getImFileUri());
		imRes.getContents().add(im);
	}

	private void createModel() {
		// build IMM
		this.prepareFacade(InstrumentationModelFactory.eINSTANCE.createInstrumentationModel());
		saveToDisk();
	}

	private void loadModel() {
		this.prepareFacade(ModelUtil.readFromFile(dirLayout.getImFilePath().toFile(), InstrumentationModel.class));
	}

	public void loadOrCreateModelResources() {
		if (!fileExists()) {
			createModel();
		} else {
			loadModel();
		}
	}

	private boolean fileExists() {
		return dirLayout.getImFilePath().toFile().exists();
	}

	@Override
	public ImDirLayout getDirLayout() {
		return dirLayout;
	}

	public InstrumentationModel getModel() {
		return (InstrumentationModel) this.getResourceInResourceSet().getContents().get(0);
	}

	@Override
	public ResourceSet getResource() {
		return imResourceSet;
	}
}

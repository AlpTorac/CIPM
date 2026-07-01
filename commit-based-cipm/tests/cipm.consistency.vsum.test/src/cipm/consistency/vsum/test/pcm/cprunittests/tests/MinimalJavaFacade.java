package cipm.consistency.vsum.test.pcm.cprunittests.tests;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import cipm.consistency.models.code.CodeModelDirLayout;
import cipm.consistency.models.code.CodeModelFacade;

public class MinimalJavaFacade implements CodeModelFacade {
	private Resource currentResource;

	@Override
	public void initialize(Path resPath) {
		if (resPath == null) return;

		var resourceSet = new ResourceSetImpl();
		currentResource = resourceSet.getResource(URI.createFileURI(resPath.toString()), true);
	}

	public void setJavaResource(Resource javaRes) {
		this.currentResource = javaRes;
		try {
			this.currentResource.load(null);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public Resource parseSourceCodeDir(Path sourceCodeDir) {
		throw new UnsupportedOperationException();
	}

	@Override
	public CodeModelDirLayout getDirLayout() {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Resource> getResources() {
		return null;
	}

	@Override
	public Resource getResource() {
		return currentResource;
	}

	@Override
	public Path createNamedCopyOfParsedModel(String name) throws IOException {
		throw new UnsupportedOperationException();
	}

	public void saveToDisk() {
		if (this.currentResource != null) {
			try {
				this.currentResource.save(null);
			} catch (IOException e) {
				throw new IllegalStateException(e);
			}
		}
	}

	public void saveAndReload() {
		this.saveToDisk();
		this.reload();
	}

	@Override
	public void reload() {
		this.currentResource.unload();
		try {
			this.currentResource.load(null);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}
}

package cipm.consistency.models;

import java.nio.file.Path;
import java.util.List;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

public interface ModelFacade {

	public void initialize(Path rootPath);

	public ModelDirLayout getDirLayout();

	/**
	 * @return The resource set, which contains all resources of the model.
	 */
	public ResourceSet getResource();

	/**
	 * @return All resources of the model.
	 * @see {@link #getResource()}
	 */
	public default List<Resource> getResources() {
		var res = this.getResource();
		if (res != null) {
			return List.copyOf(res.getResources());
		}
		return null;
	}

	/**
	 * Reload models from disk
	 */
	public void reload();

//    public List<Resource> createModelResources();

//    public T getModel();

//    public List<Resource> loadOrCreateModelResources();

//    public void saveToDisk();

//    public boolean existsOnDisk();
}

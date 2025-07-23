package cipm.consistency.models;

import java.io.IOException;
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
	public ResourceSet getResourceSet();

	/**
	 * @return All resources of the model.
	 * @see {@link #getResourceSet()}
	 */
	public default List<Resource> getResources() {
		var res = this.getResourceSet();
		if (res != null) {
			return List.copyOf(res.getResources());
		}
		return null;
	}

	/**
	 * Reload models from disk
	 */
	public void reload();

	/**
	 * TODO Discuss whether this method here is OK
	 * 
	 * @param name The unique part (i.e. no common prefix or suffix in name schemes)
	 *             of this model's name
	 * @return The list of paths to the resources of this model.
	 */
	public List<Path> createNamedCopy(String name) throws IOException;

	public ResourceSet parseModel(Path modelDirPath);

//    public List<Resource> createModelResources();

//    public T getModel();

//    public List<Resource> loadOrCreateModelResources();

//    public void saveToDisk();

//    public boolean existsOnDisk();
}

package cipm.consistency.fitests.similarity.jamopp.parser;

import java.io.File;
import java.nio.file.Path;
import java.util.Collection;

/**
 * An interface for classes meant to find model directories. How model
 * directories are discovered and filtered depends on the concrete implementor.
 * <br>
 * <br>
 * Implemented minimally, as discovering model directories may span across
 * multiple "root" directories, and may have to be adapted heavily for concrete
 * scenarios.
 * 
 * @author Alp Torac Genc
 */
public interface IModelDirDiscoveryStrategy {
	/**
	 * Finds and returns paths to all directories that contain model directories.
	 * Use {@link #discoverModelDirs(File)} on the directories found here to get the
	 * actual model directories.
	 * 
	 * @param dirToDiscover The top-most directory, whose contents will be scanned
	 * 
	 * @return All directories containing model directories that are found according
	 *         to the concrete implementor.
	 */
	public Collection<Path> discoverModelParentDirs(File dirToDiscover);

	/**
	 * Finds and returns paths to all model directories.
	 * 
	 * @param dirToDiscover The top-most directory, whose contents will be scanned
	 * 
	 * @return All model directories that are found according to the concrete
	 *         implementor
	 */
	public Collection<Path> discoverModelDirs(File dirToDiscover);

	/**
	 * @param dir A directory that potentially contains files of one (and only one)
	 *            model
	 * @return Whether the given directory is contains files of one (and only one)
	 *         model
	 */
	public boolean isModelDirectory(File dir);
}

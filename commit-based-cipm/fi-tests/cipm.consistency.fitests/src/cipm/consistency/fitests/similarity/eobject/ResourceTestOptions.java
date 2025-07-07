package cipm.consistency.fitests.similarity.eobject;

/**
 * TODO Write proper commentary
 * 
 * Use Boolean instead of boolean to indicate that an option has not been set.
 * 
 * @author Alp Torac Genc
 */
public class ResourceTestOptions {
	private Boolean shouldUnloadAllResources;
	private Boolean shouldDeleteAllResources;
	private Boolean shouldSaveCachedResources;
	private Boolean shouldRemoveResourcesFromCache;

	public void setShouldUnloadAllResources(Boolean shouldUnloadAllResources) {
		this.shouldUnloadAllResources = shouldUnloadAllResources;
	}

	public void setShouldDeleteAllResources(Boolean shouldDeleteAllResources) {
		this.shouldDeleteAllResources = shouldDeleteAllResources;
	}

	public void setShouldSaveCachedResources(Boolean shouldSaveCachedResources) {
		this.shouldSaveCachedResources = shouldSaveCachedResources;
	}

	public void setShouldRemoveResourcesFromCache(Boolean shouldRemoveResourcesFromCache) {
		this.shouldRemoveResourcesFromCache = shouldRemoveResourcesFromCache;
	}

	/**
	 * Can be used to clean up memory, if the created resource files cause memory
	 * issues. Override in implementors, if necessary.
	 * 
	 * @return Whether all created resource instances should be unloaded after each
	 *         test. Defaults to true.
	 */
	public Boolean shouldUnloadAllResources() {
		return shouldUnloadAllResources;
	}

	/**
	 * Can be used to remove all created resource files, if they are not needed.
	 * Override if necessary.
	 * 
	 * @return Whether all created resource files should be deleted after each test.
	 *         Defaults to false.
	 */
	public Boolean shouldDeleteAllResources() {
		return shouldDeleteAllResources;
	}

	/**
	 * Override if necessary.
	 * 
	 * @return Whether the cached resources should be saved after each test.
	 */
	public Boolean shouldSaveCachedResources() {
		return shouldSaveCachedResources;
	}

	/**
	 * Override if necessary.
	 * 
	 * @return Whether cached resources should be removed after each test.
	 */
	public Boolean shouldRemoveResourcesFromCache() {
		return shouldRemoveResourcesFromCache;
	}
}

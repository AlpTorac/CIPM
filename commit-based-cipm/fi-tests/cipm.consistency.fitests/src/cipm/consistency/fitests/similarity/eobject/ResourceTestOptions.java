package cipm.consistency.fitests.similarity.eobject;

/**
 * TODO Write proper commentary
 * 
 * @author Alp Torac Genc
 */
public class ResourceTestOptions {
	private boolean shouldUnloadAllResources;
	private boolean shouldDeleteAllResources;

	public void setShouldUnloadAllResources(boolean shouldUnloadAllResources) {
		this.shouldUnloadAllResources = shouldUnloadAllResources;
	}

	public void setShouldDeleteAllResources(boolean shouldDeleteAllResources) {
		this.shouldDeleteAllResources = shouldDeleteAllResources;
	}

	/**
	 * Can be used to clean up memory, if the created resource files cause memory
	 * issues. Override in implementors, if necessary.
	 * 
	 * @return Whether all created resource instances should be unloaded after each
	 *         test. Defaults to true.
	 */
	public boolean shouldUnloadAllResources() {
		return shouldUnloadAllResources;
	}

	/**
	 * Can be used to remove all created resource files, if they are not needed.
	 * Override if necessary.
	 * 
	 * @return Whether all created resource files should be deleted after each test.
	 *         Defaults to false.
	 */
	public boolean shouldDeleteAllResources() {
		return shouldDeleteAllResources;
	}

	public void copyOptionsFrom(ResourceTestOptions opts) {
		this.shouldUnloadAllResources = opts.shouldUnloadAllResources;
		this.shouldDeleteAllResources = opts.shouldDeleteAllResources;
	}
}

package cipm.consistency.fitests.similarity.jamopp.parser;

import cipm.consistency.fitests.similarity.eobject.ResourceTestOptions;

public class ParserTestOptions extends ResourceTestOptions {
	private boolean shouldSaveCachedResources;
	private boolean shouldRemoveResourcesFromCache;

	public void setShouldSaveCachedResources(boolean shouldSaveCachedResources) {
		this.shouldSaveCachedResources = shouldSaveCachedResources;
	}

	public void setShouldRemoveResourcesFromCache(boolean shouldRemoveResourcesFromCache) {
		this.shouldRemoveResourcesFromCache = shouldRemoveResourcesFromCache;
	}

	/**
	 * Override if necessary.
	 * 
	 * @return Whether the cached resources should be saved after each test.
	 */
	public boolean shouldSaveCachedResources() {
		return shouldSaveCachedResources;
	}

	/**
	 * Override if necessary.
	 * 
	 * @return Whether cached resources should be removed after each test.
	 */
	public boolean shouldRemoveResourcesFromCache() {
		return shouldRemoveResourcesFromCache;
	}

	public void copyOptionsFrom(ParserTestOptions opts) {
		super.copyOptionsFrom(opts);
		this.shouldSaveCachedResources = opts.shouldSaveCachedResources;
		this.shouldRemoveResourcesFromCache = opts.shouldRemoveResourcesFromCache;
	}
}

package cipm.consistency.fitests.similarity.jamopp.parsertests;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;

/**
 * A utility object, which encapsulates caching logic (for parsed models) and
 * can be used to hasten tests.
 * 
 * @author Alp Torac Genc
 */
public class CacheUtil {
	private Map<String, Resource> resourceCache;

	public CacheUtil() {
		this.resourceCache = this.initResourceCache();
	}

	/**
	 * This is used from within the constructor.
	 * 
	 * @return The underlying map, which will be used to store the parsed models.
	 */
	protected Map<String, Resource> initResourceCache() {
		return new HashMap<>();
	}

	/**
	 * Intended to be used from its future sub-classes (if any).
	 * 
	 * @return The underlying data structure that is used for caching.
	 */
	protected Map<String, Resource> getResourceCache() {
		return this.resourceCache;
	}

	/**
	 * Adds the given resource with the given key to the cache. Replaces the
	 * resource, if the key is already in the cache.
	 * 
	 * @param key The key associated with the given resource
	 * @param res A given resource
	 */
	public void addToCache(String key, Resource res) {
		this.getResourceCache().put(key, res);
	}

	/**
	 * @return Gets the resource associated with the given key from the cache. Null,
	 *         if there is no such key in the cache.
	 */
	public Resource getFromCache(String key) {
		return this.getResourceCache().get(key);
	}

	/**
	 * @return All contents of the underlying cache
	 */
	public Map<String, Resource> getAllCacheContent() {
		return new HashMap<String, Resource>(this.getResourceCache());
	}

	/**
	 * @return Whether the given key is present in the cache.
	 */
	public boolean isInCache(String key) {
		return this.getResourceCache().containsKey(key);
	}

	/**
	 * Removes the cached resource associated with the given key.
	 */
	public void removeFromCache(String key) {
		this.getResourceCache().remove(key);
	}

	/**
	 * Removes all entries from the cache.
	 */
	public void cleanCache() {
		this.getResourceCache().clear();
	}
}

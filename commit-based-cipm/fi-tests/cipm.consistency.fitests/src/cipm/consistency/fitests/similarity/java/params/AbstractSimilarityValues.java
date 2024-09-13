package cipm.consistency.fitests.similarity.java.params;

import java.util.HashMap;
import java.util.Map;

/**
 * An abstract class for classes to implement, which uses a {@link Map} to store
 * mappings of ({@link Object} object class, {@link Object}
 * attribute) to their expected similarity checking results.
 * 
 * @see {@link #addSimilarityEntry(Class, Object, Boolean)} for more
 *      information on adding entries.
 * @see {@link #getExpectedSimilarityResult(Class, Object)} for more
 *      information on expected similarity checking results.
 * 
 * @author atora
 */
public abstract class AbstractSimilarityValues implements ISimilarityValues {
	/**
	 * The expected similarity checking result for cases, where there is no matching
	 * {@link SimilarityEntry} in this instance
	 * 
	 * @see {@link #getExpectedSimilarityResult(Class, Object)}
	 */
	private Boolean defaultSimResult = null;
	/**
	 * Maps {@link SimilarityEntry} to their expected similarity checking result.
	 * 
	 * @see {@link #getExpectedSimilarityResult(Class, Object)}
	 */
	private Map<SimilarityEntry, Boolean> similarityValues;

	/**
	 * Constructs an instance and initialises the internal data structure that
	 * stores expected similarity checking results.
	 * 
	 * @see {@link #addSimilarityEntry(Class, Object, Boolean)} for more
	 *      information on adding entries.
	 * @see {@link #getExpectedSimilarityResult(Class, Object)} for more
	 *      information on expected similarity checking results.
	 */
	public AbstractSimilarityValues() {
		this.similarityValues = this.initSimilarityValues();
	}

	/**
	 * @return Creates a {@link SimilarityEntry} with the given parameters.
	 */
	protected SimilarityEntry createEntry(Class<? extends Object> objCls, Object attr) {
		return new SimilarityEntry(objCls, attr);
	}

	/**
	 * First, tries to find a direct match for the given parameters. If there is no
	 * direct match, it relaxes objCls to super interfaces of objCls and tries to
	 * find a match again.
	 * 
	 * @param objCls The interface of the Object instance
	 * @param An     attribute of the Object instance, which is the subject of the
	 *               current similarity check
	 * @return The corresponding {@link SimilarityEntry}
	 */
	protected SimilarityEntry findEntry(Class<? extends Object> objCls, Object attr) {
		var directMatch = this.findDirectEntry(objCls, attr);

		return directMatch != null ? directMatch : this.findParentEntry(objCls, attr);
	}

	/**
	 * Tries to find a directly matching entry.
	 * 
	 * @param objCls The interface of the Object instance
	 * @param attr   An attribute of the Object instance, which is the subject of
	 *               the current similarity check
	 * @return The corresponding {@link SimilarityEntry}
	 */
	protected SimilarityEntry findDirectEntry(Class<? extends Object> objCls, Object attr) {
		return this.similarityValues.keySet().stream().filter((se) -> se.objCls.equals(objCls) && se.attr.equals(attr))
				.findFirst().orElse(null);
	}

	/**
	 * Tries to find an entry of a super interface using a depth-first recursion in
	 * the hierarchy of objCls towards Object. <br>
	 * <br>
	 * <b>This method DOES NOT look for a directly matching entry for objCls.</b>
	 * 
	 * @param objCls The interface of the Object instance
	 * @param attr   An attribute of the Object instance, which is the subject of
	 *               the current similarity check
	 * @return The corresponding {@link SimilarityEntry}
	 */
	protected SimilarityEntry findParentEntry(Class<? extends Object> objCls, Object attr) {
		var parents = objCls.getInterfaces();
		SimilarityEntry result = null;

		for (var p : parents) {
			result = this.findDirectEntry(p, attr);
			if (result != null)
				return result;

			result = this.findParentEntry(p, attr);
			if (result != null)
				return result;
		}

		return result;
	}

	/**
	 * @return The map that will be used by this instance.
	 */
	protected Map<SimilarityEntry, Boolean> initSimilarityValues() {
		return new HashMap<SimilarityEntry, Boolean>();
	}

	/**
	 * Adds the given {@link SimilarityEntry} along with its expected similarity
	 * result to this instance.
	 */
	protected void addEntry(SimilarityEntry se, Boolean expectedSimResult) {
		this.similarityValues.put(se, expectedSimResult);
	}

	public void addSimilarityEntry(Class<? extends Object> objCls, Object attr,
			Boolean expectedSimResult) {
		this.addEntry(this.createEntry(objCls, attr), expectedSimResult);
	}

	public void removeSimilarityEntry(Class<? extends Object> objCls, Object attr) {
		var se = this.findEntry(objCls, attr);

		if (se != null)
			this.similarityValues.remove(se);
	}

	public Boolean getExpectedSimilarityResult(Class<? extends Object> objCls, Object attr) {
		var se = this.findEntry(objCls, attr);

		return se == null ? this.getDefaultSimilarityResult() : this.similarityValues.get(se);
	}

	public void clear() {
		this.similarityValues.clear();
	}

	@Override
	public void setDefaultSimilarityResult(Boolean defSimRes) {
		this.defaultSimResult = defSimRes;
	}

	@Override
	public Boolean getDefaultSimilarityResult() {
		return this.defaultSimResult;
	}

	/**
	 * Contains a class object from an {@link Object} instance and an
	 * {@link Object} of the said instance.
	 * 
	 * @author atora
	 */
	protected class SimilarityEntry {
		/**
		 * The class of an {@link Object} instance.
		 */
		private final Class<? extends Object> objCls;
		/**
		 * An attribute of the {@link Object} instance.
		 */
		private final Object attr;

		/**
		 * @param objCls Class object from an {@link Object} instance
		 * @param attr   An attribute of the {@link Object} instance
		 */
		private SimilarityEntry(Class<? extends Object> objCls, Object attr) {
			this.objCls = objCls;
			this.attr = attr;
		}

		/**
		 * @return The class object stored in this instance.
		 */
		public Class<? extends Object> getObjCls() {
			return objCls;
		}

		/**
		 * @return The attribute stored in this entry.
		 */
		public Object getAttr() {
			return attr;
		}

		/**
		 * Compares two {@link SimilarityEntry} instances based on what they store.
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;

			if (obj == null || !(obj instanceof SimilarityEntry))
				return false;

			var castedO = (SimilarityEntry) obj;

			if (this.attr == null ^ castedO.attr == null)
				return false;

			if (this.objCls == null ^ castedO.objCls == null)
				return false;

			// Covers both members being null
			if (this.attr == castedO.attr && this.objCls == castedO.objCls)
				return true;

			return this.attr.equals(castedO.attr) && this.objCls.equals(castedO.objCls);
		}
	}
}

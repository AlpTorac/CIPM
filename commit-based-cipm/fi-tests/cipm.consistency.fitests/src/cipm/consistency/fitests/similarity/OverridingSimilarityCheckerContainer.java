package cipm.consistency.fitests.similarity;

import java.util.Collection;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

public class OverridingSimilarityCheckerContainer implements ISimilarityCheckerContainer {
	private final ISimilarityCheckerContainer scc;
	private final Set<SimilarityCheckerOverrideEntry> overrides = new HashSet<>();

	public OverridingSimilarityCheckerContainer(ISimilarityCheckerContainer scc) {
		this.scc = scc;
	}

	@Override
	public void newSimilarityChecker() {
		scc.newSimilarityChecker();
	}

	@Override
	public Boolean isSimilar(Object element1, Object element2) {
		return hasOverrideFor(element1, element2) ? getOverrideFor(element1, element2)
				: scc.isSimilar(element1, element2);
	}

	@Override
	public Boolean areSimilar(Collection<?> elements1, Collection<?> elements2) {
		if (elements1 == null || elements2 == null || (elements1.size() != elements2.size()))
			return scc.areSimilar(elements1, elements2);

		var it1 = elements1.iterator();
		var it2 = elements2.iterator();

		while (it1.hasNext()) {
			var elem1 = it1.next();
			var elem2 = it2.next();
			if (isSimilar(elem1, elem2) == Boolean.FALSE) {
				return Boolean.FALSE;
			}
		}

		return Boolean.TRUE;
	}

	public boolean hasOverrideFor(Object elem1, Object elem2) {
		return overrides.stream().anyMatch((e) -> elementsEqual(elem1, elem2, e));
	}

	public Boolean getOverrideFor(Object elem1, Object elem2) throws NoSuchElementException {
		return overrides.stream().filter((e) -> elementsEqual(elem1, elem2, e)).findFirst().get().resultOverride;
	}

	public void overrideFor(Object elem1, Object elem2, Boolean resultOverride) {
		var duplicate = overrides.stream().filter((e) -> elementsEqual(elem1, elem2, e)).findFirst();
		duplicate.ifPresent(overrides::remove);
		overrides.add(new SimilarityCheckerOverrideEntry(elem1, elem2, resultOverride));
	}

	public void overrideFor(Object[] elems1, Object[] elems2, Boolean resultOverride) {
		if (elems1.length != elems2.length)
			throw new IllegalArgumentException("Array sizes must be equal");

		for (int i = 0; i < elems1.length; i++) {
			overrideFor(elems1[i], elems2[i], resultOverride);
		}
	}

	public void resetOverrides() {
		this.overrides.clear();
	}

	private static boolean elementsEqual(Object elem1, Object elem2, SimilarityCheckerOverrideEntry entry) {
		return (entry.elem1 == elem1 && entry.elem2 == elem2) || (entry.elem2 == elem1 && entry.elem1 == elem2);
	}

	private static boolean elementsEqual(SimilarityCheckerOverrideEntry entry1, SimilarityCheckerOverrideEntry entry2) {
		return (entry1.elem1 == entry2.elem1 && entry1.elem2 == entry2.elem2)
				|| (entry1.elem2 == entry2.elem1 && entry1.elem1 == entry2.elem2);
	}

	private class SimilarityCheckerOverrideEntry {
		private final Object elem1;
		private final Object elem2;
		private final Boolean resultOverride;

		private SimilarityCheckerOverrideEntry(Object elem1, Object elem2, Boolean resultOverride) {
			this.elem1 = elem1;
			this.elem2 = elem2;
			this.resultOverride = resultOverride;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (!(obj instanceof SimilarityCheckerOverrideEntry))
				return false;

			var castedO = (SimilarityCheckerOverrideEntry) obj;

			return elementsEqual(this, castedO);
		}
	}
}

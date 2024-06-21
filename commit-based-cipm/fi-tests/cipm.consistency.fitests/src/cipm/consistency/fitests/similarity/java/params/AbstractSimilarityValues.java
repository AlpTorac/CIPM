package cipm.consistency.fitests.similarity.java.params;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public abstract class AbstractSimilarityValues implements ISimilarityValues {
	private Boolean defaultSimResult = null;
	private Map<SimilarityEntry, Boolean> similarityValues;
	
	public AbstractSimilarityValues() {
		this.similarityValues = this.initSimilarityValues();
	}
	
	protected SimilarityEntry createEntry(Class<? extends EObject> objCls, EStructuralFeature attr) {
		return new SimilarityEntry(objCls, attr);
	}
	
	protected SimilarityEntry findEntry(Class<? extends EObject> objCls, EStructuralFeature attr) {
		return this.similarityValues.keySet().stream()
				.filter((se) -> se.objCls.equals(objCls) && se.attr.equals(attr))
				.findFirst()
				.orElse(null);
	}
	
	protected Map<SimilarityEntry, Boolean> initSimilarityValues() {
		return new HashMap<SimilarityEntry, Boolean>();
	}
	
	protected void addEntry(SimilarityEntry se, Boolean expectedSimResult) {
		this.similarityValues.put(se, expectedSimResult);
	}
	
	@SuppressWarnings("unchecked")
	protected Class<? extends EObject> getClassFromStructuralFeature(EStructuralFeature attr) {
		return (Class<? extends EObject>) attr.getContainerClass();
	}
	
	public void addSimilarityEntry(EStructuralFeature attr, Boolean expectedSimResult) {
		this.addEntry(this.createEntry(this.getClassFromStructuralFeature(attr), attr), expectedSimResult);
	}
	
	public void addSimilarityEntry(Class<? extends EObject> objCls, EStructuralFeature attr, Boolean expectedSimResult) {
		this.addEntry(this.createEntry(objCls, attr), expectedSimResult);
	}
	
	public void removeSimilarityEntry(Class<? extends EObject> objCls, EStructuralFeature attr) {
		var se = this.findEntry(objCls, attr);
		
		if (se != null) this.similarityValues.remove(se);
	}
	
	public Boolean getExpectedSimilarityResult(Class<? extends EObject> objCls, EStructuralFeature attr) {
		var se = this.findEntry(objCls, attr);
		
		return se == null ? this.getDefaultSimilarityResult() : this.similarityValues.get(se);
	}
	
	public Boolean getExpectedSimilarityResult(EStructuralFeature attr) {
		return this.getExpectedSimilarityResult(this.getClassFromStructuralFeature(attr), attr);
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
	
	protected class SimilarityEntry {
		private final Class<? extends EObject> objCls;
		private final EStructuralFeature attr;
		
		private SimilarityEntry(Class<? extends EObject> objCls, EStructuralFeature attr) {
			this.objCls = objCls;
			this.attr = attr;
		}

		public Class<? extends EObject> getObjCls() {
			return objCls;
		}

		public EStructuralFeature getAttr() {
			return attr;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) return true;
			
			if (obj == null || !(obj instanceof SimilarityEntry)) return false;
			
			var castedO = (SimilarityEntry) obj;
			
			if (this.attr == null ^ castedO.attr == null) return false;
			
			if (this.objCls == null ^ castedO.objCls == null) return false;
			
			// Covers both members being null
			if (this.attr == castedO.attr && this.objCls == castedO.objCls) return true;
			
			return this.attr.equals(castedO.attr) &&
					this.objCls.equals(castedO.objCls);
		}
	}
}

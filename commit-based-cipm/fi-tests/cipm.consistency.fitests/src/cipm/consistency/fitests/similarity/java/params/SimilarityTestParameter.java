package cipm.consistency.fitests.similarity.java.params;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.EObject;

public class SimilarityTestParameter {
	private Map<SimilarityTestParameterEnum, Object> paramMap;
	
	public SimilarityTestParameter() {
		this.paramMap = this.initParamMap();
	}
	
	protected SimilarityTestParameter(Map<SimilarityTestParameterEnum, Object> paramMap) {
		this.paramMap = paramMap;
	}
	
	protected Map<SimilarityTestParameterEnum, Object> initParamMap() {
		return new EnumMap<SimilarityTestParameterEnum, Object>(SimilarityTestParameterEnum.class);
	}
	
	protected Map<SimilarityTestParameterEnum, Object> cloneParamMap() {
		var clone = this.initParamMap();
		
		for (var e : this.paramMap.entrySet()) {
			clone.put(e.getKey(), e.getValue());
		}
		
		return clone;
	}
	
	protected SimilarityTestParameter withParam(SimilarityTestParameterEnum key, Object val) {
		this.paramMap.put(key, val);
		return this;
	}
	
	public SimilarityTestParameter withObjLeft(EObject objLeft) {
		return this.withParam(SimilarityTestParameterEnum.OBJ_LEFT, objLeft);
	}
	
	public SimilarityTestParameter withObjRight(EObject objRight) {
		return this.withParam(SimilarityTestParameterEnum.OBJ_RIGHT, objRight);
	}
	
	public SimilarityTestParameter withExpectedSimResult(Boolean expectedSimilarityResult) {
		return this.withParam(SimilarityTestParameterEnum.EXPECTED_SIMILARITY_RESULT,
				expectedSimilarityResult);
	}
	
	public SimilarityTestParameter withTestType(String testType) {
		return this.withParam(SimilarityTestParameterEnum.TEST_TYPE, testType);
	}
	
	public <T extends EObject> T getObjLeft() {
		return this.getParam(SimilarityTestParameterEnum.OBJ_LEFT);
	}
	
	public <T extends EObject> T getObjRight() {
		return this.getParam(SimilarityTestParameterEnum.OBJ_RIGHT);
	}
	
	public Boolean getExpectedSimResult() {
		return this.getParam(SimilarityTestParameterEnum.EXPECTED_SIMILARITY_RESULT);
	}
	
	public String getTestType() {
		return this.getParam(SimilarityTestParameterEnum.TEST_TYPE);
	}
	
	@SuppressWarnings("unchecked")
	protected <T extends Object> T getParam(SimilarityTestParameterEnum key) {
		return (T) this.paramMap.get(key);
	}
	
	public Collection<SimilarityTestParameter> overrideAndPermute(SimilarityTestParameterEnum key, Object[] vals) {
		var result = new ArrayList<SimilarityTestParameter>();
		
		for (var val : vals) {
			var clone = this.cloneParamMap();
			clone.put(key, val);
			result.add(new SimilarityTestParameter(clone));
		}
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	protected void overrideAndPermute(Entry<SimilarityTestParameterEnum, Object[]>[] dimensions, final Collection<SimilarityTestParameter> current) {
		if (dimensions.length < 0) return;
		else if (dimensions.length > 1) {
			var dimToPermute = dimensions.length;
			var currentDim = dimensions[dimToPermute-1];
			
			Entry<SimilarityTestParameterEnum, Object[]>[] dims = new Entry[dimToPermute-1];
			
			for (int i = 0; i < dims.length; i++) {
				dims[i] = dimensions[i];
			}
			
			this.overrideAndPermute(dims, current);
			
			// Executed once all succeeding dimensions are permuted
			
			var toPermute = (SimilarityTestParameter[]) current.toArray();
			current.clear();
			
			for (var tp : toPermute) {
				tp.overrideAndPermute(currentDim.getKey(), currentDim.getValue()).forEach((e) -> current.add(e));
			}
		}
		else {
			current.addAll(this.overrideAndPermute(dimensions[0].getKey(), dimensions[0].getValue()));
		}
	}
	
	@SuppressWarnings("unchecked")
	public Collection<SimilarityTestParameter> overrideAndPermute(Map<SimilarityTestParameterEnum, Object[]> dimensions) {
		var result = new ArrayList<SimilarityTestParameter>();
		this.overrideAndPermute((Entry<SimilarityTestParameterEnum, Object[]>[])
				dimensions.entrySet().toArray(), result);
		return result;
	}
	
	@Override
	public String toString() {
		return this.getParam(SimilarityTestParameterEnum.TEST_TYPE).toString() +
				" with expected result: " +
				this.getParam(SimilarityTestParameterEnum.EXPECTED_SIMILARITY_RESULT).toString();
	}
	
	private enum SimilarityTestParameterEnum {
		OBJ_LEFT,
		OBJ_RIGHT,
		EXPECTED_SIMILARITY_RESULT,
		TEST_TYPE
		;
	}
}
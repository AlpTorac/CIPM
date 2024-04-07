package cipm.consistency.fitests.similarity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.Switch;
import org.splevo.jamopp.diffing.similarity.SimilarityComparer;

public class DummySimilarityComparer extends SimilarityComparer {
	private List<Switch<Boolean>> switchesToAdd;
	
	public DummySimilarityComparer(Map<Pattern, String> classifierNormalizations,
			Map<Pattern, String> compilationUnitNormalizations, Map<Pattern, String> packageNormalizations) {
		super(classifierNormalizations, compilationUnitNormalizations, packageNormalizations);
		
		this.switchesToAdd = new ArrayList<Switch<Boolean>>();
	}
	
	public DummySimilarityComparer() {
		this(
				new HashMap<Pattern, String>(),
				new HashMap<Pattern, String>(),
				new HashMap<Pattern, String>()
		);
	}
	
	public void setSwitches(List<Switch<Boolean>> newSwitches) {
		this.switchesToAdd = newSwitches;
	}
	
	@Override
    public Boolean checkSimilarityForResolvedAndSameType(EObject element1, EObject element2, boolean checkStatementPosition) {
    	var clone = this.clone(checkStatementPosition);
    	var dss = new DummySimilaritySwitch(clone);
    	dss.replaceSwitches(this.switchesToAdd);
    	return dss.compare(element1, element2);
    }
    
    @Override
    public DummySimilarityComparer clone(boolean checkStatementPosition) {
    	var clone = new DummySimilarityComparer();
    	
    	clone.setChecksStatementPositionOnDefault(checkStatementPosition);
    	return clone;
    }
}

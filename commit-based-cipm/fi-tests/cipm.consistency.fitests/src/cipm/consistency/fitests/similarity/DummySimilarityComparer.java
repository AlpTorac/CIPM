package cipm.consistency.fitests.similarity;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EObject;
import org.splevo.jamopp.diffing.similarity.SimilarityComparer;

public class DummySimilarityComparer extends SimilarityComparer {
	private InnerSwitchFactory switchFac;
	
	public DummySimilarityComparer(Map<Pattern, String> classifierNormalizations,
			Map<Pattern, String> compilationUnitNormalizations, Map<Pattern, String> packageNormalizations) {
		
		super(classifierNormalizations, compilationUnitNormalizations, packageNormalizations);
	}
	
	public DummySimilarityComparer() {
		this(
				new HashMap<Pattern, String>(),
				new HashMap<Pattern, String>(),
				new HashMap<Pattern, String>()
		);
	}
	
	public InnerSwitchFactory getSwitchFactory() {
		return this.switchFac;
	}
	
	public void setSwitchFactory(InnerSwitchFactory switchFac) {
		this.switchFac = switchFac;
	}
	
	@Override
    public Boolean checkSimilarityForResolvedAndSameType(EObject element1, EObject element2, boolean checkStatementPosition) {
    	var clone = this.clone(checkStatementPosition);
    	var dss = new DummySimilaritySwitch(clone);
    	dss.setSwitches(this.getSwitchFactory().createSwitchesFor(dss));
    	return dss.compare(element1, element2);
    }
    
    @Override
    public DummySimilarityComparer clone(boolean checkStatementPosition) {
    	var clone = new DummySimilarityComparer(
    				this.getClassifierNormalizations(),
    				this.getCompilationUnitNormalizations(),
    				this.getPackageNormalizations()
    			);
    	
    	clone.setChecksStatementPositionOnDefault(checkStatementPosition);
    	clone.setSwitchFactory(this.getSwitchFactory());
    	return clone;
    }
}

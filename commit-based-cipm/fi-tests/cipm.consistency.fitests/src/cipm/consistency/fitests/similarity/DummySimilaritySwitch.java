package cipm.consistency.fitests.similarity;

import java.util.List;

import org.eclipse.emf.ecore.util.Switch;
import org.splevo.jamopp.diffing.similarity.SimilaritySwitch;

public class DummySimilaritySwitch extends SimilaritySwitch {
	private List<Switch<Boolean>> switchesToAdd = null;

	public DummySimilaritySwitch(DummySimilarityComparer sc) {
		super(sc);
	}
	
	public DummySimilaritySwitch(DummySimilarityComparer sc,
			List<Switch<Boolean>> switchesToAdd) {
		super(sc);
		this.switchesToAdd = switchesToAdd;
		
		if (this.switchesToAdd != null) {
			this.switchesToAdd.forEach((s) -> this.addSwitch(s));
		}
	}
	
	@Override
	protected void initInnerSwitches() {
		// ToDo: Make this flexible in SimilaritySwitch by separating it from constructor
		// Maybe overload this method with a variant that gives control over which inner
		// switches will be used
	}
	
	public void replaceSwitches(List<Switch<Boolean>> switches) {
		if (this.switchesToAdd != null) {
			this.switchesToAdd.forEach((s) -> this.removeSwitch(s));
		}
		
		switches.forEach((s) -> this.addSwitch(s));
	}
}

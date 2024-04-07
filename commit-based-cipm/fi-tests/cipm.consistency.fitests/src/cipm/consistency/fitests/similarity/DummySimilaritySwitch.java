package cipm.consistency.fitests.similarity;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.util.Switch;
import org.splevo.jamopp.diffing.similarity.SimilaritySwitch;

public class DummySimilaritySwitch extends SimilaritySwitch implements HasInnerSwitches {
	private List<Switch<Boolean>> switches;

	public DummySimilaritySwitch(DummySimilarityComparer sc) {
		super(sc);
	}
	
	public DummySimilaritySwitch(DummySimilarityComparer sc,
			List<Switch<Boolean>> switchesToAdd) {
		super(sc);
		
		this.setSwitches(switchesToAdd);
	}
	
	@Override
	public DummySimilarityComparer getSimilarityComparer() {
		return (DummySimilarityComparer) super.getSimilarityComparer();
	}
	
	@Override
	public List<Switch<Boolean>> getSwitches() {
		if (this.switches == null) {
			this.switches = new ArrayList<Switch<Boolean>>();
		}
		
		return this.switches;
	}
	
	@Override
	public void addSwitch(Switch<Boolean> sw) {
		this.getSwitches().add(sw);
		super.addSwitch(sw);
	}
	
	@Override
	public void removeSwitch(Switch<Boolean> sw) {
		super.removeSwitch(sw);
		this.getSwitches().remove(sw);
	}
	
	@Override
	public void setSwitches(List<Switch<Boolean>> switches) {
		if (switches != null && !switches.isEmpty()) {
			this.clearSwitches();
			switches.forEach((s) -> this.addSwitch(s));
			assert this.getSwitches().size() == switches.size();
		}
	}
}

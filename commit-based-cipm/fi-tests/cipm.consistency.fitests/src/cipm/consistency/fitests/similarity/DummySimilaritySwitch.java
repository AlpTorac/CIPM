package cipm.consistency.fitests.similarity;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.util.Switch;
import org.splevo.jamopp.diffing.similarity.SimilaritySwitch;

public class DummySimilaritySwitch extends SimilaritySwitch {
	private final List<Switch<Boolean>> switchesToAdd = new ArrayList<Switch<Boolean>>();

	public DummySimilaritySwitch(DummySimilarityComparer sc) {
		this(sc, null);
	}
	
	public DummySimilaritySwitch(DummySimilarityComparer sc,
			List<Switch<Boolean>> switchesToAdd) {
		super(sc);
		
		this.replaceSwitches(switchesToAdd);
	}
	
	@Override
	public void addSwitch(Switch<Boolean> sw) {
		this.switchesToAdd.add(sw);
		super.addSwitch(sw);
	}
	
	@Override
	public void removeSwitch(Switch<Boolean> sw) {
		super.removeSwitch(sw);
		this.switchesToAdd.remove(sw);
	}
	
	public void replaceSwitches(List<Switch<Boolean>> switches) {
		if (switches != null) {
			this.clearSwitches();
			switches.forEach((s) -> this.addSwitch(s));
		}
	}
	
	public void clearSwitches() {
		this.switchesToAdd.forEach((s) -> this.removeSwitch(s));
	}
}

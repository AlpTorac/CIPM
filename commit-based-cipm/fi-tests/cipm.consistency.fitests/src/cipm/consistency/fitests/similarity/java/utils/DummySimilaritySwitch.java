package cipm.consistency.fitests.similarity.java.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.util.Switch;
import org.splevo.jamopp.diffing.similarity.JavaSimilaritySwitch;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequestHandler;

public class DummySimilaritySwitch extends JavaSimilaritySwitch implements HasInnerSwitches {
	private List<Switch<Boolean>> switches;
	
	public DummySimilaritySwitch(ISimilarityRequestHandler srh, boolean checkStatementPosition) {
		super(srh, checkStatementPosition);
	}
	
	public DummySimilaritySwitch(ISimilarityRequestHandler srh) {
		super(srh);
	}
	
	public DummySimilaritySwitch(ISimilarityRequestHandler srh,
			Collection<Switch<Boolean>> switches) {
		super(srh, switches);
	}
	
    public DummySimilaritySwitch(ISimilarityRequestHandler srh,
    		Switch<Boolean>[] switches) {
    	super(srh, switches);
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

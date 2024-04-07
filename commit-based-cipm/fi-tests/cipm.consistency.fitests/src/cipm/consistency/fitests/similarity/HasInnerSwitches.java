package cipm.consistency.fitests.similarity;

import java.util.List;

import org.eclipse.emf.ecore.util.Switch;

public interface HasInnerSwitches {
	public List<Switch<Boolean>> getSwitches();
	public void setSwitches(List<Switch<Boolean>> newSwitches);
	
	public default void addSwitch(Switch<Boolean> switchToAdd) {
		this.getSwitches().add(switchToAdd);
	}
	
	public default void removeSwitch(Switch<Boolean> switchToRemove) {
		this.getSwitches().remove(switchToRemove);
	}
	
	public default int getSwitchCount() {
		return this.getSwitches().size();
	}
	
	public default List<Switch<Boolean>> cloneSwitchList() {
		return this.getSwitches().subList(0, this.getSwitchCount());
	}

	public default void clearSwitches() {
		@SuppressWarnings("unchecked")
		var switches = (Switch<Boolean>[]) this.getSwitches().toArray(Switch[]::new);
		
		for (var s : switches) {
			this.removeSwitch(s);
		}
	}
}

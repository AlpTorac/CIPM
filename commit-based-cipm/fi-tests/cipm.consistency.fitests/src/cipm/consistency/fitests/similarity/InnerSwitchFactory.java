package cipm.consistency.fitests.similarity;

import java.util.List;

import org.eclipse.emf.ecore.util.Switch;

public interface InnerSwitchFactory {
	public List<Switch<Boolean>> createSwitchesFor(DummySimilaritySwitch dss);
}

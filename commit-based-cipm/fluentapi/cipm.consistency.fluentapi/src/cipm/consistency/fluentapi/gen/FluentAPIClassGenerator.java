package cipm.consistency.fluentapi.gen;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcoreFactory;

public class FluentAPIClassGenerator {
	// TODO Extract fluentAPIClassName
	private static final String fluentAPIClassName = "FluentEObjectAPI";

	public EClass getFluentAPIClass() {
		var fluentAPICls = EcoreFactory.eINSTANCE.createEClass();
		fluentAPICls.setAbstract(false);
		fluentAPICls.setInterface(false);
		fluentAPICls.setName(fluentAPIClassName);
		return fluentAPICls;
	}
}

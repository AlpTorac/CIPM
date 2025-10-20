package cipm.consistency.fluentapi.gen;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcoreFactory;

public class FluentAPIRootClassGenerator {
	// TODO Extract fluentAPIClassName
	private static final String fluentAPIClassName = "FluentEObjectAPI";

	public EClass getFluentAPIRootClass() {
		var fluentAPICls = EcoreFactory.eINSTANCE.createEClass();
		fluentAPICls.setAbstract(false);
		fluentAPICls.setInterface(false);
		fluentAPICls.setName(fluentAPIClassName);
		return fluentAPICls;
	}
}

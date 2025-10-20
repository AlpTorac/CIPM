package cipm.consistency.fluentapi.gen;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcoreFactory;

public class FluentAPISuperInitialisationGenerator {
	/**
	 * Named this way to make sure that the generated EClasses do not have the same
	 * name
	 */
	private static final String fluentAPISuperInitialisationName = "FluentAPISuperInitialisation";

	public EClass generateFluentAPISuperInitialisationEClass() {
		var superType = EcoreFactory.eINSTANCE.createEClass();
		superType.setAbstract(true);
		superType.setInterface(false);
		superType.setName(fluentAPISuperInitialisationName);

		return superType;
	}
}

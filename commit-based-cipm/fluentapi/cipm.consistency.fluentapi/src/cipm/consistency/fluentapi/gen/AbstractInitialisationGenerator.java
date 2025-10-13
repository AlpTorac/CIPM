package cipm.consistency.fluentapi.gen;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcoreFactory;

public class AbstractInitialisationGenerator {
	private static final String fluentAPISubClassesSuperTypeName = "AbstractInitialisation";
	private EClass abstractInitialisationEClass;

	public EClass generateFluentAPISuperType() {
		if (abstractInitialisationEClass == null) {
			var superType = EcoreFactory.eINSTANCE.createEClass();
			// TODO Turn into generic type
			superType.setAbstract(true);
			superType.setInterface(false);
			superType.setName(fluentAPISubClassesSuperTypeName);
			this.abstractInitialisationEClass = superType;
		}
		return abstractInitialisationEClass;
	}
}

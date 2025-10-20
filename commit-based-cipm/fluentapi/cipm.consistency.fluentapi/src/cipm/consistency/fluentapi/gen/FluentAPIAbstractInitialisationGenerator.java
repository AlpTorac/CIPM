package cipm.consistency.fluentapi.gen;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcoreFactory;

public class FluentAPIAbstractInitialisationGenerator {
	private static final String fluentAPISubClassesSuperTypeName = "AbstractInitialisation";

	public EClass generateAbstractInitialisationEClass() {
		var superType = EcoreFactory.eINSTANCE.createEClass();
		superType.setAbstract(true);
		superType.setInterface(false);
		superType.setName(fluentAPISubClassesSuperTypeName);

		return superType;
	}
}

package cipm.consistency.fluentapi.gen.init;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;

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

		superType.getEOperations().add(new FluentAPISuperInitialisationGetInitialisedEClassMethodGenerator()
				.generateGetInitialisedEClassMethod());

		superType.getEOperations().addAll(
				new FluentAPICreateNowMethodGenerator().generateAllCreateNowMethods(EcorePackage.Literals.EOBJECT));

		superType.getEOperations()
				.add(new FluentAPISuperInitialisationNewElementMethodGenerator().generateNewElementMethod(superType));

		superType.getEOperations().add(
				new FluentAPISuperInitialisationDropOperationGenerator().generateDropInitialisationMethod(superType));

		superType.getEOperations()
				.add(new FluentAPIInitialisationResetOperationGenerator().generateResetInitialisationMethod(superType));

		superType.getEOperations()
				.addAll(new FluentAPIInitialisationMarkMethodGenerator().generateAllMarkMethods(superType));

		return superType;
	}
}

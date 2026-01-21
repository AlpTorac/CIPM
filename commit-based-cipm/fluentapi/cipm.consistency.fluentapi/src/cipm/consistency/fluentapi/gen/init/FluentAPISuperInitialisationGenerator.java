package cipm.consistency.fluentapi.gen.init;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.FluentAPISuperInitialisationConstants;

public class FluentAPISuperInitialisationGenerator {
	public EClass generateFluentAPISuperInitialisationEClass() {
		var superType = EcoreFactory.eINSTANCE.createEClass();
		superType.setAbstract(true);
		superType.setInterface(false);
		superType.setName(FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationClassName());

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

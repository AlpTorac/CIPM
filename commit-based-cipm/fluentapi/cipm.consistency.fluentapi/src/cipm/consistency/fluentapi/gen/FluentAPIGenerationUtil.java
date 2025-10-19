package cipm.consistency.fluentapi.gen;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcoreFactory;

public class FluentAPIGenerationUtil {
	public static boolean isConcrete(EClass elemToInit) {
		return !elemToInit.isAbstract() && !elemToInit.isInterface();
	}

	public static EClass getEObjectEClass() {
		return EcoreFactory.eINSTANCE.createEObject().eClass();
	}

	public static EClass getEClassEClass() {
		return EcoreFactory.eINSTANCE.createEClass().eClass();
	}
}

package cipm.consistency.fluentapi.gen;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;

public class FluentAPICurrentElementReferenceGenerator {
	private static final String currentElementReferenceName = "currentElement";

	public static String getCurrentElementReferenceName() {
		return currentElementReferenceName;
	}

	public EReference getCurrentElementReference(EClass initialisedEClass) {
		var currentElementReference = EcoreFactory.eINSTANCE.createEReference();
		currentElementReference.setChangeable(true);
		currentElementReference.setContainment(false);
		currentElementReference.setEType(initialisedEClass);
		currentElementReference.setName(getCurrentElementReferenceName());
		currentElementReference.setUnsettable(true);
		currentElementReference.setLowerBound(1);
		currentElementReference.setUpperBound(1);
		return currentElementReference;
	}
}

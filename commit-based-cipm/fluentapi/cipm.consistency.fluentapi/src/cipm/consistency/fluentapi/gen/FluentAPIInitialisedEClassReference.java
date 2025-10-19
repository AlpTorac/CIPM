package cipm.consistency.fluentapi.gen;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;

public class FluentAPIInitialisedEClassReference {
	private static final String initialisedEClassReferenceName = "initialisedEClass";

	public static String getInitialisedEClassReferenceName() {
		return initialisedEClassReferenceName;
	}

	public EReference getInitialisedEClassReference() {
		var initialisedEClassReference = EcoreFactory.eINSTANCE.createEReference();
		initialisedEClassReference.setChangeable(false);
		initialisedEClassReference.setContainment(false);
		initialisedEClassReference.setEType(EcoreFactory.eINSTANCE.createEClass().eClass());
		initialisedEClassReference.setName(getInitialisedEClassReferenceName());
		initialisedEClassReference.setUnsettable(false);
		initialisedEClassReference.setLowerBound(1);
		initialisedEClassReference.setUpperBound(1);
		return initialisedEClassReference;
	}
}

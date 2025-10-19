package cipm.consistency.fluentapi.gen;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;

public class FluentAPIInitialisationEClassesReferenceGenerator {
	private static final String initialisationsReferenceName = "inits";
	/**
	 * {@value #initialisationsReferenceName} is a many-valued, non-containment
	 * EReference containing EClass instances. It is assumed that these EClasses
	 * each belong to a XInitialisation type.
	 * 
	 * @return The EReference responsible for containing EClass of each concrete
	 *         initialisation XInitialisation
	 */
	public EReference getInitialisationEClassesReference() {
		var initialisationsRef = EcoreFactory.eINSTANCE.createEReference();
		initialisationsRef.setChangeable(true);
		initialisationsRef.setContainment(false);
		initialisationsRef.setEType(EcoreFactory.eINSTANCE.createEClass().eClass());
		initialisationsRef.setName(initialisationsReferenceName);
		initialisationsRef.setLowerBound(0);
		initialisationsRef.setUpperBound(EReference.UNBOUNDED_MULTIPLICITY);
		return initialisationsRef;
	}
}

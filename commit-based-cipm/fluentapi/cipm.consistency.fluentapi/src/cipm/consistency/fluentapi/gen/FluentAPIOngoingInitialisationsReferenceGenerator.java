package cipm.consistency.fluentapi.gen;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;

public class FluentAPIOngoingInitialisationsReferenceGenerator {
	private static final String ongoingInitialisationsReferenceName = "ongoingInits";
	/**
	 * {@value #initialisationsReferenceName} is a many-valued, non-containment
	 * EReference containing AbstractInitialisation instances.
	 * 
	 * @return The EReference responsible for containing each concrete
	 *         initialisation AbstractInitialisation belonging to an ongoing
	 *         initialisation
	 */
	public EReference getOngoingInitialisationsReference(EClass initsSuperTypeEClass) {
		var ongoingInitsRef = EcoreFactory.eINSTANCE.createEReference();
		ongoingInitsRef.setChangeable(true);
		ongoingInitsRef.setContainment(false);
		ongoingInitsRef.setEType(initsSuperTypeEClass);
		ongoingInitsRef.setName(ongoingInitialisationsReferenceName);
		ongoingInitsRef.setLowerBound(0);
		ongoingInitsRef.setUpperBound(EReference.UNBOUNDED_MULTIPLICITY);
		return ongoingInitsRef;
	}
}

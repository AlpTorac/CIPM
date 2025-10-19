package cipm.consistency.fluentapi.gen;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;

public class FluentAPIRootAPIReferenceGenerator {
	private static final String rootAPIReferenceName = "rootAPI";

	public static String getRootAPIReferenceName() {
		return rootAPIReferenceName;
	}

	public EReference getRootAPIReference(EClass rootAPIEClass) {
		var rootAPIRef = EcoreFactory.eINSTANCE.createEReference();
		rootAPIRef.setChangeable(true);
		rootAPIRef.setContainment(false);
		rootAPIRef.setEType(rootAPIEClass);
		rootAPIRef.setName(getRootAPIReferenceName());
		rootAPIRef.setLowerBound(1);
		rootAPIRef.setUpperBound(1);
		return rootAPIRef;
	}
}

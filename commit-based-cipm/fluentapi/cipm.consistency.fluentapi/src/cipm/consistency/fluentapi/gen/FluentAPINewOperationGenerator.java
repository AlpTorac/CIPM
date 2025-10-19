package cipm.consistency.fluentapi.gen;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EcoreFactory;

import cipm.consistency.fluentapi.gen.methods.AbstractInitialisationMethods;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;

public class FluentAPINewOperationGenerator {
	private static final String genModelURL = "http://www.eclipse.org/emf/2002/GenModel";

	private static final String newOperationNamePrefix = "new";
	private static final String eOperationBodyKey = "body";
	
	public EOperation getNewOperationFor(EClass elemToInit) {
		var op = EcoreFactory.eINSTANCE.createEOperation();
		var elemInstanceName = elemToInit.getInstanceClass().getSimpleName();
		op.setName(newOperationNamePrefix + elemInstanceName);
		op.setEType(elemToInit);

		// Add the method body
		var anno = EcoreFactory.eINSTANCE.createEAnnotation();
		anno.setSource(genModelURL);
		// TODO Add hooks to creation methods (?)
		var bodyValue = getNewOperationBodyFor(elemToInit);
		anno.getDetails().put(eOperationBodyKey, bodyValue);

		op.getEAnnotations().add(anno);
		return op;
	}

	public String getNewOperationBodyFor(EClass elemToInit) {
		return FluentAPIMethodsUtil.callMethodWithThisArgumentAndReturnThis(AbstractInitialisationMethods.class, "newElement");
	}
}

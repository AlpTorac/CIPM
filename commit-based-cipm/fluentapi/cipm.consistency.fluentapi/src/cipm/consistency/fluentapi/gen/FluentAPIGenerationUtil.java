package cipm.consistency.fluentapi.gen;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EcoreFactory;

public class FluentAPIGenerationUtil {
	private static final String eoperationBodyKey = "body";

	public static String getEOperationBodyKey() {
		return eoperationBodyKey;
	}

	public static boolean isConcrete(EClass elemToInit) {
		return !elemToInit.isAbstract() && !elemToInit.isInterface();
	}

	public static EClass getEObjectEClass() {
		return EcoreFactory.eINSTANCE.createEObject().eClass();
	}

	public static EClass getEClassEClass() {
		return EcoreFactory.eINSTANCE.createEClass().eClass();
	}

	public static EParameter generateSingleValuedEParameter(String name, EClassifier type) {
		var param = EcoreFactory.eINSTANCE.createEParameter();
		param.setName(name);
		param.setEType(type);
		param.setLowerBound(1);
		param.setUpperBound(1);
		return param;
	}

	public static EOperation generateEOperationWithBody(String name, String genSource, String methodBody,
			EParameter... params) {
		var op = EcoreFactory.eINSTANCE.createEOperation();
		op.setName(name);

		if (params != null) {
			for (var param : params)
				op.getEParameters().add(param);
		}

		// Add the method body
		var anno = EcoreFactory.eINSTANCE.createEAnnotation();
		anno.setSource(genSource);
		anno.getDetails().put(getEOperationBodyKey(), methodBody);
		op.getEAnnotations().add(anno);
		return op;
	}
}

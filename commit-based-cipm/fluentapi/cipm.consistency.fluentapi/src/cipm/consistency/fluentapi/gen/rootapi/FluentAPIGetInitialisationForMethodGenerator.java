package cipm.consistency.fluentapi.gen.rootapi;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.methods.FluentEObjectAPIMethods;

public class FluentAPIGetInitialisationForMethodGenerator {

	private static final String genModelURL = "http://www.eclipse.org/emf/2002/GenModel";

	private static final String getInitialisationMethodName = "getInitialisationForX";

	private static final String getInitialisationParamName = "eClsToInit";
	private static final String getInitialisationMethodBody = FluentAPIMethodsUtil
			.joinLOC("return (%s)" + FluentEObjectAPIMethods.class.getName() + ".getInitialisationForX(this, %s)");

	private static final String getInitialisationForClassParamName = "clsToInit";
	private static final String getInitialisationForClassMethodBody = FluentAPIMethodsUtil
			.joinLOC("return (%s)" + FluentEObjectAPIMethods.class.getName() + ".getInitialisationForX(this, %s)");

	private static final String getInitialisationForEObjectParamName = "eobjToInit";
	private static final String getInitialisationForEObjectMethodBody = FluentAPIMethodsUtil
			.joinLOC("return (%s)" + FluentEObjectAPIMethods.class.getName() + ".getInitialisationForX(this, %s)");

	public EOperation getInitialisationForEClassMethod(EClass initsSuperType) {
		var param = getInitialisationForEClassParam();
		return FluentAPIGenerationUtil
				.generateEOperationWithBody(getInitialisationMethodName, genModelURL, initsSuperType,
						String.format(getInitialisationMethodBody,
								FluentAPIGenerationUtil.getFullyQualifiedEClassName(initsSuperType), param.getName()),
						param);
	}

	public EParameter getInitialisationForEClassParam() {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(getInitialisationParamName,
				FluentAPIGenerationUtil.getEClassEClass());
	}

	public EOperation getInitialisationForClassMethod(EClass initsSuperType) {
		var param = getInitialisationForClassParam();
		return FluentAPIGenerationUtil
				.generateEOperationWithBody(getInitialisationMethodName, genModelURL, initsSuperType,
						String.format(getInitialisationForClassMethodBody,
								FluentAPIGenerationUtil.getFullyQualifiedEClassName(initsSuperType), param.getName()),
						param);
	}

	public EParameter getInitialisationForClassParam() {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(getInitialisationForClassParamName,
				EcorePackage.Literals.EJAVA_CLASS);
	}

	public EOperation getInitialisationForEObjectMethod(EClass initsSuperType) {
		var param = getInitialisationForEObjectParam();
		return FluentAPIGenerationUtil
				.generateEOperationWithBody(getInitialisationMethodName, genModelURL, initsSuperType,
						String.format(getInitialisationForEObjectMethodBody,
								FluentAPIGenerationUtil.getFullyQualifiedEClassName(initsSuperType), param.getName()),
						param);
	}

	public EParameter getInitialisationForEObjectParam() {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(getInitialisationForEObjectParamName,
				EcorePackage.Literals.EOBJECT);
	}
}

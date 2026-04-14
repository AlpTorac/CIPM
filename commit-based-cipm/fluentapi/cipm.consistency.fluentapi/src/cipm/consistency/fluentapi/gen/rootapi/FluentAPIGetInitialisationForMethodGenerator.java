package cipm.consistency.fluentapi.gen.rootapi;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.FluentAPIRootAPIConstants;
import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelPackageProvider;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.methods.FluentEObjectAPIMethods;

public class FluentAPIGetInitialisationForMethodGenerator {
	// TODO Add documentation

	private static final String getInitialisationMethodBody = FluentAPIMethodsUtil
			.joinLOC("return (%s)" + FluentEObjectAPIMethods.class.getName() + ".getInitialisationForX(this, "
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIGetInitialisationForEClassParameterName() + ")");

	private static final String getInitialisationForClassMethodBody = FluentAPIMethodsUtil
			.joinLOC("return (%s)" + FluentEObjectAPIMethods.class.getName() + ".getInitialisationForX(this, "
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIGetInitialisationForClassParameterName() + ")");

	private static final String getInitialisationForEObjectMethodBody = FluentAPIMethodsUtil
			.joinLOC("return (%s)" + FluentEObjectAPIMethods.class.getName() + ".getInitialisationForX(this, "
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIGetInitialisationForEObjectParameterName() + ")");

	public EOperation getInitialisationForEClassMethod(EClass initsSuperType, FluentAPITargetMetamodelPackageProvider provider) {
		var param = getInitialisationForEClassParam();
		return FluentAPIGenerationUtil.generateEOperationWithBody(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIGetInitialisationForMethodName(), initsSuperType,
				String.format(getInitialisationMethodBody,
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(initsSuperType)),
				param);
	}

	public EParameter getInitialisationForEClassParam() {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIGetInitialisationForEClassParameterName(),
				EcorePackage.Literals.ECLASS);
	}

	public EOperation getInitialisationForClassMethod(EClass initsSuperType, FluentAPITargetMetamodelPackageProvider provider) {
		var param = getInitialisationForClassParam();
		return FluentAPIGenerationUtil.generateEOperationWithBody(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIGetInitialisationForMethodName(), initsSuperType,
				String.format(getInitialisationForClassMethodBody,
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(initsSuperType)),
				param);
	}

	public EParameter getInitialisationForClassParam() {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIGetInitialisationForClassParameterName(),
				EcorePackage.Literals.EJAVA_CLASS);
	}

	public EOperation getInitialisationForEObjectMethod(EClass initsSuperType, FluentAPITargetMetamodelPackageProvider provider) {
		var param = getInitialisationForEObjectParam();
		return FluentAPIGenerationUtil.generateEOperationWithBody(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIGetInitialisationForMethodName(), initsSuperType,
				String.format(getInitialisationForEObjectMethodBody,
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(initsSuperType)),
				param);
	}

	public EParameter getInitialisationForEObjectParam() {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIGetInitialisationForEObjectParameterName(),
				EcorePackage.Literals.EOBJECT);
	}
}

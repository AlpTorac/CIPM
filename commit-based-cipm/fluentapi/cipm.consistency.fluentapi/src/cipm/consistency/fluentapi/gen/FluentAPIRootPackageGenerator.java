package cipm.consistency.fluentapi.gen;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;

public class FluentAPIRootPackageGenerator {
	private static final String fluentAPIRootPacName = "api";

	public EPackage generateRootPackage(FluentAPITargetMetamodelPackageProvider targetMetamodelPackageProvider) {
		var rootPac = EcoreFactory.eINSTANCE.createEPackage();
		rootPac.setName(fluentAPIRootPacName);
		rootPac.setNsPrefix(fluentAPIRootPacName);
		rootPac.setNsURI(URI.createFileURI(fluentAPIRootPacName).toString());
		return rootPac;
	}
}

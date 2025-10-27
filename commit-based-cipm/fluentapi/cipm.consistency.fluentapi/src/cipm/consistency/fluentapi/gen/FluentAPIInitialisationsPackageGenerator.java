package cipm.consistency.fluentapi.gen;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;

public class FluentAPIInitialisationsPackageGenerator {
	private static final String pacName = "inits";
	private static final String fullPacName = FluentAPIRootPackageGenerator.getRootPackageName() + "." + pacName;

	public static URI getPackageURI() {
		return FluentAPIRootPackageGenerator.getRootPackageURI().appendSegment(pacName);
	}

	public static String getPackageName() {
		return pacName;
	}

	public static String getFullPackageName() {
		return fullPacName;
	}

	public EPackage generateInitialisationsPackage(EPackage rootPac) {
		return FluentAPIGenerationUtil.generateSubPackage(rootPac, getPackageName());
	}
}

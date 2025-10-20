package cipm.consistency.fluentapi.gen;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;

public class FluentAPIRootPackageGenerator {
	private static final String rootPacName = FluentAPIRootPackageGenerator.class.getPackageName().replaceAll("\\.gen",
			".api");
	private static final String srcDirName = "src";

	public List<EPackage> generateRootPackage(FluentAPITargetMetamodelPackageProvider targetMetamodelPackageProvider) {
		return generatePackages(rootPacName);
	}

	private List<EPackage> generatePackages(String fullPacName) {
		var pacs = new ArrayList<EPackage>();
		var currentURI = URI.createFileURI(new File("").getAbsolutePath());
		var nss = List.of(fullPacName.split("\\."));
		for (int i = 0; i < nss.size(); i++) {
			var pacName = nss.get(i);
			var pacNss = nss.subList(0, i);

			var pac = EcoreFactory.eINSTANCE.createEPackage();
			pac.setName(pacName);
			pac.setNsPrefix(pacName);

			var nsUri = currentURI.appendSegment(srcDirName).appendSegments(pacNss.toArray(String[]::new))
					.appendSegment(pacName);
			pac.setNsURI(nsUri.toString());
			pacs.add(pac);
		}

		for (int i = 1; i < pacs.size(); i++) {
			pacs.get(i - 1).getESubpackages().add(pacs.get(i));
		}

		return pacs;
	}
}

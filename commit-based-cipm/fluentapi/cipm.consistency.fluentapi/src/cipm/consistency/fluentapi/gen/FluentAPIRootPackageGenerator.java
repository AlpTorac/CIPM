package cipm.consistency.fluentapi.gen;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;

public class FluentAPIRootPackageGenerator {
	private static final String rootPacName = FluentAPIRootPackageGenerator.class.getPackageName().replaceAll("\\.gen",
			".api");
	private static final String srcDirName = "src";

	public static String getRootPackageName() {
		return rootPacName;
	}

	public static Path getRootPackageDirectoryPath() {
		return Path.of(rootPacName, srcDirName);
	}

	public static URI getRootPackageURI() {
		return URI.createFileURI(new File("").getAbsolutePath()).appendSegment(srcDirName);
	}

	public List<EPackage> generateRootPackage() {
		return FluentAPIGenerationUtil.generatePackages(getRootPackageURI(), getRootPackageName());
	}
}

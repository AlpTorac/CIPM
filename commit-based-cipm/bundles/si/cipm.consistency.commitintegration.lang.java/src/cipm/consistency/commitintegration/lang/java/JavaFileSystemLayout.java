package cipm.consistency.commitintegration.lang.java;

import java.nio.file.Path;

import cipm.consistency.models.ModelDirLayoutImpl;

/**
 * This class represents the layout on the file system related to the Java
 * models.
 * 
 * @author Martin Armbruster
 */
public class JavaFileSystemLayout extends ModelDirLayoutImpl {
	private static final String javaModelFileName = "Java.javaxmi";

	private static final String moduleConfigurationFileName = "module-configuration.properties";

	private static final String externalCallTargetPairsFileName = "external-call-target-pairs.json";

	private Path moduleConfiguration;

	private Path externalCallTargetPairsFile;

	@Override
	public void initialize(final Path parent) {
		super.initialize(parent);
		this.moduleConfiguration = parent.resolve(JavaFileSystemLayout.moduleConfigurationFileName);
		this.externalCallTargetPairsFile = parent.resolve(JavaFileSystemLayout.externalCallTargetPairsFileName);
	}

	public Path getModuleConfiguration() {
		return this.moduleConfiguration;
	}

	public Path getExternalCallTargetPairsFile() {
		return this.externalCallTargetPairsFile;
	}

	@Override
	public Path getRootDirPath() {
		// TODO Auto-generated method stub
		return super.getRootDirPath().resolve(JavaFileSystemLayout.javaModelFileName);
	}
}

package cipm.consistency.models.pcm;

import cipm.consistency.base.shared.pcm.LocalFilesystemPCM
import java.nio.file.Path
import org.eclipse.emf.common.util.URI
import org.eclipse.xtend.lib.annotations.Accessors
import cipm.consistency.models.ModelDirLayoutImpl

@Accessors
class PcmDirLayout extends ModelDirLayoutImpl {
	@Accessors
	static final String pcmRepositoryFileExtension = ".repository";
	@Accessors
	static final String pcmSystemFileExtension = ".system";
	@Accessors
	static final String pcmAllocationFileExtension = ".allocation";
	@Accessors
	static final String pcmUsageModelFileExtension = ".usagemodel";
	@Accessors
	static final String pcmResourceEnvironmentFileExtension = ".resourceenvironment";

	@Accessors
	static final String pcmRepositoryFileName = "Repository";
	@Accessors
	static final String pcmSystemFileName = "System";
	@Accessors
	static final String pcmAllocationFileName = "Allocation";
	@Accessors
	static final String pcmUsageModelFileName = "Usage";
	@Accessors
	static final String pcmResourceEnvironmentFileName = "ResourceEnvironment";

	Path pcmRepositoryPath
	URI pcmRepositoryURI
	Path pcmSystemPath
	URI pcmSystemURI
	Path pcmAllocationPath
	URI pcmAllocationURI
	Path pcmUsageModelPath
	URI pcmUsageModelURI
	Path pcmResourceEnvironmentPath
	URI pcmResourceEnvironmentURI

	override void initialize(Path rootDirPath) {
		super.initialize(rootDirPath)
		pcmRepositoryPath = rootDirPath.resolve(pcmRepositoryFileName + pcmRepositoryFileExtension).toAbsolutePath();
		pcmRepositoryURI = URI.createFileURI(pcmRepositoryPath.toString());
		pcmSystemPath = rootDirPath.resolve(pcmSystemFileName + pcmSystemFileExtension).toAbsolutePath();
		pcmSystemURI = URI.createFileURI(pcmSystemPath.toString());
		pcmAllocationPath = rootDirPath.resolve(pcmAllocationFileName + pcmAllocationFileExtension).toAbsolutePath();
		pcmAllocationURI = URI.createFileURI(pcmAllocationPath.toString());
		pcmUsageModelPath = rootDirPath.resolve(pcmUsageModelFileName + pcmUsageModelFileExtension).toAbsolutePath();
		pcmUsageModelURI = URI.createFileURI(pcmUsageModelPath.toString());
		pcmResourceEnvironmentPath = rootDirPath.resolve(
			pcmResourceEnvironmentFileName + pcmResourceEnvironmentFileExtension).toAbsolutePath();
		pcmResourceEnvironmentURI = URI.createFileURI(pcmResourceEnvironmentPath.toString());
	}

	def LocalFilesystemPCM getFilePCM() {
		var filePCM = new LocalFilesystemPCM();
		filePCM.setRepositoryFile(pcmRepositoryPath.toFile());
		filePCM.setAllocationModelFile(pcmAllocationPath.toFile());
		filePCM.setSystemFile(pcmSystemPath.toFile());
		filePCM.setResourceEnvironmentFile(pcmResourceEnvironmentPath.toFile());
		filePCM.setUsageModelFile(pcmUsageModelPath.toFile());
		return filePCM;
	}
}

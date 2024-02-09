package cipm.consistency.vsum.test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.EnumMap;
import java.util.Map;

import cipm.consistency.cpr.javapcm.CommitIntegrationJavaPCMChangePropagationSpecification;
import tools.vitruv.framework.propagation.ChangePropagationSpecification;

public class TeaStoreRepoSettings implements HasRepoSettings {
	public enum TeaStoreCommitTag {
		COMMIT_1_0("1_0"),
		COMMIT_1_1("1_1"),
		COMMIT_1_2("1_2"),
		COMMIT_1_2_1("1_2_1"),
		COMMIT_1_3("1_3"),
		COMMIT_1_3_1("1_3_1"),
		;

		private final String tagName;
		
		private TeaStoreCommitTag(String tagName) {
			this.tagName = tagName;
		}
		
		public String getTagName() {
			return this.tagName;
		}
	}
	
	/*
	 * Commented out commit tag hashes from TeaStoreCITest. Not deleted for the sake of identifying
	 * which commit hash is meant to be what easier for future debugging.
	 * 
	 * private static final String COMMIT_TAG_1_0 = "b0d046e178dbaab7e045de57c01795ce5d1dac92";
	 * private static final String COMMIT_TAG_1_1 = "77733d9c6ab6680c6cc460c631cd408a588a595c";
	 * private static final String COMMIT_TAG_1_2 = "53c6efa1dca64a87e536d8c5a3dcc3c12ad933b5";
	 * private static final String COMMIT_TAG_1_2_1 = "f8f13f4390f80d3dc8adb0a6167938a688ddb45e";
	 * private static final String COMMIT_TAG_1_3 = "745469e55fad8a801a92b0be96dc009acbe7e3fb";
	 * private static final String COMMIT_TAG_1_3_1 = "de69e957597d20d4be17fc7db2a0aa2fb3a414f7";
	 */
	
	@SuppressWarnings("serial")
	private static final Map<TeaStoreCommitTag, String> COMMIT_TAGS =
	new EnumMap<TeaStoreCommitTag, String>(TeaStoreCommitTag.class) {{
		put(TeaStoreCommitTag.COMMIT_1_0, "b0d046e178dbaab7e045de57c01795ce5d1dac92");
		put(TeaStoreCommitTag.COMMIT_1_1, "77733d9c6ab6680c6cc460c631cd408a588a595c");
		put(TeaStoreCommitTag.COMMIT_1_2, "53c6efa1dca64a87e536d8c5a3dcc3c12ad933b5");
		put(TeaStoreCommitTag.COMMIT_1_2_1, "f8f13f4390f80d3dc8adb0a6167938a688ddb45e");
		put(TeaStoreCommitTag.COMMIT_1_3, "745469e55fad8a801a92b0be96dc009acbe7e3fb");
		put(TeaStoreCommitTag.COMMIT_1_3_1, "de69e957597d20d4be17fc7db2a0aa2fb3a414f7");
	}};
	
	/**
	 * @param commitHashIdentifier The {@link TeaStoreCommitTag} of the desired commit tag hash
	 * (as {@link TeaStoreCommitTag} instance)
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public String getCommitHash(Object commitHashIdentifier) {
		return COMMIT_TAGS.get(commitHashIdentifier);
	}
	
	@Override
	public String getTestGroup() {
		return "TeaStoreTest";
	}

	@Override
	public String getRepositoryAddress() {
		return "file:///C:/Users/atora/OneDrive/Belgeler/GitHub/TeaStore";
	}

	@Override
	public String getSettingsAddress() {
		return this.getExecFilesAddress() + File.separator + "settings.properties";
	}
	
	@Override
	public String getExecFilesAddress() {
		return "teastore-exec-files";
	}
	
	@Override
	public ChangePropagationSpecification getJavaPCMSpec() {
		return new CommitIntegrationJavaPCMChangePropagationSpecification();
	}

	@Override
	public HasRepoSettings getRepoSettings() {
		return this;
	}
	
	public String getExternalCallTargetPairsFileName() {
		return "external-call-target-pairs.json";
	}
	
	public String getModuleConfigsFileName() {
		return "module-configuration.properties";
	}
	
	public String getExternalCallTargetPairsAddress() {
		return this.getExecFilesAddress() + File.separator + this.getExternalCallTargetPairsFileName();
	}
	
	public String getModuleConfigsAddress() {
		return this.getExecFilesAddress() + File.separator + this.getModuleConfigsFileName();
	}
	/**
	 * {@inheritDoc}
	 * <br><br>
	 * params[0]: The path to the target directory
	 */
	@Override
	public void performTestSpecificSetUp(Object[] params) throws Exception {
		File targetPairsFile = new File(this.getExternalCallTargetPairsAddress());
		File moduleConfigsFile = new File(this.getModuleConfigsAddress());
		
		String javaDirPath = this.makeJavaDir((String) params[0]);
		
		File copyTargetPairsFile = new File(javaDirPath + File.separator + this.getExternalCallTargetPairsFileName());
		File copyModuleConfigsFile = new File(javaDirPath + File.separator + this.getModuleConfigsFileName());
		
		copyTargetPairsFile.createNewFile();
		copyModuleConfigsFile.createNewFile();
		
		Files.copy(targetPairsFile.toPath(), copyTargetPairsFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		Files.copy(moduleConfigsFile.toPath(), copyModuleConfigsFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
	}
	
	/**
	 * Creates the TestGroup/java directory under the given target directory path. 
	 * Creates all necessary directories along the way, if needed and possible.
	 * 
	 * @return The path to the newly created java directory
	 */
	protected String makeJavaDir(String pathToTargetDir) {
		String pathToTargetTestType = pathToTargetDir + File.separator + this.getTestGroup();
		
		File javaDir = new File(pathToTargetTestType + File.separator + "java");
		javaDir.mkdirs();
		return javaDir.getPath();
	}
}

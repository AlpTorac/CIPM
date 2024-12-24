package cipm.consistency.vsum.test;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import java.util.EnumMap;
import java.util.Map;

import cipm.consistency.cpr.javapcm.CommitIntegrationJavaPCMChangePropagationSpecification;
import tools.vitruv.framework.propagation.ChangePropagationSpecification;

/**
 * The implementation of {@link HasRepoSettings} for TeaStore tests. Contains a
 * map of ({@link TeaStoreCommitTag}, commit hash) key-value pairs.
 * 
 * @author atora
 * 
 * @see {@link #getCommitHash(Object)}
 */
public class TeaStoreRepoSettings implements HasRepoSettings {
	/**
	 * An enum for encapsulating individual TeaStore commit hashes to be used in
	 * unit tests.
	 * 
	 * @author atora
	 */
	public enum TeaStoreCommitTag {
		COMMIT_1_0("1_0"), COMMIT_1_1("1_1"), COMMIT_1_2("1_2"), COMMIT_1_2_1("1_2_1"), COMMIT_1_3("1_3"),
		COMMIT_1_3_1("1_3_1"), COMMIT_1_1_READER_INDENTATION_FIX("1_1_RIF"), COMMIT_1_1_WHOLE_INDENTATION_FIX("1_1_WIF"),
		COMMIT_1_0_JUST_READER_INDENTATION("1_0_JRI"), COMMIT_1_1_JUST_READER_INDENTATION("1_1_JRI"),
		COMMIT_1_1_WO_READER("1_1_WOR"),
		
		COMMIT_1_0_TO_1_1_TILL_FIRST_READER_CHANGE("1_0_to_1_1_TFRC"),
		COMMIT_1_0_TO_1_1_FIRST_READER_CHANGE("1_0_to_1_1_FRC"),
		COMMIT_1_0_TO_1_1_TILL_SECOND_READER_CHANGE("1_0_to_1_1_TSRC"),
		COMMIT_1_0_TO_1_1_SECOND_READER_CHANGE("1_0_to_1_1_SRC"),
		COMMIT_1_0_TO_1_1_AFTER_SECOND_READER_CHANGE("1_0_to_1_1_ASRC")
		;

		/**
		 * The version part of the concrete enum values as String (ex: "1_0" for
		 * {@link #COMMIT_1_0})
		 */
		private final String tagName;

		private TeaStoreCommitTag(String tagName) {
			this.tagName = tagName;
		}

		/**
		 * @return {@link #tagName}
		 */
		public String getTagName() {
			return this.tagName;
		}
	}

	/*
	 * Commented out commit tag hashes from TeaStoreCITest. Not deleted for the sake
	 * of identifying which commit hash is meant to be what easier for future
	 * debugging.
	 * 
	 * private static final String COMMIT_TAG_1_0 =
	 * "b0d046e178dbaab7e045de57c01795ce5d1dac92"; private static final String
	 * COMMIT_TAG_1_1 = "77733d9c6ab6680c6cc460c631cd408a588a595c"; private static
	 * final String COMMIT_TAG_1_2 = "53c6efa1dca64a87e536d8c5a3dcc3c12ad933b5";
	 * private static final String COMMIT_TAG_1_2_1 =
	 * "f8f13f4390f80d3dc8adb0a6167938a688ddb45e"; private static final String
	 * COMMIT_TAG_1_3 = "745469e55fad8a801a92b0be96dc009acbe7e3fb"; private static
	 * final String COMMIT_TAG_1_3_1 = "de69e957597d20d4be17fc7db2a0aa2fb3a414f7";
	 */

	/**
	 * Contains ({@link TeaStoreCommitTag}, commit hash) pairs for cleaner access to
	 * commit hashes.
	 */
	@SuppressWarnings("serial")
	private static final Map<TeaStoreCommitTag, String> COMMIT_TAGS = new EnumMap<TeaStoreCommitTag, String>(
			TeaStoreCommitTag.class) {
		{
			put(TeaStoreCommitTag.COMMIT_1_0, "b0d046e178dbaab7e045de57c01795ce5d1dac92");
			put(TeaStoreCommitTag.COMMIT_1_1, "77733d9c6ab6680c6cc460c631cd408a588a595c");
			put(TeaStoreCommitTag.COMMIT_1_2, "53c6efa1dca64a87e536d8c5a3dcc3c12ad933b5");
			put(TeaStoreCommitTag.COMMIT_1_2_1, "f8f13f4390f80d3dc8adb0a6167938a688ddb45e");
			put(TeaStoreCommitTag.COMMIT_1_3, "745469e55fad8a801a92b0be96dc009acbe7e3fb");
			put(TeaStoreCommitTag.COMMIT_1_3_1, "de69e957597d20d4be17fc7db2a0aa2fb3a414f7");
			
			put(TeaStoreCommitTag.COMMIT_1_1_READER_INDENTATION_FIX, "b0f8ce6896d252681e604e2b97b0bb8c4c128e12");
			put(TeaStoreCommitTag.COMMIT_1_1_WHOLE_INDENTATION_FIX, "d120878b8ee825f213b2ffc640478f54c6dafd45");
			
			put(TeaStoreCommitTag.COMMIT_1_0_JUST_READER_INDENTATION, "86c4bbc6850d39f9070cdede8b92bb4d8a426f75");
			put(TeaStoreCommitTag.COMMIT_1_1_JUST_READER_INDENTATION, "d8e9b8b07672b2925b330099eb0f5169a2b6481f");
			put(TeaStoreCommitTag.COMMIT_1_1_WO_READER, "7f7cab826ef68578adf7958da9dc86809efa5c42");
			
			put(TeaStoreCommitTag.COMMIT_1_0_TO_1_1_TILL_FIRST_READER_CHANGE, "428fa6e05dc23087145d1d3a7d76d15dee977a8e");
			put(TeaStoreCommitTag.COMMIT_1_0_TO_1_1_FIRST_READER_CHANGE, "e980743956829c4518fc01c0c3e900fc223bb748");
			put(TeaStoreCommitTag.COMMIT_1_0_TO_1_1_TILL_SECOND_READER_CHANGE, "b26acd6ec92f6c448c8524106e3a097db15a57eb");
			put(TeaStoreCommitTag.COMMIT_1_0_TO_1_1_SECOND_READER_CHANGE, "ed5b99b27abaa485b8f4ed10ae0633419fd54faa");
			put(TeaStoreCommitTag.COMMIT_1_0_TO_1_1_AFTER_SECOND_READER_CHANGE, "14803d170e1f6d7c250c73fd617df0695bd4067f");
		}
	};

	/**
	 * @param commitHashIdentifier The {@link TeaStoreCommitTag} of the desired
	 *                             commit tag hash (as {@link TeaStoreCommitTag}
	 *                             instance)
	 * 
	 *                             {@inheritDoc}
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
		return "file:///C:/Users/sdq-l/TeaStore";
	}

	@Override
	public String getSettingsAddress() {
		return this.getExecFilesAddress() + File.separator + "settings.properties";
	}

	/**
	 * @return Full file name of external call target pairs file with extension.
	 */
	public String getExternalCallTargetPairsFileName() {
		return "external-call-target-pairs.json";
	}

	/**
	 * @return Full file name of module configurations file with extension
	 */
	public String getModuleConfigsFileName() {
		return "module-configuration.properties";
	}

	/**
	 * @return The path to {@link #getExternalCallTargetPairsFileName()}
	 */
	public String getExternalCallTargetPairsAddress() {
		return this.getExecFilesAddress() + File.separator + this.getExternalCallTargetPairsFileName();
	}

	/**
	 * @return The path to {@link #getModuleConfigsFileName()}
	 */
	public String getModuleConfigsAddress() {
		return this.getExecFilesAddress() + File.separator + this.getModuleConfigsFileName();
	}

	@Override
	public String getExecFilesAddress() {
		return "teastore-exec-files";
	}

	/**
	 * @return The name of the directory, to which
	 *         {@link #getExternalCallTargetPairsFileName()} and
	 *         {@link #getModuleConfigsFileName()} should be copied
	 */
	public String getJavaDirName() {
		return "java";
	}

	@Override
	public ChangePropagationSpecification getJavaPCMSpec() {
		return new CommitIntegrationJavaPCMChangePropagationSpecification();
	}

	@Override
	public HasRepoSettings getRepoSettings() {
		return this;
	}

	/**
	 * {@inheritDoc} <br>
	 * <br>
	 * params[0]: The path to the target directory
	 */
	@Override
	public void performTestSpecificSetUp(Object[] params) throws Exception {
		this.copyExecFilesIntoAll((String) params[0]);
	}

	/**
	 * Copies the exec-files required to skip UI prompts during test runs into all
	 * "java" directories under the given target directory path.
	 * 
	 * @param pathToTargetDir Path to the target directory
	 */
	protected void copyExecFilesIntoAll(String pathToTargetDir) throws IOException {
		File targetPairsFile = new File(this.getExternalCallTargetPairsAddress());
		File moduleConfigsFile = new File(this.getModuleConfigsAddress());
		File targetDir = new File(pathToTargetDir);
		File[] testDirs = null;

		if (targetDir.exists()) {
			testDirs = targetDir.listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					return dir.getName().contains(getTestGroup());
				}
			});
		}

		if (testDirs != null && testDirs.length > 0) {
			for (File td : testDirs) {

				File javaDir = this.makeJavaDir(td.getPath());
				this.copyExecFilesInto(targetPairsFile, moduleConfigsFile, javaDir.getPath());
			}
		} else {
			targetDir.mkdirs();

			File tgd = this.makeTestGroupDir(targetDir.getPath());
			File javaDir = this.makeJavaDir(tgd.getPath());

			this.copyExecFilesInto(targetPairsFile, moduleConfigsFile, javaDir.getPath());
		}
	}

	/**
	 * Copies the given files into the directory at copyToPath
	 * 
	 * @param targetPairsFile   File at {@link getExternalCallTargetPairsAddress}
	 * @param moduleConfigsFile File at {@link #getModuleConfigsAddress()}
	 * @param copyToPath        Path to the directory, where the files will be
	 *                          copied into
	 */
	protected void copyExecFilesInto(File targetPairsFile, File moduleConfigsFile, String copyToPath)
			throws IOException {
		File copyTargetPairsFile = new File(copyToPath + File.separator + this.getExternalCallTargetPairsFileName());
		File copyModuleConfigsFile = new File(copyToPath + File.separator + this.getModuleConfigsFileName());

		copyTargetPairsFile.createNewFile();
		copyModuleConfigsFile.createNewFile();

		Files.copy(targetPairsFile.toPath(), copyTargetPairsFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		Files.copy(moduleConfigsFile.toPath(), copyModuleConfigsFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
	}

	/**
	 * Creates the "TestGroup" directory under the given "target" directory path.
	 * Creates all necessary directories along the way, if needed and possible.
	 * 
	 * @return The path to the newly created TestGroup directory
	 */
	protected File makeTestGroupDir(String pathToTargetDir) {
		File targetDir = new File(pathToTargetDir + File.separator + this.getTestGroup());
		targetDir.mkdirs();
		return targetDir;
	}

	/**
	 * Creates the "java" directory under the given target/TestGroup directory path.
	 * Creates all necessary directories along the way, if needed and possible.
	 * 
	 * @return The path to the newly created java directory
	 */
	protected File makeJavaDir(String pathToTestGroupDir) {
		File javaDir = new File(pathToTestGroupDir + File.separator + this.getJavaDirName());
		javaDir.mkdirs();
		return javaDir;
	}
}

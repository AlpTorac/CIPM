package cipm.consistency.fitests;

import cipm.consistency.commitintegration.diff.util.JavaModelComparator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Map;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.merge.BatchMerger;
import org.eclipse.emf.compare.merge.IMerger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class ResourcePersistenceTest {
	private static final Logger LOGGER = Logger.getLogger("cipm." + ResourcePersistenceTest.class.getSimpleName());
	
	private static final File TARGET_DIR = new File("target").getAbsoluteFile();
	
	private static final String WORKING_RESOURCES_PATH = TARGET_DIR.getParentFile().getAbsolutePath() + File.separator + "tmp";
	private static final String WORKING_TEAMMATES_PATH = WORKING_RESOURCES_PATH + File.separator + "TeammatesTest";
	
	private static final String INITIAL_MODEL_NAME = "JavaModel-test0.javaxmi";
	private static final String FIRST_MODEL_NAME = "JavaModel-test1.javaxmi";
	
	@BeforeEach
	public void setUp() throws IOException {
		this.setUpLogger();
//		this.cleanModels(WORKING_RESOURCES_PATH);
		this.copyModels(TARGET_DIR, WORKING_RESOURCES_PATH);
	}
	
	protected void setUpLogger() {
		Logger logger = Logger.getLogger("cipm");
		logger.setLevel(Level.ALL);
		logger = Logger.getLogger("jamopp");
		logger.setLevel(Level.ALL);
		logger = Logger.getRootLogger();
		logger.removeAllAppenders();
		ConsoleAppender ap = new ConsoleAppender(new PatternLayout("[%d{DATE}] %-5p: %c - %m%n"),
				ConsoleAppender.SYSTEM_OUT);
		logger.addAppender(ap);
	}
	
	/**
	 * Recursively cleans models, which have been used in tests.
	 * 
	 * @param path The path to the directory of the said models
	 */
	protected void cleanModels(String path) throws IOException {
		var file = new File(path);
		
		if (file.exists()) {
			if (file.isFile()) {
				file.delete();
				return;
			}
			
			if (file.isDirectory()) {
				var children = file.listFiles();
				
				if (children != null) {
					for (File cf : children) {
						this.cleanModels(cf.getAbsolutePath());
					}
				}
				
				file.delete();
			}
		}
	}
	
	/**
	 * Recursively copies Java-Model files from the given parent parameter to
	 * the path given via copyPath. Replaces model files, which already exist.
	 * 
	 * This spares having to generate a separate model after each
	 * test case, which modifies them.
	 * 
	 * @param parent The directory, under which the original Java-Models can be found
	 * @param copyPath The path to the directory, where the content from parent will be copied to
	 */
	protected void copyModels(File parent, String copyPath) throws IOException {
		LOGGER.debug("Copying the contents of " + parent.getAbsolutePath() + " into " + copyPath);
		for (File f : parent.listFiles()) {
			var fileName = f.getName();
			
			if (f.isDirectory()) {
				LOGGER.debug("Directory found: " + fileName);
				String newCopyAddress = copyPath + File.separator + fileName;
				LOGGER.debug("Copy address changed to " + newCopyAddress);
				File tmpDir = new File(newCopyAddress);
				
				this.copyModels(f, tmpDir.getAbsolutePath());
			}
			if (f.isFile()) {
				LOGGER.debug("File found: " + fileName);
				File tmpFile = new File(copyPath + File.separator + fileName);
				
				if (tmpFile.exists()) {
					LOGGER.debug("Existing file will be replaced");
				} else {
					LOGGER.debug("Creating file");
					tmpFile.mkdirs();
					LOGGER.debug("Created file");	
				}
				
				LOGGER.debug("Copying original file into new file");
				Files.copy(f.toPath(), tmpFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
				LOGGER.debug("Copied original file into new file: " + fileName);
				
				LOGGER.debug("Verifying equality of file content");
				Assertions.assertTrue(Files.readString(f.toPath()).equals(Files.readString(tmpFile.toPath())));
				LOGGER.debug("Verified equality of file content");
			}
		}
		LOGGER.debug("Parent directory " + parent.getName() + " has been copied");
	}
	
	@Disabled
	@Test
	public void modelLoadingTest() throws IOException {
		var res1Path = WORKING_TEAMMATES_PATH + File.separator + FIRST_MODEL_NAME;
		
		var res = this.makeAndLoadResource(new ResourceSetImpl(), URI.createFileURI(res1Path));
		
		var errors = res.getErrors();
		var warnings = res.getWarnings();
		
		errors.forEach((e) -> LOGGER.debug("Error while loading: " + e.getMessage()));
		Assertions.assertEquals(errors.size(), 0);
		warnings.forEach((e) -> LOGGER.debug("Warning while loading: " + e.getMessage()));
		
		LOGGER.debug("Validating loaded model");
		this.validateEObjects(res);
		LOGGER.debug("Validated loaded model");
		
		// Attempt to save it back to check for potential errors
		res.save(null);
		
		res.unload();
	}
	
	@Disabled("Takes a long time to complete")
	@Test
	public void modelComparingTest() throws IOException {
		var res1Path = WORKING_TEAMMATES_PATH + File.separator + INITIAL_MODEL_NAME;
		var res2Path = WORKING_TEAMMATES_PATH + File.separator + FIRST_MODEL_NAME;
		
		var res1 = this.makeAndLoadResource(new ResourceSetImpl(), URI.createFileURI(res1Path));
		var res1List = new ArrayList<Resource>();
		res1List.add(res1);
		
		var res2 = this.makeAndLoadResource(new ResourceSetImpl(), URI.createFileURI(res2Path));
		var res2List = new ArrayList<Resource>();
		res2List.add(res2);
		
		LOGGER.debug("Comparing models");
		var cmp = JavaModelComparator.compareJavaModels(res2, res1, res2List, res1List, null);
		
		this.assertComparisonDone(cmp, true, true);
		this.assertAllMatchDiffsHaveDiffs(cmp);
		this.assertAllDiffsHaveMatches(cmp);
		
		int matchCount = cmp.getMatches().size();
		int diffCount = cmp.getDifferences().size();
		LOGGER.debug("Models compared. Match count: " + matchCount + ", Difference count: " + diffCount);
		
		res1.unload();
		res2.unload();
	}
	
//	@Disabled
	@Test
	public void changeReplayTest() throws IOException {
		var res1Path = WORKING_TEAMMATES_PATH + File.separator + INITIAL_MODEL_NAME;
		var res2Path = WORKING_TEAMMATES_PATH + File.separator + FIRST_MODEL_NAME;
		
		var res1 = this.makeAndLoadResource(new ResourceSetImpl(), URI.createFileURI(res1Path));
		var res1List = new ArrayList<Resource>();
		res1List.add(res1);
		
		var res2 = this.makeAndLoadResource(new ResourceSetImpl(), URI.createFileURI(res2Path));
		var res2List = new ArrayList<Resource>();
		res2List.add(res2);
		
		LOGGER.debug("Comparing models");
		var changes = JavaModelComparator.compareJavaModels(res2, res1, res2List, res1List, null);
		LOGGER.debug("Compared models");
		
		var diffs = changes.getDifferences();

		var mergerRegistry = IMerger.RegistryImpl.createStandaloneInstance();
		var merger = new BatchMerger(mergerRegistry);

		var newRes1Path = WORKING_TEAMMATES_PATH + File.separator + "new0.javaxmi";
		var newRes2Path = WORKING_TEAMMATES_PATH + File.separator + "new1.javaxmi";
		res1.setURI(URI.createFileURI(newRes1Path));
		res2.setURI(URI.createFileURI(newRes2Path));
		
		merger.copyAllLeftToRight(diffs, new BasicMonitor());
		
		this.validateEObjects(res1);
		this.validateEObjects(res2);
		
		res1.save(null);
		res2.save(null);
	}
	
	protected void validateEObjects(Resource res) {
		var diagnostician = new Diagnostician();
		
		res.getAllContents().forEachRemaining((obj) -> {
			var diagnostic = diagnostician.validate(obj);
			var objClass = obj.eClass();
			var objName = diagnostician.getObjectLabel(obj);
			
			if (diagnostic.getSeverity() == Diagnostic.ERROR) {
				LOGGER.debug("Validation for " + objName
						+ " of type " + objClass.getName()
						+ " resulted in error");
			}
			
//			Assertions.assertNotEquals(diagnostic.getSeverity(), Diagnostic.ERROR, diagnostic.getMessage());
		});
	}
	
	protected Resource makeAndLoadResource(ResourceSet resSet, URI uri) throws IOException {
		var res = resSet.createResource(uri);
		LOGGER.debug("Loading resource at: " + uri.toFileString());
		res.load(null);
		LOGGER.debug("Loaded resource");
		return res;
	}
	
	protected void assertResourceLoaded(Resource res) {
		Assertions.assertNotNull(res);
		Assertions.assertTrue(res.isLoaded());
	}
	
	protected void assertComparisonDone(Comparison cmp, boolean expectMatches, boolean expectDifferences) {
		Assertions.assertTrue(!expectMatches || cmp.getMatches().size() > 0);
		Assertions.assertTrue(!expectDifferences || cmp.getDifferences().size() > 0);
	}
	
	/**
	 * Asserts that all found matches, which reference found differences,
	 * are also referenced from the said found differences.
	 */
	protected void assertAllMatchDiffsHaveDiffs(Comparison cmp) {
		var matches = cmp.getMatches();
		var diffs = cmp.getDifferences();
		
		Assertions.assertTrue(matches.stream()
				.allMatch(((m) -> {
					var mDiffs = m.getAllDifferences();
					boolean result = true;
					
					for (var md : mDiffs) {
						result = result && diffs.contains(md);
					}
					
					return result;
				})));
	}
	
	/**
	 * Asserts that all found differences have a match, which is
	 * contained in the list of all found matches.
	 * 
	 * Can take a long time to compute.
	 */
	protected void assertAllDiffsHaveMatches(Comparison cmp) {
		var matches = cmp.getMatches();
		var diffs = cmp.getDifferences();
		
		Assertions.assertTrue(diffs.stream()
				.allMatch(((d) -> {
					var diffMatch = d.getMatch();
					
					/*
					 * Match referenced in difference should either be
					 * in "matches" or be a sub-match of a match in "matches"
					 */
					boolean result = matches.contains(diffMatch) ||
							matches.stream().anyMatch((m) -> {
								for (var sm : m.getAllSubmatches()) {
									if (sm == diffMatch) {
										return true;
									}
								}
								return false;
							});
					
					if (!result) {
						LOGGER.debug("A difference has no corresponding match");
					}
					
					return result;
				})));
	}
}
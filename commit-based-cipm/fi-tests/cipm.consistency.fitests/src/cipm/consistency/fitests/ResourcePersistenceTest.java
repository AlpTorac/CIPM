package cipm.consistency.fitests;

import cipm.consistency.commitintegration.diff.util.JavaModelComparator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class ResourcePersistenceTest {
	private static final Logger LOGGER = Logger.getLogger("cipm." + ResourcePersistenceTest.class.getSimpleName());
	
	private static final File TARGET_DIR = new File("target").getAbsoluteFile();
	
	private static final String WORKING_RESOURCES_PATH = TARGET_DIR.getParentFile().getAbsolutePath() + File.separator + "tmp";
	private static final String WORKING_TEAMMATES_PATH = WORKING_RESOURCES_PATH + File.separator + "TeammatesTest";
	
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
			}
		}
		LOGGER.debug("Parent directory " + parent.getName() + " has been copied");
	}
	
	@Test
	public void modelLoadingTest() throws IOException {
		var res1Path = WORKING_TEAMMATES_PATH + File.separator + "JavaModel-test0.javaxmi";
		
		var resURI = URI.createFileURI(res1Path);
		var resSet = new ResourceSetImpl();
		var res = resSet.createResource(resURI);
		LOGGER.debug("Loading past commit");
		res.load(null);
		this.assertResourceLoaded(res);
		LOGGER.debug("Loaded past commit");
		res.unload();
	}
	
	@Disabled("Takes a long time to complete")
	@Test
	public void modelComparingTest() throws IOException {
		var res1Path = WORKING_TEAMMATES_PATH + File.separator + "JavaModel-test0.javaxmi";
		var res2Path = WORKING_TEAMMATES_PATH + File.separator + "JavaModel-test1.javaxmi";
		
		var res1URI = URI.createFileURI(res1Path);
		var res1Set = new ResourceSetImpl();
		var res1 = res1Set.createResource(res1URI);
		LOGGER.debug("Loading past commit");
		res1.load(null);
		LOGGER.debug("Loaded past commit");
		var res1List = new ArrayList<Resource>();
		res1List.add(res1);
		
		var res2URI = URI.createFileURI(res2Path);
		var res2Set = new ResourceSetImpl();
		var res2 = res2Set.createResource(res2URI);
		LOGGER.debug("Loading new commit");
		res2.load(null);
		LOGGER.debug("Loaded new commit");
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
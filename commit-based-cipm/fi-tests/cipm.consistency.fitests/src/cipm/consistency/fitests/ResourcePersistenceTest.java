package cipm.consistency.fitests;

import cipm.consistency.commitintegration.diff.util.JavaModelComparator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

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
import org.junit.jupiter.api.Test;

public class ResourcePersistenceTest {
	private static final Logger LOGGER = Logger.getLogger("cipm." + ResourcePersistenceTest.class.getSimpleName());
	
	@BeforeEach
	public void setUp() {
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
	
	@Test
	public void modelLoadingTest() throws IOException {
		var currentPath = new File("target").getAbsoluteFile();
		var teammatesPath = currentPath + File.separator + "TeammatesTest";
		var res1Path = teammatesPath + File.separator + "JavaModel-test0.javaxmi";
		
		var resURI = URI.createFileURI(res1Path);
		var resSet = new ResourceSetImpl();
		var res = resSet.createResource(resURI);
		LOGGER.debug("Loading past commit");
		res.load(null);
		this.assertResourceLoaded(res);
		LOGGER.debug("Loaded past commit");
		res.unload();
	}
	
	@Test
	public void modelComparingTest() throws IOException {
		var currentPath = new File("target").getAbsoluteFile();
		var teammatesPath = currentPath + File.separator + "TeammatesTest";
		var res1Path = teammatesPath + File.separator + "JavaModel-test0.javaxmi";
		var res2Path = teammatesPath + File.separator + "JavaModel-test1.javaxmi";
		
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
	
	protected String getTargetPath() {
		return "target";
	}
}
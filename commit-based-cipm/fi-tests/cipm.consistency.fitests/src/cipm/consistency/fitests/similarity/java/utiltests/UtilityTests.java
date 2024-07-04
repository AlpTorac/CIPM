package cipm.consistency.fitests.similarity.java.utiltests;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.emftext.language.java.JavaPackage;
import org.emftext.language.java.commons.Commentable;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.AbstractSimilarityTest;

/**
 * A test class, whose tests can be used to make sure no initialiser interfaces,
 * concrete initialisers or initialiser tests are missing.
 * 
 * @author atora
 */
public class UtilityTests extends AbstractSimilarityTest {
	
	// FIXME: Find a better way to determine, if there are initialiser methods that can be used in tests. Maybe something like SimilarityValues.
	// TODO: Comment better, rename unintuitive parameters and methods.
	
	/**
	 * Points at the cimp.consistency.fitests.similarity.java package. Used
	 * by discovering methods in this class.
	 */
	private static final File root = new File("").getAbsoluteFile().getParentFile();
	/**
	 * The suffix used in the concrete implementation of {@link EObject} classes.
	 */
	private static final String implSuffix = "Impl";
	/**
	 * The prefix used in initialiser interfaces.
	 */
	private static final String initialiserInterfacePrefix = "I";
	/**
	 * The suffix used in initialisers.
	 */
	private static final String initialiserSuffix = "Initialiser";
	/**
	 * The suffix used in tests.
	 */
	private static final String testSuffix = "Test";
	
	/**
	 * Pattern for testable initialiser method names (without whitespace):
	 * 
	 * preceding text +
	 * "default" +
	 * return type +
	 * "add" or "set" +
	 * rest of the method name (without "Assertion") +
	 * parameters +
	 * proceeding text
	 */
	private static final String testableMethodPattern = "(.*)default\\w+(add|set)\\w+(?<!Assertion)\\((.*)";
	
	@BeforeEach
	public void setUp() {
		this.setUpLogger();
	}

	@AfterEach
	public void tearDown() {
		return;
	}
	
	/**
	 * @return The given text without whitespaces (includes line breaks).
	 */
	public String removeWhitespaces(String text) {
		return text.replaceAll("\\n", "").replaceAll("\\r", "").replaceAll("\\s", "");
	}
	
	/**
	 * @return Classes of concrete implementations and interfaces of all Java-Model elements.
	 */
	public Set<Class<?>> getAllPossibleClasses() {
		return this.getAllPossibleClasses(JavaPackage.eINSTANCE.getESubpackages());
	}
	
	/**
	 * Recursively discovers sub-packages of cPac (including cPac)
	 * for {@link EClassifier}s
	 * contained within, aggregates the classes represented by the EClassifiers
	 * as a Set and returns the Set.
	 * 
	 * @param cPac The package, which is the start point of the discovery.
	 * @return All classes represented by EClassifiers contained in cPac and
	 * its sub-packages. Includes types of interfaces as well as concrete implementation
	 * classes.
	 */
	protected Set<Class<?>> getAllPossibleClasses(EPackage cPac) {
		var clss = cPac.getEClassifiers();
		var subPacs = cPac.getESubpackages();
		
		var foundClss = new HashSet<Class<?>>();
		
		if (clss != null) {
			for (var cls : clss) {
				foundClss.add(cls.getInstanceClass());
				
				/*
				 * Although cls is technically of type EClassifier,
				 * it also implements EClass
				 */
				if (cls instanceof EClass) {
					var castedCls = (EClass) cls;
					
					/*
					 * Add the concrete implementation class, if
					 * cls represents a concrete class
					 */
					if (!castedCls.isAbstract()) {
						foundClss.add(cPac.getEFactoryInstance()
								.create(castedCls).getClass());
					}
				}
			}
		}
		
		if (subPacs != null) {
			foundClss.addAll(this.getAllPossibleClasses(subPacs));
		}
		
		return foundClss;
	}
	
	/**
	 * @return All classes represented by EClassifiers contained in pacs and
	 * their sub-packages. Includes types of interfaces as well as concrete implementation
	 * classes.
	 * @see {@link #getAllPossibleClasses(EPackage)}}
	 */
	protected Set<Class<?>> getAllPossibleClasses(Collection<EPackage> pacs) {
		var foundClss = new HashSet<Class<?>>();
		
		for (var pac : pacs) {
			foundClss.addAll(this.getAllPossibleClasses(pac));
		}
		
		return foundClss;
	}
	
	/**
	 * @return A list of all files under the given path and its sub-directories.
	 */
	public Collection<File> getAllFiles(File currentPath) {
		var result = new ArrayList<File>();
		
		if (currentPath.isFile()) {
			result.add(currentPath);
		} else {
			var files = currentPath.listFiles();
			if (files != null) {
				for (var f : files) {
					result.addAll(this.getAllFiles(f));
				}
			}
		}
		
		return result;
	}
	
	/**
	 * @return Whether the given file's name (without extension)
	 * equals to the given fileName.
	 */
	public boolean fileNameEquals(File file, String fileName) {
		return file != null && file.getName().split("\\.")[0].equals(fileName);
	}
	
	/**
	 * @return The name of the concrete initialiser corresponding to cls.
	 */
	public String getConcreteInitialiserName(Class<?> cls) {
		return cls.getSimpleName() + initialiserSuffix;
	}
	
	/**
	 * @return The name of the initialiser interface corresponding to cls.
	 */
	public String getInterfaceInitialiserName(Class<?> cls) {
		return initialiserInterfacePrefix + cls.getSimpleName() + initialiserSuffix;
	}
	
	/**
	 * @return The name of the test corresponding to cls.
	 */
	public String getTestName(Class<?> cls) {
		return cls.getSimpleName()+testSuffix;
	}
	
	/**
	 * @return The classes from {@link #getClassesInFullHierarchy}, which
	 * should have a corresponding initialiser interface.
	 */
	public Collection<Class<?>> getAllInitialiserCandidates() {
		var fullHierarchy = this.getAllPossibleClasses();
		
		var intfcs = fullHierarchy.stream()
				.filter((c) -> Commentable.class.isAssignableFrom(c))
				.filter((c) -> !c.getSimpleName().endsWith(implSuffix))
				.toArray(Class<?>[]::new);
		
		return List.of(intfcs);
	}
	
	/**
	 * @return The classes from {@link #getClassesInFullHierarchy}, which
	 * should have a corresponding concrete initialiser.
	 */
	public Collection<Class<?>> getAllConcreteInitialiserCandidates() {
		var fullHierarchy = this.getAllPossibleClasses();
		
		var intfcs = fullHierarchy.stream()
				.filter((c) -> Commentable.class.isAssignableFrom(c))
				.filter((c) -> fullHierarchy.stream()
						.anyMatch((c2) -> c2.getSimpleName()
								.equals(c.getSimpleName()+implSuffix)))
				.toArray(Class<?>[]::new);
		
		return List.of(intfcs);
	}
	
	/**
	 * Prints the interfaces between everything from {@link #getAllPossibleClasses()}
	 * and {@link Commentable}.
	 */
	@Disabled
	@Test
	public void printFullHierarchy() {
		var hSet = this.getAllPossibleClasses();
		System.out.println(hSet.toString());
	}
	
	/**
	 * Checks if all interface initialisers (ISomethingInitialiser.java) files are
	 * implemented and present under {@link #root}.
	 */
	@Test
	public void testAllInitialiserInterfacesPresent() {
		var intfcs = this.getAllInitialiserCandidates();
		var matches = this.getClassesWithInitialiserInterface();
		
		this.getLogger().info(matches.size() + " initialiser interfaces out of "+intfcs.size()+" are present");
		
		if (matches.size() != intfcs.size()) {
			Assertions.fail("Initialisers missing for: " +
						intfcs.stream()
							.filter((e) -> !matches.contains(e))
							.map((e) -> e.getSimpleName())
							.reduce("", (s1, s2) -> s1 + ", " + s2)
					);
		}
	}
	
	/**
	 * @return All classes in the need of an initialiser that actually
	 * have a corresponding initialiser interface under {@link root}.
	 */
	public Collection<Class<?>> getClassesWithInitialiserInterface() {
		var allFiles = this.getAllFiles(root);
		
		return List.of(
				this.getAllInitialiserCandidates().stream()
				.filter((c) -> allFiles.stream()
						.anyMatch((f) -> this.fileNameEquals(f,
								this.getInterfaceInitialiserName(c))))
				.toArray(Class<?>[]::new)
				);
	}
	
	/**
	 * Checks if all concrete initialisers (SomethingInitialiser.java) files are
	 * implemented and present under {@link #root}.
	 */
	@Test
	public void testAllConcreteInitialisersPresent() {
		var intfcs = this.getAllConcreteInitialiserCandidates();
		var matches = this.getClassesWithConcreteInitialiser();
		
		this.getLogger().info(matches.size() + " concrete initialisers out of "+intfcs.size()+" are present");
		
		if (matches.size() != intfcs.size()) {
			Assertions.fail("Concrete initialisers missing for: " +
						intfcs.stream()
							.filter((e) -> !matches.contains(e))
							.map((e) -> e.getSimpleName())
							.reduce("", (s1, s2) -> s1 + ", " + s2)
					);
		}
	}
	
	/**
	 * @return All classes in the need of a concrete initialiser that actually
	 * have a corresponding concrete initialiser interface under {@link root}.
	 */
	public Collection<Class<?>> getClassesWithConcreteInitialiser() {
		var allFiles = this.getAllFiles(root);
		
		return List.of(
				this.getAllConcreteInitialiserCandidates().stream()
				.filter((c) -> allFiles.stream()
						.anyMatch((f) -> this.fileNameEquals(f,
								this.getConcreteInitialiserName(c))))
				.toArray(Class<?>[]::new)
				);
	}
	
	/**
	 * @return Whether the given initialiser file contains any methods
	 * that can be used in tests.
	 */
	public boolean containsTestableMethods(File f) {
		boolean containsTestableMethods = false;
		
		try {
			var content = Files.readString(f.toPath());
			content = this.removeWhitespaces(content);
			containsTestableMethods = content.matches(testableMethodPattern);
		} catch (IOException e) {
			return false;
		}
		
		return containsTestableMethods;
	}
	
	/**
	 * Checks if all classes in the need of an initialiser, which have their own
	 * methods that modify them (the ones they do not get from inheritance),
	 * have corresponding tests.
	 * 
	 * Only verifies, if there are corresponding test files. Does not check the
	 * unit tests they implement.
	 */
	@Test
	public void testAllInterfaceTestsPresent() {
		var allFiles = this.getAllFiles(root);
		var intfcs = this.getAllInitialiserCandidates();
		
		var matches = List.of(
				intfcs.stream()
				.filter((c) -> allFiles.stream()
						.anyMatch((f) -> {
							
							// Check if f is the corresponding initialiser file.
							if (!this.fileNameEquals(f, this.getInterfaceInitialiserName(c))) {
								return false;
							}
							
							return !this.containsTestableMethods(f) || allFiles.stream()
									.anyMatch((tf) -> fileNameEquals(tf, getTestName(c)));
						}))
				.toArray(Class<?>[]::new)
				);
		
		this.getLogger().info(matches.size()+" out of "+intfcs.size()+" interfaces are covered by tests");
		
		if (matches.size() != intfcs.size()) {
			var mismatches = List.of(intfcs.stream()
					.filter((e) -> !matches.contains(e))
					.toArray(Class<?>[]::new));
			
			Assertions.fail("Tests missing for: " +
						mismatches.stream()
							.map((e) -> e.getSimpleName())
							.reduce("", (s1, s2) -> s1 + ", " + s2)
							.substring(2)
					);
		}
	}
	
	/**
	 * Checks if {@link #testableMethodPattern} works as intended.
	 */
	@Test
	public void testPattern() {
		Assertions.assertTrue("defaulttypesetxyz(".matches(testableMethodPattern));
		Assertions.assertTrue("defaulttypeaddxyz(".matches(testableMethodPattern));
		Assertions.assertTrue("abcdefaulttypesetxyz(".matches(testableMethodPattern));
		Assertions.assertTrue("defaulttypeaddxyz(abc".matches(testableMethodPattern));
		Assertions.assertTrue("abcdefaulttypeaddxyz(cde".matches(testableMethodPattern));
		
		Assertions.assertFalse("defaulttypeaddxyzAssertion(".matches(testableMethodPattern));
		Assertions.assertFalse("defaulttypesetxyzAssertion(".matches(testableMethodPattern));
		Assertions.assertFalse("abcdefaulttypeaddxyzAssertion(".matches(testableMethodPattern));
		Assertions.assertFalse("defaulttypesetxyzAssertion(abc".matches(testableMethodPattern));
		Assertions.assertFalse("abcdefaulttypesetxyzAssertion(cde".matches(testableMethodPattern));
	}
}

package cipm.consistency.fitests.similarity.java.utiltests;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.emftext.language.java.JavaPackage;
import org.emftext.language.java.commons.Commentable;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.AbstractSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.InitialiserPackage;

/**
 * A test class, whose tests can be used to make sure no initialiser interfaces,
 * concrete initialisers or initialiser tests are missing.
 * 
 * @author atora
 */
public class UtilityTests extends AbstractSimilarityTest {
	/**
	 * Points at the cimp.consistency.fitests.similarity.java package. Used by
	 * discovering methods in this class.
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
	 * @return Classes of concrete implementations and interfaces of all Java-Model
	 *         elements.
	 */
	public Set<Class<?>> getAllPossibleClasses() {
		return this.getAllPossibleClasses(JavaPackage.eINSTANCE.getESubpackages());
	}

	/**
	 * Recursively discovers sub-packages of cPac (including cPac) for
	 * {@link EClassifier}s contained within, aggregates the classes represented by
	 * the EClassifiers as a Set and returns the Set.
	 * 
	 * @param cPac The package, which is the start point of the discovery.
	 * @return All classes represented by EClassifiers contained in cPac and its
	 *         sub-packages. Includes types of interfaces as well as concrete
	 *         implementation classes.
	 */
	protected Set<Class<?>> getAllPossibleClasses(EPackage cPac) {
		var clss = cPac.getEClassifiers();
		var subPacs = cPac.getESubpackages();

		var foundClss = new HashSet<Class<?>>();

		if (clss != null) {
			for (var cls : clss) {
				foundClss.add(cls.getInstanceClass());

				/*
				 * Although cls is technically of type EClassifier, it also implements EClass
				 */
				if (cls instanceof EClass) {
					var castedCls = (EClass) cls;

					/*
					 * Add the concrete implementation class, if cls represents a concrete class
					 */
					if (!castedCls.isAbstract()) {
						foundClss.add(cPac.getEFactoryInstance().create(castedCls).getClass());
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
	 * @return All classes represented by EClassifiers contained in pacs and their
	 *         sub-packages. Includes types of interfaces as well as concrete
	 *         implementation classes.
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
	 * @return An instance of all initialisers.
	 */
	public Collection<IInitialiser> getAllEObjInitialiserInstances() {
		return new InitialiserPackage().getAllInitialiserInstances();
	}

	/**
	 * @return Class objects of all initialisers.
	 */
	public Collection<Class<? extends IInitialiser>> getAllEObjInitialiserClasses() {
		return new InitialiserPackage().getAllInitialiserClasses();
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
		return cls.getSimpleName() + testSuffix;
	}

	/**
	 * @return The classes from {@link #getClassesInFullHierarchy}, which should
	 *         have a corresponding initialiser interface.
	 */
	public Collection<Class<?>> getAllInitialiserCandidates() {
		var fullHierarchy = this.getAllPossibleClasses();

		var intfcs = fullHierarchy.stream().filter((c) -> Commentable.class.isAssignableFrom(c))
				.filter((c) -> !c.getSimpleName().endsWith(implSuffix)).toArray(Class<?>[]::new);

		return List.of(intfcs);
	}

	/**
	 * @return The classes from {@link #getClassesInFullHierarchy}, which should
	 *         have a corresponding concrete initialiser.
	 */
	public Collection<Class<?>> getAllConcreteInitialiserCandidates() {
		var fullHierarchy = this.getAllPossibleClasses();

		var intfcs = fullHierarchy.stream().filter((c) -> Commentable.class.isAssignableFrom(c))
				.filter((c) -> fullHierarchy.stream()
						.anyMatch((c2) -> c2.getSimpleName().equals(c.getSimpleName() + implSuffix)))
				.toArray(Class<?>[]::new);

		return List.of(intfcs);
	}

	/**
	 * @return The class of the initialiser meant to instantiate objClass.
	 */
	public Class<? extends IInitialiser> getInitialiserClsFor(Class<?> objClass) {
		return new InitialiserPackage().getInitialiserClsFor(objClass);
	}

	/**
	 * @return All classes in the need of an initialiser that actually have a
	 *         corresponding initialiser interface under {@link root}.
	 */
	public Collection<Class<?>> getClassesWithInitialiserInterface() {
		var initClss = this.getAllEObjInitialiserClasses();

		return List.of(this.getAllInitialiserCandidates().stream().filter(
				(c) -> initClss.stream().anyMatch((f) -> f.getSimpleName().equals(this.getInterfaceInitialiserName(c))))
				.toArray(Class<?>[]::new));
	}

	/**
	 * @return A list of all files under {@link #root}.
	 */
	public Collection<File> getAllFiles() {
		return this.getAllFiles(root);
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
	 * @return Whether the given file's name (without extension) equals to the given
	 *         fileName.
	 */
	public boolean fileNameEquals(File file, String fileName) {
		return file != null && file.getName().split("\\.")[0].equals(fileName);
	}

	/**
	 * @return The initialiser meant to instantiate objClass.
	 */
	public IInitialiser getInitialiserInstanceFor(Class<?> objClass) {
		return new InitialiserPackage().getInitialiserInstanceFor(objClass);
	}

	/**
	 * @return A String representing the given stream. The provided toStringFunc
	 *         will be used to transform stream elements into Strings.
	 */
	public <T extends Object> String streamToString(Stream<T> stream, Function<T, String> toStringFunc) {
		return stream.map((e) -> toStringFunc.apply(e)).reduce("", (s1, s2) -> s1 + ", " + s2).substring(2);
	}

	/**
	 * Opens a stream on the given list and delegates to
	 * {@link #clsStreamToString(Stream)}.
	 */
	public String clsListToString(List<? extends Class<?>> list) {
		return this.clsStreamToString(list.stream());
	}

	/**
	 * A variant of {@link #streamToString(Stream, Function)} for Class streams.
	 * 
	 * Maps stream elements (classes) to String by returning their simple name.
	 */
	public String clsStreamToString(Stream<? extends Class<?>> list) {
		return this.streamToString(list, (cls) -> cls.getSimpleName());
	}

	/**
	 * Prints the interfaces between everything from
	 * {@link #getAllPossibleClasses()} and {@link Commentable}.
	 */
	@Test
	public void printFullHierarchy() {
		var hSet = this.getAllPossibleClasses();
		this.getLogger().info(this.clsStreamToString(hSet.stream()));
	}

	/**
	 * Checks if all necessary concrete initialisers are provided to tests.
	 */
	@Test
	public void testAllInitialiserPackagesRegistered() {
		var clss = this.getAllConcreteInitialiserCandidates();
		var registeredInits = new InitialiserPackage().getAllInitialiserInstances();

		var matches = List.of(
				clss.stream().filter((cls) -> registeredInits.stream().anyMatch((init) -> init.isInitialiserFor(cls)))
						.toArray(Class<?>[]::new));

		this.getLogger().info(matches.size() + " out of " + clss.size() + " concrete initialisers are registered");

		if (matches.size() != clss.size()) {
			Assertions.fail("Initialisers not registered: "
					+ this.clsStreamToString(clss.stream().filter((cls) -> !matches.contains(cls))));
		}
	}

	/**
	 * Checks if all interface initialisers (ISomethingInitialiser.java) files are
	 * implemented and present under {@link #root}.
	 */
	@Test
	public void testAllInitialiserInterfacesPresent() {
		var intfcs = this.getAllInitialiserCandidates();
		var matches = this.getClassesWithInitialiserInterface();

		this.getLogger().info(matches.size() + " out of " + intfcs.size() + " initialiser interfaces are present");

		if (matches.size() != intfcs.size()) {
			Assertions.fail("Initialisers missing for: "
					+ this.clsStreamToString(intfcs.stream().filter((e) -> !matches.contains(e))));
		}
	}

	/**
	 * Checks if all concrete initialisers (SomethingInitialiser.java) files are
	 * implemented and present under {@link #root}.
	 */
	@Test
	public void testAllConcreteInitialisersPresent() {
		var pac = new InitialiserPackage();
		var intfcs = this.getAllConcreteInitialiserCandidates();
		Predicate<Class<?>> pred = (initCls) -> pac.getInitialiserInstanceFor(initCls) != null;
		var matches = intfcs.stream().filter(pred);
		var count = matches.count();

		this.getLogger().info(count + " out of " + intfcs.size() + " concrete initialisers are present");

		if (count != intfcs.size()) {
			Assertions.fail("Concrete initialisers missing for: "
					+ this.clsStreamToString(intfcs.stream().filter(pred.negate())));
		}
	}

	/**
	 * Checks if all classes in the need of an initialiser, which have their own
	 * methods that modify them (the ones they do not get from inheritance), have
	 * corresponding tests.
	 * 
	 * Only verifies, if there are corresponding test files. Does not check the unit
	 * tests they implement.
	 */
	@Test
	public void testAllInterfaceTestsPresent() {
		var intfcs = this.getAllInitialiserCandidates();
		var allFiles = this.getAllFiles();

		var matches = List.of(intfcs.stream()
				.filter((c) -> !IInitialiser.declaresModificationMethods(this.getInitialiserClsFor(c))
						|| allFiles.stream().anyMatch((tf) -> fileNameEquals(tf, getTestName(c))))
				.toArray(Class<?>[]::new));

		this.getLogger().info(matches.size() + " out of " + intfcs.size() + " interfaces are covered by tests");

		if (matches.size() != intfcs.size()) {
			Assertions.fail("Tests missing for: "
					+ this.clsStreamToString(intfcs.stream().filter((e) -> !matches.contains(e))));
		}
	}
}

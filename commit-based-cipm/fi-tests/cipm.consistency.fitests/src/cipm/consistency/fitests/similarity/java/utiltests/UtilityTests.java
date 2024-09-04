package cipm.consistency.fitests.similarity.java.utiltests;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;

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
	 * Points at the {@link cipm.consistency.fitests.similarity.java} package. Used
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
	 * @return Types of concrete implementations and interfaces of all Java-Model
	 *         elements.
	 */
	public Set<Class<?>> getAllPossibleTypes() {
		return this.getAllPossibleTypes(JavaPackage.eINSTANCE.getESubpackages());
	}

	/**
	 * Recursively discovers sub-packages of cPac (including cPac) for
	 * {@link EClassifier}s contained within, aggregates the types represented by
	 * the EClassifiers as a Set and returns the Set.
	 * 
	 * @param cPac The package, which is the start point of the discovery.
	 * @return All types represented by EClassifiers contained in cPac and its
	 *         sub-packages. Includes types of interfaces as well as concrete
	 *         implementation classes.
	 */
	protected Set<Class<?>> getAllPossibleTypes(EPackage cPac) {
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
			foundClss.addAll(this.getAllPossibleTypes(subPacs));
		}

		return foundClss;
	}

	/**
	 * @return All types represented by {@link EClassifiers} contained in pacs and
	 *         their sub-packages. Includes types of interfaces as well as concrete
	 *         implementation classes.
	 * @see {@link #getAllPossibleTypes(EPackage)}}
	 */
	protected Set<Class<?>> getAllPossibleTypes(Collection<EPackage> pacs) {
		var foundClss = new HashSet<Class<?>>();

		for (var pac : pacs) {
			foundClss.addAll(this.getAllPossibleTypes(pac));
		}

		return foundClss;
	}

	/**
	 * @return An instance of all initialisers.
	 */
	public Collection<IInitialiser> getAllInitialiserInstances() {
		return new InitialiserPackage().getAllInitialiserInstances();
	}

	/**
	 * @return Types of all initialisers.
	 */
	public Collection<Class<? extends IInitialiser>> getAllInitialiserTypes() {
		return new InitialiserPackage().getAllInitialiserInterfaceTypes();
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
	public String getInitialiserInterfaceName(Class<?> cls) {
		return initialiserInterfacePrefix + cls.getSimpleName() + initialiserSuffix;
	}

	/**
	 * @return The name of the test corresponding to cls.
	 */
	public String getTestName(Class<?> cls) {
		return cls.getSimpleName() + testSuffix;
	}

	/**
	 * Used to determine which {@link EObject} implementors should have an
	 * {@link IInitialiser}. <br>
	 * <br>
	 * Here, such implementors (initialiser candidates) implement
	 * {@link Commentable} and their names do not end with {@link #implSuffix}.
	 * 
	 * @return The classes from {@link #getAllPossibleTypes()}, which should have a
	 *         corresponding initialiser interface.
	 */
	public Collection<Class<?>> getAllInitialiserCandidates() {
		var fullHierarchy = this.getAllPossibleTypes();

		var intfcs = fullHierarchy.stream().filter((c) -> Commentable.class.isAssignableFrom(c))
				.filter((c) -> !c.getSimpleName().endsWith(implSuffix)).toArray(Class<?>[]::new);

		return List.of(intfcs);
	}

	/**
	 * @return The types from {@link #getAllPossibleTypes()}, which should have a
	 *         corresponding concrete initialiser, i.e. a class implementing
	 *         {@link IInitialiser} that can be instantiated.
	 */
	public Collection<Class<?>> getAllConcreteInitialiserCandidates() {
		var fullHierarchy = this.getAllPossibleTypes();

		var intfcs = fullHierarchy.stream().filter((c) -> Commentable.class.isAssignableFrom(c))
				.filter((c) -> fullHierarchy.stream()
						.anyMatch((c2) -> c2.getSimpleName().equals(c.getSimpleName() + implSuffix)))
				.toArray(Class<?>[]::new);

		return List.of(intfcs);
	}

	/**
	 * @return The type of the initialiser meant to instantiate objClass.
	 */
	public Class<? extends IInitialiser> getInitialiserInterfaceFor(Class<?> objClass) {
		return new InitialiserPackage().getInitialiserInterfaceTypeFor(objClass);
	}

	/**
	 * @return All types in the need of an initialiser that actually have a
	 *         corresponding initialiser interface under the {@link #root}
	 *         directory.
	 */
	public Collection<Class<?>> getClassesWithInitialiserInterface() {
		var initClss = this.getAllInitialiserTypes();

		return List.of(this.getAllInitialiserCandidates().stream().filter(
				(c) -> initClss.stream().anyMatch((f) -> f.getSimpleName().equals(this.getInitialiserInterfaceName(c))))
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
	 * Prints the interfaces between everything from {@link #getAllPossibleTypes()}
	 * and {@link Commentable}.
	 */
	@Disabled("Can be enabled to print all possible concrete EObject types, does not test anything")
	@Test
	public void printFullHierarchy() {
		var hSet = this.getAllPossibleTypes();
		this.getLogger().info(this.clsStreamToString(hSet.stream()));
	}

	/**
	 * Checks if all necessary concrete initialisers can be accessed under
	 * {@link InitialiserPackage}, which is used in initialiser tests. <br>
	 * <br>
	 * Prints the amount of accessible/registered initialiser types. The missing
	 * types can be found in the assertion message.
	 */
	@Test
	public void testAllConcreteInitialisersRegistered() {
		var clss = this.getAllConcreteInitialiserCandidates();
		var registeredInits = new InitialiserPackage().getAllInitialiserInstances();

		var matches = List.of(
				clss.stream().filter((cls) -> registeredInits.stream().anyMatch((init) -> init.isInitialiserFor(cls)))
						.toArray(Class<?>[]::new));

		this.getLogger().info(matches.size() + " out of " + clss.size() + " concrete initialisers are registered");

		if (matches.size() != clss.size()) {
			Assertions.fail("Concrete initialisers not registered: "
					+ this.clsStreamToString(clss.stream().filter((cls) -> !matches.contains(cls))));
		}
	}

	/**
	 * Checks if all necessary initialiser interface types can be accessed under
	 * {@link InitialiserPackage}, which is used in initialiser tests. <br>
	 * <br>
	 * Prints the amount of accessible/registered initialiser interface types. The
	 * missing types can be found in the assertion message.
	 */
	@Test
	public void testAllInitialiserInterfacesRegistered() {
		var clss = this.getAllInitialiserCandidates();
		var registeredInits = new InitialiserPackage().getAllInitialiserInterfaceTypes();

		var matches = List.of(clss.stream()
				.filter((cls) -> registeredInits.stream()
						.anyMatch((init) -> this.getInitialiserInterfaceName(cls).equals(init.getSimpleName())))
				.toArray(Class<?>[]::new));

		this.getLogger().info(matches.size() + " out of " + clss.size() + " initialiser interfaces are registered");

		if (matches.size() != clss.size()) {
			Assertions.fail("Initialiser interfaces not registered: "
					+ this.clsStreamToString(clss.stream().filter((cls) -> !matches.contains(cls))));
		}
	}

	/**
	 * Checks if all concrete initialisers (SomethingInitialiser.java) files are
	 * implemented and present under {@link #root}. <br>
	 * <br>
	 * Prints the number of present concrete initialiser files. Information on the
	 * missing files can be found in the assertion message. <br>
	 * <br>
	 * <b>Only checks whether the said files are present, does not inspect their
	 * content at all.</b>
	 */
	@Test
	public void testAllConcreteInitialisersPresent() {
		var intfcs = this.getAllConcreteInitialiserCandidates();
		var files = this.getAllFiles();

		var matches = List.of(intfcs.stream().filter(
				(ifc) -> files.stream().anyMatch((f) -> this.fileNameEquals(f, this.getConcreteInitialiserName(ifc))))
				.toArray(Class<?>[]::new));
		var count = matches.size();

		this.getLogger().info(count + " out of " + intfcs.size() + " concrete initialiser files are present");

		if (count != intfcs.size()) {
			Assertions.fail("Concrete initialiser files missing for: "
					+ this.clsStreamToString(intfcs.stream().filter((ifc) -> !matches.contains(ifc))));
		}
	}

	/**
	 * Checks if all interface initialisers (ISomethingInitialiser.java) files are
	 * implemented and present under {@link #root}. <br>
	 * <br>
	 * Prints the number of present initialiser files. Information on the missing
	 * files can be found in the assertion message. <br>
	 * <br>
	 * <b>Only checks whether the said files are present, does not inspect their
	 * content at all.</b>
	 */
	@Test
	public void testAllInitialiserInterfacesPresent() {
		var intfcs = this.getAllInitialiserCandidates();
		var files = this.getAllFiles();

		var matches = List.of(intfcs.stream().filter(
				(ifc) -> files.stream().anyMatch((f) -> this.fileNameEquals(f, this.getInitialiserInterfaceName(ifc))))
				.toArray(Class<?>[]::new));
		var count = matches.size();

		this.getLogger().info(count + " out of " + intfcs.size() + " initialiser files are present");

		if (count != intfcs.size()) {
			Assertions.fail("Initialiser files missing for: "
					+ this.clsStreamToString(intfcs.stream().filter((ifc) -> !matches.contains(ifc))));
		}
	}

	/**
	 * Checks if all classes in the need of an initialiser, which have their own
	 * methods that modify them (the methods they do not get from inheritance), have
	 * their corresponding test file. <br>
	 * <br>
	 * In short, checks if all necessary initialiser test files are present. <br>
	 * <br>
	 * Prints the number of required test files that are actually present.
	 * Information on the missing test files can be found in the assertion message.
	 * <br>
	 * <br>
	 * <b> Only checks whether there are corresponding test files. Does not check
	 * the unit tests they implement. </b>
	 */
	@Test
	public void testAllInterfaceTestsPresent() {
		var intfcs = this.getAllInitialiserCandidates();
		var allFiles = this.getAllFiles();

		var matches = List.of(intfcs.stream()
				.filter((c) -> !IInitialiser.declaresModificationMethods(this.getInitialiserInterfaceFor(c))
						|| allFiles.stream().anyMatch((tf) -> fileNameEquals(tf, getTestName(c))))
				.toArray(Class<?>[]::new));

		this.getLogger().info(matches.size() + " out of " + intfcs.size() + " interfaces are covered by tests");

		if (matches.size() != intfcs.size()) {
			Assertions.fail("Tests missing for: "
					+ this.clsStreamToString(intfcs.stream().filter((e) -> !matches.contains(e))));
		}
	}
}

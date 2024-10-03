package cipm.consistency.fitests.similarity.jamopp.utiltests;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.jamopp.AbstractEMFTextSimilarityTest;
import cipm.consistency.initialisers.IInitialiser;
import cipm.consistency.initialisers.eobject.InitialiserNameHelper;
import cipm.consistency.initialisers.jamopp.EMFtextHelper;

/**
 * A test class, whose tests can be used to make sure no initialiser interfaces,
 * concrete initialisers or initialiser tests are missing.
 * 
 * @author atora
 */
public class UtilityTests extends AbstractEMFTextSimilarityTest {
	/**
	 * Points at the parent package. Used by discovering methods in this class.
	 */
	private static final File root = new File(Paths
			.get(new File("").toPath().toString(), "src", UtilityTests.class.getPackageName()
					.substring(0, UtilityTests.class.getPackageName().lastIndexOf(".")).replace(".", File.separator))
			.toAbsolutePath().toUri());

	/**
	 * The suffix used in tests.
	 */
	private static final String testSuffix = "Test";

	/**
	 * @return The given text without whitespaces (includes line breaks).
	 */
	public String removeWhitespaces(String text) {
		return text.replaceAll("\\n", "").replaceAll("\\r", "").replaceAll("\\s", "");
	}

	/**
	 * @return An instance of all initialisers.
	 */
	public Collection<IInitialiser> getAllInitialiserInstances() {
		return this.getUsedInitialiserPackage().getAllInitialiserInstances();
	}

	/**
	 * @return Types of all initialisers.
	 */
	public Collection<Class<? extends IInitialiser>> getAllInitialiserTypes() {
		return this.getUsedInitialiserPackage().getAllInitialiserInterfaceTypes();
	}

	/**
	 * @return The name of the concrete initialiser corresponding to cls.
	 */
	public String getConcreteInitialiserName(Class<?> cls) {
		return InitialiserNameHelper.getConcreteInitialiserName(cls);
	}

	/**
	 * @return The name of the initialiser interface corresponding to cls.
	 */
	public String getInitialiserInterfaceName(Class<?> cls) {
		return InitialiserNameHelper.getInitialiserInterfaceName(cls);
	}

	/**
	 * @return The name of the test corresponding to cls.
	 */
	public String getTestName(Class<?> cls) {
		return cls.getSimpleName() + testSuffix;
	}

	/**
	 * @return The type of the initialiser meant to instantiate objClass.
	 */
	public Class<? extends IInitialiser> getInitialiserInterfaceFor(Class<?> objClass) {
		return this.getUsedInitialiserPackage().getInitialiserInterfaceTypeFor(objClass);
	}

	/**
	 * @return All types in the need of an initialiser that actually have a
	 *         corresponding initialiser interface under the {@link #root}
	 *         directory.
	 */
	public Collection<Class<?>> getClassesWithInitialiserInterface() {
		var initClss = this.getAllInitialiserTypes();

		return List.of(EMFtextHelper.getAllInitialiserCandidates().stream().filter(
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
		return this.getUsedInitialiserPackage().getInitialiserInstanceFor(objClass);
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
	 * Checks if all necessary concrete initialisers can be accessed under the used
	 * initialiser package, which is used in initialiser tests. <br>
	 * <br>
	 * Prints the amount of accessible/registered initialiser types. The missing
	 * types can be found in the assertion message.
	 */
	@Test
	public void testAllConcreteInitialisersRegistered() {
		var clss = EMFtextHelper.getAllConcreteInitialiserCandidates();
		var registeredInits = this.getUsedInitialiserPackage().getAllInitialiserInstances();

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
	 * Checks if all necessary initialiser interface types can be accessed under the
	 * used initialiser package, which is used in initialiser tests. <br>
	 * <br>
	 * Prints the amount of accessible/registered initialiser interface types. The
	 * missing types can be found in the assertion message.
	 */
	@Test
	public void testAllInitialiserInterfacesRegistered() {
		var clss = EMFtextHelper.getAllInitialiserCandidates();
		var registeredInits = this.getUsedInitialiserPackage().getAllInitialiserInterfaceTypes();

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
		var intfcs = EMFtextHelper.getAllInitialiserCandidates();
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

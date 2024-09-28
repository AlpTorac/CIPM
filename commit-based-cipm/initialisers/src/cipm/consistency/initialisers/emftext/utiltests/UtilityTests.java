package cipm.consistency.initialisers.emftext.utiltests;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import cipm.consistency.initialisers.IInitialiser;
import cipm.consistency.initialisers.IInitialiserPackage;
import cipm.consistency.initialisers.emftext.EMFtextHelper;
import cipm.consistency.initialisers.emftext.EMFtextInitialiserPackage;
import cipm.consistency.initialisers.eobject.InitialiserNameHelper;

/**
 * A test class, whose tests can be used to make sure no initialiser interfaces
 * or concrete initialisers are missing.
 * 
 * @author atora
 */
public class UtilityTests {
	private static final Logger LOGGER = Logger.getLogger(UtilityTests.class);
	/**
	 * Points at the parent package. Used by discovering methods in this class.
	 */
	private static final File root = new File(Paths
			.get(new File("").toPath().toString(), "src", UtilityTests.class.getPackageName()
					.substring(0, UtilityTests.class.getPackageName().lastIndexOf(".")).replace(".", File.separator))
			.toAbsolutePath().toUri());

	@BeforeEach
	public void setUp() {
		Logger logger = this.getLogger();
		logger.setLevel(Level.ALL);

		logger = Logger.getRootLogger();
		logger.removeAllAppenders();
		ConsoleAppender ap = new ConsoleAppender(new PatternLayout("[%d{DATE}] %-5p: %c - %m%n"),
				ConsoleAppender.SYSTEM_OUT);
		logger.addAppender(ap);
	}

	public Logger getLogger() {
		return LOGGER;
	}

	public IInitialiserPackage getUsedInitialiserPackage() {
		return new EMFtextInitialiserPackage();
	}

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

		return List.of(EMFtextHelper.getAllInitialiserCandidates().stream()
				.filter((c) -> initClss.stream().anyMatch(
						(f) -> f.getSimpleName().equals(InitialiserNameHelper.getInitialiserInterfaceName(c))))
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
	 * Prints the interfaces between everything from {@link #getAllPossibleTypes()}
	 * and {@link Commentable}.
	 */
	@Disabled("Can be enabled to print all possible concrete EObject types, does not test anything")
	@Test
	public void printFullHierarchy() {
		var hSet = EMFtextHelper.getAllPossibleTypes();
		this.getLogger().info(this.clsStreamToString(hSet.stream()));
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
				.filter((cls) -> registeredInits.stream().anyMatch(
						(init) -> InitialiserNameHelper.getInitialiserInterfaceName(cls).equals(init.getSimpleName())))
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
		var intfcs = EMFtextHelper.getAllConcreteInitialiserCandidates();
		var files = this.getAllFiles();

		var matches = List.of(intfcs.stream()
				.filter((ifc) -> files.stream()
						.anyMatch((f) -> this.fileNameEquals(f, InitialiserNameHelper.getConcreteInitialiserName(ifc))))
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
		var intfcs = EMFtextHelper.getAllInitialiserCandidates();
		var files = this.getAllFiles();

		var matches = List.of(intfcs.stream()
				.filter((ifc) -> files.stream().anyMatch(
						(f) -> this.fileNameEquals(f, InitialiserNameHelper.getInitialiserInterfaceName(ifc))))
				.toArray(Class<?>[]::new));
		var count = matches.size();

		this.getLogger().info(count + " out of " + intfcs.size() + " initialiser files are present");

		if (count != intfcs.size()) {
			Assertions.fail("Initialiser files missing for: "
					+ this.clsStreamToString(intfcs.stream().filter((ifc) -> !matches.contains(ifc))));
		}
	}
}

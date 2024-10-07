package cipm.consistency.initialisers.jamopp.utiltests;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
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
import cipm.consistency.initialisers.eobject.InitialiserNameHelper;
import cipm.consistency.initialisers.jamopp.JaMoPPHelper;
import cipm.consistency.initialisers.jamopp.JaMoPPInitialiserPackage;

/**
 * A test class, whose tests can be used to make sure no initialiser interfaces
 * or concrete initialisers are missing.
 * 
 * @author Alp Torac Genc
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
		return new JaMoPPInitialiserPackage();
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
	public Collection<Class<? extends IInitialiser>> getAllInitialiserInterfaceTypes() {
		return this.getUsedInitialiserPackage().getAllInitialiserInterfaceTypes();
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
	 * {@link JaMoPPHelper#getAllPossibleTypes()}
	 */
	public Set<Class<?>> getAllPossibleJaMoPPEObjectTypes() {
		return JaMoPPHelper.getAllPossibleTypes();
	}

	/**
	 * {@link JaMoPPHelper#getAllInitialiserCandidates()}
	 */
	public Collection<Class<?>> getAllInitialiserCandidates() {
		return JaMoPPHelper.getAllInitialiserCandidates();
	}

	/**
	 * {@link JaMoPPHelper#getAllConcreteInitialiserCandidates()}
	 */
	public Collection<Class<?>> getAllConcreteInitialiserCandidates() {
		return JaMoPPHelper.getAllConcreteInitialiserCandidates();
	}

	/**
	 * {@link InitialiserNameHelper#getInitialiserInterfaceName(Class)}
	 */
	public String getInitialiserInterfaceName(Class<?> cls) {
		return InitialiserNameHelper.getInitialiserInterfaceName(cls);
	}

	/**
	 * {@link InitialiserNameHelper#getConcreteInitialiserName(Class)}
	 */
	public String getConcreteInitialiserName(Class<?> ifc) {
		return InitialiserNameHelper.getConcreteInitialiserName(ifc);
	}

	/**
	 * Prints the interfaces between everything from {@link #getAllPossibleTypes()}
	 * and {@link Commentable}.
	 */
	@Disabled("Can be enabled to print all possible concrete EObject types, does not test anything")
	@Test
	public void printFullHierarchy() {
		var hSet = this.getAllPossibleJaMoPPEObjectTypes();
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
		var clss = this.getAllConcreteInitialiserCandidates();
		var registeredInits = this.getAllInitialiserInstances();

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
		var clss = this.getAllInitialiserCandidates();
		var registeredInits = this.getAllInitialiserInterfaceTypes();

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
}

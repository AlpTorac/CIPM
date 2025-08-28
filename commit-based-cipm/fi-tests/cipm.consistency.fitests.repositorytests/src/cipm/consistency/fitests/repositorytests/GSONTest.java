package cipm.consistency.fitests.repositorytests;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.jamopp.parser.timemeasurement.DefaultTimeMeasurementDataStructure;
import cipm.consistency.fitests.similarity.jamopp.parser.timemeasurement.GSONLoadingStrategy;
import cipm.consistency.fitests.similarity.jamopp.parser.timemeasurement.GSONPersistingStrategy;
import cipm.consistency.fitests.similarity.jamopp.parser.timemeasurement.GeneralTimeMeasurementTag;
import cipm.consistency.fitests.similarity.jamopp.parser.timemeasurement.ITimeMeasurementDataStructure;
import cipm.consistency.fitests.similarity.jamopp.parser.timemeasurement.ITimeMeasurementLoadingStrategy;
import cipm.consistency.fitests.similarity.jamopp.parser.timemeasurement.ITimeMeasurementPersistingStrategy;
import cipm.consistency.fitests.similarity.jamopp.parser.timemeasurement.ITimeMeasurementTag;
import cipm.consistency.fitests.similarity.jamopp.parser.timemeasurement.ParserTestTimeMeasurerKeyType;

/**
 * TODO Commentary
 */
public class GSONTest {
	private static final DateTimeFormatter fileContentTimePattern = DateTimeFormatter.ISO_DATE_TIME;
	private static final Path targetRootPath = Path.of("target").toAbsolutePath();
	private static final Path timeMeasurementsRootPath = targetRootPath.resolve("timeMeasurements");
	@SuppressWarnings("unchecked")
	private static final ITimeMeasurementLoadingStrategy loadingStrat = new GSONLoadingStrategy(fileContentTimePattern,
			DefaultTimeMeasurementDataStructure.class,
			new Class[] { GeneralTimeMeasurementTag.class, RepoTimeMeasurementTag.class });
	private static final ITimeMeasurementPersistingStrategy persistingStrat = new GSONPersistingStrategy(
			fileContentTimePattern);

	private Path formerTimeMeasurementPath;
	private Path newTimeMeasurementPath;

	@BeforeEach
	public void setUp() {
		var timeMeasurementsRootDir = timeMeasurementsRootPath.toFile();
		if (!timeMeasurementsRootDir.exists() || timeMeasurementsRootDir.listFiles().length == 0) {
			Assertions.fail(
					"There are no time measurement files to use in tests, make sure to provide at least one time measurement file in JSON format under: "
							+ timeMeasurementsRootPath.toString());
		} else {
			formerTimeMeasurementPath = timeMeasurementsRootDir.listFiles()[0].toPath().toAbsolutePath();
			newTimeMeasurementPath = targetRootPath.resolve(formerTimeMeasurementPath.getFileName()).toAbsolutePath();
		}
	}

	@AfterEach
	public void tearDown() {
		if (newTimeMeasurementPath != null && newTimeMeasurementPath.toFile().exists()) {
			try {
				Files.delete(newTimeMeasurementPath);
			} catch (IOException e) {
				e.printStackTrace();
				Assertions.fail(e);
			}
		}
		formerTimeMeasurementPath = null;
		newTimeMeasurementPath = null;
	}

	private String readTimeMeasurement(Path pathToTimeMeasurementToRead) {
		String content = null;
		try {
			content = Files.readString(pathToTimeMeasurementToRead);
			Assertions.assertFalse(content.isBlank());
		} catch (IOException e) {
			e.printStackTrace();
			Assertions.fail(e);
		}

		return content;
	}

	private ITimeMeasurementDataStructure loadTimeMeasurement(Path pathToTimeMeasurementToLoad) {
		return loadingStrat.load(pathToTimeMeasurementToLoad);
	}

	private void persistTimeMeasurement(ITimeMeasurementDataStructure dataStructure, Path savePath) {
		persistingStrat.save(dataStructure, savePath);
	}

	private void assertDataStructureIntact(ITimeMeasurementDataStructure timeMeasurements) {
		Assertions.assertNotNull(timeMeasurements.getTimeMeasurerDescription());

		Assertions.assertNotNull(timeMeasurements.getEndTime());

		Assertions.assertNotNull(timeMeasurements.getStartTime());

		var entries = timeMeasurements.getTimeMeasurementEntries();
		Assertions.assertNotNull(entries);
		Assertions.assertFalse(entries.isEmpty());

		timeMeasurements.getTimeMeasurementEntries().forEach((e) -> {
			var key = e.getKey();
			Assertions.assertNotNull(key);
			key.getKeys().entrySet().forEach((ke) -> {
				Assertions.assertNotNull(ke.getKey());
				Assertions.assertTrue(ParserTestTimeMeasurerKeyType.class.isAssignableFrom(ke.getKey().getClass()));
				Assertions.assertNotNull(ke.getValue());
			});
			Assertions.assertNotNull(e.getTag());
			Assertions.assertTrue(ITimeMeasurementTag.class.isAssignableFrom(e.getTag().getClass()));
		});

		var tu = timeMeasurements.getTimeUnit();
		Assertions.assertNotNull(tu);
		Assertions.assertTrue(TimeUnit.class.isAssignableFrom(tu.getClass()));
	}

	/**
	 * Ensures that loading previously saved time measurements works as intended
	 */
	@Test
	public void testDataStructureLoading() {
		var timeMeasurements = this.loadTimeMeasurement(formerTimeMeasurementPath);
		this.assertDataStructureIntact(timeMeasurements);
	}

	/**
	 * Ensures that loaded and re-saved time measurements can be parsed as intended
	 */
	@Test
	public void testSavedDataStructureLoading() {
		var timeMeasurements = this.loadTimeMeasurement(formerTimeMeasurementPath);
		this.persistTimeMeasurement(timeMeasurements, newTimeMeasurementPath);
		var persistedTimeMeasurements = this.loadTimeMeasurement(newTimeMeasurementPath);
		this.assertDataStructureIntact(persistedTimeMeasurements);
	}

	/**
	 * Ensures that loaded and re-saved time measurements can be parsed as intended
	 * and the content of their files are equal
	 */
	@Test
	public void testSavedDataStructureLoading_ContentEquality() {
		var formerFileContent = this.readTimeMeasurement(formerTimeMeasurementPath);
		var formerTimeMeasurements = this.loadTimeMeasurement(formerTimeMeasurementPath);
		this.persistTimeMeasurement(formerTimeMeasurements, newTimeMeasurementPath);

		var newFileContent = this.readTimeMeasurement(newTimeMeasurementPath);
		// Enable if GSON serialises Long instances with trailing zeroes (".0")
		// newFileContent = newFileContent.replaceAll("\\.0,", ",");

		// Ensure that serialising produces the same file
		Assertions.assertEquals(formerFileContent, newFileContent);

		var newTimeMeasurements = this.loadTimeMeasurement(newTimeMeasurementPath);

		// Ensure that deserialising freshly serialised instance produces an equal
		// instance
		Assertions.assertTrue(formerTimeMeasurements.equals(newTimeMeasurements));
	}
}

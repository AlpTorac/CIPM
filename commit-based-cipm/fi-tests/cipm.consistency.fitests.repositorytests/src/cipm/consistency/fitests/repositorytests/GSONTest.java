package cipm.consistency.fitests.repositorytests;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.jamopp.parser.timemeasurement.GSONDataStructureLoadingStrategy;
import cipm.consistency.fitests.similarity.jamopp.parser.timemeasurement.GSONPersistingStrategy;
import cipm.consistency.fitests.similarity.jamopp.parser.timemeasurement.GeneralTimeMeasurementTag;
import cipm.consistency.fitests.similarity.jamopp.parser.timemeasurement.ITimeMeasurementDataStructure;
import cipm.consistency.fitests.similarity.jamopp.parser.timemeasurement.ITimeMeasurementLoadingStrategy;
import cipm.consistency.fitests.similarity.jamopp.parser.timemeasurement.ITimeMeasurementPersistingStrategy;
import cipm.consistency.fitests.similarity.jamopp.parser.timemeasurement.ITimeMeasurementTag;

/**
 * TODO Commentary
 */
public class GSONTest {
	private static final DateTimeFormatter fileContentTimePattern = DateTimeFormatter.ISO_DATE_TIME;
	private static final Path timeMeasurementsRootPath = Path.of("target", "timeMeasurements").toAbsolutePath();
	@SuppressWarnings("unchecked")
	private static final ITimeMeasurementLoadingStrategy loadingStrat = new GSONDataStructureLoadingStrategy(
			fileContentTimePattern, new Class[] { GeneralTimeMeasurementTag.class, RepoTimeMeasurementTag.class });
	private static final ITimeMeasurementPersistingStrategy persistingStrat = new GSONPersistingStrategy(
			fileContentTimePattern, DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss"));

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
			newTimeMeasurementPath = new File("").toPath().toAbsolutePath()
					.resolve(formerTimeMeasurementPath.getFileName());
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

	@Test
	public void loadDataStructure() {
		var timeMeasurements = this.loadTimeMeasurement(formerTimeMeasurementPath);

		Assertions.assertNotNull(timeMeasurements.getTimeMeasurerDescription());

		Assertions.assertNotNull(timeMeasurements.getEndTime());

		Assertions.assertNotNull(timeMeasurements.getStartTime());

		Assertions.assertNotNull(timeMeasurements.getTimeMeasurementEntries());
		Assertions.assertFalse(timeMeasurements.getTimeMeasurementEntries().isEmpty());
		timeMeasurements.getTimeMeasurementEntries().forEach((e) -> {
			Assertions.assertNotNull(e.getKey());
			// TODO Assertions about the key
			Assertions.assertNotNull(e.getTag());
			Assertions.assertTrue(ITimeMeasurementTag.class.isAssignableFrom(e.getTag().getClass()));
		});

		Assertions.assertNotNull(timeMeasurements.getTimeUnit());
	}

	@Test
	public void loadAndSaveDataStructure() {
		var formerFileContent = this.readTimeMeasurement(formerTimeMeasurementPath);
		var formerTimeMeasurements = this.loadTimeMeasurement(formerTimeMeasurementPath);

		var newFilePath = new File("").getAbsoluteFile().toPath();
		// TODO Change to "newTimeMeasurementPath" after extracting file name from
		// persisting strategy
		this.persistTimeMeasurement(formerTimeMeasurements, newFilePath);

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

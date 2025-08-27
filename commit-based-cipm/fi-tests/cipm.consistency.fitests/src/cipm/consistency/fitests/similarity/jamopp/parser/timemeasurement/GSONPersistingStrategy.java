package cipm.consistency.fitests.similarity.jamopp.parser.timemeasurement;

import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * TODO Commentary
 * 
 * @author Alp Torac Genc
 */
public class GSONPersistingStrategy implements ITimeMeasurementPersistingStrategy {
	/**
	 * @see {@link #GSONPersistingStrategy(String, DateTimeFormatter)}
	 */
	private DateTimeFormatter fileContentTimePattern;
	/**
	 * @see {@link #GSONPersistingStrategy(String, DateTimeFormatter)}
	 */
	private DateTimeFormatter filenameTimeFormatter;

	/**
	 * @param fileContentTimePattern The pattern that will be used to transform a
	 *                               date to a string, which will be written into
	 *                               the saved measurements file.
	 * @param filenameTimeFormatter  The pattern that will be used to transform a
	 *                               date to a string, which will be used in the
	 *                               name of the saved measurements file.
	 */
	public GSONPersistingStrategy(DateTimeFormatter fileContentTimePattern, DateTimeFormatter filenameTimeFormatter) {
		this.fileContentTimePattern = fileContentTimePattern;
		this.filenameTimeFormatter = filenameTimeFormatter;
	}

	public void save(ITimeMeasurementDataStructure dataStructure, Path measurementsSavePath) {
		var filePath = measurementsSavePath
				.resolve(this.getFullFileName(dataStructure.getStartTime(), dataStructure.getEndTime()));

		// Ensure that all necessary parent directories exist prior to saving
		measurementsSavePath.toFile().mkdirs();

		// TODO Register type adapter for dates (use fileContentTimePattern somehow)
		// TODO Register type adapter for tags

		var gson = new GsonBuilder().setPrettyPrinting()
				.registerTypeHierarchyAdapter(LocalDateTime.class, this.getDateAdapter()).create();
		try (BufferedWriter writer = Files.newBufferedWriter(filePath); var gsonWriter = gson.newJsonWriter(writer)) {
			gson.toJson(dataStructure, dataStructure.getClass(), gsonWriter);
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(
					String.format("Could not save the expected similarity results at %s", filePath), e);
		}
	}

	private JsonSerializer<LocalDateTime> getDateAdapter() {
		return new JsonSerializer<LocalDateTime>() {
			@Override
			public JsonElement serialize(LocalDateTime src, Type typeOfSrc, JsonSerializationContext context) {
				return new JsonPrimitive(fileContentTimePattern.format(src));
			}
		};
	}

	/**
	 * @return The name (with file extension) of the measurements file that will be
	 *         saved.
	 */
	private String getFullFileName(LocalDateTime startTime, LocalDateTime endTime) {
		var fileExtension = ".json";
		return String.format("%s___%s%s", filenameTimeFormatter.format(startTime),
				filenameTimeFormatter.format(endTime), fileExtension);
	}
}

package cipm.consistency.fitests.similarity.jamopp.parser.timemeasurement;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

/**
 * TODO Rename and Commentary
 * 
 * TODO Add methods for adding type adapters (leave current type adapters here)
 * 
 * Note: {@link Gson} may require type adapters (especially
 * {@link JsonDeserializer}) for cases, where the data structure to be parsed
 * internally declares attributes with non-constructible types (abstract classes
 * or interfaces). In such cases, either the necessary type adapters should be
 * provided manually or this class has to be extended. Not providing the
 * necessary type adapters will result in exceptions.
 * 
 * @author Alp Torac Genc
 */
public class GSONDataStructureLoadingStrategy implements ITimeMeasurementLoadingStrategy {
	/**
	 * @see {@link #GSONLoadingStrategy(DateTimeFormatter, Class[])}
	 */
	private final DateTimeFormatter fileContentDateFormatter;
	/**
	 * @see {@link #GSONLoadingStrategy(DateTimeFormatter, Class[])}
	 */
	private final Set<ITimeMeasurementTag> possibleTags = new HashSet<ITimeMeasurementTag>();

	/**
	 * {@link #GSONDataStructureLoadingStrategy(DateTimeFormatter, Class[])} without
	 * any class parameters
	 */
	public GSONDataStructureLoadingStrategy(DateTimeFormatter fileContentDateFormatter) {
		this(fileContentDateFormatter, null);
	}

	/**
	 * @param fileContentDateFormatter See {@link #getFileContentDateFormatter()}
	 * @param possibleTagSubclasses    See {@link #addTagSubclass(Class)}
	 */
	public GSONDataStructureLoadingStrategy(DateTimeFormatter fileContentDateFormatter,
			Class<ITimeMeasurementTag>[] possibleTagSubclasses) {
		this.fileContentDateFormatter = fileContentDateFormatter;

		if (possibleTagSubclasses != null) {
			for (var ts : possibleTagSubclasses) {
				this.addTagSubclass(ts);
			}
		}
	}

	/**
	 * @return The {@link Gson} instance to use while parsing the data structure
	 */
	protected Gson buildGSON() {
		return new GsonBuilder().registerTypeHierarchyAdapter(LocalDateTime.class, this.getDateDeserializer())
				.registerTypeHierarchyAdapter(ITimeMeasurementTag.class, this.getTagDeserializer()).create();
	}

	/**
	 * 
	 * @param possibleTagSubclass A concrete (enum) sub-class of
	 *                            {@link ITimeMeasurementTag} that should be
	 *                            considered, while parsing the data structure.
	 *                            These sub-classes have to be provided manually, as
	 *                            there is no clean way to access all sub-types of a
	 *                            given type programmatically
	 */
	public void addTagSubclass(Class<ITimeMeasurementTag> possibleTagSubclass) {
		if (possibleTagSubclass != null) {
			for (var ec : possibleTagSubclass.getEnumConstants()) {
				this.possibleTags.add(ec);
			}
		}
	}

	/**
	 * @implSpec Attempts to parse a {@link DefaultTimeMeasurementDataStructure} instance from the
	 *           file at the given absolute path. Throws
	 *           {@link IllegalArgumentException} if an {@link IOException} occurs
	 *           in the process.
	 */
	@Override
	public DefaultTimeMeasurementDataStructure load(Path pathToDataStructureFile) {
		String fileContent;
		try {
			fileContent = Files.readString(pathToDataStructureFile);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}

		return this.buildGSON().fromJson(fileContent, DefaultTimeMeasurementDataStructure.class);
	}

	/**
	 * @return The date format, which will be used while parsing dates
	 */
	public DateTimeFormatter getFileContentDateFormatter() {
		return this.fileContentDateFormatter;
	}

	private JsonDeserializer<LocalDateTime> getDateDeserializer() {
		return new JsonDeserializer<LocalDateTime>() {
			@Override
			public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
					throws JsonParseException {
				var date = json.getAsString();
				return LocalDateTime.from(getFileContentDateFormatter().parse(date));
			}
		};
	}

	private JsonDeserializer<ITimeMeasurementTag> getTagDeserializer() {
		return new JsonDeserializer<ITimeMeasurementTag>() {
			@Override
			public ITimeMeasurementTag deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
					throws JsonParseException {
				var tagString = json.getAsString();
				ITimeMeasurementTag tag = null;
				for (var tagEnum : possibleTags) {
					if (tagString.equals(tagEnum.toString())) {
						tag = tagEnum;
						break;
					}
				}
				return tag;
			}
		};
	}
}

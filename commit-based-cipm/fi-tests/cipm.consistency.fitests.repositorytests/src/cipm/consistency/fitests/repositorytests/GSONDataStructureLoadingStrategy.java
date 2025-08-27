package cipm.consistency.fitests.repositorytests;

import java.lang.reflect.Type;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import cipm.consistency.fitests.similarity.jamopp.parser.timemeasurement.GSONDataStructure;
import cipm.consistency.fitests.similarity.jamopp.parser.timemeasurement.ITimeMeasurementDataStructureEntry;
import cipm.consistency.fitests.similarity.jamopp.parser.timemeasurement.ITimeMeasurementLoadingStrategy;
import cipm.consistency.fitests.similarity.jamopp.parser.timemeasurement.ITimeMeasurementTag;
import cipm.consistency.fitests.similarity.jamopp.parser.timemeasurement.ParserTestTimeMeasurementKey;
import cipm.consistency.fitests.similarity.jamopp.parser.timemeasurement.TimeMeasurementEntry;

public class GSONDataStructureLoadingStrategy implements ITimeMeasurementLoadingStrategy {
	private static Gson gson;
	/**
	 * @see {@link #GSONLoadingStrategy(DateTimeFormatter, Class[])}
	 */
	private final DateTimeFormatter fileContentDateFormatter;
	/**
	 * @see {@link #GSONLoadingStrategy(DateTimeFormatter, Class[])}
	 */
	private final List<ITimeMeasurementTag> possibleTags = new ArrayList<ITimeMeasurementTag>();

	/**
	 * @param fileContentDateFormatter The date format, which will be used while
	 *                                 parsing dates
	 * @param possibleTagSubclasses    All concrete (enum) sub-classes of
	 *                                 {@link ITimeMeasurementTag} that should be
	 *                                 considered. This has to be provided manually,
	 *                                 as there is no clean way to access
	 *                                 sub-classes of a given class programmatically
	 */
	public GSONDataStructureLoadingStrategy(DateTimeFormatter fileContentDateFormatter,
			Class<ITimeMeasurementTag>[] possibleTagSubclasses) {
		this.fileContentDateFormatter = fileContentDateFormatter;

		for (var ts : possibleTagSubclasses) {
			for (var ec : ts.getEnumConstants()) {
				this.possibleTags.add(ec);
			}
		}

		this.initialiseGSON();
	}

	private void initialiseGSON() {
		if (gson == null) {
			gson = new GsonBuilder().registerTypeHierarchyAdapter(LocalDateTime.class, this.getDateDeserializer())
					.registerTypeHierarchyAdapter(ITimeMeasurementDataStructureEntry.class, this.getEntryDeserializer())
					.registerTypeHierarchyAdapter(ITimeMeasurementTag.class, this.getTagDeserializer()).create();
		}
	}

	@Override
	public GSONDataStructure load(Path pathToDataStructureFile) {
		// TODO Auto-generated method stub
		return null;
	}

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

	private JsonDeserializer<ITimeMeasurementDataStructureEntry> getEntryDeserializer() {
		return new JsonDeserializer<ITimeMeasurementDataStructureEntry>() {
			@Override
			public ITimeMeasurementDataStructureEntry deserialize(JsonElement json, Type typeOfT,
					JsonDeserializationContext context) throws JsonParseException {
				var entryObj = json.getAsJsonObject();
				var time = gson.fromJson(entryObj.get("timeElapsed"), Long.class);

				var key = gson.fromJson(entryObj.get("key"), ParserTestTimeMeasurementKey.class);

				var tag = gson.fromJson(entryObj.get("tag"), ITimeMeasurementTag.class);
				var entry = new TimeMeasurementEntry(key, tag);
				entry.setTimeElapsed(time);

				return entry;
			}
		};
	}
}

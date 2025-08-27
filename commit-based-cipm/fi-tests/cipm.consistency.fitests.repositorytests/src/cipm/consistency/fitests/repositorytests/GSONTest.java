package cipm.consistency.fitests.repositorytests;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import cipm.consistency.fitests.similarity.jamopp.parser.timemeasurement.GSONDataStructure;
import cipm.consistency.fitests.similarity.jamopp.parser.timemeasurement.GSONPersistingStrategy;
import cipm.consistency.fitests.similarity.jamopp.parser.timemeasurement.GeneralTimeMeasurementTag;
import cipm.consistency.fitests.similarity.jamopp.parser.timemeasurement.ITimeMeasurementDataStructureEntry;
import cipm.consistency.fitests.similarity.jamopp.parser.timemeasurement.ITimeMeasurementTag;
import cipm.consistency.fitests.similarity.jamopp.parser.timemeasurement.ParserTestTimeMeasurementKey;
import cipm.consistency.fitests.similarity.jamopp.parser.timemeasurement.TimeMeasurementEntry;

/**
 * TODO Either remove or deal with magic strings before pushing
 */
public class GSONTest {
	private Gson gson = null;
	private Path newJsonPath;

	@AfterEach
	public void tearDown() {
		if (newJsonPath != null && newJsonPath.toFile().exists()) {
			try {
				Files.delete(newJsonPath);
			} catch (IOException e) {
				e.printStackTrace();
				Assertions.fail(e);
			}
		}
		newJsonPath = null;
	}

	@Test
	public void deserialiseAndSerialiseJSON() {
		gson = new GsonBuilder().registerTypeHierarchyAdapter(LocalDateTime.class, this.getDateDeserializer())
				.registerTypeHierarchyAdapter(ITimeMeasurementDataStructureEntry.class, getEntryDeserializer())
				.registerTypeHierarchyAdapter(ITimeMeasurementTag.class, getTagDeserializer()).create();

		var formerFile = new File(
				"C:\\Users\\sdq-l\\CIPM2\\commit-based-cipm\\fi-tests\\cipm.consistency.fitests.repositorytests\\target\\timeMeasurements\\27-08-2025_21-07-21___27-08-2025_21-08-11.json");

		String formerFileContent = null;
		try {
			formerFileContent = Files.readString(formerFile.toPath());
		} catch (IOException e) {
			e.printStackTrace();
			Assertions.fail(e);
		}

		var formerJson = gson.fromJson(formerFileContent, GSONDataStructure.class);

		// Deserialising works

		var persistingStrat = new GSONPersistingStrategy(DateTimeFormatter.ISO_DATE_TIME,
				DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss"));

		var newFile = new File("").getAbsoluteFile();
		persistingStrat.save(formerJson, newFile.toPath());
		newJsonPath = newFile.toPath().resolve("27-08-2025_21-07-21___27-08-2025_21-08-11.json");

		String newFileContent = null;
		try {
			newFileContent = Files.readString(newJsonPath);
//			newFileContent = newFileContent.replaceAll("\\.0,", ",");
		} catch (IOException e) {
			e.printStackTrace();
			Assertions.fail(e);
		}

		Assertions.assertEquals(formerFileContent, newFileContent);

		// Serialising works and produces the same file

		var newJson = gson.fromJson(newFileContent, GSONDataStructure.class);

		Assertions.assertTrue(formerJson.equals(newJson));

		// Deserialising freshly serialised instance produces an equal instance
	}

	private JsonDeserializer<LocalDateTime> getDateDeserializer() {
		return new JsonDeserializer<LocalDateTime>() {
			@Override
			public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
					throws JsonParseException {
				var date = json.getAsString();
				return LocalDateTime.from(DateTimeFormatter.ISO_DATE_TIME.parse(date));
			}
		};
	}

	private JsonDeserializer<ITimeMeasurementTag> getTagDeserializer() {
		return new JsonDeserializer<ITimeMeasurementTag>() {
			private final List<ITimeMeasurementTag> possibleTags;

			{
				possibleTags = new ArrayList<ITimeMeasurementTag>();
				for (var e : GeneralTimeMeasurementTag.values()) {
					possibleTags.add(e);
				}
				for (var e : RepoTimeMeasurementTag.values()) {
					possibleTags.add(e);
				}
			}

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

//				var keyMap = gson.fromJson(entryObj.getAsJsonObject("key").get("keyMap"), HashMap.class);
//				var key = new ParserTestTimeMeasurementKey().fromStringKeyMap(keyMap);

				var key = gson.fromJson(entryObj.get("key"), ParserTestTimeMeasurementKey.class);

				var tag = gson.fromJson(entryObj.get("tag"), ITimeMeasurementTag.class);
				var entry = new TimeMeasurementEntry(key, tag);
				entry.setTimeElapsed(time);

				return entry;
			}
		};
	}
}

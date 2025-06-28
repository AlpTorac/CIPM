package cipm.consistency.fitests.similarity.jamopp.parser;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.function.Function;

import org.apache.commons.lang.time.StopWatch;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

public class ParserTestTimeMeasurer {
	private final static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");
	private static ParserTestTimeMeasurer instance;

	@Expose
	private String startTime;

	@Expose
	private String endTime;

	@Expose
	private final String timeMeasurer = StopWatch.class.getName();
	@Expose
	private final String timeUnit = "Milliseconds (ms)";

	@Expose
	private Long overallRunTime;

	@Expose
	private final Map<ITimeMeasurementTag, Long> measurementTagSummary = new HashMap<ITimeMeasurementTag, Long>();

	@Expose
	private final Map<String, Long> measurementKeySummary = new HashMap<String, Long>();

	@Expose
	private final Collection<TimeMeasurementEntry> measurements = new ArrayList<TimeMeasurementEntry>();

	/**
	 * A stack that contains all StopWatch instances that are used during
	 * performance measurement. The reason to use a stack here is, there are cases,
	 * where methods make calls to other methods and their run times overlap. By
	 * suspending the outer method's StopWatch and pushing a new StopWatch onto the
	 * stack, the inner methods' run times can be measured accurately. Then the new
	 * StopWatch can be popped and stopped to get the run time of the inner method.
	 * Finally, the outer method's StopWatch can be resumed to resume the time
	 * measurement.
	 */
	private final Stack<StopWatch> watches = new Stack<StopWatch>();

	private ParserTestTimeMeasurer() {
	}

	public static ParserTestTimeMeasurer getInstance() {
		if (instance == null)
			instance = new ParserTestTimeMeasurer();
		return instance;
	}

	public void startTimeMeasurement(String key, ITimeMeasurementTag tag) {
		if (this.startTime == null) {
			this.startTime = timeFormatter.format(LocalDateTime.now());
		}

		/*
		 * Suspends the potential outer method's Stopwatch, so that time measurements do
		 * not overlap
		 */
		if (!watches.isEmpty()) {
			var outerMethodWatch = watches.peek();
			outerMethodWatch.suspend();
		}

		var currentMethodWatch = new StopWatch();

		this.measurements.add(new TimeMeasurementEntry(currentMethodWatch, key, tag));

		watches.push(currentMethodWatch);
		currentMethodWatch.start();
	}

	public void stopTimeMeasurement() {
		var currentMethodWatch = watches.pop();
		currentMethodWatch.stop();

		/*
		 * Resumes the potential outer method's Stopwatch, which was previously
		 * suspended
		 */
		if (!watches.isEmpty()) {
			watches.peek().resume();
		}
	}

	public void endTimeMeasurement() {
		if (this.endTime == null) {
			this.endTime = timeFormatter.format(LocalDateTime.now());
		}

		this.measurements.forEach((m) -> m.computeTime());
	}

	public void save(Path measurementsSavePath) {
		this.endTimeMeasurement();
		this.summariseTimeMeasurements();

		var fileExtension = ".json";
		var filename = String.format("%s___%s%s", this.startTime, this.endTime, fileExtension);
		var filePath = measurementsSavePath.resolve(filename);

		// Ensure that all necessary parent directories exist prior to saving
		measurementsSavePath.toFile().mkdirs();

		var gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
		try (BufferedWriter writer = Files.newBufferedWriter(filePath); var gsonWriter = gson.newJsonWriter(writer)) {
			gson.toJson(this, this.getClass(), gsonWriter);
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(
					String.format("Could not save the expected similarity results at %s", filePath), e);
		}

		/*
		 * Reset the summary maps after having saved, as the values of their entries
		 * will contain duplicated measurements otherwise.
		 */
		this.clearSummaryMaps();
	}

	private void clearSummaryMaps() {
		measurementTagSummary.clear();
		measurementKeySummary.clear();
	}

	private <K> void summariseTimeMeasurements(Map<K, Long> summaryMap, Function<TimeMeasurementEntry, K> keyAccess) {
		for (var measurementEntry : this.measurements) {
			var key = keyAccess.apply(measurementEntry);
			var measurement = measurementEntry.getMilis();

			if (summaryMap.containsKey(key)) {
				var summaryEntry = summaryMap.get(key);
				summaryMap.replace(key, summaryEntry + measurement);
			} else {
				summaryMap.put(key, measurement);
			}
		}
	}

	private void summariseTimeMeasurements() {
		this.summariseTimeMeasurements(this.measurementTagSummary, TimeMeasurementEntry::getTag);
		this.summariseTimeMeasurements(this.measurementKeySummary, TimeMeasurementEntry::getKey);

		this.overallRunTime = this.measurementTagSummary.values().stream().reduce(Long.valueOf(0), (t1, t2) -> t1 + t2);
	}

	private class TimeMeasurementEntry {
		private StopWatch watch;

		@Expose
		private Long milis;

		@Expose
		private final String key;
		@Expose
		private final ITimeMeasurementTag tag;

		private TimeMeasurementEntry(StopWatch watch, String key, ITimeMeasurementTag tag) {
			this.watch = watch;
			this.tag = tag;
			this.key = key;
		}

		public Long getMilis() {
			return milis;
		}

		public String getKey() {
			return key;
		}

		public ITimeMeasurementTag getTag() {
			return tag;
		}

		public StopWatch getWatch() {
			return watch;
		}

		public void computeTime() {
			if (this.watch != null) {
				this.milis = Long.valueOf(this.watch.getTime());
				this.watch = null;
			}
		}
	}
}

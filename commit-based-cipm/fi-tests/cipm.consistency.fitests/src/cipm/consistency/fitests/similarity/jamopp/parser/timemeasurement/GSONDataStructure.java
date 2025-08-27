package cipm.consistency.fitests.similarity.jamopp.parser.timemeasurement;

import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.google.gson.annotations.Expose;

public class GSONDataStructure implements ITimeMeasurementDataStructure {
	/**
	 * The time when time measurement has begun
	 */
	private TemporalAccessor startTime;
	/**
	 * The time when time measurement has ended
	 */
	private TemporalAccessor endTime;

	/**
	 * The sum of all taken time measurements.
	 */
	@Expose
	private Long overallRunTime;

	/**
	 * The time unit in time measurements. Only declared in order to include it to
	 * the time measurement file.
	 */
	@Expose
	private String timeUnit;

	/**
	 * The name of the tool that is used for taking time measurements. Only declared
	 * in order to include it to the time measurement file.
	 */
	@Expose
	private String timeMeasurer;

	/**
	 * Contains the sum of time measurements for individual tags in
	 * {@link #overallRunTime}. Only declared in order to include it to the time
	 * measurement file. Should be reset after saving all time measurements, so that
	 * the values here are not duplicated.
	 */
	@Expose
	private final Map<ITimeMeasurementTag, Long> measurementTagSummary = new HashMap<ITimeMeasurementTag, Long>();

	/**
	 * Contains the proportion of time measurements with certain tags in
	 * {@link #overallRunTime} (in percentage). Only declared in order to include it
	 * to the time measurement file. Should be reset after saving all time
	 * measurements, so that the values here are not duplicated.
	 */
	@Expose
	private final Map<ITimeMeasurementTag, String> measurementTagPercentageSummary = new HashMap<ITimeMeasurementTag, String>();

	/**
	 * Contains all time measurements taken.
	 */
	@Expose
	private final Collection<ITimeMeasurementDataStructureEntry> measurements = new ArrayList<ITimeMeasurementDataStructureEntry>();

	@Override
	public void timeMeasuringStarted(TemporalAccessor startTime) {
		this.startTime = startTime;
	}

	@Override
	public void timeMeasuringFinished(TemporalAccessor endTime) {
		this.endTime = endTime;
		this.summariseTimeMeasurements();
	}

	@Override
	public void reset() {
		this.startTime = null;

		this.endTime = null;

		this.overallRunTime = null;
		this.timeMeasurer = null;
		this.timeUnit = null;
	}

	/**
	 * Reset the summary maps after having saved, as the values of their entries
	 * will contain duplicated measurements otherwise.
	 */
	public void dataStructureSaved() {
		this.clearSummaryMaps();
	}

	/**
	 * Cleans all values derived from the taken time measurements, so that no time
	 * measurement is duplicated while computing them.
	 */
	private void clearSummaryMaps() {
		measurementTagSummary.clear();
		measurementTagPercentageSummary.clear();
	}

	/**
	 * Summarises all taken time measurements by grouping them based on the given
	 * key, and then by summing all entries in each group.
	 * 
	 * @param <K>        The type of the key, based on which taken time entries are
	 *                   to be grouped
	 * @param summaryMap A map, which will contain the summary of all taken time
	 *                   measurements based on the foreseen key
	 * @param keyAccess  A function for deriving the key, which will be used to
	 *                   split taken time measurements, from their entries.
	 */
	private <K> void summariseTimeMeasurements(Map<K, Long> summaryMap,
			Function<ITimeMeasurementDataStructureEntry, K> keyAccess) {
		for (var measurementEntry : this.measurements) {
			var key = keyAccess.apply(measurementEntry);
			var measurement = measurementEntry.getTimeUnitCount();

			if (summaryMap.containsKey(key)) {
				var summaryEntry = summaryMap.get(key);
				summaryMap.replace(key, summaryEntry + measurement);
			} else {
				summaryMap.put(key, measurement);
			}
		}
	}

	/**
	 * Summarises all taken time measurements and puts the derived values into the
	 * foreseen Map-based attributes of this class.
	 */
	private void summariseTimeMeasurements() {
		this.summariseTimeMeasurements(this.measurementTagSummary, ITimeMeasurementDataStructureEntry::getTag);

		this.overallRunTime = this.measurementTagSummary.values().stream().reduce(Long.valueOf(0), (t1, t2) -> t1 + t2);

		this.measurementTagSummary.entrySet().forEach((e) -> this.measurementTagPercentageSummary.put(e.getKey(),
				String.format("%.2f", (e.getValue().doubleValue() / overallRunTime.doubleValue()) * 100)));
	}

	@Override
	public void addTimeMeasurement(ITimeMeasurementDataStructureEntry entry) {
		this.measurements.add(entry);
	}

	@Override
	public TemporalAccessor getStartTime() {
		return this.startTime;
	}

	@Override
	public TemporalAccessor getEndTime() {
		return this.endTime;
	}
}

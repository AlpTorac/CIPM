package cipm.consistency.fitests.similarity.jamopp.parser.timemeasurement;

import java.time.temporal.TemporalAccessor;

public interface ITimeMeasurementDataStructure {
	public TemporalAccessor getStartTime();

	public TemporalAccessor getEndTime();

	/**
	 * Signals the data structure that time measuring started.
	 */
	public void timeMeasuringStarted(TemporalAccessor startTime);

	/**
	 * Signals the data structure that time measuring is over.
	 */
	public void timeMeasuringFinished(TemporalAccessor endTime);

	/**
	 * Resets all current information within this instance.
	 */
	public void reset();

	/**
	 * Signals the data structure that it has been saved.<br>
	 * <br>
	 * If a time measuring is to start anew and the current time measurements should
	 * be reset, a call to {@link #reset()} is necessary.
	 */
	public void dataStructureSaved();

	public void addTimeMeasurement(ITimeMeasurementDataStructureEntry entry);
}

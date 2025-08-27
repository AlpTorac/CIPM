package cipm.consistency.fitests.similarity.jamopp.parser.timemeasurement;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * TODO Commentary
 * 
 * @author Alp Torac Genc
 */
public interface ITimeMeasurementDataStructure {
	public String getTimeMeasurerDescription();

	public void setTimeMeasurerDescription(String description);

	public TimeUnit getTimeUnit();

	public void setTimeUnit(TimeUnit unit);

	/**
	 * @return The time when time measurement has begun
	 */
	public LocalDateTime getStartTime();

	/**
	 * @return The time when time measurement has ended
	 */
	public LocalDateTime getEndTime();

	/**
	 * Signals the data structure that time measuring started. Calling this method
	 * multiple times before calling {@link #timeMeasuringFinished(LocalDateTime)}
	 * or {@link #reset()} should have no effect.
	 */
	public void timeMeasuringStarted(LocalDateTime startTime);

	/**
	 * Signals the data structure that time measuring is over. This method should be
	 * called after {@link #timeMeasuringStarted(LocalDateTime)} but before
	 * {@link #reset()} for it to have any effect. In any other case, this method
	 * should do nothing.
	 */
	public void timeMeasuringFinished(LocalDateTime endTime);

	/**
	 * Resets all current information within this instance.
	 */
	public void reset();

	/**
	 * Signals the data structure that it has been saved.<br>
	 * <br>
	 * If time measuring is to start anew and the current time measurements should
	 * be reset, an additional call to {@link #reset()} is necessary.
	 */
	public void dataStructureSaved();

	/**
	 * Adds the given time measurement entry (as
	 * {@link ITimeMeasurementDataStructureEntry} instance) to this data structure.
	 */
	public void addTimeMeasurement(ITimeMeasurementDataStructureEntry entry);

	/**
	 * The underlying collection, which stores all entries, should not be returned
	 * as is, since that may result in unforeseen modifications from outside. The
	 * returned entries are allowed to the original ones, so that no unnecessary
	 * copies of the entries are made.
	 * 
	 * @return All time measurement entries added to this instance.
	 */
	public Collection<ITimeMeasurementDataStructureEntry> getTimeMeasurementEntries();
}

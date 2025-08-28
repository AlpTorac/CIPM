package cipm.consistency.fitests.similarity.jamopp.parser.timemeasurement;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * TODO Commentary
 * 
 * @author Alp Torac Genc
 */
public interface ITimeMeasuringStrategy {
	/**
	 * Starts measuring the time for a certain purpose given via the parameters. If
	 * another time measurement is ongoing (i.e. if this method is called multiple
	 * times without {@link #stopTimeMeasurement()} calls in between), the previous
	 * time measurement is paused until the new time measurement is stopped via
	 * {@link #stopTimeMeasurement()}. <br>
	 * <br>
	 * This method is to be seen as the opening bracket for the closing bracket
	 * {@link #stopTimeMeasurement()} such that the time elapsed while executing the
	 * lines between this method call and that method call is the time measurement.
	 * Not using them similar to brackets will result in problems. <br>
	 * <br>
	 * 
	 * @param key The key of the taken time measurement, which describes what the
	 *            time measurement is taken from
	 * @param tag The tag of the time measurement, which is used to group time
	 *            measurements
	 */
	public void startTimeMeasurement(ParserTestTimeMeasurementKey key, ITimeMeasurementTag tag);

	/**
	 * Stops the most recently started time measurement (via
	 * {@link #startTimeMeasurement(String, ITimeMeasurementTag)}). If the most
	 * recent time measurement paused a previous time measurement, it is resumed.
	 * <br>
	 * <br>
	 * This method is to be seen as the closing bracket for the opening bracket
	 * {@link #startTimeMeasurement(String, ITimeMeasurementTag)}, such that the
	 * time elapsed while executing the lines between that method call and this
	 * method call is the time measurement. Not using them similar to brackets will
	 * result in inaccurate measurements. <br>
	 * <br>
	 * If taking time measurements should end altogether, use
	 * {@link #finishTimeMeasuring()} instead.
	 * 
	 * @return The time measurement entry that is generated for the stopped time
	 *         measurement.
	 */
	public TimeMeasurementEntry stopTimeMeasurement();

	/**
	 * Signals to the concrete implementor that time measuring has started. Calling
	 * this multiple times before calling {@link #timeMeasuringFinished()} should
	 * have no effect past the first call.
	 */
	public void timeMeasuringStarted();

	/**
	 * Signals to the concrete implementor that time measuring has ended. Calling
	 * this before calling {@link #timeMeasuringStarted()} should have no effect.
	 */
	public void timeMeasuringFinished();

	/**
	 * @return Whether time measurements are currently being taken.
	 */
	public boolean hasTimeMeasurementStarted();

	/**
	 * @return Whether time measurement taking is currently over.
	 */
	public boolean hasTimeMeasurementFinished();

	/**
	 * Can be used to acquire information on how time is measured. Check the
	 * concrete implementor for more information on the returned value.
	 * 
	 * @return The description of the underlying tool that is used for taking time
	 *         measurements.
	 */
	public String getTimeMeasurerDescription();

	/**
	 * @return The time unit used while taking measurements.
	 */
	public TimeUnit getTimeUnit();

	/**
	 * @return The time when time measurement has begun
	 */
	public LocalDateTime getStartTime();

	/**
	 * @return The time when time measurement has ended
	 */
	public LocalDateTime getEndTime();
}

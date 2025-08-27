package cipm.consistency.fitests.similarity.jamopp.parser.timemeasurement;

/**
 * TODO Commentary
 * 
 * @author Alp Torac Genc
 */
public interface ITimeMeasurementDataStructureEntry {
	/**
	 * The concrete time unit should be specified within the
	 * {@link ITimeMeasurementDataStructure} that stores this instance
	 * 
	 * @return The amount of time units associated with the time measurement
	 */
	public long getTimeElapsed();

	/**
	 * @param timeElapsed {@link #getTimeElapsed()}
	 */
	public void setTimeElapsed(long timeElapsed);

	/**
	 * @return Keys associated with the time measurement, which describe what was
	 *         measured
	 */
	public ParserTestTimeMeasurementKey getKey();

	/**
	 * @return The tag of the time measurement, which can be used for a high-level
	 *         grouping of time measurements based on what they are taken from
	 */
	public ITimeMeasurementTag getTag();
}

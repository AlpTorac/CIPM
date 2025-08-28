package cipm.consistency.fitests.similarity.jamopp.parser.timemeasurement;

import java.nio.file.Path;

/**
 * TODO Revise commentary
 * 
 * A class for taking time measurements using
 * {@link org.apache.commons.lang.time.StopWatch}, and saving them. <br>
 * <br>
 * The time measurements taken here are contain no duplications; i.e. if another
 * time measurement is taken while a previous time measurement continues (for
 * instance, while a method's run time is measured, a new time measurement
 * starts for one of its inner method calls), they will be separate. <br>
 * <br>
 * Also contains the means to save the time measurements to JSON files using the
 * GSON library. While saving the time measurements, the (non-static) attributes
 * of this class annotated with {@link com.google.gson.annotations.Expose} will
 * be translated to JSON objects and then written to a JSON file. This way, only
 * the desired attributes of this class are saved, as opposed to all of them.
 * 
 * @author Alp Torac Genc
 */
public class ParserTestTimeMeasurer {
	private ITimeMeasurementDataStructure dataStructure;
	private ITimeMeasurementPersistingStrategy persistingStrat;
	private ITimeMeasuringStrategy measuringStrat;

	/**
	 * The only instance of this class.
	 */
	private static ParserTestTimeMeasurer instance;

	private ParserTestTimeMeasurer() {
	}

	/**
	 * @return The only instance of this class.
	 */
	public static ParserTestTimeMeasurer getInstance() {
		if (instance == null)
			instance = new ParserTestTimeMeasurer();
		return instance;
	}

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
	 * A call to {@link #startTimeMeasuring()} is necessary before using this
	 * method. If time measuring should start anew, additionally {@link #reset()}
	 * should be called.
	 * 
	 * @param key The key of the taken time measurement, which describes what the
	 *            time measurement is taken from
	 * @param tag The tag of the time measurement, which is used to group time
	 *            measurements
	 */
	public void startTimeMeasurement(ParserTestTimeMeasurementKey key, ITimeMeasurementTag tag) {
		this.measuringStrat.startTimeMeasurement(key, tag);
	}

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
	 */
	public void stopTimeMeasurement() {
		this.dataStructure.addTimeMeasurement(this.measuringStrat.stopTimeMeasurement());
	}

	/**
	 * Signals that taking time measurements should start.
	 */
	public void startTimeMeasuring() {
		this.measuringStrat.timeMeasuringStarted();

		this.dataStructure.setTimeMeasurerDescription(this.measuringStrat.getTimeMeasurerDescription());
		this.dataStructure.setTimeUnit(this.measuringStrat.getTimeUnit());
		this.dataStructure.timeMeasuringStarted(this.measuringStrat.getStartTime());
	}

	/**
	 * Signals that taking time measurements is over and the taken time measurements
	 * should be processed. <br>
	 * <br>
	 * If a singular time measurement should be stopped, use
	 * {@link #stopTimeMeasurement()} instead.
	 */
	public void finishTimeMeasuring() {
		this.measuringStrat.timeMeasuringFinished();
		var time = this.measuringStrat.getEndTime();
		this.dataStructure.timeMeasuringFinished(time);
	}

	/**
	 * Ends taking time measurements, if not already done, then processes all taken
	 * time measurements. Finally, saves all taken time measurements, as well as
	 * their summaries represented by certain attributes of this instance, at the
	 * given path, in a JSON file.
	 * 
	 * @param measurementsSavePath The absolute path, at which all taken time
	 *                             measurements should be saved.
	 */
	public void save(Path measurementsSavePath) {
		this.finishTimeMeasuring();

		this.persistingStrat.save(dataStructure, measurementsSavePath);

		this.dataStructure.dataStructureSaved();
	}

	public void reset() {
		this.dataStructure.reset();
	}

	public ITimeMeasurementDataStructure getDataStructure() {
		return dataStructure;
	}

	public void setDataStructure(ITimeMeasurementDataStructure dataStructure) {
		this.dataStructure = dataStructure;
	}

	public ITimeMeasurementPersistingStrategy getPersistingStrat() {
		return persistingStrat;
	}

	public void setPersistingStrat(ITimeMeasurementPersistingStrategy persistingStrat) {
		this.persistingStrat = persistingStrat;
	}

	public ITimeMeasuringStrategy getMeasuringStrat() {
		return measuringStrat;
	}

	public void setMeasuringStrat(ITimeMeasuringStrategy measuringStrat) {
		this.measuringStrat = measuringStrat;
	}
}

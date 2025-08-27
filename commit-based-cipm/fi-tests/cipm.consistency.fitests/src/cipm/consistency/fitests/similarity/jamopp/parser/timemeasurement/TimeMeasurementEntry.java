package cipm.consistency.fitests.similarity.jamopp.parser.timemeasurement;

/**
 * A class that encapsulates singular time measurements.
 * 
 * @author Alp Torac Genc
 */
public class TimeMeasurementEntry implements ITimeMeasurementDataStructureEntry {
	private long timeUnitCount;
	private final ParserTestTimeMeasurementKey key;
	private final ITimeMeasurementTag tag;

	/**
	 * @param key {@link #getKey()}
	 * @param tag {@link #getTag()}
	 */
	public TimeMeasurementEntry(ParserTestTimeMeasurementKey key, ITimeMeasurementTag tag) {
		this.tag = tag;
		this.key = key;
	}

	public long getTimeUnitCount() {
		return timeUnitCount;
	}

	public void setTimeUnitCount(long timeUnitCount) {
		this.timeUnitCount = timeUnitCount;
	}

	public ParserTestTimeMeasurementKey getKey() {
		return key;
	}

	public ITimeMeasurementTag getTag() {
		return tag;
	}
}
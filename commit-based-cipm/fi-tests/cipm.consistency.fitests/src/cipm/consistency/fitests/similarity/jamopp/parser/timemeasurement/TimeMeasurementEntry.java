package cipm.consistency.fitests.similarity.jamopp.parser.timemeasurement;

/**
 * A class that encapsulates singular time measurements.
 * 
 * @author Alp Torac Genc
 */
public class TimeMeasurementEntry implements ITimeMeasurementDataStructureEntry {
	private long timeElapsed;
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

	public long getTimeElapsed() {
		return timeElapsed;
	}

	public void setTimeElapsed(long timeElapsed) {
		this.timeElapsed = timeElapsed;
	}

	public ParserTestTimeMeasurementKey getKey() {
		return key;
	}

	public ITimeMeasurementTag getTag() {
		return tag;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof TimeMeasurementEntry)) {
			return false;
		}
		var castedO = (TimeMeasurementEntry) obj;

		return this.getTag().equals(castedO.getTag()) && this.getKey().equals(castedO.getKey())
				&& this.getTimeElapsed() == castedO.getTimeElapsed();
	}
}
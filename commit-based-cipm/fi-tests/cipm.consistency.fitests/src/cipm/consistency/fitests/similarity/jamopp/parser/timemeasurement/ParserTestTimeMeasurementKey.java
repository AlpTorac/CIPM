package cipm.consistency.fitests.similarity.jamopp.parser.timemeasurement;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO Revise all commentary regarding this class
 * 
 * A class that contains information about time measurements. There is no
 * mandatory information that should be given to this class. <br>
 * <br>
 * For convenience and clarity, instances should be constructed via builder
 * classes such as {@link ParserTestTimeMeasurementKeyBuilder}. Its constructor
 * is left public to allow parsing instances of this class from data files.
 * 
 * @author Alp Torac Genc
 */
public class ParserTestTimeMeasurementKey {
	private final Map<ParserTestTimeMeasurerKeyType, String> keyMap;

	public ParserTestTimeMeasurementKey(Map<ParserTestTimeMeasurerKeyType, String> keyMap) {
		this.keyMap = keyMap;
	}

	/**
	 * @return A copy of all added keys and their values. Modifying the return value
	 *         will not affect this instance.
	 */
	public Map<ParserTestTimeMeasurerKeyType, String> getKeys() {
		return new HashMap<ParserTestTimeMeasurerKeyType, String>(keyMap);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ParserTestTimeMeasurementKey)) {
			return false;
		}
		var castedO = (ParserTestTimeMeasurementKey) obj;

		return (this.keyMap == null && castedO.keyMap == null) || (this.keyMap.size() == castedO.keyMap.size()
				&& this.keyMap.entrySet().containsAll(castedO.keyMap.entrySet()));
	}
}

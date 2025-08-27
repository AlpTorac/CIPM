package cipm.consistency.fitests.similarity.jamopp.parser.timemeasurement;

import java.nio.file.Path;

/**
 * TODO Commentary
 * 
 * @author Alp Torac Genc
 */
public interface ITimeMeasurementPersistingStrategy {
	/**
	 * Saves the given dataStructure according to the concrete implementation.
	 * 
	 * @param dataStructure        The data structure to be persisted
	 * @param measurementsSavePath The absolute path, at which the given data
	 *                             structure should be persisted
	 */
	public void save(ITimeMeasurementDataStructure dataStructure, Path measurementsSavePath);
}

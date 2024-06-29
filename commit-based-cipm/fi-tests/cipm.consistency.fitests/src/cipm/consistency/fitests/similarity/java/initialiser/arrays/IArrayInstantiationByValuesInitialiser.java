package cipm.consistency.fitests.similarity.java.initialiser.arrays;

import org.emftext.language.java.arrays.ArrayInitializer;
import org.emftext.language.java.arrays.ArrayInstantiationByValues;

public interface IArrayInstantiationByValuesInitialiser extends
	IArrayInstantiationInitialiser {

	public default boolean setArrayInitializer(ArrayInstantiationByValues arrIns, ArrayInitializer init) {
		if (init != null) {
			arrIns.setArrayInitializer(init);
			return arrIns.getArrayInitializer().equals(init);
		}
		return true;
	}
}

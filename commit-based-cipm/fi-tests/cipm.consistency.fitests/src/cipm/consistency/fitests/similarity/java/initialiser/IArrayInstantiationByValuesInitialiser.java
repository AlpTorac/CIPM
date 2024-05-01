package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.arrays.ArrayInitializer;
import org.emftext.language.java.arrays.ArrayInstantiationByValues;

public interface IArrayInstantiationByValuesInitialiser extends
	IArrayInstantiationInitialiser {

	public default void setArrayInitializer(ArrayInstantiationByValues arrIns, ArrayInitializer init) {
		if (init != null) {
			arrIns.setArrayInitializer(init);
			assert arrIns.getArrayInitializer().equals(init);
		}
	}
}

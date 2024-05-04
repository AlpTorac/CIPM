package cipm.consistency.fitests.similarity.java.initialiser.testable;

import org.emftext.language.java.arrays.ArrayInitializer;
import org.emftext.language.java.arrays.ArrayInstantiationByValues;

import cipm.consistency.fitests.similarity.java.initialiser.IArrayInstantiationInitialiser;

public interface IArrayInstantiationByValuesInitialiser extends
	IArrayInstantiationInitialiser {

	public default void setArrayInitializer(ArrayInstantiationByValues arrIns, ArrayInitializer init) {
		if (init != null) {
			arrIns.setArrayInitializer(init);
			assert arrIns.getArrayInitializer().equals(init);
		}
	}
}

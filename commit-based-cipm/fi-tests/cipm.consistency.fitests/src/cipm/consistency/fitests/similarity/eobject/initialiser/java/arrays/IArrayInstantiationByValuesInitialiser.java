package cipm.consistency.fitests.similarity.eobject.initialiser.java.arrays;

import org.emftext.language.java.arrays.ArrayInitializer;
import org.emftext.language.java.arrays.ArrayInstantiationByValues;

public interface IArrayInstantiationByValuesInitialiser extends IArrayInstantiationInitialiser {

	@Override
	public ArrayInstantiationByValues instantiate();

	public default boolean setArrayInitializer(ArrayInstantiationByValues arrIns, ArrayInitializer arrInit) {
		if (arrInit != null) {
			arrIns.setArrayInitializer(arrInit);
			return arrIns.getArrayInitializer().equals(arrInit);
		}
		return true;
	}
}

package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.classifiers.Interface;
import org.emftext.language.java.instantiations.Initializable;
import org.emftext.language.java.types.TypeReference;

public interface IInterfaceInitialiser extends IConcreteClassifierInitialiser {
	public default void addDefaultExtends(Interface intfc, TypeReference tref) {
		if (tref != null) {
			intfc.getDefaultExtends().add(tref);
			assert intfc.getDefaultExtends().contains(tref);
		}
	}
	
	public default void addExtends(Interface intfc, TypeReference tref) {
		if (tref != null) {
			intfc.getExtends().add(tref);
			assert intfc.getExtends().contains(tref);
		}
	}
}

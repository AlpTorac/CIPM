package cipm.consistency.fitests.similarity.java.initialiser.classifiers;

import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.types.TypeReference;

import cipm.consistency.fitests.similarity.java.initialiser.testable.IConcreteClassifierInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IImplementorInitialiser;

public interface IClassInitialiser extends IConcreteClassifierInitialiser, IImplementorInitialiser {
	public default void setDefaultExtends(Class cls, TypeReference tref) {
		if (tref != null) {
			cls.setDefaultExtends(tref);
			assert cls.getDefaultExtends().equals(tref);
		}
	}
	
	public default void setExtends(Class cls, TypeReference tref) {
		if (tref != null) {
			cls.setExtends(tref);
			assert cls.getExtends().equals(tref);
		}
	}
}

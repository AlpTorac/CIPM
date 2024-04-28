package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.types.TypeReference;

public interface IClassInitialiser extends IConcreteClassifierInitialiser, IImplementorInitialiser {
	@Override
	public Class instantiate();
	
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

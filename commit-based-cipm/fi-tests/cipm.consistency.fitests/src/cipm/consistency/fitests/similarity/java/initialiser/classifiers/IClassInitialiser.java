package cipm.consistency.fitests.similarity.java.initialiser.classifiers;

import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.types.TypeReference;

public interface IClassInitialiser extends IConcreteClassifierInitialiser, IImplementorInitialiser {
    @Override
    public Class instantiate();
	public default boolean setDefaultExtends(Class cls, TypeReference tref) {
		if (tref != null) {
			cls.setDefaultExtends(tref);
			return cls.getDefaultExtends().equals(tref);
		}
		return true;
	}
	
	public default boolean setExtends(Class cls, TypeReference tref) {
		if (tref != null) {
			cls.setExtends(tref);
			return cls.getExtends().equals(tref);
		}
		return true;
	}
}

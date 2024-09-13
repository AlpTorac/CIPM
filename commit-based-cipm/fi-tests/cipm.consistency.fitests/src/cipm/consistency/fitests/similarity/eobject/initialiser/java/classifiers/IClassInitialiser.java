package cipm.consistency.fitests.similarity.eobject.initialiser.java.classifiers;

import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.types.TypeReference;

public interface IClassInitialiser extends IConcreteClassifierInitialiser, IImplementorInitialiser {
	@Override
	public Class instantiate();

	public default boolean setDefaultExtends(Class cls, TypeReference defExt) {
		if (defExt != null) {
			cls.setDefaultExtends(defExt);
			return cls.getDefaultExtends().equals(defExt);
		}
		return true;
	}

	public default boolean setExtends(Class cls, TypeReference ext) {
		if (ext != null) {
			cls.setExtends(ext);
			return cls.getExtends().equals(ext);
		}
		return true;
	}
}

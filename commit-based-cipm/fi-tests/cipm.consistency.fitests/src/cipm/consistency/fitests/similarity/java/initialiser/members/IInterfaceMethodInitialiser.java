package cipm.consistency.fitests.similarity.java.initialiser.members;

import org.emftext.language.java.annotations.AnnotationValue;
import org.emftext.language.java.classifiers.Interface;
import org.emftext.language.java.members.InterfaceMethod;

import cipm.consistency.fitests.similarity.java.initialiser.IMethodInitialiser;

public interface IInterfaceMethodInitialiser extends IMethodInitialiser {
	public default void setDefaultValue(InterfaceMethod im, AnnotationValue av) {
		if (av != null) {
			im.setDefaultValue(av);
			assert im.getDefaultValue().equals(av);
		}
	}
}

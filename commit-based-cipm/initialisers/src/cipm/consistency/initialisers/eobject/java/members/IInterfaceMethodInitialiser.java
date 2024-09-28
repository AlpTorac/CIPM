package cipm.consistency.initialisers.eobject.java.members;

import org.emftext.language.java.annotations.AnnotationValue;
import org.emftext.language.java.members.InterfaceMethod;

public interface IInterfaceMethodInitialiser extends IMethodInitialiser {
	@Override
	public InterfaceMethod instantiate();

	public default boolean setDefaultValue(InterfaceMethod im, AnnotationValue defVal) {
		if (defVal != null) {
			im.setDefaultValue(defVal);
			return im.getDefaultValue().equals(defVal);
		}
		return true;
	}
}

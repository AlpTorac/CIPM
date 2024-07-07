package cipm.consistency.fitests.similarity.java.initialiser.members;

import org.emftext.language.java.annotations.AnnotationValue;
import org.emftext.language.java.members.InterfaceMethod;

import cipm.consistency.fitests.similarity.java.initialiser.ModificationMethod;

public interface IInterfaceMethodInitialiser extends IMethodInitialiser {
    @Override
    public InterfaceMethod instantiate();
    @ModificationMethod
	public default boolean setDefaultValue(InterfaceMethod im, AnnotationValue av) {
		if (av != null) {
			im.setDefaultValue(av);
			return im.getDefaultValue().equals(av);
		}
		return true;
	}
}

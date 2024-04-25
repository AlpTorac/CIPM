package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.annotations.AnnotationValue;
import org.emftext.language.java.members.InterfaceMethod;

public interface IInterfaceMethodInitialiser extends IMethodInitialiser {
	@Override
	public InterfaceMethod instantiate();
	
	@Override
	public default InterfaceMethod minimalInstantiation() {
		return (InterfaceMethod) IMethodInitialiser.super.minimalInstantiation();
	}
	
	public default void setDefaultValue(InterfaceMethod im, AnnotationValue av) {
		if (av != null) {
			im.setDefaultValue(av);
			assert im.getDefaultValue().equals(av);
		}
	}
}

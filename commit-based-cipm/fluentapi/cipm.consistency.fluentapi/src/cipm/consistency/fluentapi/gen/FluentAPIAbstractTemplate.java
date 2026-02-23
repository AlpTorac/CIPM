package cipm.consistency.fluentapi.gen;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EClass;

public abstract class FluentAPIAbstractTemplate {
	protected static String getElementToInitialiseName(EClass elemToInitECls) {
		return StringUtils.capitalize(elemToInitECls.getName());
	}

	protected static String getSerialisedClassObject(String fullyQualifiedClassName) {
		return fullyQualifiedClassName + ".class";
	}

	protected static String capitaliseFirstLetter(String str) {
		return StringUtils.capitalize(str);
	}
}

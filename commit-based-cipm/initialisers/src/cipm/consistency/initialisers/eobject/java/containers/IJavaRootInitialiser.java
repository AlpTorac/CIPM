package cipm.consistency.initialisers.eobject.java.containers;

import org.emftext.language.java.containers.JavaRoot;
import org.emftext.language.java.containers.Origin;

import cipm.consistency.initialisers.eobject.java.annotations.IAnnotableInitialiser;
import cipm.consistency.initialisers.eobject.java.commons.INamedElementInitialiser;
import cipm.consistency.initialisers.eobject.java.commons.INamespaceAwareElementInitialiser;
import cipm.consistency.initialisers.eobject.java.imports.IImportingElementInitialiser;

public interface IJavaRootInitialiser extends INamedElementInitialiser, INamespaceAwareElementInitialiser,
		IAnnotableInitialiser, IImportingElementInitialiser {
	@Override
	public JavaRoot instantiate();

	public default boolean setOrigin(JavaRoot jr, Origin origin) {
		if (origin != null) {
			jr.setOrigin(origin);
			return jr.getOrigin().equals(origin);
		}
		return true;
	}
}

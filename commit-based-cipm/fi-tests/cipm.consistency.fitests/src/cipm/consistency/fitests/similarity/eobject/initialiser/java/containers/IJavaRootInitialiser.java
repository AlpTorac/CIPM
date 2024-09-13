package cipm.consistency.fitests.similarity.eobject.initialiser.java.containers;

import org.emftext.language.java.containers.JavaRoot;
import org.emftext.language.java.containers.Origin;

import cipm.consistency.fitests.similarity.eobject.initialiser.java.annotations.IAnnotableInitialiser;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.commons.INamedElementInitialiser;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.commons.INamespaceAwareElementInitialiser;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.imports.IImportingElementInitialiser;

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

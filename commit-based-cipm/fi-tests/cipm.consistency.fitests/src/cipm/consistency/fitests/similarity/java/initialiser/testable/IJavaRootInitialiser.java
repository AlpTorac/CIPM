package cipm.consistency.fitests.similarity.java.initialiser.testable;

import org.emftext.language.java.containers.JavaRoot;
import org.emftext.language.java.containers.Origin;

public interface IJavaRootInitialiser extends INamedElementInitialiser, INamespaceAwareElementInitialiser,
IAnnotableInitialiser, IImportingElementInitialiser {
	public default void initialiseOrigin(JavaRoot jr, Origin origin) {
		if (origin != null) {
			jr.setOrigin(origin);
			assert jr.getOrigin().equals(origin);
		}
	}
}

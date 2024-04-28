package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.containers.JavaRoot;
import org.emftext.language.java.containers.Origin;
import org.emftext.language.java.members.InterfaceMethod;

public interface IJavaRootInitialiser extends INamedElementInitialiser, INamespaceAwareElementInitialiser,
IAnnotableInitialiser, IImportingElementInitialiser {
	@Override
	public JavaRoot instantiate();
	
	public default void addClassifierInSamePackage(JavaRoot jr, ConcreteClassifier cc) {
		if (cc != null) {
			jr.getClassifiersInSamePackage().add(cc);
			assert jr.getClassifiersInSamePackage().contains(cc);
		}
	}
	
	public default void initialiseOrigin(JavaRoot jr, Origin origin) {
		if (origin != null) {
			jr.setOrigin(origin);
			assert jr.getOrigin().equals(origin);
		}
	}
}

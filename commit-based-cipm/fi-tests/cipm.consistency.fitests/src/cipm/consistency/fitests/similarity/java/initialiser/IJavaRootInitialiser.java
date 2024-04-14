package cipm.consistency.fitests.similarity.java.initialiser;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.containers.JavaRoot;
import org.emftext.language.java.containers.Origin;
import org.emftext.language.java.imports.ImportingElement;

public interface IJavaRootInitialiser extends INamedElementInitialiser, INamespaceAwareElementInitialiser,
IAnnotableInitialiser, IImportingElementInitialiser {
	@Override
	public JavaRoot instantiate();
	
	@Override
	public default JavaRoot clone(EObject obj) {
		return (JavaRoot) IAnnotableInitialiser.super.clone(obj);
	}
	
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

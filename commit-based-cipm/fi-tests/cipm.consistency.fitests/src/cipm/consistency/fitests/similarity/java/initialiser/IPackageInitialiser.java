package cipm.consistency.fitests.similarity.java.initialiser;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.commons.NamespaceAwareElement;
import org.emftext.language.java.containers.Module;
import org.emftext.language.java.containers.Package;

public interface IPackageInitialiser extends IJavaRootInitialiser {
	@Override
	public Package instantiate();
	
	@Override
	public default Package clone(EObject obj) {
		return (Package) IJavaRootInitialiser.super.clone(obj);
	}
	
	public default void initialiseModuleField(Package pac, Module mod) {
		if (mod != null) {
			pac.setModule(mod);
			assert pac.getModule().equals(mod);
		}
	}
	
	public default void addClassifier(Package pac, ConcreteClassifier cc) {
		if (cc != null) {
			pac.getClassifiers().add(cc);
			assert pac.getClassifiers().contains(cc);
		}
	}
}
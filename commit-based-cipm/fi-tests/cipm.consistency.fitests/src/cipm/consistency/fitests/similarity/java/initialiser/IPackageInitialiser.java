package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.containers.Module;
import org.emftext.language.java.containers.Package;

public interface IPackageInitialiser extends IJavaRootInitialiser {
	@Override
	public Package getCurrentObject();
	
	public default void initialiseModuleField(Module mod) {
		var cObj = this.getCurrentObject();
		
		if (mod != null) {
			cObj.setModule(mod);
			assert cObj.getModule().equals(mod);
		}
	}
	
	public default void addClassifier(ConcreteClassifier cc) {
		var cObj = this.getCurrentObject();
		
		if (cc != null) {
			cObj.getClassifiers().add(cc);
			assert cObj.getClassifiers().contains(cc);
		}
	}
	
	@Override
	public default Package build() {
		return (Package) IJavaRootInitialiser.super.build();
	}
}
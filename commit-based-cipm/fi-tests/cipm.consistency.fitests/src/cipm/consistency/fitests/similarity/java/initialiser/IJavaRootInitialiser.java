package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.containers.JavaRoot;
import org.emftext.language.java.containers.Origin;

public interface IJavaRootInitialiser extends INameInitialiser, INamespaceAwareElementInitialiser,
IAnnotableInitialiser, IImportingElementInitialiser {
	@Override
	public JavaRoot getCurrentObject();
	
	public default void addClassifierInSamePackage(ConcreteClassifier cc) {
		var cObj = this.getCurrentObject();
		
		if (cc != null) {
			cObj.getClassifiersInSamePackage().add(cc);
			assert cObj.getClassifiersInSamePackage().contains(cc);
		}
	}
	
	public default void initialiseOrigin(Origin origin) {
		var cObj = this.getCurrentObject();
		
		if (origin != null) {
			cObj.setOrigin(origin);
			assert cObj.getOrigin().equals(origin);
		}
	}
	
	@Override
	public default JavaRoot build() {
		return (JavaRoot) IAnnotableInitialiser.super.build();
	}
}

package cipm.consistency.fitests.similarity.java.initialiser.instantiations;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.classifiers.AnonymousClass;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.instantiations.NewConstructorCall;
import org.emftext.language.java.types.TypeReference;

import cipm.consistency.fitests.similarity.java.initialiser.classifiers.ClassInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.types.ClassifierReferenceInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.types.ITypedElementInitialiser;

public interface INewConstructorCallInitialiser extends
	IInstantiationInitialiser,
	ITypedElementInitialiser {

	public default void setAnonymousClass(NewConstructorCall ncc, AnonymousClass ac) {
		if (ac != null) {
			ncc.setAnonymousClass(ac);
			assert ncc.getAnonymousClass().equals(ac);
		}
	}
	
	// TODO: Move to a helper interface
	@Override
	public default void minimalInitialisation(EObject obj) {
		var castedO = (NewConstructorCall) obj;
		
		var trefInit = new ClassifierReferenceInitialiser();
		var clsInit = new ClassInitialiser();
		
		ConcreteClassifier cls = clsInit.instantiate();
		clsInit.minimalInitialisation(cls);
		
		TypeReference tref = trefInit.instantiate();
		trefInit.minimalInitialisation(tref);
		trefInit.setTarget(tref, cls);
		
		this.setTypeReference(castedO, tref);
	}
}

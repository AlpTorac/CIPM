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

	public default boolean setAnonymousClass(NewConstructorCall ncc, AnonymousClass ac) {
		if (ac != null) {
			ncc.setAnonymousClass(ac);
			return ncc.getAnonymousClass().equals(ac);
		}
		return true;
	}
	
	@Override
	public default boolean minimalInitialisation(EObject obj) {
		// FIXME: See if this can go to a helper interface
		var castedO = (NewConstructorCall) obj;
		boolean result = true;
		
		if (castedO.getTypeReference() == null) {
			var trefInit = new ClassifierReferenceInitialiser();
			var clsInit = new ClassInitialiser();
			
			ConcreteClassifier cls = clsInit.instantiate();
			result = result && clsInit.minimalInitialisation(cls);
			
			TypeReference tref = trefInit.instantiate();
			result = result && trefInit.minimalInitialisation(tref)
					&& trefInit.setTarget(tref, cls)
					&& this.setTypeReference(castedO, tref)
					&& castedO.getTypeReference().equals(tref);
		}
		return result && castedO.getTypeReference() != null;
	}
}

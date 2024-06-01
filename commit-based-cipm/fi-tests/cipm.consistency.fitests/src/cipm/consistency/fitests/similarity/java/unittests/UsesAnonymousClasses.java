package cipm.consistency.fitests.similarity.java.unittests;

import org.emftext.language.java.classifiers.AnonymousClass;

import cipm.consistency.fitests.similarity.java.initialiser.classifiers.AnonymousClassInitialiser;

public interface UsesAnonymousClasses extends UsesMethods {
	public default AnonymousClass createMinimalAnonymousClass() {
		var acInit = new AnonymousClassInitialiser();
		var ac = acInit.instantiate();
		acInit.minimalInitialisation(ac);
		return ac;
	}
	
	public default AnonymousClass createMinimalAnonymousClassWithMethod(String methodName) {
		var acInit = new AnonymousClassInitialiser();
		var ac = this.createMinimalAnonymousClass();
		acInit.addMember(ac, this.createMinimalClsMethod(methodName));
		return ac;
	}
}

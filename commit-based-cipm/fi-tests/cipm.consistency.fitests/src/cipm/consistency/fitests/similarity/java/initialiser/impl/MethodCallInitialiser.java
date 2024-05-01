package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.references.MethodCall;
import org.emftext.language.java.references.ReferencesFactory;

import cipm.consistency.fitests.similarity.java.initialiser.IMethodCallInitialiser;

public class MethodCallInitialiser implements IMethodCallInitialiser {
	@Override
	public IMethodCallInitialiser newInitialiser() {
		return new MethodCallInitialiser();
	}

	@Override
	public MethodCall instantiate() {
		return ReferencesFactory.eINSTANCE.createMethodCall();
	}
}
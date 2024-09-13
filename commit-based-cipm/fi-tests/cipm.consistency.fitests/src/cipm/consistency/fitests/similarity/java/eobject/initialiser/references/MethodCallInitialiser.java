package cipm.consistency.fitests.similarity.java.eobject.initialiser.references;

import org.emftext.language.java.references.MethodCall;
import org.emftext.language.java.references.ReferencesFactory;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class MethodCallInitialiser extends AbstractInitialiserBase implements IMethodCallInitialiser {
	@Override
	public IMethodCallInitialiser newInitialiser() {
		return new MethodCallInitialiser();
	}

	@Override
	public MethodCall instantiate() {
		return ReferencesFactory.eINSTANCE.createMethodCall();
	}
}
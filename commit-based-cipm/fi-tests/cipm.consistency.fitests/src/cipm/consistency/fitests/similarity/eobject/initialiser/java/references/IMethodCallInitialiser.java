package cipm.consistency.fitests.similarity.eobject.initialiser.java.references;

import org.emftext.language.java.references.MethodCall;

import cipm.consistency.fitests.similarity.eobject.initialiser.java.generics.ICallTypeArgumentableInitialiser;

public interface IMethodCallInitialiser
		extends IElementReferenceInitialiser, IArgumentableInitialiser, ICallTypeArgumentableInitialiser {
	@Override
	public MethodCall instantiate();
}

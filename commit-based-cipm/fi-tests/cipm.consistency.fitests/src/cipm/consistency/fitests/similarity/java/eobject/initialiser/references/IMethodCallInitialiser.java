package cipm.consistency.fitests.similarity.java.eobject.initialiser.references;

import org.emftext.language.java.references.MethodCall;

import cipm.consistency.fitests.similarity.java.eobject.initialiser.generics.ICallTypeArgumentableInitialiser;

public interface IMethodCallInitialiser
		extends IElementReferenceInitialiser, IArgumentableInitialiser, ICallTypeArgumentableInitialiser {
	@Override
	public MethodCall instantiate();
}

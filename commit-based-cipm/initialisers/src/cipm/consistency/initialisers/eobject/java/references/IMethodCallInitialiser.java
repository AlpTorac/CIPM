package cipm.consistency.initialisers.eobject.java.references;

import org.emftext.language.java.references.MethodCall;

import cipm.consistency.initialisers.eobject.java.generics.ICallTypeArgumentableInitialiser;

public interface IMethodCallInitialiser
		extends IElementReferenceInitialiser, IArgumentableInitialiser, ICallTypeArgumentableInitialiser {
	@Override
	public MethodCall instantiate();
}

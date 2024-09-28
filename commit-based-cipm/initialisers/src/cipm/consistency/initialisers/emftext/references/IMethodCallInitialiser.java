package cipm.consistency.initialisers.emftext.references;

import org.emftext.language.java.references.MethodCall;

import cipm.consistency.initialisers.emftext.generics.ICallTypeArgumentableInitialiser;

public interface IMethodCallInitialiser
		extends IElementReferenceInitialiser, IArgumentableInitialiser, ICallTypeArgumentableInitialiser {
	@Override
	public MethodCall instantiate();
}

package cipm.consistency.fitests.similarity.java.initialiser.testable;

import org.emftext.language.java.generics.CallTypeArgumentable;
import org.emftext.language.java.generics.TypeArgument;

import cipm.consistency.fitests.similarity.java.initialiser.ICommentableInitialiser;

public interface ICallTypeArgumentableInitialiser extends ICommentableInitialiser {
	public default void addCallTypeArgument(CallTypeArgumentable cta, TypeArgument ta) {
		if (ta != null) {
			cta.getCallTypeArguments().add(ta);
			assert cta.getCallTypeArguments().contains(ta);
		}
	}
}
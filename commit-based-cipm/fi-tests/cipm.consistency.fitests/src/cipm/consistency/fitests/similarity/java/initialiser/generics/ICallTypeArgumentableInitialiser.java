package cipm.consistency.fitests.similarity.java.initialiser.generics;

import org.emftext.language.java.generics.CallTypeArgumentable;
import org.emftext.language.java.generics.TypeArgument;

import cipm.consistency.fitests.similarity.java.initialiser.commons.ICommentableInitialiser;

public interface ICallTypeArgumentableInitialiser extends ICommentableInitialiser {
	public default boolean addCallTypeArgument(CallTypeArgumentable cta, TypeArgument ta) {
		if (ta != null) {
			cta.getCallTypeArguments().add(ta);
			return cta.getCallTypeArguments().contains(ta);
		}
		return true;
	}
	public default boolean addCallTypeArguments(CallTypeArgumentable cta, TypeArgument[] tas) {
		return this.addXs(cta, tas, this::addCallTypeArgument);
	}
}

package org.splevo.jamopp.diffing.similarity.base.ecore;

import org.eclipse.emf.ecore.EObject;

public interface IComposedSwitchWrapper {
	public EObject getCompareElement();

	public Boolean compare(EObject eo1, EObject eo2);
}

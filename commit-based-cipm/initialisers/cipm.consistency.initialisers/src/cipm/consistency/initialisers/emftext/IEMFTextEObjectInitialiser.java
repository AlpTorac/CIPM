package cipm.consistency.initialisers.emftext;

import cipm.consistency.initialisers.eobject.IEObjectInitialiser;

/**
 * An interface meant to be implemented by {@link IEObjectInitialiser}
 * sub-types, whose purpose is to create {@link EObject} implementors within
 * EMFText.
 * 
 * @author atora
 */
public interface IEMFTextEObjectInitialiser extends IEObjectInitialiser {
	@Override
	public IEMFTextEObjectInitialiser newInitialiser();
}

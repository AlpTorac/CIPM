package cipm.consistency.initialisers.eobject.java.modifiers;

import org.emftext.language.java.modifiers.Volatile;

public interface IVolatileInitialiser extends IModifierInitialiser {
	@Override
	public Volatile instantiate();

}

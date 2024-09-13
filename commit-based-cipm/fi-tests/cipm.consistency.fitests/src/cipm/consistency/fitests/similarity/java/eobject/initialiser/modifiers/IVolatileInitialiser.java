package cipm.consistency.fitests.similarity.java.eobject.initialiser.modifiers;

import org.emftext.language.java.modifiers.Volatile;

public interface IVolatileInitialiser extends IModifierInitialiser {
	@Override
	public Volatile instantiate();

}

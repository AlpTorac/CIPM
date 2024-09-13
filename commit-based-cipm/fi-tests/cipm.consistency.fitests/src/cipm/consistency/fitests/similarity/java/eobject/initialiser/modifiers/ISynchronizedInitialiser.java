package cipm.consistency.fitests.similarity.java.eobject.initialiser.modifiers;

import org.emftext.language.java.modifiers.Synchronized;

public interface ISynchronizedInitialiser extends IModifierInitialiser {
	@Override
	public Synchronized instantiate();

}

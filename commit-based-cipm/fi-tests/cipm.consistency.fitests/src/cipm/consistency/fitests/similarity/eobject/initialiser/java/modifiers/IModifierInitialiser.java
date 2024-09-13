package cipm.consistency.fitests.similarity.eobject.initialiser.java.modifiers;

import org.emftext.language.java.modifiers.Modifier;

public interface IModifierInitialiser extends IAnnotationInstanceOrModifierInitialiser {
	@Override
	public Modifier instantiate();

}

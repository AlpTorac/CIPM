package cipm.consistency.fitests.similarity.java.initialiser.modifiers;

import org.emftext.language.java.modifiers.Private;

public interface IPrivateInitialiser extends IModifierInitialiser {
    @Override
    public Private instantiate();

}

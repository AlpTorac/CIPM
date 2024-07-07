package cipm.consistency.fitests.similarity.java.initialiser.commons;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.commons.NamedElement;

import cipm.consistency.fitests.similarity.java.initialiser.ModificationMethod;

public interface INamedElementInitialiser extends ICommentableInitialiser {
    @Override
    public NamedElement instantiate();
	public default String getDefaultName() {
		return "";
	}
	
	/**
	 * Uses {@link #getDefaultName()} to generate a valid
	 * name.
	 */
	@Override
	public default boolean minimalInitialisation(EObject obj) {
		NamedElement castedO = (NamedElement) obj;
		
		if (castedO.getName() == null) {
			return this.setName(castedO, this.getDefaultName());
		}
		
		return true;
	}
	
	@ModificationMethod
	public default boolean setName(NamedElement ne, String name) {
		if (name != null) {
			ne.setName(name);
			return ne.getName().equals(name);
		}
		return true;
	}
}

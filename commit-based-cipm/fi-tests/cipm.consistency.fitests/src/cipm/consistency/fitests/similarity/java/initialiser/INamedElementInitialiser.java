package cipm.consistency.fitests.similarity.java.initialiser;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.commons.NamedElement;

public interface INamedElementInitialiser extends ICommentableInitialiser {
	public void shouldSetDefaultName(boolean setDefaultName);
	public boolean isSetDefaultName();
	
	public default String getDefaultName() {
		return "";
	}
	
	@Override
	public NamedElement instantiate();
	
	/**
	 * Uses {@link #getDefaultName()} to generate a valid
	 * name.
	 */
	@Override
	public default NamedElement minimalInstantiation() {
		var result = this.instantiate();
		this.initialiseName(result, this.getDefaultName());
		return result;
	}
	
	@Override
	public default NamedElement clone(EObject obj) {
		return (NamedElement) ICommentableInitialiser.super.clone(obj);
	}
	
	public default void initialiseName(NamedElement ne, String name) {
		if (name != null) {
			ne.setName(name);
			assert ne.getName().equals(name);
		}
		else if (this.isSetDefaultName()) {
			ne.setName(this.getDefaultName());
			assert ne.getName() == null || ne.getName().equals(this.getDefaultName());
		}
	}
}

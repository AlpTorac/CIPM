package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.commons.NamedElement;

public interface INameInitialiser extends ICommentableInitialiser {
	public void shouldSetDefaultName(boolean setDefaultName);
	public boolean isSetDefaultName();
	
	public default String getDefaultName() {
		return "";
	}
	
	@Override
	public NamedElement getCurrentObject();
	
	public default void initialiseName(String name) {
		var cObj = this.getCurrentObject();
		
		if (name != null) {
			cObj.setName(name);
			assert cObj.getName().equals(name);
		}
		else if (this.isSetDefaultName()) {
			cObj.setName(this.getDefaultName());
			assert cObj.getName() == null || cObj.getName().equals(this.getDefaultName());
		}
	}
	
	@Override
	public default NamedElement build() {
		return (NamedElement) ICommentableInitialiser.super.build();
	}
}

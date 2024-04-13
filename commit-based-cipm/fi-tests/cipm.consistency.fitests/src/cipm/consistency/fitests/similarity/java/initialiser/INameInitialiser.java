package cipm.consistency.fitests.similarity.java.initialiser;

import java.util.Map;

import org.emftext.language.java.commons.NamedElement;

import cipm.consistency.fitests.similarity.java.ResourceParameters;

public interface INameInitialiser extends EObjectInitialiser {
	public boolean isSetDefaultName();
	public default String getDefaultName() {
		return "";
	}
	
	public default NamedElement initialiseName(NamedElement elem, String name) {
		if (name != null) {
			elem.setName(name);
			assert elem.getName().equals(name);
		}
		else if (this.isSetDefaultName()) {
			elem.setName(this.getDefaultName());
			assert elem.getName() == null || elem.getName().equals(this.getDefaultName());
		}
		return elem;
	}
	
	public default NamedElement initialiseNamedElement(NamedElement elem, Map<ResourceParameters, Object> params) {
		return this.initialiseName(elem, this.getNameParam(params));
	}
	
	public default String getNameParam(Map<ResourceParameters, Object> params) {
		return (String) params.get(ResourceParameters.NAME);
	}
}

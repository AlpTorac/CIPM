package cipm.consistency.fitests.similarity.java.action.modification;

import org.emftext.language.java.commons.NamespaceAwareElement;

import cipm.consistency.fitests.similarity.java.action.IDevAction;

public class SetNamespace implements IDevAction<NamespaceAwareElement> {

	private String[] nss;
	
	public String[] getNamespaces() {
		if (this.nss != null) {
			return this.nss.clone();
		} else {
			return null;
		}
	}
	
	public SetNamespace withNamespaces(String... nss) {
		this.nss = nss;
		return this;
	}
	
	@Override
	public boolean apply(NamespaceAwareElement obj) {
		if (this.canBePerformed(obj)) {
			var objNss = obj.getNamespaces();
			objNss.clear();
			
			if (this.nss != null) {
				for (var ns : this.nss) {
					objNss.add(ns);
				}
				
				var result = objNss.size() == this.nss.length;
				
				for (var ns : this.nss) {
					result = result && objNss.contains(ns);
				}
				
				return result;
			} else {
				return objNss.size() == 0;
			}
		} else {
			return false;
		}
	}

	@Override
	public SetNamespace newActionInstance() {
		return new SetNamespace();
	}

	@Override
	public SetNamespace cloneAction() {
		var clone = this.newActionInstance();
		
		if (this.nss != null) {
			clone.withNamespaces(this.getNamespaces());
		}
		
		return clone;
	}

}

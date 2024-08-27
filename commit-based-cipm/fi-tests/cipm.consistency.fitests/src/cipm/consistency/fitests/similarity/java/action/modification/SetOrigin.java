package cipm.consistency.fitests.similarity.java.action.modification;

import org.emftext.language.java.containers.JavaRoot;
import org.emftext.language.java.containers.Origin;

import cipm.consistency.fitests.similarity.java.action.IDevAction;

public class SetOrigin implements IDevAction<JavaRoot> {
	private Origin origin;
	
	public SetOrigin withOrigin(Origin origin) {
		this.origin = origin;
		return this;
	}
	
	@Override
	public boolean apply(JavaRoot obj) {
		if (this.canBePerformed(obj)) {
			obj.setOrigin(this.origin);
			return obj.getOrigin() == this.origin;
		}
		
		return false;
	}

	@Override
	public SetOrigin newActionInstance() {
		return new SetOrigin();
	}

	@Override
	public SetOrigin cloneAction() {
		var clone = this.newActionInstance();
		
		if (this.origin != null) {
			clone.withOrigin(this.origin);
		}
		
		return null;
	}

}

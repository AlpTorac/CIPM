package cipm.consistency.initialisers.emftext.types;

import org.emftext.language.java.types.Boolean;

public interface IBooleanInitialiser extends IPrimitiveTypeInitialiser {
	@Override
	public Boolean instantiate();

}
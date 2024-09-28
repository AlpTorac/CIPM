package cipm.consistency.initialisers.emftext.types;

import org.emftext.language.java.types.Int;

public interface IIntInitialiser extends IPrimitiveTypeInitialiser {
	@Override
	public Int instantiate();

}
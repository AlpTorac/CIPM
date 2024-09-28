package cipm.consistency.initialisers.emftext.types;

import org.emftext.language.java.types.Char;

public interface ICharInitialiser extends IPrimitiveTypeInitialiser {
	@Override
	public Char instantiate();

}
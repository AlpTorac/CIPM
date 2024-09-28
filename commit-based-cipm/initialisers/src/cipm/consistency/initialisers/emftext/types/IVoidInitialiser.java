package cipm.consistency.initialisers.emftext.types;

import org.emftext.language.java.types.Void;

public interface IVoidInitialiser extends IPrimitiveTypeInitialiser {
	@Override
	public Void instantiate();

}
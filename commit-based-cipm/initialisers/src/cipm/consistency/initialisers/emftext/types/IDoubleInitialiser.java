package cipm.consistency.initialisers.emftext.types;

import org.emftext.language.java.types.Double;

public interface IDoubleInitialiser extends IPrimitiveTypeInitialiser {
	@Override
	public Double instantiate();

}
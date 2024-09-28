package cipm.consistency.initialisers.emftext.types;

import org.emftext.language.java.types.Float;

public interface IFloatInitialiser extends IPrimitiveTypeInitialiser {
	@Override
	public Float instantiate();

}
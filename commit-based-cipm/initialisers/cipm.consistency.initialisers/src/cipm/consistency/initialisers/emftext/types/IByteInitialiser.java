package cipm.consistency.initialisers.emftext.types;

import org.emftext.language.java.types.Byte;

public interface IByteInitialiser extends IPrimitiveTypeInitialiser {
	@Override
	public Byte instantiate();

}
package cipm.consistency.fitests.similarity.java.initialiser.types;

import org.emftext.language.java.types.Byte;
import org.emftext.language.java.types.TypesFactory;

public class ByteInitialiser implements IByteInitialiser {
	@Override
	public IByteInitialiser newInitialiser() {
		return new ByteInitialiser();
	}
	
	@Override
	public Byte instantiate() {
		return TypesFactory.eINSTANCE.createByte();
	}
}
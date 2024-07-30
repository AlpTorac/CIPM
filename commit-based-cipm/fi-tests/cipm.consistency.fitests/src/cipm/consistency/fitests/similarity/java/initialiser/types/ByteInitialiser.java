package cipm.consistency.fitests.similarity.java.initialiser.types;

import org.emftext.language.java.types.Byte;
import org.emftext.language.java.types.TypesFactory;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class ByteInitialiser extends AbstractInitialiserBase implements IByteInitialiser {
	@Override
	public IByteInitialiser newInitialiser() {
		return new ByteInitialiser();
	}

	@Override
	public Byte instantiate() {
		return TypesFactory.eINSTANCE.createByte();
	}
}
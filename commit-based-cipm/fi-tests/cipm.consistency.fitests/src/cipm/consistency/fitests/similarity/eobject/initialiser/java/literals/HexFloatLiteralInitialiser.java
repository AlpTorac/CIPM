package cipm.consistency.fitests.similarity.eobject.initialiser.java.literals;

import org.emftext.language.java.literals.HexFloatLiteral;
import org.emftext.language.java.literals.LiteralsFactory;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class HexFloatLiteralInitialiser extends AbstractInitialiserBase implements IHexFloatLiteralInitialiser {
	@Override
	public IHexFloatLiteralInitialiser newInitialiser() {
		return new HexFloatLiteralInitialiser();
	}

	@Override
	public HexFloatLiteral instantiate() {
		return LiteralsFactory.eINSTANCE.createHexFloatLiteral();
	}
}
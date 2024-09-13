package cipm.consistency.fitests.similarity.eobject.initialiser.java.literals;

import org.emftext.language.java.literals.HexIntegerLiteral;
import org.emftext.language.java.literals.LiteralsFactory;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class HexIntegerLiteralInitialiser extends AbstractInitialiserBase implements IHexIntegerLiteralInitialiser {
	@Override
	public IHexIntegerLiteralInitialiser newInitialiser() {
		return new HexIntegerLiteralInitialiser();
	}

	@Override
	public HexIntegerLiteral instantiate() {
		return LiteralsFactory.eINSTANCE.createHexIntegerLiteral();
	}
}
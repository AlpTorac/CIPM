package cipm.consistency.fitests.similarity.java.initialiser.literals;

import org.emftext.language.java.literals.HexLongLiteral;
import org.emftext.language.java.literals.LiteralsFactory;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class HexLongLiteralInitialiser extends AbstractInitialiserBase implements IHexLongLiteralInitialiser {
	@Override
	public IHexLongLiteralInitialiser newInitialiser() {
		return new HexLongLiteralInitialiser();
	}

	@Override
	public HexLongLiteral instantiate() {
		return LiteralsFactory.eINSTANCE.createHexLongLiteral();
	}
}
package cipm.consistency.initialisers.emftext.literals;

import org.emftext.language.java.literals.IntegerLiteral;

public interface IIntegerLiteralInitialiser extends ILiteralInitialiser {
	@Override
	public IntegerLiteral instantiate();

}
package cipm.consistency.initialisers.emftext.literals;

import org.emftext.language.java.literals.DoubleLiteral;

public interface IDoubleLiteralInitialiser extends ILiteralInitialiser {
	@Override
	public DoubleLiteral instantiate();

}
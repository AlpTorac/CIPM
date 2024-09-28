package cipm.consistency.initialisers.emftext.literals;

import org.emftext.language.java.literals.Literal;

import cipm.consistency.initialisers.emftext.expressions.IPrimaryExpressionInitialiser;

public interface ILiteralInitialiser extends IPrimaryExpressionInitialiser {
	@Override
	public Literal instantiate();

}
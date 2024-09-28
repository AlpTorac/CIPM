package cipm.consistency.initialisers.eobject.java.literals;

import org.emftext.language.java.literals.Literal;

import cipm.consistency.initialisers.eobject.java.expressions.IPrimaryExpressionInitialiser;

public interface ILiteralInitialiser extends IPrimaryExpressionInitialiser {
	@Override
	public Literal instantiate();

}
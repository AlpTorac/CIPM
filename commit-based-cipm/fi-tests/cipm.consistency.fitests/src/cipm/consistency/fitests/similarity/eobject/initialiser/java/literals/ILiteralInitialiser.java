package cipm.consistency.fitests.similarity.eobject.initialiser.java.literals;

import org.emftext.language.java.literals.Literal;

import cipm.consistency.fitests.similarity.eobject.initialiser.java.expressions.IPrimaryExpressionInitialiser;

public interface ILiteralInitialiser extends IPrimaryExpressionInitialiser {
	@Override
	public Literal instantiate();

}
package cipm.consistency.fitests.similarity.java.initialiser.literals;

import org.emftext.language.java.literals.Literal;

import cipm.consistency.fitests.similarity.java.initialiser.expressions.IPrimaryExpressionInitialiser;

public interface ILiteralInitialiser extends IPrimaryExpressionInitialiser {
	@Override
	public Literal instantiate();

}
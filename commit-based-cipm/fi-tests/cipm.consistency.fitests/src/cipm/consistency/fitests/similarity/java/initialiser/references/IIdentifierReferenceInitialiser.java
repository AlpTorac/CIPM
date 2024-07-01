package cipm.consistency.fitests.similarity.java.initialiser.references;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.references.IdentifierReference;

import cipm.consistency.fitests.similarity.java.initialiser.annotations.IAnnotableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.arrays.IArrayTypeableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.instantiations.ExplicitConstructorCallInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.statements.BlockInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.statements.ExpressionStatementInitialiser;

public interface IIdentifierReferenceInitialiser extends IAnnotableInitialiser,
	IArrayTypeableInitialiser,
	IElementReferenceInitialiser
{
	@Override
	public default EObject minimalInitialisationWithContainer(EObject obj) {
		// FIXME: See if this can go to a helper interface
		var castedO = (IdentifierReference) obj;
		IAnnotableInitialiser.super.minimalInitialisation(castedO);
		
		if (castedO.eContainer() == null) {
			var insInit = new ExplicitConstructorCallInitialiser();
			var ecc = insInit.instantiate();
			insInit.minimalInitialisation(ecc);
			insInit.addArgument(ecc, castedO);
			
			var esInit = new ExpressionStatementInitialiser();
			var es = esInit.instantiate();
			esInit.setExpression(es, ecc);
			
			var bInit = new BlockInitialiser();
			var block = bInit.instantiate();
			bInit.addStatement(block, es);
			
			return block;
		}
		
		return castedO.eContainer();
	}
}

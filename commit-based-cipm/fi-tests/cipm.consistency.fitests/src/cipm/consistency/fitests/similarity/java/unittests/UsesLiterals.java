package cipm.consistency.fitests.similarity.java.unittests;

import java.math.BigInteger;

import org.emftext.language.java.literals.IntegerLiteral;
import org.emftext.language.java.literals.Super;
import org.emftext.language.java.literals.This;
import org.emftext.language.java.modifiers.Static;
import org.emftext.language.java.modifiers.Transitive;

import cipm.consistency.fitests.similarity.java.initialiser.modifiers.StaticInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.modifiers.TransitiveInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.params.LiteralFactory;

public interface UsesLiterals {
	public default IntegerLiteral createInteger(BigInteger val) {
		return new LiteralFactory().createDecIntegerLiteral(val);
	}
	
	public default IntegerLiteral createInteger(int val) {
		return this.createInteger(BigInteger.valueOf(val));
	}
	
	public default This createThis() {
		return new LiteralFactory().createThisLiteral();
	}
	
	public default Super createSuper() {
		return new LiteralFactory().createSuperLiteral();
	}
	
	public default Static createStatic() {
		return new StaticInitialiser().instantiate();
	}
	
	public default Transitive createTransitive() {
		return new TransitiveInitialiser().instantiate();
	}
}

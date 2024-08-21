package cipm.consistency.fitests.similarity.java.unittests;

import java.math.BigInteger;

import org.emftext.language.java.literals.BinaryIntegerLiteral;
import org.emftext.language.java.literals.BinaryLongLiteral;
import org.emftext.language.java.literals.BooleanLiteral;
import org.emftext.language.java.literals.CharacterLiteral;
import org.emftext.language.java.literals.DecimalDoubleLiteral;
import org.emftext.language.java.literals.DecimalFloatLiteral;
import org.emftext.language.java.literals.DecimalIntegerLiteral;
import org.emftext.language.java.literals.DecimalLongLiteral;
import org.emftext.language.java.literals.HexDoubleLiteral;
import org.emftext.language.java.literals.HexFloatLiteral;
import org.emftext.language.java.literals.HexIntegerLiteral;
import org.emftext.language.java.literals.HexLongLiteral;
import org.emftext.language.java.literals.NullLiteral;
import org.emftext.language.java.literals.OctalIntegerLiteral;
import org.emftext.language.java.literals.OctalLongLiteral;
import org.emftext.language.java.literals.Super;
import org.emftext.language.java.literals.This;
import org.emftext.language.java.modifiers.Static;
import org.emftext.language.java.modifiers.Transitive;

import cipm.consistency.fitests.similarity.java.initialiser.literals.BinaryIntegerLiteralInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.literals.BinaryLongLiteralInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.literals.BooleanLiteralInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.literals.CharacterLiteralInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.literals.DecimalDoubleLiteralInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.literals.DecimalFloatLiteralInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.literals.DecimalIntegerLiteralInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.literals.DecimalLongLiteralInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.literals.HexDoubleLiteralInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.literals.HexFloatLiteralInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.literals.HexIntegerLiteralInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.literals.HexLongLiteralInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.literals.NullLiteralInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.literals.OctalIntegerLiteralInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.literals.OctalLongLiteralInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.literals.SuperInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.literals.ThisInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.modifiers.StaticInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.modifiers.TransitiveInitialiser;

/**
 * An interface that can be implemented by tests, which work with
 * {@link Literal} instances. <br>
 * <br>
 * Contains methods that can be used to create {@link Literal} instances. The
 * methods wrap the value provided to them with the corresponding
 * {@link Literal} implementations.
 */
public interface UsesLiterals {
	public default CharacterLiteral createCharacterLiteral(String val) {
		var init = new CharacterLiteralInitialiser();
		var lit = init.instantiate();
		init.setValue(lit, val);
		return lit;
	}

	public default BooleanLiteral createBooleanLiteral(boolean val) {
		var init = new BooleanLiteralInitialiser();
		var lit = init.instantiate();
		init.setValue(lit, val);
		return lit;
	}

	public default HexFloatLiteral createHexFloatLiteral(float val) {
		var init = new HexFloatLiteralInitialiser();
		var lit = init.instantiate();
		init.setHexValue(lit, val);
		return lit;
	}

	public default DecimalFloatLiteral createDecimalFloatLiteral(float val) {
		var init = new DecimalFloatLiteralInitialiser();
		var lit = init.instantiate();
		init.setDecimalValue(lit, val);
		return lit;
	}

	public default HexDoubleLiteral createHexDoubleLiteral(double val) {
		var init = new HexDoubleLiteralInitialiser();
		var lit = init.instantiate();
		init.setHexValue(lit, val);
		return lit;
	}

	public default DecimalDoubleLiteral createDecimalDoubleLiteral(double val) {
		var init = new DecimalDoubleLiteralInitialiser();
		var lit = init.instantiate();
		init.setDecimalValue(lit, val);
		return lit;
	}

	public default BinaryIntegerLiteral createBinaryIntegerLiteral(BigInteger val) {
		var init = new BinaryIntegerLiteralInitialiser();
		var lit = init.instantiate();
		init.setBinaryValue(lit, val);
		return lit;
	}

	public default BinaryIntegerLiteral createBinaryIntegerLiteral(int val) {
		return this.createBinaryIntegerLiteral(BigInteger.valueOf(val));
	}

	public default DecimalIntegerLiteral createDecimalIntegerLiteral(BigInteger val) {
		var init = new DecimalIntegerLiteralInitialiser();
		var lit = init.instantiate();
		init.setDecimalValue(lit, val);
		return lit;
	}

	public default DecimalIntegerLiteral createDecimalIntegerLiteral(int val) {
		return this.createDecimalIntegerLiteral(BigInteger.valueOf(val));
	}

	public default HexIntegerLiteral createHexIntegerLiteral(BigInteger val) {
		var init = new HexIntegerLiteralInitialiser();
		var lit = init.instantiate();
		init.setHexValue(lit, val);
		return lit;
	}

	public default HexIntegerLiteral createHexIntegerLiteral(int val) {
		return this.createHexIntegerLiteral(BigInteger.valueOf(val));
	}

	public default OctalIntegerLiteral createOctalIntegerLiteral(BigInteger val) {
		var init = new OctalIntegerLiteralInitialiser();
		var lit = init.instantiate();
		init.setOctalValue(lit, val);
		return lit;
	}

	public default OctalIntegerLiteral createOctalIntegerLiteral(int val) {
		return this.createOctalIntegerLiteral(BigInteger.valueOf(val));
	}

	public default BinaryLongLiteral createBinaryLongLiteral(BigInteger val) {
		var init = new BinaryLongLiteralInitialiser();
		var lit = init.instantiate();
		init.setBinaryValue(lit, val);
		return lit;
	}

	public default BinaryLongLiteral createBinaryLongLiteral(int val) {
		return this.createBinaryLongLiteral(BigInteger.valueOf(val));
	}

	public default DecimalLongLiteral createDecimalLongLiteral(BigInteger val) {
		var init = new DecimalLongLiteralInitialiser();
		var lit = init.instantiate();
		init.setDecimalValue(lit, val);
		return lit;
	}

	public default DecimalLongLiteral createDecimalLongLiteral(int val) {
		return this.createDecimalLongLiteral(BigInteger.valueOf(val));
	}

	public default HexLongLiteral createHexLongLiteral(BigInteger val) {
		var init = new HexLongLiteralInitialiser();
		var lit = init.instantiate();
		init.setHexValue(lit, val);
		return lit;
	}

	public default HexLongLiteral createHexLongLiteral(int val) {
		return this.createHexLongLiteral(BigInteger.valueOf(val));
	}

	public default OctalLongLiteral createOctalLongLiteral(BigInteger val) {
		var init = new OctalLongLiteralInitialiser();
		var lit = init.instantiate();
		init.setOctalValue(lit, val);
		return lit;
	}

	public default OctalLongLiteral createOctalLongLiteral(int val) {
		return this.createOctalLongLiteral(BigInteger.valueOf(val));
	}

	public default NullLiteral createNullLiteral() {
		return new NullLiteralInitialiser().instantiate();
	}

	public default This createThis() {
		return new ThisInitialiser().instantiate();
	}

	public default Super createSuper() {
		return new SuperInitialiser().instantiate();
	}

	public default Static createStatic() {
		return new StaticInitialiser().instantiate();
	}

	public default Transitive createTransitive() {
		return new TransitiveInitialiser().instantiate();
	}
}
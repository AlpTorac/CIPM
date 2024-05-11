package cipm.consistency.fitests.similarity.java.generators;

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
import org.emftext.language.java.literals.LiteralsFactory;

public class LiteralFactory implements ILiteralFactory {
	protected LiteralsFactory getFactory() {
		return LiteralsFactory.eINSTANCE;
	}
	
	@Override
	public BooleanLiteral createBooleanLiteral(boolean val) {
		var result = this.getFactory().createBooleanLiteral();
		result.setValue(val);
		return result;
	}

	@Override
	public CharacterLiteral createCharacterLiteral(String val) {
		var result = this.getFactory().createCharacterLiteral();
		result.setValue(val);
		return result;
	}

	@Override
	public DecimalDoubleLiteral createDecDoubleLiteral(double val) {
		var result = this.getFactory().createDecimalDoubleLiteral();
		result.setDecimalValue(val);
		return result;
	}

	@Override
	public HexDoubleLiteral createHexDoubleLiteral(double val) {
		var result = this.getFactory().createHexDoubleLiteral();
		result.setHexValue(val);
		return result;
	}

	@Override
	public DecimalFloatLiteral createDecFloatLiteral(float val) {
		var result = this.getFactory().createDecimalFloatLiteral();
		result.setDecimalValue(val);
		return result;
	}

	@Override
	public HexFloatLiteral createHexFloatLiteral(float val) {
		var result = this.getFactory().createHexFloatLiteral();
		result.setHexValue(val);
		return result;
	}

	@Override
	public BinaryIntegerLiteral createBinIntegerLiteral(BigInteger val) {
		var result = this.getFactory().createBinaryIntegerLiteral();
		result.setBinaryValue(val);
		return result;
	}

	@Override
	public DecimalIntegerLiteral createDecIntegerLiteral(BigInteger val) {
		var result = this.getFactory().createDecimalIntegerLiteral();
		result.setDecimalValue(val);
		return result;
	}

	@Override
	public HexIntegerLiteral createHexIntegerLiteral(BigInteger val) {
		var result = this.getFactory().createHexIntegerLiteral();
		result.setHexValue(val);
		return result;
	}

	@Override
	public OctalIntegerLiteral createOctIntegerLiteral(BigInteger val) {
		var result = this.getFactory().createOctalIntegerLiteral();
		result.setOctalValue(val);
		return result;
	}

	@Override
	public BinaryLongLiteral createBinLongLiteral(BigInteger val) {
		var result = this.getFactory().createBinaryLongLiteral();
		result.setBinaryValue(val);
		return result;
	}

	@Override
	public DecimalLongLiteral createDecLongLiteral(BigInteger val) {
		var result = this.getFactory().createDecimalLongLiteral();
		result.setDecimalValue(val);
		return result;
	}

	@Override
	public HexLongLiteral createHexLongLiteral(BigInteger val) {
		var result = this.getFactory().createHexLongLiteral();
		result.setHexValue(val);
		return result;
	}

	@Override
	public OctalLongLiteral createOctLongLiteral(BigInteger val) {
		var result = this.getFactory().createOctalLongLiteral();
		result.setOctalValue(val);
		return result;
	}

	@Override
	public NullLiteral createNullLiteral() {
		return this.getFactory().createNullLiteral();
	}

	@Override
	public This createThisLiteral() {
		return this.getFactory().createThis();
	}

	@Override
	public Super createSuperLiteral() {
		return this.getFactory().createSuper();
	}
}

package cipm.consistency.fitests.similarity.java.initialiser.params;

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

public interface ILiteralFactory {
	public BooleanLiteral createBooleanLiteral(boolean val);
	public CharacterLiteral createCharacterLiteral(String val);
	
	public DecimalDoubleLiteral createDecDoubleLiteral(double val);
	public HexDoubleLiteral createHexDoubleLiteral(double val);
	
	public DecimalFloatLiteral createDecFloatLiteral(float val);
	public HexFloatLiteral createHexFloatLiteral(float val);
	
	public BinaryIntegerLiteral createBinIntegerLiteral(BigInteger val);
	public DecimalIntegerLiteral createDecIntegerLiteral(BigInteger val);
	public HexIntegerLiteral createHexIntegerLiteral(BigInteger val);
	public OctalIntegerLiteral createOctIntegerLiteral(BigInteger val);
	
	public BinaryLongLiteral createBinLongLiteral(BigInteger val);
	public DecimalLongLiteral createDecLongLiteral(BigInteger val);
	public HexLongLiteral createHexLongLiteral(BigInteger val);
	public OctalLongLiteral createOctLongLiteral(BigInteger val);
	
	public NullLiteral createNullLiteral();
	public This createThisLiteral();
	public Super createSuperLiteral();
}

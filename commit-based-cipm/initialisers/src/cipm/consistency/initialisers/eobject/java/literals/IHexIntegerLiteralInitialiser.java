package cipm.consistency.initialisers.eobject.java.literals;

import java.math.BigInteger;

import org.emftext.language.java.literals.HexIntegerLiteral;

public interface IHexIntegerLiteralInitialiser extends IIntegerLiteralInitialiser {
	@Override
	public HexIntegerLiteral instantiate();

	public default boolean setHexValue(HexIntegerLiteral hil, BigInteger val) {
		if (val != null) {
			hil.setHexValue(val);
			return hil.getHexValue().equals(val);
		}
		return true;
	}

	public default boolean setHexValue(HexIntegerLiteral hil, int val) {
		return this.setHexValue(hil, BigInteger.valueOf(val));
	}
}

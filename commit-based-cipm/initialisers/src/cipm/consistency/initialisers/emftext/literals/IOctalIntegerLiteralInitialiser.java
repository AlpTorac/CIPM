package cipm.consistency.initialisers.emftext.literals;

import java.math.BigInteger;

import org.emftext.language.java.literals.OctalIntegerLiteral;

public interface IOctalIntegerLiteralInitialiser extends IIntegerLiteralInitialiser {
	@Override
	public OctalIntegerLiteral instantiate();

	public default boolean setOctalValue(OctalIntegerLiteral oil, BigInteger val) {
		if (val != null) {
			oil.setOctalValue(val);
			return oil.getOctalValue().equals(val);
		}
		return true;
	}

	public default boolean setOctalValue(OctalIntegerLiteral oil, int val) {
		return this.setOctalValue(oil, BigInteger.valueOf(val));
	}
}

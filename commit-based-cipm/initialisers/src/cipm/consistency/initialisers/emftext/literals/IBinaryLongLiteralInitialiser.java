package cipm.consistency.initialisers.emftext.literals;

import java.math.BigInteger;

import org.emftext.language.java.literals.BinaryLongLiteral;

public interface IBinaryLongLiteralInitialiser extends ILongLiteralInitialiser {
	@Override
	public BinaryLongLiteral instantiate();

	public default boolean setBinaryValue(BinaryLongLiteral bil, BigInteger val) {
		if (val != null) {
			bil.setBinaryValue(val);
			return bil.getBinaryValue().equals(val);
		}
		return true;
	}

	public default boolean setBinaryValue(BinaryLongLiteral bil, long val) {
		return this.setBinaryValue(bil, BigInteger.valueOf(val));
	}
}

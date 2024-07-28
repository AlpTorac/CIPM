package cipm.consistency.fitests.similarity.java.initialiser.literals;

import java.math.BigInteger;

import org.emftext.language.java.literals.HexLongLiteral;

public interface IHexLongLiteralInitialiser extends ILongLiteralInitialiser {
    @Override
    public HexLongLiteral instantiate();

    public default boolean setHexValue(HexLongLiteral hll, BigInteger val) {
    	if (val != null) {
    		hll.setHexValue(val);
    		return hll.getHexValue().equals(val);
    	}
    	return true;
    }
    
    public default boolean setHexValue(HexLongLiteral hll, long val) {
    	return this.setHexValue(hll, BigInteger.valueOf(val));
    }
}

package cipm.consistency.fitests.similarity.java.initialiser.literals;

import java.math.BigInteger;

import org.emftext.language.java.literals.OctalLongLiteral;

public interface IOctalLongLiteralInitialiser extends ILongLiteralInitialiser {
    @Override
    public OctalLongLiteral instantiate();

    public default boolean setOctalValue(OctalLongLiteral oll, BigInteger val) {
    	if (val != null) {
    		oll.setOctalValue(val);
    		return oll.getOctalValue().equals(val);
    	}
    	return true;
    }
    
    public default boolean setOctalValue(OctalLongLiteral oll, int val) {
    	return this.setOctalValue(oll, BigInteger.valueOf(val));
    }
}

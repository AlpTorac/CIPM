package cipm.consistency.fitests.similarity.java.initialiser.literals;

import java.math.BigInteger;

import org.emftext.language.java.literals.DecimalIntegerLiteral;

public interface IDecimalIntegerLiteralInitialiser extends IIntegerLiteralInitialiser {
    @Override
    public DecimalIntegerLiteral instantiate();
    
    public default boolean setDecimalValue(DecimalIntegerLiteral dil, BigInteger val) {
    	if (val != null) {
    		dil.setDecimalValue(val);
        	return dil.getDecimalValue().equals(val);
    	}
    	return true;
    }
    
    public default boolean setDecimalValue(DecimalIntegerLiteral dil, int val) {
    	return this.setDecimalValue(dil, BigInteger.valueOf(val));
    }
}

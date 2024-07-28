package cipm.consistency.fitests.similarity.java.initialiser.literals;

import java.math.BigInteger;

import org.emftext.language.java.literals.DecimalLongLiteral;

public interface IDecimalLongLiteralInitialiser extends ILongLiteralInitialiser {
    @Override
    public DecimalLongLiteral instantiate();
    
    public default boolean setDecimalValue(DecimalLongLiteral dll, BigInteger val) {
    	if (val != null) {
    		dll.setDecimalValue(val);
        	return dll.getDecimalValue().equals(val);
    	}
    	return true;
    }
    
    public default boolean setDecimalValue(DecimalLongLiteral dll, long val) {
    	return this.setDecimalValue(dll, BigInteger.valueOf(val));
    }
}

package cipm.consistency.fitests.similarity.java.initialiser.literals;

import java.math.BigInteger;

import org.emftext.language.java.literals.BinaryIntegerLiteral;

public interface IBinaryIntegerLiteralInitialiser extends IIntegerLiteralInitialiser {
    @Override
    public BinaryIntegerLiteral instantiate();
    
    public default boolean setBinaryValue(BinaryIntegerLiteral bil, BigInteger val) {
    	if (val != null) {
    		bil.setBinaryValue(val);
    		return bil.getBinaryValue().equals(val);
    	}
    	return true;
    }
    
    public default boolean setBinaryValue(BinaryIntegerLiteral bil, int val) {
    	return this.setBinaryValue(bil, BigInteger.valueOf(val));
    }
}

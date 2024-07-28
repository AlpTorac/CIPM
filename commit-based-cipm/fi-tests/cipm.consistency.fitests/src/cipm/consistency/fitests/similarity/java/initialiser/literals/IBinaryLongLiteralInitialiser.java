package cipm.consistency.fitests.similarity.java.initialiser.literals;

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
    
    public default boolean setBinaryValue(BinaryLongLiteral bil, int val) {
    	return this.setBinaryValue(bil, BigInteger.valueOf(val));
    }
}

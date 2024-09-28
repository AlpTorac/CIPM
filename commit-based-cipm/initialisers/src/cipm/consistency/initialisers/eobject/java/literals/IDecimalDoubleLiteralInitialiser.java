package cipm.consistency.initialisers.eobject.java.literals;

import org.emftext.language.java.literals.DecimalDoubleLiteral;

public interface IDecimalDoubleLiteralInitialiser extends IDoubleLiteralInitialiser {
	@Override
	public DecimalDoubleLiteral instantiate();

	public default boolean setDecimalValue(DecimalDoubleLiteral ddl, double val) {
		ddl.setDecimalValue(val);
		return ddl.getDecimalValue() == val;
	}
}

package cipm.consistency.initialisers.emftext.literals;

import org.emftext.language.java.literals.HexDoubleLiteral;

public interface IHexDoubleLiteralInitialiser extends IDoubleLiteralInitialiser {
	@Override
	public HexDoubleLiteral instantiate();

	public default boolean setHexValue(HexDoubleLiteral hdl, double val) {
		hdl.setHexValue(val);
		return hdl.getHexValue() == val;
	}
}
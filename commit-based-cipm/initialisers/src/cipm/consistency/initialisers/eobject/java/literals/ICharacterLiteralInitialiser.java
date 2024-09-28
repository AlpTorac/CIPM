package cipm.consistency.initialisers.eobject.java.literals;

import org.emftext.language.java.literals.CharacterLiteral;

public interface ICharacterLiteralInitialiser extends ILiteralInitialiser {
	@Override
	public CharacterLiteral instantiate();

	public default boolean setValue(CharacterLiteral cl, String val) {
		if (val != null) {
			cl.setValue(val);
			return cl.getValue().equals(val);
		}
		return true;
	}
}

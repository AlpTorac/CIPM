package cipm.consistency.fitests.similarity.java.initialiser.literals;

import org.emftext.language.java.literals.HexFloatLiteral;

public interface IHexFloatLiteralInitialiser extends IFloatLiteralInitialiser {
    @Override
    public HexFloatLiteral instantiate();

}

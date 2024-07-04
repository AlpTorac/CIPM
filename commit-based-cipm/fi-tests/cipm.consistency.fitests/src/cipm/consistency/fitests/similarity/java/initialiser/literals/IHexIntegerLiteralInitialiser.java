package cipm.consistency.fitests.similarity.java.initialiser.literals;

import org.emftext.language.java.literals.HexIntegerLiteral;

public interface IHexIntegerLiteralInitialiser extends IIntegerLiteralInitialiser {
    @Override
    public HexIntegerLiteral instantiate();

}

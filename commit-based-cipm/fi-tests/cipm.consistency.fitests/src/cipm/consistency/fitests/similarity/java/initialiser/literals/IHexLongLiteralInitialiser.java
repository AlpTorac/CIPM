package cipm.consistency.fitests.similarity.java.initialiser.literals;

import org.emftext.language.java.literals.HexLongLiteral;

public interface IHexLongLiteralInitialiser extends ILongLiteralInitialiser {
    @Override
    public HexLongLiteral instantiate();

}

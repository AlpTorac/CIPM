package cipm.consistency.fitests.similarity.java.initialiser.literals;

import org.emftext.language.java.literals.HexDoubleLiteral;

public interface IHexDoubleLiteralInitialiser extends IDoubleLiteralInitialiser {
    @Override
    public HexDoubleLiteral instantiate();

}

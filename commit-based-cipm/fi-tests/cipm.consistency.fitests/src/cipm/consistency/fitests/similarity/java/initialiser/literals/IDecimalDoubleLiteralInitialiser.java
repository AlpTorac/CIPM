package cipm.consistency.fitests.similarity.java.initialiser.literals;

import org.emftext.language.java.literals.DecimalDoubleLiteral;

public interface IDecimalDoubleLiteralInitialiser extends IDoubleLiteralInitialiser {
    @Override
    public DecimalDoubleLiteral instantiate();

}

package cipm.consistency.fitests.similarity.java.initialiser.literals;

import org.emftext.language.java.literals.DecimalIntegerLiteral;

public interface IDecimalIntegerLiteralInitialiser extends IIntegerLiteralInitialiser {
    @Override
    public DecimalIntegerLiteral instantiate();

}

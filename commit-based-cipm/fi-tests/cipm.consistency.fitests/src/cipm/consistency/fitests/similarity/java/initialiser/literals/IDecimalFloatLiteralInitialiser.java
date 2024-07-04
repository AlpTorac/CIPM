package cipm.consistency.fitests.similarity.java.initialiser.literals;

import org.emftext.language.java.literals.DecimalFloatLiteral;

public interface IDecimalFloatLiteralInitialiser extends IFloatLiteralInitialiser {
    @Override
    public DecimalFloatLiteral instantiate();

}

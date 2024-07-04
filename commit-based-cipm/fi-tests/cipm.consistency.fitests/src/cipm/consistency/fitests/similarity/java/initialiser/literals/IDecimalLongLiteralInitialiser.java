package cipm.consistency.fitests.similarity.java.initialiser.literals;

import org.emftext.language.java.literals.DecimalLongLiteral;

public interface IDecimalLongLiteralInitialiser extends ILongLiteralInitialiser {
    @Override
    public DecimalLongLiteral instantiate();

}

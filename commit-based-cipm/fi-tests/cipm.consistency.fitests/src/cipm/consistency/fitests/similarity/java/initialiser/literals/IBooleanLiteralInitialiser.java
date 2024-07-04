package cipm.consistency.fitests.similarity.java.initialiser.literals;

import org.emftext.language.java.literals.BooleanLiteral;

public interface IBooleanLiteralInitialiser extends ILiteralInitialiser {
    @Override
    public BooleanLiteral instantiate();

}
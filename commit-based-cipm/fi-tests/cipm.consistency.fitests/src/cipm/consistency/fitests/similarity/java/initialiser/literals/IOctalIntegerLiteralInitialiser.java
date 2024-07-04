package cipm.consistency.fitests.similarity.java.initialiser.literals;

import org.emftext.language.java.literals.OctalIntegerLiteral;

public interface IOctalIntegerLiteralInitialiser extends IIntegerLiteralInitialiser {
    @Override
    public OctalIntegerLiteral instantiate();

}

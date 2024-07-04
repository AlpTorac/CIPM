package cipm.consistency.fitests.similarity.java.initialiser.literals;

import org.emftext.language.java.literals.OctalLongLiteral;

public interface IOctalLongLiteralInitialiser extends ILongLiteralInitialiser {
    @Override
    public OctalLongLiteral instantiate();

}

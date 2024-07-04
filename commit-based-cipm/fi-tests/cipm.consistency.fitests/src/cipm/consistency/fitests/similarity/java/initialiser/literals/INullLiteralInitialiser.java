package cipm.consistency.fitests.similarity.java.initialiser.literals;

import org.emftext.language.java.literals.NullLiteral;

public interface INullLiteralInitialiser extends ILiteralInitialiser {
    @Override
    public NullLiteral instantiate();

}

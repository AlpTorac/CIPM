package cipm.consistency.fitests.similarity.java.initialiser.literals;

import org.emftext.language.java.literals.BinaryIntegerLiteral;

public interface IBinaryIntegerLiteralInitialiser extends IIntegerLiteralInitialiser {
    @Override
    public BinaryIntegerLiteral instantiate();

}

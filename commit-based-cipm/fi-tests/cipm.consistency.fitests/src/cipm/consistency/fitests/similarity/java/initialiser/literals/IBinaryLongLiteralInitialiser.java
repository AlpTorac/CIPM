package cipm.consistency.fitests.similarity.java.initialiser.literals;

import org.emftext.language.java.literals.BinaryLongLiteral;

public interface IBinaryLongLiteralInitialiser extends ILongLiteralInitialiser {
    @Override
    public BinaryLongLiteral instantiate();

}

package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.statements.DoWhileLoop;

public interface IDoWhileLoopInitialiser extends IWhileLoopInitialiser {
    @Override
    public DoWhileLoop instantiate();

}

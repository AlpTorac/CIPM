package cipm.consistency.fitests.similarity.java.initialiser.imports;

import org.emftext.language.java.imports.ClassifierImport;

public interface IClassifierImportInitialiser extends IImportInitialiser {
    @Override
    public ClassifierImport instantiate();

}

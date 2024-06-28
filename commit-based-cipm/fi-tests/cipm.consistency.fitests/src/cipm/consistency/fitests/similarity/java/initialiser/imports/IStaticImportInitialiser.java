package cipm.consistency.fitests.similarity.java.initialiser.imports;

import org.emftext.language.java.imports.StaticImport;
import org.emftext.language.java.modifiers.Static;

public interface IStaticImportInitialiser extends IImportInitialiser {
	public default void setStatic(StaticImport sImp, Static st) {
		if (st != null) {
			sImp.setStatic(st);
			assert sImp.getStatic().equals(st);
		}
	}
}

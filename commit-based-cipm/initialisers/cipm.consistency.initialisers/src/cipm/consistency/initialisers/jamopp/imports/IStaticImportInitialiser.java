package cipm.consistency.initialisers.jamopp.imports;

import org.emftext.language.java.imports.StaticImport;
import org.emftext.language.java.modifiers.Static;

public interface IStaticImportInitialiser extends IImportInitialiser {
	@Override
	public StaticImport instantiate();

	public default boolean setStatic(StaticImport sImp, Static st) {
		if (st != null) {
			sImp.setStatic(st);
			return sImp.getStatic().equals(st);
		}
		return true;
	}
}
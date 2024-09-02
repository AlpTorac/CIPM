package cipm.consistency.fitests.similarity.java.initialiser.imports;

import org.emftext.language.java.imports.StaticMemberImport;
import org.emftext.language.java.references.ReferenceableElement;

public interface IStaticMemberImportInitialiser extends IStaticImportInitialiser {
	@Override
	public StaticMemberImport instantiate();

	public default boolean addStaticMember(StaticMemberImport smi, ReferenceableElement staticMem) {
		if (staticMem != null) {
			smi.getStaticMembers().add(staticMem);
			return smi.getStaticMembers().contains(staticMem);
		}
		return true;
	}

	public default boolean addStaticMembers(StaticMemberImport smi, ReferenceableElement[] staticMems) {
		return this.doMultipleModifications(smi, staticMems, this::addStaticMember);
	}
}

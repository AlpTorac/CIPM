package cipm.consistency.fitests.similarity.java.initialiser.imports;

import org.emftext.language.java.imports.StaticMemberImport;
import org.emftext.language.java.references.ReferenceableElement;

import cipm.consistency.fitests.similarity.java.initialiser.testable.IStaticImportInitialiser;

public interface IStaticMemberImportInitialiser extends
	IStaticImportInitialiser {
	public default void addStaticMember(StaticMemberImport smi, ReferenceableElement re) {
		if (re != null) {
			smi.getStaticMembers().add(re);
			assert smi.getStaticMembers().contains(re);
		}
	}
	
	public default void addStaticMembers(StaticMemberImport smi, ReferenceableElement[] res) {
		this.addXs(smi, res, this::addStaticMember);
	}
}

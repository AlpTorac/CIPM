package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.imports.StaticMemberImport;
import org.emftext.language.java.references.ReferenceableElement;

public interface IStaticMemberImportInitialiser extends
	IStaticImportInitialiser {
	public default void addStaticMember(StaticMemberImport smi, ReferenceableElement re) {
		if (re != null) {
			smi.getStaticMembers().add(re);
			assert smi.getStaticMembers().contains(re);
		}
	}
}

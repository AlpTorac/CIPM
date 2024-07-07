package cipm.consistency.fitests.similarity.java.initialiser.imports;

import org.emftext.language.java.imports.StaticMemberImport;
import org.emftext.language.java.references.ReferenceableElement;

import cipm.consistency.fitests.similarity.java.initialiser.ModificationMethod;

public interface IStaticMemberImportInitialiser extends
	IStaticImportInitialiser {
	@ModificationMethod
	public default boolean addStaticMember(StaticMemberImport smi, ReferenceableElement re) {
		if (re != null) {
			smi.getStaticMembers().add(re);
			return smi.getStaticMembers().contains(re);
		}
		return true;
	}
	
	public default boolean addStaticMembers(StaticMemberImport smi, ReferenceableElement[] res) {
		return this.addXs(smi, res, this::addStaticMember);
	}
}

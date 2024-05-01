package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.modules.ProvidesModuleDirective;
import org.emftext.language.java.types.TypeReference;

public interface IProvidesModuleDirectiveInitialiser extends
	IModuleDirectiveInitialiser,
	ITypedElementInitialiser {
	
	public default void addServiceProvider(ProvidesModuleDirective pmd, TypeReference tref) {
		if (tref != null) {
			pmd.getServiceProviders().add(tref);
			assert pmd.getServiceProviders().contains(tref);
		}
	}
}

package cipm.consistency.fitests.similarity.java.initialiser.modules;

import org.emftext.language.java.modules.ProvidesModuleDirective;
import org.emftext.language.java.types.TypeReference;

import cipm.consistency.fitests.similarity.java.initialiser.IModuleDirectiveInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.ITypedElementInitialiser;

public interface IProvidesModuleDirectiveInitialiser extends
	IModuleDirectiveInitialiser,
	ITypedElementInitialiser {
	
	public default void addServiceProvider(ProvidesModuleDirective pmd, TypeReference tref) {
		if (tref != null) {
			pmd.getServiceProviders().add(tref);
			assert pmd.getServiceProviders().contains(tref);
		}
	}
	
	public default void addServiceProviders(ProvidesModuleDirective pmd, TypeReference[] trefs) {
		this.addXs(pmd, trefs, this::addServiceProvider);
	}
}

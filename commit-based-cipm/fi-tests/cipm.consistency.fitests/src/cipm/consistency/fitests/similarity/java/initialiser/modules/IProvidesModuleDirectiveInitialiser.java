package cipm.consistency.fitests.similarity.java.initialiser.modules;

import org.emftext.language.java.modules.ProvidesModuleDirective;
import org.emftext.language.java.types.TypeReference;

import cipm.consistency.fitests.similarity.java.initialiser.types.ITypedElementInitialiser;

public interface IProvidesModuleDirectiveInitialiser extends
	IModuleDirectiveInitialiser,
	ITypedElementInitialiser {
	@Override
	public ProvidesModuleDirective instantiate();
	
	public default boolean addServiceProvider(ProvidesModuleDirective pmd, TypeReference tref) {
		if (tref != null) {
			pmd.getServiceProviders().add(tref);
			return pmd.getServiceProviders().contains(tref);
		}
		return true;
	}
	
	public default boolean addServiceProviders(ProvidesModuleDirective pmd, TypeReference[] trefs) {
		return this.addXs(pmd, trefs, this::addServiceProvider);
	}
}

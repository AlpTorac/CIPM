package cipm.consistency.fluentapi.pcm.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.palladiosimulator.pcm.core.entity.EntityPackage;
import org.palladiosimulator.pcm.core.entity.ResourceRequiredRole;
import org.palladiosimulator.pcm.repository.RepositoryComponent;
import org.palladiosimulator.pcm.repository.RepositoryPackage;

import cipm.consistency.fluentapi.pcm.api.ApiFactory;

/**
 * 
 * TODO Add proper commentary
 * 
 * TODO See if you can address this within metamodel tests, since improper
 * initial values in metamodels should not mean that fluent API is failing.
 * 
 * <p>
 * The purpose of this test class is to show that many-valued feature
 * modifications (in PCM) are not problematic in realistic cases, although they
 * currently fail in metamodel tests.
 * 
 * @author Alp Torac Genc
 */
public class FluentAPIPcmManyValuedFeatureModificationTest {
	@Test
	public void init_WithAddedArray() {
		var api = ApiFactory.eINSTANCE.createFluentPcmAPI();
		var repo = api.newRepository()
				.withAddedComponents__Repository(
						new RepositoryComponent[] { api.createNewBasicComponent(), api.createNewBasicComponent() })
				.createNow();
		Assertions.assertEquals(2, repo.getComponents__Repository().size());
	}

	@Test
	public void superInit_WithAddedArray() {
		var api = ApiFactory.eINSTANCE.createFluentPcmAPI();
		var repo = api.createNewRepository();
		api.modifyRepository(repo).xWithAddedFeat(RepositoryPackage.Literals.REPOSITORY__COMPONENTS_REPOSITORY,
				new RepositoryComponent[] { api.createNewBasicComponent(), api.createNewBasicComponent() });
		Assertions.assertEquals(2, repo.getComponents__Repository().size());
	}

	@Test
	public void api_WithAddedArray() {
		var api = ApiFactory.eINSTANCE.createFluentPcmAPI();
		var repo = api.createNewRepository();
		api.xWithAddedFeat(repo, RepositoryPackage.Literals.REPOSITORY__COMPONENTS_REPOSITORY,
				new RepositoryComponent[] { api.createNewBasicComponent(), api.createNewBasicComponent() });
		Assertions.assertEquals(2, repo.getComponents__Repository().size());
	}

	@Test
	public void api_WithAddedArray_AfterModification() {
		var api = ApiFactory.eINSTANCE.createFluentPcmAPI();
		var repo = api.newRepository()
				.withAddedComponents__Repository(
						new RepositoryComponent[] { api.createNewBasicComponent(), api.createNewBasicComponent() })
				.createNow();

		api.xWithAddedFeat(repo, RepositoryPackage.Literals.REPOSITORY__COMPONENTS_REPOSITORY,
				new RepositoryComponent[] { api.createNewBasicComponent(), api.createNewBasicComponent() });
		Assertions.assertEquals(4, repo.getComponents__Repository().size());
	}

	/**
	 * This is a failing test scenario in metamodel tests, but it works here => The
	 * initial value of
	 * NewResourceInterfaceRequiringEntity.getResourceRequiredRoles__ResourceInterfaceRequiringEntity
	 * is not appropriate
	 */
	@Test
	public void api_WithAddedArray_Failing() {
		var api = ApiFactory.eINSTANCE.createFluentPcmAPI();
		var obj = api.createNewResourceInterfaceRequiringEntity();

		api.xWithAddedFeat(obj,
				EntityPackage.Literals.RESOURCE_INTERFACE_REQUIRING_ENTITY__RESOURCE_REQUIRED_ROLES_RESOURCE_INTERFACE_REQUIRING_ENTITY,
				new ResourceRequiredRole[] { api.createNewResourceRequiredRole(),
						api.createNewResourceRequiredRole() });
		Assertions.assertEquals(2, obj.getResourceRequiredRoles__ResourceInterfaceRequiringEntity().size());
	}

	/**
	 * This is a failing test scenario in metamodel tests, but it works here => The
	 * initial value of
	 * NewResourceInterfaceRequiringEntity.getResourceRequiredRoles__ResourceInterfaceRequiringEntity
	 * is not appropriate
	 */
	@Test
	public void superInit_WithAddedArray_Failing() {
		var api = ApiFactory.eINSTANCE.createFluentPcmAPI();
		var obj = api.createNewResourceInterfaceRequiringEntity();

		api.modifyResourceInterfaceRequiringEntity(obj).xWithAddedFeat(
				EntityPackage.Literals.RESOURCE_INTERFACE_REQUIRING_ENTITY__RESOURCE_REQUIRED_ROLES_RESOURCE_INTERFACE_REQUIRING_ENTITY,
				new ResourceRequiredRole[] { api.createNewResourceRequiredRole(),
						api.createNewResourceRequiredRole() });
		Assertions.assertEquals(2, obj.getResourceRequiredRoles__ResourceInterfaceRequiringEntity().size());
	}
}

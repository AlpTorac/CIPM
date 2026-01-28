package cipm.consistency.fluentapi.test;

import org.eclipse.emf.ecore.EObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.api.ApiFactory;
import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelFeatureFilter;
import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelPackageProvider;
import cipm.consistency.fluentapi.gen.metamodels.java.FluentAPIJavaMetamodelFeatureFilter;
import cipm.consistency.fluentapi.gen.metamodels.java.FluentAPIJavaMetamodelPackageProvider;

public class FluentAPIMetamodelCoverageTest {
	private static final FluentAPITargetMetamodelFeatureFilter featureFilter = new FluentAPIJavaMetamodelFeatureFilter();
	private static final FluentAPITargetMetamodelPackageProvider metamodelProvider = new FluentAPIJavaMetamodelPackageProvider();

	/**
	 * Ensures that each concrete class within the target metamodel is addressed by
	 * top-level methods that are not meant for a specific type (i.e. api.metX()
	 * methods).
	 */
	@Test
	public void concreteElementCoverageTest_TopLevelMethods() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var allConcreteEClss = metamodelProvider.getAllTargetMetamodelConcreteEClasses();
		for (var eCls : allConcreteEClss) {
			Assertions.assertInstanceOf(eCls.getInstanceClass(), api.newX(eCls).createNow());
			Assertions.assertInstanceOf(eCls.getInstanceClass(), api.newX(eCls.getInstanceClass()).createNow());
			Assertions.assertInstanceOf(eCls.getInstanceClass(), api.createNewX(eCls.getInstanceClass()));
			Assertions.assertInstanceOf(eCls.getInstanceClass(),
					api.modifyX((EObject) api.createNewX(eCls.getInstanceClass())).createNow());
		}
	}

	/**
	 * Ensures that each concrete class within the target metamodel has its own
	 * XInitialisation class.
	 */
	@Test
	public void concreteElementCoverageTest_XInitialisation() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var allConcreteEClss = metamodelProvider.getAllTargetMetamodelConcreteEClasses();
		for (var eCls : allConcreteEClss) {
			Assertions.assertEquals(eCls, api.newX(eCls).getInitialisedEClass());
		}
	}

	/**
	 * Ensures that each concrete class within the target metamodel can be modified
	 * via the api.modifyX() method
	 */
	@Test
	public void concreteElementCoverageTest_ModifyMethod() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var allConcreteEClss = metamodelProvider.getAllTargetMetamodelConcreteEClasses();
		for (var eCls : allConcreteEClss) {
			var init = api.newX(eCls);
			var instance = init.createNow();
			Assertions.assertInstanceOf(init.getClass(), api.modifyX(instance));
			Assertions.assertEquals(instance, api.modifyX(instance).createNow());
		}
	}

	@Test
	public void concreteElementCoverageTest_ContinueMethod() {

		// TODO Remove continueNewest method (since it is the same as continue method)
		// TODO Remove continueOldest method (see below)
		// TODO Remove continueFromStart method (see below)
		// TODO Remove continueFromEnd method (see below)

		// TODO Add superInit.getNextInit(int) method (replaces fromStart, fromEnd)
		// TODO Add superInit.getPrevInit(int) method (replaces fromStart, fromEnd)
		// TODO Add init.getNextInit(int) method (replaces fromStart, fromEnd)
		// TODO Add init.getPrevInit(int) method (replaces fromStart, fromEnd)

		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var allConcreteEClss = metamodelProvider.getAllTargetMetamodelConcreteEClasses();
		for (var eCls : allConcreteEClss) {
			var init = api.newX(eCls);
			Assertions.assertSame(init, api.continueX(eCls.getInstanceClass()));
		}
	}

	/**
	 * Ensures that each modifiable feature of each concrete class within the target
	 * metamodel can be modified via the api.
	 */
	@Test
	public void concreteElementCoverageTest_WithFeatureMethods() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var allConcreteEClss = metamodelProvider.getAllTargetMetamodelConcreteEClasses();
		for (var eCls : allConcreteEClss) {
			var instance = (EObject) api.createNewX(eCls.getInstanceClass());
			for (var feat : featureFilter.getModifiableFeatures(eCls)) {
				if (feat.isMany()) {
					api.xWithAddedFeat(instance, feat, instance.eGet(feat));
					api.xWithRemovedFeat(instance, feat, instance.eGet(feat));
					api.xWithExactFeat(instance, feat, instance.eGet(feat));
				} else {
					api.xWithFeat(instance, feat, instance.eGet(feat));
					api.xWithoutFeat(instance, feat);
				}
				if (featureFilter.canShareFeatureWithContainer(metamodelProvider, eCls, feat)) {
					api.xWithFeatOfContainer(instance, feat);
				}
			}
		}
	}

	/**
	 * Ensures that marking and marking-related methods are enabled for each
	 * concrete class of the target metamodel
	 */
	@Test
	public void concreteElementCoverageTest_MarkMethods() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var allConcreteEClss = metamodelProvider.getAllTargetMetamodelConcreteEClasses();
		for (var eCls : allConcreteEClss) {
			var key = new Object();
			var init = api.newX(eCls.getInstanceClass()).markCurrent(key);
			var instance = init.getCurrentElement();
			Assertions.assertInstanceOf(eCls.getInstanceClass(), instance);

			Assertions.assertSame(instance, api.getMarked(key));
			Assertions.assertSame(init, api.continueMarkedX(key));
			Assertions.assertSame(instance, api.continueMarkedX(key).createNow());
			Assertions.assertInstanceOf(init.getClass(), api.modifyMarkedX(key));
			Assertions.assertSame(instance, api.modifyMarkedX(key).createNow());
		}
	}
}

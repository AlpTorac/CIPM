package cipm.consistency.fluentapi.test.metamodel;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.api.ApiFactory;
import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelFeatureFilter;
import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelPackageProvider;
import cipm.consistency.fluentapi.gen.metamodels.java.FluentAPIJavaMetamodelFeatureFilter;
import cipm.consistency.fluentapi.gen.metamodels.java.FluentAPIJavaMetamodelPackageProvider;
import cipm.consistency.fluentapi.test.AbstractFluentAPITest;

public class FluentAPIMetamodelCoverageTest extends AbstractFluentAPITest {
	private static final FluentAPITargetMetamodelFeatureFilter featureFilter = new FluentAPIJavaMetamodelFeatureFilter();
	private static final FluentAPITargetMetamodelPackageProvider metamodelProvider = new FluentAPIJavaMetamodelPackageProvider();

	/**
	 * Ensures that each concrete class within the target metamodel can be
	 * instantiated via api.newX().createNow().
	 */
	@Test
	public void concreteElementCoverageTest_API_NewX() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var allConcreteEClss = metamodelProvider.getAllTargetMetamodelConcreteEClasses();
		for (var eCls : allConcreteEClss) {
			Assertions.assertInstanceOf(eCls.getInstanceClass(), api.newX(eCls).createNow());
			Assertions.assertInstanceOf(eCls.getInstanceClass(), api.newX(eCls.getInstanceClass()).createNow());
		}
	}

	/**
	 * Ensures that each concrete class within the target metamodel can be
	 * instantiated via api.createNewX().
	 */
	@Test
	public void concreteElementCoverageTest_API_CreateNewX() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var allConcreteEClss = metamodelProvider.getAllTargetMetamodelConcreteEClasses();
		for (var eCls : allConcreteEClss) {
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
	public void concreteElementCoverageTest_API_XInitialisationExistence() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var allConcreteEClss = metamodelProvider.getAllTargetMetamodelConcreteEClasses();
		for (var eCls : allConcreteEClss) {
			var initViaClass = api.getInitialisationForX(eCls.getInstanceClass());
			var initViaEClass = api.getInitialisationForX(eCls);
			var initViaObj = api.getInitialisationForX(eCls.getEPackage().getEFactoryInstance().create(eCls));
			Assertions.assertEquals(eCls, initViaClass.getInitialisedEClass());
			Assertions.assertEquals(eCls, initViaEClass.getInitialisedEClass());
			Assertions.assertEquals(eCls, initViaObj.getInitialisedEClass());

			Assertions.assertEquals(initViaEClass.getClass(), initViaClass.getClass());
			Assertions.assertEquals(initViaEClass.getClass(), initViaObj.getClass());
		}
	}

	/**
	 * Ensures that each concrete class within the target metamodel can be modified
	 * via the api.modifyX() method
	 */
	@Test
	public void concreteElementCoverageTest_API_ModifyX() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var allConcreteEClss = metamodelProvider.getAllTargetMetamodelConcreteEClasses();
		for (var eCls : allConcreteEClss) {
			var init = api.newX(eCls);
			var instance = init.createNow();
			Assertions.assertInstanceOf(init.getClass(), api.modifyX(instance));
			Assertions.assertEquals(instance, api.modifyX(instance).createNow());
		}
	}

	/**
	 * Ensures that each concrete class within the target metamodel may have its
	 * construction be continued via the api.continueX method.
	 */
	@Test
	public void concreteElementCoverageTest_API_ContinueX() {
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
	public void concreteElementCoverageTest_API_xFeature() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var allConcreteEClss = metamodelProvider.getAllTargetMetamodelConcreteEClasses();
		for (var eCls : allConcreteEClss) {
			var instance = (EObject) api.createNewX(eCls.getInstanceClass());
			for (var feat : featureFilter.getModifiableFeatures(eCls)) {
				if (feat.isMany()) {
					api.xWithAddedFeat(instance, feat, instance.eGet(feat));
					api.xWithAddedFeat(instance, feat, ((EList<?>) instance.eGet(feat)).toArray());
					api.xWithAddedFeat(instance, feat, List.copyOf(((EList<?>) instance.eGet(feat))));

					api.xWithRemovedFeat(instance, feat, instance.eGet(feat));
					api.xWithRemovedFeat(instance, feat, ((EList<?>) instance.eGet(feat)).toArray());
					api.xWithRemovedFeat(instance, feat, List.copyOf(((EList<?>) instance.eGet(feat))));

					api.xCleanFeat(instance, feat);
				} else {
					api.xWithFeat(instance, feat, instance.eGet(feat));
					api.xWithoutFeat(instance, feat);
				}
			}
		}
	}

	/**
	 * Ensures that marking and marking-related methods are enabled for each
	 * concrete class of the target metamodel
	 */
	@Test
	public void concreteElementCoverageTest_API_Mark() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var allConcreteEClss = metamodelProvider.getAllTargetMetamodelConcreteEClasses();
		for (var eCls : allConcreteEClss) {
			var keyAPI = new Object();

			var instance = eCls.getEPackage().getEFactoryInstance().create(eCls);
			Assertions.assertInstanceOf(eCls.getInstanceClass(), instance);

			api.mark(keyAPI, instance);
			Assertions.assertSame(instance, api.getMarked(keyAPI));

			api.unmark(keyAPI);
			Assertions.assertNull(api.getMarked(keyAPI));

			api.mark(keyAPI, instance);
			Assertions.assertSame(instance, api.getMarked(keyAPI));

			api.unmark(keyAPI, instance);
			Assertions.assertNull(api.getMarked(keyAPI));
		}
	}

	/**
	 * Ensures that marking and marking-related methods are enabled for each
	 * concrete class of the target metamodel
	 */
	@Test
	public void concreteElementCoverageTest_API_ModifyMarkedX() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var allConcreteEClss = metamodelProvider.getAllTargetMetamodelConcreteEClasses();
		for (var eCls : allConcreteEClss) {
			var keyAPI = new Object();

			var instance = eCls.getEPackage().getEFactoryInstance().create(eCls);
			Assertions.assertInstanceOf(eCls.getInstanceClass(), instance);

			api.mark(keyAPI, instance);

			// modifyMarkedX call creates a new initialisation instance
			var modMarkedInit = api.modifyMarkedX(keyAPI);
			Assertions.assertSame(instance, modMarkedInit.createNow());
		}
	}

	/**
	 * Ensures that marking and marking-related methods are enabled for each
	 * concrete class of the target metamodel
	 */
	@Test
	public void concreteElementCoverageTest_API_ContinueMarkedX() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var allConcreteEClss = metamodelProvider.getAllTargetMetamodelConcreteEClasses();
		for (var eCls : allConcreteEClss) {
			var keyAPI = new Object();
			var init = api.newX(eCls);
			var instance = init.getCurrentElement();

			api.mark(keyAPI, instance);

			Assertions.assertSame(init, api.continueMarkedX(keyAPI));
			Assertions.assertSame(instance, init.createNow());
		}
	}

	/**
	 * Ensures that each modifiable feature of each concrete class within the target
	 * metamodel can be modified via the superInit.
	 */
	@Test
	public void concreteElementCoverageTest_SuperInit_xFeature() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var allConcreteEClss = metamodelProvider.getAllTargetMetamodelConcreteEClasses();
		for (var eCls : allConcreteEClss) {
			var instance = (EObject) api.createNewX(eCls.getInstanceClass());
			for (var feat : featureFilter.getModifiableFeatures(eCls)) {
				if (feat.isMany()) {
					api.modifyX(instance).xWithAddedFeat(feat, instance.eGet(feat));
					api.modifyX(instance).xWithAddedFeat(feat, ((EList<?>) instance.eGet(feat)).toArray());
					api.modifyX(instance).xWithAddedFeat(feat, List.copyOf(((EList<?>) instance.eGet(feat))));

					api.modifyX(instance).xWithRemovedFeat(feat, instance.eGet(feat));
					api.modifyX(instance).xWithRemovedFeat(feat, ((EList<?>) instance.eGet(feat)).toArray());
					api.modifyX(instance).xWithRemovedFeat(feat, List.copyOf(((EList<?>) instance.eGet(feat))));

					api.modifyX(instance).xCleanFeat(feat);
				} else {
					api.modifyX(instance).xWithFeat(feat, instance.eGet(feat));
					api.modifyX(instance).xWithoutFeat(feat);
				}
			}
		}
	}

	/**
	 * Ensures that marking and marking-related methods are enabled for each
	 * concrete class of the target metamodel
	 */
	@Test
	public void concreteElementCoverageTest_SuperInit_MarkX() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var allConcreteEClss = metamodelProvider.getAllTargetMetamodelConcreteEClasses();
		for (var eCls : allConcreteEClss) {
			var keySuperInit = new Object();

			var init = api.newX(eCls);
			var instance = init.getCurrentElement();

			init.mark(keySuperInit);
			Assertions.assertSame(instance, api.getMarked(keySuperInit));

			init.unmark(keySuperInit);
			Assertions.assertNull(api.getMarked(keySuperInit));
		}
	}
}

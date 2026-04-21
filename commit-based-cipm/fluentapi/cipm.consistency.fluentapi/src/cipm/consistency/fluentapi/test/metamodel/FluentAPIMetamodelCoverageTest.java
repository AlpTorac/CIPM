package cipm.consistency.fluentapi.test.metamodel;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import cipm.consistency.fluentapi.pcm.api.ApiFactory;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelFeatureFilter;
import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelPackageProvider;
import cipm.consistency.fluentapi.gen.metamodels.MetamodelUtil;
import cipm.consistency.fluentapi.gen.metamodels.java.FluentAPIJavaMetamodelFeatureFilter;
import cipm.consistency.fluentapi.gen.metamodels.java.FluentAPIJavaMetamodelPackageProvider;
import cipm.consistency.fluentapi.gen.metamodels.pcm.FluentAPIPcmMetamodelFeatureFilter;
import cipm.consistency.fluentapi.gen.metamodels.pcm.FluentAPIPcmMetamodelPackageProvider;
import cipm.consistency.fluentapi.test.AbstractFluentAPITest;

public class FluentAPIMetamodelCoverageTest extends AbstractFluentAPITest {
	private static final FluentAPITargetMetamodelPackageProvider pcmProvider = new FluentAPIPcmMetamodelPackageProvider();
	private static final FluentAPITargetMetamodelFeatureFilter pcmFilter = new FluentAPIPcmMetamodelFeatureFilter();
	private static final FluentAPITargetMetamodelPackageProvider javaProvider = new FluentAPIJavaMetamodelPackageProvider();
	private static final FluentAPITargetMetamodelFeatureFilter javaFilter = new FluentAPIJavaMetamodelFeatureFilter();

	private static final Object[] emptyArr = new Object[0];

	private static Stream<Arguments> genArgs() {
		return Stream.of(
				Arguments.of(pcmProvider, pcmFilter,
						cipm.consistency.fluentapi.pcm.api.ApiFactory.eINSTANCE.createFluentPcmAPI()),
				Arguments.of(javaProvider, javaFilter,
						cipm.consistency.fluentapi.java.api.ApiFactory.eINSTANCE.createFluentJavaAPI()));
	}

	private Object invokeMethod(Object eobj, String metName, Object[] args) {
		var paramCount = args == null ? 0 : args.length;

		var mets = List.of(List.of(eobj.getClass().getDeclaredMethods()).stream()
				.filter((m) -> m.getParameterCount() == paramCount).filter((m) -> m.getName().equals(metName))
				.toArray(Method[]::new));

		if (mets.isEmpty())
			throw new IllegalArgumentException("Method is not declared in the given api object");

		var met = mets.size() == 1 ? mets.get(0) : mets.stream().filter((m) -> {
			if (paramCount == 0)
				return true;
			var paramTypes = m.getParameterTypes();
			for (int i = 0; i < paramTypes.length; i++) {
				var arg = args[i];
				var pt = paramTypes[i];
				if (arg != null && !pt.isAssignableFrom(arg.getClass()))
					return false;
			}
			return true;
		}).findFirst().get();

		Object result = null;
		try {
			result = met.invoke(eobj, args);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			Assertions.fail(e);
		}
		return result;
	}

	private Object invokeMethods(Object eobj, List<String> metNames, List<Object[]> params) {
		if (metNames.size() != params.size())
			throw new IllegalArgumentException(
					"Each method in the chain must get parameters, even if it uses no parameters");
		Object result = eobj;
		for (int i = 0; i < metNames.size(); i++) {
			result = invokeMethod(result, metNames.get(i), params.get(i));
		}
		return result;
	}

	/**
	 * Ensures that each concrete class within the target metamodel can be
	 * instantiated via api.newX().createNow().
	 */
	@MethodSource("genArgs")
	@ParameterizedTest
	public void concreteElementCoverageTest_API_NewX(FluentAPITargetMetamodelPackageProvider metamodelProvider,
			FluentAPITargetMetamodelFeatureFilter featureFilter, EObject api) {
		var allConcreteEClss = metamodelProvider.getAllConcreteEClassedInOriginalMetamodel();
		for (var eCls : allConcreteEClss) {
			Assertions.assertInstanceOf(eCls.getInstanceClass(),
					invokeMethods(api, List.of("newX", "createNow"), List.of(new Object[] { eCls }, emptyArr)));
			Assertions.assertInstanceOf(eCls.getInstanceClass(), invokeMethods(api, List.of("newX", "createNow"),
					List.of(new Object[] { eCls.getInstanceClass() }, emptyArr)));
		}
	}

	/**
	 * Ensures that each concrete class within the target metamodel can be
	 * instantiated via api.createNewX().
	 */
	@MethodSource("genArgs")
	@ParameterizedTest
	public void concreteElementCoverageTest_API_CreateNewX(FluentAPITargetMetamodelPackageProvider metamodelProvider,
			FluentAPITargetMetamodelFeatureFilter featureFilter, EObject api) {
		var allConcreteEClss = metamodelProvider.getAllConcreteEClassedInOriginalMetamodel();
		for (var eCls : allConcreteEClss) {
			var instance = invokeMethod(api, "createNewX", new Object[] { eCls.getInstanceClass() });
			Assertions.assertInstanceOf(eCls.getInstanceClass(), instance);
			Assertions.assertInstanceOf(eCls.getInstanceClass(),
					invokeMethods(api, List.of("modifyX", "createNow"), List.of(new Object[] { instance }, emptyArr)));
		}
	}

	/**
	 * Ensures that each concrete class within the target metamodel has its own
	 * XInitialisation class.
	 */
	@MethodSource("genArgs")
	@ParameterizedTest
	public void concreteElementCoverageTest_API_XInitialisationExistence(
			FluentAPITargetMetamodelPackageProvider metamodelProvider,
			FluentAPITargetMetamodelFeatureFilter featureFilter, EObject api) {
		var allConcreteEClss = metamodelProvider.getAllConcreteEClassedInOriginalMetamodel();
		for (var eCls : allConcreteEClss) {

			var initViaClass = invokeMethod(api, "getInitialisationForX", new Object[] { eCls.getInstanceClass() });
			var initViaEClass = invokeMethod(api, "getInitialisationForX", new Object[] { eCls });
			var initViaObj = invokeMethod(api, "getInitialisationForX",
					new Object[] { eCls.getEPackage().getEFactoryInstance().create(eCls) });

			Assertions.assertEquals(eCls, invokeMethod(initViaClass, "getInitialisedEClass", emptyArr));
			Assertions.assertEquals(eCls, invokeMethod(initViaEClass, "getInitialisedEClass", emptyArr));
			Assertions.assertEquals(eCls, invokeMethod(initViaObj, "getInitialisedEClass", emptyArr));

			Assertions.assertEquals(initViaEClass.getClass(), initViaClass.getClass());
			Assertions.assertEquals(initViaEClass.getClass(), initViaObj.getClass());
		}
	}

	/**
	 * Ensures that each concrete class within the target metamodel can be modified
	 * via the api.modifyX() method
	 */
	@MethodSource("genArgs")
	@ParameterizedTest
	public void concreteElementCoverageTest_API_ModifyX(FluentAPITargetMetamodelPackageProvider metamodelProvider,
			FluentAPITargetMetamodelFeatureFilter featureFilter, EObject api) {
		var allConcreteEClss = metamodelProvider.getAllConcreteEClassedInOriginalMetamodel();
		for (var eCls : allConcreteEClss) {
			var init = invokeMethod(api, "newX", new Object[] { eCls });
			var instance = invokeMethod(init, "createNow", emptyArr);
			Assertions.assertInstanceOf(init.getClass(), invokeMethod(api, "modifyX", new Object[] { instance }));
			Assertions.assertEquals(instance,
					invokeMethods(api, List.of("modifyX", "createNow"), List.of(new Object[] { instance }, emptyArr)));
		}
	}

	/**
	 * Ensures that each concrete class within the target metamodel may have its
	 * construction be continued via the api.continueX method.
	 */
	@MethodSource("genArgs")
	@ParameterizedTest
	public void concreteElementCoverageTest_API_ContinueX(FluentAPITargetMetamodelPackageProvider metamodelProvider,
			FluentAPITargetMetamodelFeatureFilter featureFilter, EObject api) {
		var allConcreteEClss = metamodelProvider.getAllConcreteEClassedInOriginalMetamodel();
		for (var eCls : allConcreteEClss) {
			var init = invokeMethod(api, "newX", new Object[] { eCls });
			Assertions.assertSame(init, invokeMethod(api, "continueX", new Object[] { eCls.getInstanceClass() }));
		}
	}

//	TODO Clean up
//	
//	private EClass getConcreteEClassFor(Collection<EClass> availableEClss, EClass eCls) {
//		if (eCls instanceof EClass && FluentAPIGenerationUtil.isConcrete((EClass) eCls))
//			return eCls;
//		Predicate<EClass> pred = (cls) -> FluentAPIGenerationUtil.isConcrete(cls) && eCls.isSuperTypeOf(cls);
//		var concreteECls = availableEClss.stream().filter(pred).findFirst().orElse(null);
//
//		if (concreteECls == null && eCls.getEPackage() != null) {
//			var pac = eCls.getEPackage();
//			while (pac.getESuperPackage() != null)
//				pac = pac.getESuperPackage();
//
//			concreteECls = MetamodelUtil.getAllEClasses(pac).stream().filter(pred).findFirst().orElse(null);
//		}
//
//		return concreteECls;
//	}
//
//	private Object getSampleParameterFor(EObject instance, EStructuralFeature feat, Collection<EClass> availableEClss) {
//		Object sample = null;
//
//		if (feat instanceof EReference) {
//			var cls = getConcreteEClassFor(availableEClss, (EClass) feat.getEType());
//			sample = cls.getEPackage().getEFactoryInstance().create(cls);
//		} else {
//			sample = EcorePackage.eINSTANCE.getEFactoryInstance().create((EClass) feat.getEType());
//		}
//
//		if (feat.isMany()) {
//			var list = new BasicEList<>();
//			list.add(sample);
//			return list;
//		} else {
//			return sample;
//		}
//	}
//
//	/**
//	 * Ensures that each modifiable feature of each concrete class within the target
//	 * metamodel can be modified via the api.
//	 */
//	@MethodSource("genArgs")
//	@ParameterizedTest
//	public void concreteElementCoverageTest_API_xAddedFeature(FluentAPITargetMetamodelPackageProvider metamodelProvider,
//			FluentAPITargetMetamodelFeatureFilter featureFilter, EObject api) {
//		var allConcreteEClss = metamodelProvider.getAllConcreteEClassedInOriginalMetamodel();
//		for (var eCls : allConcreteEClss) {
//			var instance = (EObject) invokeMethod(api, "createNewX", new Object[] { eCls.getInstanceClass() });
//			for (var feat : featureFilter.getModifiableFeatures(eCls).stream().filter((f) -> f.isMany())
//					.collect(Collectors.toList())) {
//				invokeMethod(api, "xWithAddedFeat", new Object[] { instance, feat, instance.eGet(feat) });
//				Assertions.assertTrue(((EList<?>) instance.eGet(feat)).containsAll((EList<?>) sampleAlone));
//
//				var sampleAsArray = Array.newInstance(feat.getEType().getInstanceClass(), 1);
//				var sampleAsArrayCmp = ((EList<?>) getSampleParameterFor(instance, feat, allConcreteEClss)).get(0);
//				Array.set(sampleAsArray, 0, sampleAsArrayCmp);
//				invokeMethod(api, "xWithAddedFeat", new Object[] { instance, feat, sampleAsArray });
//				Assertions.assertTrue(((EList<?>) instance.eGet(feat)).contains(sampleAsArrayCmp));
//
//				var sampleAsList = List.copyOf(((EList<?>) getSampleParameterFor(instance, feat, allConcreteEClss)));
//				invokeMethod(api, "xWithAddedFeat", new Object[] { instance, feat, sampleAsList });
//				Assertions.assertTrue(((EList<?>) instance.eGet(feat)).containsAll(sampleAsList));
//			}
//		}
//	}

	/**
	 * Ensures that each modifiable feature of each concrete class within the target
	 * metamodel can be modified via the api.
	 */
	@MethodSource("genArgs")
	@ParameterizedTest
	public void concreteElementCoverageTest_API_xFeature(FluentAPITargetMetamodelPackageProvider metamodelProvider,
			FluentAPITargetMetamodelFeatureFilter featureFilter, EObject api) {
		var allConcreteEClss = metamodelProvider.getAllConcreteEClassedInOriginalMetamodel();
		for (var eCls : allConcreteEClss) {
			var instance = (EObject) invokeMethod(api, "createNewX", new Object[] { eCls.getInstanceClass() });
			for (var feat : featureFilter.getModifiableFeatures(eCls)) {
				if (feat.isMany()) {
					invokeMethod(api, "xWithAddedFeat", new Object[] { instance, feat, instance.eGet(feat) });
					invokeMethod(api, "xWithAddedFeat",
							new Object[] { instance, feat, ((EList<?>) instance.eGet(feat)).toArray() });
					invokeMethod(api, "xWithAddedFeat",
							new Object[] { instance, feat, List.copyOf(((EList<?>) instance.eGet(feat))) });

					invokeMethod(api, "xWithRemovedFeat", new Object[] { instance, feat, instance.eGet(feat) });
					invokeMethod(api, "xWithRemovedFeat",
							new Object[] { instance, feat, ((EList<?>) instance.eGet(feat)).toArray() });
					invokeMethod(api, "xWithRemovedFeat",
							new Object[] { instance, feat, List.copyOf(((EList<?>) instance.eGet(feat))) });

					invokeMethod(api, "xCleanFeat", new Object[] { instance, feat });
				} else {
					invokeMethod(api, "xWithFeat", new Object[] { instance, feat, instance.eGet(feat) });
					invokeMethod(api, "xWithoutFeat", new Object[] { instance, feat });
				}
			}
		}
	}

	/**
	 * Ensures that marking and marking-related methods are enabled for each
	 * concrete class of the target metamodel
	 */
	@MethodSource("genArgs")
	@ParameterizedTest
	public void concreteElementCoverageTest_API_Mark(FluentAPITargetMetamodelPackageProvider metamodelProvider,
			FluentAPITargetMetamodelFeatureFilter featureFilter, EObject api) {
		var allConcreteEClss = metamodelProvider.getAllConcreteEClassedInOriginalMetamodel();
		for (var eCls : allConcreteEClss) {
			var keyAPI = new Object();

			var instance = eCls.getEPackage().getEFactoryInstance().create(eCls);
			Assertions.assertInstanceOf(eCls.getInstanceClass(), instance);

			invokeMethod(api, "mark", new Object[] { keyAPI, instance });
			Assertions.assertSame(instance, invokeMethod(api, "getMarkedX", new Object[] { keyAPI }));

			invokeMethod(api, "unmark", new Object[] { keyAPI });
			Assertions.assertNull(invokeMethod(api, "getMarkedX", new Object[] { keyAPI }));

			invokeMethod(api, "mark", new Object[] { keyAPI, instance });
			Assertions.assertSame(instance, invokeMethod(api, "getMarkedX", new Object[] { keyAPI }));

			invokeMethod(api, "unmark", new Object[] { keyAPI, instance });
			Assertions.assertNull(invokeMethod(api, "getMarkedX", new Object[] { keyAPI }));
		}
	}

	/**
	 * Ensures that marking and marking-related methods are enabled for each
	 * concrete class of the target metamodel
	 */
	@MethodSource("genArgs")
	@ParameterizedTest
	public void concreteElementCoverageTest_API_ModifyMarkedX(FluentAPITargetMetamodelPackageProvider metamodelProvider,
			FluentAPITargetMetamodelFeatureFilter featureFilter, EObject api) {
		var allConcreteEClss = metamodelProvider.getAllConcreteEClassedInOriginalMetamodel();
		for (var eCls : allConcreteEClss) {
			var keyAPI = new Object();

			var instance = eCls.getEPackage().getEFactoryInstance().create(eCls);
			Assertions.assertInstanceOf(eCls.getInstanceClass(), instance);

			invokeMethod(api, "mark", new Object[] { keyAPI, instance });

			// modifyMarkedX call creates a new initialisation instance

			var modMarkedInit = invokeMethod(api, "modifyMarkedX", new Object[] { keyAPI });
			Assertions.assertSame(instance, invokeMethod(modMarkedInit, "createNow", emptyArr));
		}
	}

	/**
	 * Ensures that marking and marking-related methods are enabled for each
	 * concrete class of the target metamodel
	 */
	@MethodSource("genArgs")
	@ParameterizedTest
	public void concreteElementCoverageTest_API_ContinueMarkedX(
			FluentAPITargetMetamodelPackageProvider metamodelProvider,
			FluentAPITargetMetamodelFeatureFilter featureFilter, EObject api) {
		var allConcreteEClss = metamodelProvider.getAllConcreteEClassedInOriginalMetamodel();
		for (var eCls : allConcreteEClss) {
			var keyAPI = new Object();
			var init = invokeMethod(api, "newX", new Object[] { eCls });
			var instance = invokeMethod(init, "getCurrentElement", emptyArr);

			invokeMethod(api, "mark", new Object[] { keyAPI, instance });

			Assertions.assertSame(init, invokeMethod(api, "continueMarkedX", new Object[] { keyAPI }));
			Assertions.assertSame(instance, invokeMethod(init, "createNow", emptyArr));
		}
	}

	/**
	 * Ensures that each modifiable feature of each concrete class within the target
	 * metamodel can be modified via the superInit.
	 */
	@MethodSource("genArgs")
	@ParameterizedTest
	public void concreteElementCoverageTest_SuperInit_xFeature(
			FluentAPITargetMetamodelPackageProvider metamodelProvider,
			FluentAPITargetMetamodelFeatureFilter featureFilter, EObject api) {
		var allConcreteEClss = metamodelProvider.getAllConcreteEClassedInOriginalMetamodel();
		for (var eCls : allConcreteEClss) {
			var instance = (EObject) invokeMethod(api, "createNewX", new Object[] { eCls.getInstanceClass() });
			for (var feat : featureFilter.getModifiableFeatures(eCls)) {
				if (feat.isMany()) {
					invokeMethods(api, List.of("modifyX", "xWithAddedFeat"),
							List.of(new Object[] { instance }, new Object[] { instance, feat, instance.eGet(feat) }));
					invokeMethods(api, List.of("modifyX", "xWithAddedFeat"), List.of(new Object[] { instance },
							new Object[] { instance, feat, ((EList<?>) instance.eGet(feat)).toArray() }));
					invokeMethods(api, List.of("modifyX", "xWithAddedFeat"), List.of(new Object[] { instance },
							new Object[] { instance, feat, List.copyOf(((EList<?>) instance.eGet(feat))) }));

					invokeMethods(api, List.of("modifyX", "xWithRemovedFeat"),
							List.of(new Object[] { instance }, new Object[] { instance, feat, instance.eGet(feat) }));
					invokeMethods(api, List.of("modifyX", "xWithRemovedFeat"), List.of(new Object[] { instance },
							new Object[] { instance, feat, ((EList<?>) instance.eGet(feat)).toArray() }));
					invokeMethods(api, List.of("modifyX", "xWithRemovedFeat"), List.of(new Object[] { instance },
							new Object[] { instance, feat, List.copyOf(((EList<?>) instance.eGet(feat))) }));

					invokeMethods(api, List.of("modifyX", "xCleanFeat"),
							List.of(new Object[] { instance }, new Object[] { instance, feat }));
				} else {
					invokeMethods(api, List.of("modifyX", "xWithFeat"),
							List.of(new Object[] { instance }, new Object[] { instance, feat, instance.eGet(feat) }));
					invokeMethods(api, List.of("modifyX", "xWithoutFeat"),
							List.of(new Object[] { instance }, new Object[] { instance, feat }));
				}
			}
		}
	}

	/**
	 * Ensures that marking and marking-related methods are enabled for each
	 * concrete class of the target metamodel
	 */
	@MethodSource("genArgs")
	@ParameterizedTest
	public void concreteElementCoverageTest_SuperInit_MarkX(FluentAPITargetMetamodelPackageProvider metamodelProvider,
			FluentAPITargetMetamodelFeatureFilter featureFilter, EObject api) {
		var allConcreteEClss = metamodelProvider.getAllConcreteEClassedInOriginalMetamodel();
		for (var eCls : allConcreteEClss) {
			var keySuperInit = new Object();

			var init = invokeMethod(api, "newX", new Object[] { eCls });
			var instance = invokeMethod(init, "getCurrentElement", emptyArr);

			invokeMethod(init, "mark", new Object[] { keySuperInit });
			Assertions.assertSame(instance, invokeMethod(api, "getMarkedX", new Object[] { keySuperInit }));

			invokeMethod(init, "unmark", new Object[] { keySuperInit });
			Assertions.assertNull(invokeMethod(api, "getMarkedX", new Object[] { keySuperInit }));
		}
	}
}

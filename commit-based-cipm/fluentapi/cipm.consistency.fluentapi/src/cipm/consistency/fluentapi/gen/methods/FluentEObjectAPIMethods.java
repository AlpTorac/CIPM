package cipm.consistency.fluentapi.gen.methods;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EStructuralFeature;

import cipm.consistency.fluentapi.gen.init.FluentAPIInitialisationConstants;
import cipm.consistency.fluentapi.gen.superinit.FluentAPISuperInitialisationConstants;

public final class FluentEObjectAPIMethods {
	// TODO Add commentary

	public static EObject xWithFeat(EObject api, EObject objToModify, EStructuralFeature feat, Object featVal) {
		var init = getInitialisationForX(api, objToModify);

		var opName = FluentAPIInitialisationConstants.getFluentAPIInitialisationWithXFeatNameForType(feat);
		var withOp = init.eClass().getEOperations().stream().filter((op) -> op.getName().equals(opName)).findFirst()
				.get();
		try {
			init.eInvoke(withOp, new BasicEList<>(Collections.singleton(featVal)));
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		FluentAPIInitialisationStorage.dropOngoingInitialisation(init);
		return api;
	}

	public static EObject xWithoutFeat(EObject api, EObject objToModify, EStructuralFeature feat) {
		var init = getInitialisationForX(api, objToModify);
		var opName = FluentAPIInitialisationConstants.getFluentAPIInitialisationWithoutXFeatNameForType(feat);
		var withoutOp = init.eClass().getEOperations().stream().filter((op) -> op.getName().equals(opName)).findFirst()
				.get();
		try {
			init.eInvoke(withoutOp, new BasicEList<>());
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		FluentAPIInitialisationStorage.dropOngoingInitialisation(init);
		return api;
	}

	public static EObject xCleanFeat(EObject api, EObject objToModify, EStructuralFeature feat) {
		var init = getInitialisationForX(api, objToModify);
		var opName = FluentAPIInitialisationConstants.getFluentAPIInitialisationCleanXFeatNameForType(feat);
		var cleanOp = init.eClass().getEOperations().stream().filter((op) -> op.getName().equals(opName)).findFirst()
				.get();
		try {
			init.eInvoke(cleanOp, new BasicEList<>());
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		FluentAPIInitialisationStorage.dropOngoingInitialisation(init);
		return api;
	}

	private static boolean isArrayType(EParameter p) {
		return ((p.getEType() != null && p.getEType().getInstanceClass() != null
				&& p.getEType().getInstanceClass().isArray())
				|| (p.getEGenericType() != null && p.getEGenericType().getERawType() != null
						&& p.getEGenericType().getERawType().getInstanceClass() != null
						&& p.getEGenericType().getERawType().getInstanceClass().isArray()));
	}

	private static boolean isCollectionType(EParameter p) {
		return ((p.getEType() != null && p.getEType().getInstanceClass() != null
				&& Collection.class.isAssignableFrom(p.getEType().getInstanceClass()))
				|| (p.getEGenericType() != null && p.getEGenericType().getERawType() != null
						&& p.getEGenericType().getERawType().getInstanceClass() != null
						&& Collection.class.isAssignableFrom(p.getEGenericType().getERawType().getInstanceClass())));
	}

	private static EOperation getArrayVariant(List<EOperation> ops) {
		return ops.stream().filter((o) -> o.getEParameters().stream().anyMatch((p) -> !p.isMany() && isArrayType(p)))
				.findFirst().get();
	}

	private static EOperation getCollectionVariant(List<EOperation> ops) {
		return ops.stream()
				.filter((o) -> o.getEParameters().stream().anyMatch((p) -> !p.isMany() && isCollectionType(p)))
				.findFirst().get();
	}

	private static EOperation getSingleValueVariant(List<EOperation> ops) {
		return ops.stream()
				.filter((o) -> o.getEParameters().stream().anyMatch((p) -> !p.isMany() && !isCollectionType(p)))
				.findFirst().get();
	}

	private static EOperation getVariantForFeatureValue(Object featVal, List<EOperation> ops) {
		if (featVal.getClass().isArray()) {
			return getArrayVariant(ops);
		} else if (featVal instanceof Collection) {
			return getCollectionVariant(ops);
		} else {
			return getSingleValueVariant(ops);
		}
	}

	public static EObject xWithAddedFeat(EObject api, EObject objToModify, EStructuralFeature feat, Object featVal) {
		var init = getInitialisationForX(api, objToModify);
		var opName = FluentAPIInitialisationConstants.getFluentAPIInitialisationWithAddedXFeatNameForType(feat);
		var withAddedOps = init.eClass().getEOperations().stream().filter((op) -> op.getName().equals(opName))
				.collect(Collectors.toList());
		var op = getVariantForFeatureValue(featVal, withAddedOps);
		var argList = new BasicEList<>();

		argList.add(featVal);
		try {
			init.eInvoke(op, argList);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		FluentAPIInitialisationStorage.dropOngoingInitialisation(init);
		return api;
	}

	public static EObject xWithRemovedFeat(EObject api, EObject objToModify, EStructuralFeature feat, Object featVal) {
		var init = getInitialisationForX(api, objToModify);
		var opName = FluentAPIInitialisationConstants.getFluentAPIInitialisationWithRemovedXFeatNameForType(feat);
		var withRemovedOps = init.eClass().getEOperations().stream().filter((op) -> op.getName().equals(opName))
				.collect(Collectors.toList());
		var op = getVariantForFeatureValue(featVal, withRemovedOps);
		var argList = new BasicEList<>();
		argList.add(featVal);
		try {
			init.eInvoke(op, argList);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		FluentAPIInitialisationStorage.dropOngoingInitialisation(init);
		return api;
	}

	@SuppressWarnings("unchecked")
	public static EList<Class<? extends EObject>> getAllSupportedClasses(EObject me) {
		var result = new BasicEList<Class<? extends EObject>>();
//		getInits(me).stream()
//				.map((c) -> c.getEOperations().stream()
//						.filter((op) -> op.getName().equals(FluentAPICreateNowMethodGenerator.getCreateNowMethodName()))
//						.findFirst().get().getEType())
//				.map((c) -> (EClass) c).forEach((c) -> result.add(c));

		var initsPac = me.eClass().getEPackage().getESubpackages().stream()
				.filter((pac) -> pac.getName()
						.equals(FluentAPIInitialisationConstants.getFluentAPIInitialisationsPackageName()))
				.findFirst().get();

		var initEClasses = initsPac.getEClassifiers().stream()
				.filter((eCls) -> eCls instanceof EClass && eCls.getName()
						.endsWith(FluentAPIInitialisationConstants.getFluentAPIInitialisationClassNameSuffix()))
				.map((eCls) -> (EClass) eCls).collect(Collectors.toList());

		initEClasses.stream().map((eCls) -> eCls.getInstanceClass()).map((cls) -> {
			try {
				return cls.getDeclaredMethod(
						FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationCreateNowMethodName());
			} catch (NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
				throw new IllegalStateException(e);
			}
		}).map((met) -> (Class<? extends EObject>) met.getReturnType())

//				.map((eCls) -> eCls.getEOperations().stream()
//						.filter((op) -> op.getName().equals(FluentAPICreateNowMethodGenerator.getCreateNowMethodName()))
//						.findFirst().get().getEType())
//				.map((returnType) -> (EClass) returnType)

				.forEach(result::add);

		return result;
	}

	public static EObject getInitialisationInstanceForX(EObject me, Class<?> eobjCls) {
		var initsPac = me.eClass().getEPackage().getESubpackages().stream()
				.filter((pac) -> pac.getName()
						.equals(FluentAPIInitialisationConstants.getFluentAPIInitialisationsPackageName()))
				.findFirst().get();

		var initEClass = (EClass) initsPac.getEClassifiers().stream().filter((eCls) -> eCls instanceof EClass)
				.map((eCls) -> (EClass) eCls).filter((eCls) -> isInitialisationFor(eCls, eobjCls)).findFirst().get();
		var initInstance = initEClass.getEPackage().getEFactoryInstance().create(initEClass);
		initInstance.eSet(
				initInstance.eClass().getEStructuralFeature(
						FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationRootAPIReferenceName()),
				me);
		FluentAPIInitialisationStorage.addOngoingInitialisation(initInstance);
		return initInstance;
	}

	public static EObject getInitialisationInstanceForXWithNewElement(EObject me, Class<?> eobjCls) {
		var initInstance = getInitialisationInstanceForX(me, eobjCls);
		var newElemOp = initInstance.eClass().getEOperations().stream()
				.filter((op) -> op.getName()
						.equals(FluentAPIInitialisationConstants.getFluentAPIInitialisationNewElementOperationName()))
				.findFirst().get();
		try {
			initInstance.eInvoke(newElemOp, new BasicEList<>());
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return initInstance;
	}

	public static EObject getInitialisationForX(EObject me, EClass eCls) {
		return getInitialisationForX(me, eCls.getInstanceClass());
	}

	public static EObject getInitialisationForX(EObject me, Class<?> eobjCls) {
		return getInitialisationInstanceForXWithNewElement(me, eobjCls);
	}

	public static EObject getInitialisationForX(EObject me, EObject eobjToInit) {
		var initInstance = getInitialisationInstanceForX(me, eobjToInit.eClass().getInstanceClass());

		initInstance.eSet(initInstance.eClass().getEStructuralFeature(
				FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationCurrentElementReferenceName()),
				eobjToInit);

		return initInstance;
	}

	public static boolean isInitialisationFor(EClass initECls, Class<?> eobjCls) {
		return !initECls.isAbstract() && initECls.getName()
				.substring(0,
						initECls.getName().length()
								- FluentAPIInitialisationConstants.getFluentAPIInitialisationClassNameSuffix().length())
				.equals(eobjCls.getSimpleName());
	}

	public static EObject continueElement(Class<?> eobjCls) {
		var initsOfMatchingType = FluentAPIInitialisationStorage.getOngoingInits().stream()
				.filter((i) -> isInitialisationFor(i.eClass(), eobjCls))
				.collect(Collectors.toCollection(ArrayList::new));
		return !initsOfMatchingType.isEmpty() ? initsOfMatchingType.get(initsOfMatchingType.size() - 1) : null;
	}

	public static EObject getPreviousInit(EObject init, Class<?> eobjCls) {
		return getPreviousInit(init, eobjCls, 1);
	}

	public static EObject getNextInit(EObject init, Class<?> eobjCls) {
		return getNextInit(init, eobjCls, 1);
	}

	private static boolean initIndexInBounds(Collection<?> col, int idx) {
		return idx >= 0 && idx < col.size();
	}

	public static EObject getPreviousInit(EObject init, Class<?> eobjCls, int stepsBack) {
		var initsOfMatchingType = FluentAPIInitialisationStorage.getOngoingInits().stream()
				.filter((i) -> isInitialisationFor(i.eClass(), eobjCls))
				.collect(Collectors.toCollection(ArrayList::new));
		var idx = initsOfMatchingType.indexOf(init) - stepsBack;
		return initIndexInBounds(initsOfMatchingType, idx) ? initsOfMatchingType.get(idx) : null;
	}

	public static EObject getNextInit(EObject init, Class<?> eobjCls, int stepsForward) {
		var initsOfMatchingType = FluentAPIInitialisationStorage.getOngoingInits().stream()
				.filter((i) -> isInitialisationFor(i.eClass(), eobjCls))
				.collect(Collectors.toCollection(ArrayList::new));
		var idx = initsOfMatchingType.indexOf(init) + stepsForward;
		return initIndexInBounds(initsOfMatchingType, idx) ? initsOfMatchingType.get(idx) : null;
	}
}

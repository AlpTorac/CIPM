package cipm.consistency.fluentapi.gen.methods;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EStructuralFeature;

import cipm.consistency.fluentapi.gen.init.FluentAPIInitialisationConstants;
import cipm.consistency.fluentapi.gen.init.FluentAPIInitialisationWithOperationGenerator;
import cipm.consistency.fluentapi.gen.rootapi.FluentAPIRootAPIConstants;
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
		dropInitialisation(api, init);
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
		dropInitialisation(api, init);
		return api;
	}

	private static boolean isArrayType(EParameter p) {
		return ((p.getEType() != null && p.getEType().getInstanceClass() != null
				&& p.getEType().getInstanceClass().isArray())
				|| (p.getEGenericType() != null && p.getEGenericType().getERawType() != null
						&& p.getEGenericType().getERawType().getInstanceClass() != null
						&& p.getEGenericType().getERawType().getInstanceClass().isArray()));
	}

	public static EObject xWithAddedFeat(EObject api, EObject objToModify, EStructuralFeature feat, Object featVal) {
		var init = getInitialisationForX(api, objToModify);
		var opName = FluentAPIInitialisationConstants.getFluentAPIInitialisationWithAddedXFeatNameForType(feat);
		var withAddedOps = init.eClass().getEOperations().stream().filter((op) -> op.getName().equals(opName))
				.collect(Collectors.toList());
		EOperation op = null;
		var argList = new BasicEList<>();
		if (featVal instanceof Collection) {
			op = withAddedOps.stream().filter((o) -> o.getEParameters().stream().anyMatch((p) -> p.isMany()))
					.findFirst().get();
		} else if (featVal.getClass().isArray()) {
			op = withAddedOps.stream()
					.filter((o) -> o.getEParameters().stream().anyMatch((p) -> !p.isMany() && isArrayType(p)))
					.findFirst().get();
		} else {
			op = withAddedOps.stream().filter((o) -> o.getEParameters().stream().anyMatch((p) -> !p.isMany()))
					.findFirst().get();
		}
		argList.add(featVal);
		try {
			init.eInvoke(op, argList);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		dropInitialisation(api, init);
		return api;
	}

	public static EObject xWithRemovedFeat(EObject api, EObject objToModify, EStructuralFeature feat, Object featVal) {
		var init = getInitialisationForX(api, objToModify);
		var opName = FluentAPIInitialisationConstants.getFluentAPIInitialisationWithRemovedXFeatNameForType(feat);
		var withRemovedOps = init.eClass().getEOperations().stream().filter((op) -> op.getName().equals(opName))
				.collect(Collectors.toList());
		EOperation op = null;
		var argList = new BasicEList<>();
		if (featVal instanceof Collection) {
			op = withRemovedOps.stream().filter((o) -> o.getEParameters().stream().anyMatch((p) -> p.isMany()))
					.findFirst().get();
		} else if (featVal.getClass().isArray()) {
			op = withRemovedOps.stream()
					.filter((o) -> o.getEParameters().stream().anyMatch((p) -> !p.isMany() && isArrayType(p)))
					.findFirst().get();
		} else {
			op = withRemovedOps.stream().filter((o) -> o.getEParameters().stream().noneMatch((p) -> p.isMany()))
					.findFirst().get();
		}
		argList.add(featVal);
		try {
			init.eInvoke(op, argList);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		dropInitialisation(api, init);
		return api;
	}

	public static EObject xWithExactFeat(EObject api, EObject objToModify, EStructuralFeature feat, Object featVal) {
		var init = getInitialisationForX(api, objToModify);
		var opName = FluentAPIInitialisationConstants.getFluentAPIInitialisationWithExactXFeatNameForType(feat);
		var withExactOp = init.eClass().getEOperations().stream().filter((op) -> op.getName().equals(opName))
				.collect(Collectors.toList());
		EOperation op = null;
		var argList = new BasicEList<>();
		if (featVal instanceof Collection) {
			op = withExactOp.stream().filter((o) -> o.getEParameters().stream().anyMatch((p) -> p.isMany())).findFirst()
					.get();
		} else {
			op = withExactOp.stream()
					.filter((o) -> o.getEParameters().stream().anyMatch((p) -> !p.isMany() && isArrayType(p)))
					.findFirst().get();
		}
		argList.add(featVal);
		try {
			init.eInvoke(op, argList);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		dropInitialisation(api, init);
		return api;
	}

	public static EObject xWithFeatOfContainer(EObject api, EObject objToModify, EStructuralFeature feat) {
		var init = getInitialisationForX(api, objToModify);
		var opName = FluentAPIInitialisationConstants.getFluentAPIInitialisationWithXFeatOfContainerNameForType(feat);
		var withFeatOfConOp = init.eClass().getEOperations().stream().filter((op) -> op.getName().equals(opName))
				.findFirst().get();
		try {
			init.eInvoke(withFeatOfConOp, new BasicEList<>());
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		dropInitialisation(api, init);
		return api;
	}

	public static EList<Class<?>> getAllSupportedClasses(EObject me) {
		var result = new BasicEList<Class<?>>();
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
				.filter((eCls) -> eCls instanceof EClass && eCls.getName().endsWith("Initialisation"))
				.map((eCls) -> (EClass) eCls).collect(Collectors.toList());

		initEClasses.stream().map((eCls) -> eCls.getInstanceClass()).map((cls) -> {
			try {
				return cls.getDeclaredMethod(
						FluentAPIInitialisationConstants.getFluentapiinitialisationcreatenowmethodname());
			} catch (NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
				throw new IllegalStateException(e);
			}
		}).map((met) -> met.getReturnType())

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
		getOngoingInits(me).add(initInstance);
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
		// TODO Clean up and do it properly
		// Trim "Initialisation" from XInitialisation EClass name
		return !initECls.isAbstract() && initECls.getName()
				.substring(0, initECls.getName().length() - "Initialisation".length()).equals(eobjCls.getSimpleName());
	}

	public static void dropInitialisation(EObject me, EObject init) {
		getOngoingInits(me).remove(init);
	}

	public static EObject continueElement(EObject me, Class<?> eobjCls) {
		var initsOfMatchingType = getOngoingInits(me).stream().filter((i) -> isInitialisationFor(i.eClass(), eobjCls))
				.collect(Collectors.toCollection(ArrayList::new));
		return !initsOfMatchingType.isEmpty() ? initsOfMatchingType.get(initsOfMatchingType.size() - 1) : null;
	}

	/**
	 * @return Oldest idx-th initialisation (idx starts with 0)
	 */
	public static EObject continueElementFromStart(EObject me, Class<?> eobjCls, int idx) {
		var initsOfMatchingType = getOngoingInits(me).stream().filter((i) -> isInitialisationFor(i.eClass(), eobjCls))
				.collect(Collectors.toCollection(ArrayList::new));
		return initsOfMatchingType.size() > idx ? initsOfMatchingType.get(idx) : null;
	}

	/**
	 * @return Oldest initialisation (the first initialisation, idx 0)
	 */
	public static EObject continueOldestElement(EObject me, Class<?> eobjCls) {
		return getOngoingInits(me).stream().filter((i) -> isInitialisationFor(i.eClass(), eobjCls)).findFirst()
				.orElse(null);
	}

	/**
	 * @return Newest idx-th initialisation (idx starts with 0)
	 */
	public static EObject continueElementFromEnd(EObject me, Class<?> eobjCls, int idx) {
		var initsOfMatchingType = getOngoingInits(me).stream().filter((i) -> isInitialisationFor(i.eClass(), eobjCls))
				.collect(Collectors.toCollection(ArrayList::new));
		return initsOfMatchingType.size() > idx ? initsOfMatchingType.get(initsOfMatchingType.size() - 1 - idx) : null;
	}

	public static EObject getPreviousInit(EObject init, EObject api, Class<?> eobjCls) {
		var initsOfMatchingType = getOngoingInits(api).stream().filter((i) -> isInitialisationFor(i.eClass(), eobjCls))
				.collect(Collectors.toCollection(ArrayList::new));
		var idx = initsOfMatchingType.indexOf(init);
		return idx > 0 ? initsOfMatchingType.get(idx - 1) : null;
	}

	public static EObject getNextInit(EObject init, EObject api, Class<?> eobjCls) {
		var initsOfMatchingType = getOngoingInits(api).stream().filter((i) -> isInitialisationFor(i.eClass(), eobjCls))
				.collect(Collectors.toCollection(ArrayList::new));
		var idx = initsOfMatchingType.indexOf(init);
		return idx + 1 < initsOfMatchingType.size() ? initsOfMatchingType.get(idx + 1) : null;
	}

	public static void withInitialisation(EObject me, EClass initEClass) {
		// TODO Remove or use
		getInits(me).add(initEClass);
	}

	public static void withInitialisations(EObject me, EPackage initsPac) {
		// TODO Remove or clean up and do it properly
		// Trim "Initialisation" from XInitialisation EClass name
		initsPac.getEClassifiers().stream().filter((e) -> e instanceof EClass).map((e) -> (EClass) e)
				.filter((e) -> e.getName().endsWith("Initialisation")).forEach((e) -> getInits(me).add(e));
	}

	@SuppressWarnings("unchecked")
	public static EList<EClass> getInits(EObject me) {
		return ((EList<EClass>) me.eGet(
				me.eClass().getEStructuralFeature(FluentAPIRootAPIConstants.getRootAPIInitialisationsReferenceName())));
	}

	@SuppressWarnings("unchecked")
	public static EList<EObject> getOngoingInits(EObject me) {
		return ((EList<EObject>) me.eGet(me.eClass()
				.getEStructuralFeature(FluentAPIRootAPIConstants.getRootAPIOngoingInitialisationsReferenceName())));
	}
}

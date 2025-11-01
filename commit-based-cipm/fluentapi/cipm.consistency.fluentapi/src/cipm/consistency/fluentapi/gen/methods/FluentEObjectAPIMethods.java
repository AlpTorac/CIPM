package cipm.consistency.fluentapi.gen.methods;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import cipm.consistency.fluentapi.gen.FluentAPICurrentElementReferenceGenerator;
import cipm.consistency.fluentapi.gen.FluentAPIInitialisationEClassesReferenceGenerator;
import cipm.consistency.fluentapi.gen.FluentAPIInitialisationNewOperationGenerator;
import cipm.consistency.fluentapi.gen.FluentAPIInitialisationsPackageGenerator;
import cipm.consistency.fluentapi.gen.FluentAPIOngoingInitialisationsReferenceGenerator;
import cipm.consistency.fluentapi.gen.FluentAPIRootAPIReferenceGenerator;

public final class FluentEObjectAPIMethods {
	public static EObject getInitialisationInstanceForX(EObject me, Class<?> eobjCls) {
		var initsPac = me.eClass().getEPackage().getESubpackages().stream()
				.filter((pac) -> pac.getName().equals(FluentAPIInitialisationsPackageGenerator.getPackageName()))
				.findFirst().get();

		var initEClass = (EClass) initsPac.getEClassifiers().stream().filter((eCls) -> eCls instanceof EClass)
				.map((eCls) -> (EClass) eCls).filter((eCls) -> isInitialisationFor(eCls, eobjCls)).findFirst().get();
		var initInstance = initEClass.getEPackage().getEFactoryInstance().create(initEClass);
		initInstance.eSet(initInstance.eClass()
				.getEStructuralFeature(FluentAPIRootAPIReferenceGenerator.getRootAPIReferenceName()), me);
		getOngoingInits(me).add(initInstance);
		return initInstance;
	}

	public static EObject getInitialisationInstanceForXWithNewElement(EObject me, Class<?> eobjCls) {
		var initInstance = getInitialisationInstanceForX(me, eobjCls);
		var newElemOp = initInstance.eClass().getEOperations().stream()
				.filter((op) -> op.getName().equals(FluentAPIInitialisationNewOperationGenerator.getNewOperationName()))
				.findFirst().get();
		try {
			initInstance.eInvoke(newElemOp, new BasicEList());
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
				FluentAPICurrentElementReferenceGenerator.getCurrentElementReferenceName()), eobjToInit);

		return initInstance;
	}

	public static boolean isInitialisationFor(EClass initECls, Class<?> eobjCls) {
		return !initECls.isAbstract() && initECls.getName().startsWith(eobjCls.getSimpleName());
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
		getInits(me).add(initEClass);
	}

	public static void withInitialisations(EObject me, EPackage initsPac) {
		initsPac.getEClassifiers().stream().filter((e) -> e instanceof EClass).map((e) -> (EClass) e)
				.filter((e) -> e.getName().endsWith("Initialisation")).forEach((e) -> getInits(me).add(e));
	}

	@SuppressWarnings("unchecked")
	public static EList<EClass> getInits(EObject me) {
		return ((EList<EClass>) me.eGet(me.eClass().getEStructuralFeature(
				FluentAPIInitialisationEClassesReferenceGenerator.getInitialisationsReferenceName())));
	}

	@SuppressWarnings("unchecked")
	public static EList<EObject> getOngoingInits(EObject me) {
		return ((EList<EObject>) me.eGet(me.eClass().getEStructuralFeature(
				FluentAPIOngoingInitialisationsReferenceGenerator.getOngoingInitialisationsReferenceName())));
	}
}

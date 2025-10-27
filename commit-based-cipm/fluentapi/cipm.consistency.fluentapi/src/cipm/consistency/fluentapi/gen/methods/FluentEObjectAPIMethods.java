package cipm.consistency.fluentapi.gen.methods;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import cipm.consistency.fluentapi.gen.FluentAPIInitialisationEClassesReferenceGenerator;
import cipm.consistency.fluentapi.gen.FluentAPIInitialisationNewOperationGenerator;
import cipm.consistency.fluentapi.gen.FluentAPIInitialisationsPackageGenerator;
import cipm.consistency.fluentapi.gen.FluentAPIOngoingInitialisationsReferenceGenerator;
import cipm.consistency.fluentapi.gen.FluentAPIRootAPIReferenceGenerator;

public final class FluentEObjectAPIMethods {
//	/**
//	 * @return An instance of elementCls
//	 */
//	public static void newElement(EObject meInit) {
//		var currentElementRef = me.eClass()
//				.getEStructuralFeature(FluentAPICurrentElementReferenceGenerator.getCurrentElementReferenceName());
//		var elementCls = (EClass) currentElementRef.getEType();
//		me.eSet(currentElementRef, elementCls.getEPackage().getEFactoryInstance().create(elementCls));
//	}

	private static List<EReference> getContainmentReference(EObject elemToInit, EObject otherElem) {
		return otherElem.eClass().getEAllReferences().stream()
				.filter((ref) -> isContainmentReferenceFor(elemToInit, ref)).collect(Collectors.toList());
	}

	private static boolean isContainmentReferenceFor(EObject elemToInit, EStructuralFeature potentialContainmentFeat) {
		var refType = potentialContainmentFeat.getEType();
		var refTypeCls = refType.getInstanceClass();
		return refType instanceof EClass && refTypeCls.isAssignableFrom(elemToInit.eClass().getInstanceClass());
	}

	public static void adaptBidirectionalReference(EObject elemToInit, Object otherElem) {
		if (otherElem instanceof EObject) {
			var castedOE = (EObject) otherElem;
			for (var ref : getContainmentReference(elemToInit, castedOE)) {
				if (!ref.isMany()) {
					castedOE.eSet(ref, elemToInit);
				} else {
					((EList) castedOE.eGet(ref)).add(elemToInit);
				}
			}
		} else if (otherElem instanceof List) {
			var castedOE = (List) otherElem;
			if (!castedOE.isEmpty() && castedOE.get(0) instanceof EObject) {
				for (var obj : castedOE) {
					adaptBidirectionalReference(elemToInit, obj);
				}
			}
		}
	}

	public static EObject getInitialisationForX(EObject me, EClass eCls) {
		return getInitialisationForX(me, eCls.getInstanceClass());
	}

	public static EObject getInitialisationForX(EObject me, Class<?> eobjCls) {
		var initsPac = me.eClass().getEPackage().getESubpackages().stream()
				.filter((pac) -> pac.getName().equals(FluentAPIInitialisationsPackageGenerator.getPackageName()))
				.findFirst().get();

		var initEClass = (EClass) initsPac.getEClassifiers().stream().filter((eCls) -> eCls instanceof EClass)
				.map((eCls) -> (EClass) eCls).filter((eCls) -> isInitialisationFor(eCls, eobjCls)).findFirst().get();
		var initInstance = initEClass.getEPackage().getEFactoryInstance().create(initEClass);
		var newElemOp = initInstance.eClass().getEOperations().stream()
				.filter((op) -> op.getName().equals(FluentAPIInitialisationNewOperationGenerator.getNewOperationName()))
				.findFirst().get();
		try {
			initInstance.eInvoke(newElemOp, new BasicEList());
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		initInstance.eSet(initInstance.eClass()
				.getEStructuralFeature(FluentAPIRootAPIReferenceGenerator.getRootAPIReferenceName()), me);

		getOngoingInits(me).add(initInstance);
		return initInstance;
	}

//	public static EClass getInitialisationEClassForX(EObject me, Class<?> eobjCls) {
//		me.eClass().getEPackage().getEClassifiers().stream().filter((eCls) -> eCls instanceof EClass)
//				.map((eCls) -> (EClass) eCls).filter((eCls) -> !eCls.isAbstract())
//				.filter((eCls) -> eCls.getName().startsWith(eobjCls.getSimpleName()));
//
//		return (EClass) getInits(me).stream().filter((i) -> isInitialisationFor(i, eobjCls)).findFirst().orElse(null);
//	}
//
//	public static EClass getInitialisationEClassForXFromPackage(EObject me, Class<?> eobjCls) {
//		return me.eClass().getEPackage().getEClassifiers().stream().filter((eCls) -> eCls instanceof EClass)
//				.map((eCls) -> (EClass) eCls).filter((eCls) -> isInitialisationFor(eCls, eobjCls)).findFirst()
//				.orElse(null);
//	}

	public static boolean isInitialisationFor(EClass initECls, Class<?> eobjCls) {
		return !initECls.isAbstract() && initECls.getName().startsWith(eobjCls.getSimpleName());
	}

	public static void dropInitialisation(EObject me, EObject init) {
		getOngoingInits(me).remove(init);
	}

	public static EObject continueElement(EObject me, Class<?> eobjCls) {
		var initsOfMatchingType = getOngoingInits(me).stream().filter((i) -> isInitialisationFor(i.eClass(), eobjCls))
				.collect(Collectors.toCollection(ArrayList::new));
		return initsOfMatchingType.get(initsOfMatchingType.size() - 1);
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

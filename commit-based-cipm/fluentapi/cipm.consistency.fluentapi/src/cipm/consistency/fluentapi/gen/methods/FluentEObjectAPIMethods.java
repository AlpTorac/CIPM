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
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.FluentAPIInitialisationEClassesReferenceGenerator;
import cipm.consistency.fluentapi.gen.FluentAPIInitialisationNewOperationGenerator;
import cipm.consistency.fluentapi.gen.FluentAPIInitialisationsPackageGenerator;
import cipm.consistency.fluentapi.gen.FluentAPIOngoingInitialisationsReferenceGenerator;
import cipm.consistency.fluentapi.gen.FluentAPIRootAPIReferenceGenerator;

public final class FluentEObjectAPIMethods {
	/**
	 * @return The containment reference for elemToInit inside otherElem, if there
	 *         is exactly one containment reference with the matching type. If there
	 *         are multiple possibilities, returns null. If there is no containment
	 *         reference, {@code elemToInit.eContainer() == otherElem} is likely and
	 *         is already set, so this method returns null.
	 */
	private static EReference getContainmentReference(EObject elemToInit, EObject otherElem) {
		var directContainments = otherElem.eClass().getEReferences().stream()
				.filter((r) -> isContainmentReferenceFor(elemToInit, r)).collect(Collectors.toList());
		if (!directContainments.isEmpty()) {
			if (directContainments.size() == 1) {
				return directContainments.get(0);
			} else {
				return null;
			}
		}

		var inheritedContainments = new ArrayList<EReference>(otherElem.eClass().getEAllReferences()).stream()
				.filter((r) -> !directContainments.contains(r)).filter((r) -> isContainmentReferenceFor(elemToInit, r))
				.collect(Collectors.toList());
		if (!inheritedContainments.isEmpty()) {
			if (inheritedContainments.size() == 1) {
				return inheritedContainments.get(0);
			} else {
				return null;
			}
		}

		// TODO Maybe delegate to some "exceptions" class that returns the EReference
		// for (elemToInit, otherElem)

		return null;
	}

	private static boolean isContainmentReferenceFor(EObject elemToInit, EReference potentialContainmentFeat) {
		var refType = potentialContainmentFeat.getEType();
		var refTypeCls = refType.getInstanceClass();
		return refType instanceof EClass && refTypeCls.isAssignableFrom(elemToInit.eClass().getInstanceClass());
	}

	public static void adaptEOpposite(EObject elemToInit, EStructuralFeature elemToInitFeat, Object otherElem) {
		if (elemToInitFeat instanceof EReference) {
			var castedFeat = (EReference) elemToInitFeat;
			if (castedFeat.getEOpposite() != null && otherElem instanceof EObject) {
				((EObject) otherElem).eSet(castedFeat.getEOpposite(), elemToInit);
			}
		}
	}

	public static void adaptBidirectionalReference(EObject elemToInit, Object otherElem) {
		if (otherElem instanceof EObject) {
			var castedOE = (EObject) otherElem;
			var ref = getContainmentReference(elemToInit, castedOE);
			if (ref != null) {
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

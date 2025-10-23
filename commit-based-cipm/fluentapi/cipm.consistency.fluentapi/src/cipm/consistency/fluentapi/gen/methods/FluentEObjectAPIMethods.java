package cipm.consistency.fluentapi.gen.methods;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import cipm.consistency.fluentapi.gen.FluentAPIInitialisationEClassesReferenceGenerator;
import cipm.consistency.fluentapi.gen.FluentAPIOngoingInitialisationsReferenceGenerator;

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
	
	public static EObject getInitialisationForX(EObject me, Class<?> eobjCls) {
		var initEClass = getInitialisationEClassForX(me, eobjCls);
		var initInstance = initEClass.getEPackage().getEFactoryInstance().create(initEClass);
		getOngoingInits(me).add(initInstance);
		return initInstance;
	}

	public static EClass getInitialisationEClassForX(EObject me, Class<?> eobjCls) {
		me.eClass().getEPackage().getEClassifiers().stream().filter((eCls) -> eCls instanceof EClass)
				.map((eCls) -> (EClass) eCls).filter((eCls) -> !eCls.isAbstract())
				.filter((eCls) -> eCls.getName().startsWith(eobjCls.getSimpleName()));

		return (EClass) getInits(me).stream().filter((i) -> isInitialisationFor(i, eobjCls)).findFirst().orElse(null);
	}

	public static EClass getInitialisationEClassForXFromPackage(EObject me, Class<?> eobjCls) {
		return me.eClass().getEPackage().getEClassifiers().stream().filter((eCls) -> eCls instanceof EClass)
				.map((eCls) -> (EClass) eCls).filter((eCls) -> isInitialisationFor(eCls, eobjCls)).findFirst()
				.orElse(null);
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

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
	public static EObject getInitialisationForX(EObject me, Class<?> eobjCls) {
		var initEClass = getInitialisationEClassForX(me, eobjCls);
		var initInstance = initEClass.getEPackage().getEFactoryInstance().create(initEClass);
		getOngoingInits(me).add(initInstance);
		return initInstance;
	}

	public static EClass getInitialisationEClassForX(EObject me, Class<?> eobjCls) {
		return (EClass) getInits(me).stream()
				.filter((i) -> AbstractInitialisationMethods.isInitialisedClassEqual(i, eobjCls)).findFirst().get();
	}

	public static void dropInitialisation(EObject me, EObject init) {
		getOngoingInits(me).remove(init);
	}

	public static <T extends EObject> T clone(EObject me, T eobj) {
		return AbstractInitialisationMethods.clone(getInitialisationForX(me, eobj.getClass()), eobj);
	}

	public static <T extends EObject> T deepClone(EObject me, T eobj) {
		return AbstractInitialisationMethods.deepClone(getInitialisationForX(me, eobj.getClass()), eobj);
	}

	public static void modifyElement(EObject me, EObject eobj) {
		var init = getInitialisationForX(me, eobj.getClass());
		AbstractInitialisationMethods.setCurrentElement(init, eobj);
	}

	public static void modifyElementClone(EObject me, EObject eobj) {
		modifyElement(me, clone(me, eobj));
	}

	public static void modifyElementDeepClone(EObject me, EObject eobj) {
		modifyElement(me, deepClone(me, eobj));
	}

	public static EObject newElement(EObject me, Class<?> eobjCls) {
		var init = getInitialisationForX(me, eobjCls);
		AbstractInitialisationMethods.newElement(init);
		return init;
	}

	public static EObject continueElement(EObject me, Class<?> eobjCls) {
		var initsOfMatchingType = getOngoingInits(me).stream()
				.filter((i) -> AbstractInitialisationMethods.isInitialisedClassEqual(i, eobjCls))
				.collect(Collectors.toCollection(ArrayList::new));
		return initsOfMatchingType.get(initsOfMatchingType.size() - 1);
	}

	public static void withInitialisation(EObject me, EClass initEClass) {
		getInits(me).add(initEClass);
	}

	public static void withInitialisations(EObject me, EPackage initsPac) {
		initsPac.getEClassifiers().stream().filter((e) -> e instanceof EClass).map((e) -> (EClass) e)
				.filter((e) -> AbstractInitialisationMethods.getInitialisedEClass(e) != null)
				.forEach((e) -> getInits(me).add(e));
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

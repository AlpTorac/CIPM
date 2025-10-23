//package cipm.consistency.fluentapi.gen.methods;
//
//import java.util.List;
//
//import org.eclipse.emf.ecore.EClass;
//import org.eclipse.emf.ecore.EObject;
//import org.eclipse.emf.ecore.util.EcoreUtil;
//
//import cipm.consistency.fluentapi.gen.FluentAPICurrentElementReferenceGenerator;
//import cipm.consistency.fluentapi.gen.FluentAPIOngoingInitialisationsReferenceGenerator;
//import cipm.consistency.fluentapi.gen.FluentAPIRootAPIReferenceGenerator;
//
//public final class AbstractInitialisationMethods {
//	public static void setCurrentElement(EObject me, EObject eobj) {
//		me.eSet(me.eClass().getEStructuralFeature(
//				FluentAPICurrentElementReferenceGenerator.getCurrentElementReferenceName()), eobj);
//	}
//
//	public static EObject getCurrentElement(EObject me) {
//		return (EObject) me.eGet(me.eClass()
//				.getEStructuralFeature(FluentAPICurrentElementReferenceGenerator.getCurrentElementReferenceName()));
//	}
//
//	public static EClass getInitialisedEClass(EObject me) {
//		return (EClass) me.eGet(me.eClass()
//				.getEStructuralFeature(FluentAPIInitialisedEClassReference.getInitialisedEClassReferenceName()));
//	}
//	
//	@SuppressWarnings("unchecked")
//	public static Class<? extends EObject> getInitialisedClass(EObject me) {
//		return (Class<? extends EObject>) getInitialisedEClass(me).getInstanceClass();
//	}
//	
//	public static boolean isInitialisedClassEqual(EObject me, Class<?> eobjCls) {
//		return getInitialisedClass(me).equals(eobjCls);
//	}
//
//	/**
//	 * @return An instance of elementCls
//	 */
//	public static void newElement(EObject me) {
//		var currentElementRef = me.eClass()
//				.getEStructuralFeature(FluentAPICurrentElementReferenceGenerator.getCurrentElementReferenceName());
//		var elementCls = (EClass) currentElementRef.getEType();
//		me.eSet(currentElementRef, elementCls.getEPackage().getEFactoryInstance().create(elementCls));
//	}
//
//	public static void resetElement(EObject me) {
//		me.eUnset(me.eClass()
//				.getEStructuralFeature(FluentAPICurrentElementReferenceGenerator.getCurrentElementReferenceName()));
//	}
//
//	public static <T extends EObject> void dropInitialisation(EObject me) {
//		var apiObj = ((EObject) me
//				.eGet(me.eClass().getEStructuralFeature(FluentAPIRootAPIReferenceGenerator.getRootAPIReferenceName())));
//		((List<?>) apiObj.eGet(apiObj.eClass().getEStructuralFeature(
//				FluentAPIOngoingInitialisationsReferenceGenerator.getOngoingInitialisationsReferenceName())))
//				.remove(me);
//	}
//
//	@SuppressWarnings("unchecked")
//	public static <T extends EObject> T createElementNow(EObject me) {
//		var currentElementRef = me.eClass()
//				.getEStructuralFeature(FluentAPICurrentElementReferenceGenerator.getCurrentElementReferenceName());
//		var elem = me.eGet(currentElementRef);
//		dropInitialisation(me);
//		return (T) elem;
//	}
//
//	public static <T extends EObject> T clone(EObject me, T eobj) {
//		return EcoreUtil.copy(eobj);
//	}
//
//	@SuppressWarnings("unchecked")
//	public static <T extends EObject> T deepClone(EObject me, T eobj) {
//		var copier = new EcoreUtil.Copier();
//		var clone = (T) copier.copy(eobj);
//		copier.copyReferences();
//		return clone;
//	}
//}

package cipm.consistency.fluentapi.gen.methods;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import cipm.consistency.fluentapi.gen.FluentAPICurrentElementReferenceGenerator;
import cipm.consistency.fluentapi.gen.FluentAPIRootAPIReferenceGenerator;

public final class AbstractInitialisationMethods {
	/**
	 * @return An instance of elementCls
	 */
	public static void newElement(EObject me) {
		var currentElementRef = me.eClass().getEStructuralFeature(FluentAPICurrentElementReferenceGenerator.getCurrentElementReferenceName());
		var elementCls = (EClass) currentElementRef.getEType();
		me.eSet(currentElementRef,
				elementCls.getEPackage().getEFactoryInstance().create(elementCls));
	}

	public static void resetElement(EObject me) {
		me.eUnset(me.eClass()
				.getEStructuralFeature(FluentAPICurrentElementReferenceGenerator.getCurrentElementReferenceName()));
	}

	public static <T extends EObject> void dropInitialisation(EObject me) {
		var apiObj = ((EObject) me.eGet(me.eClass()
				.getEStructuralFeature(FluentAPIRootAPIReferenceGenerator.getRootAPIReferenceName())));
		// TODO implement
	}

	@SuppressWarnings("unchecked")
	public static <T extends EObject> T createElementNow(EObject me) {
		var currentElementRef = me.eClass().getEStructuralFeature(FluentAPICurrentElementReferenceGenerator.getCurrentElementReferenceName());
		var elem = me.eGet(currentElementRef);
		dropInitialisation(me);
		return (T) elem;
	}

	public static <T extends EObject> T clone(T eobj) {
		return EcoreUtil.copy(eobj);
	}

	@SuppressWarnings("unchecked")
	public static <T extends EObject> T deepClone(T eobj) {
		var copier = new EcoreUtil.Copier();
		var clone = (T) copier.copy(eobj);
		copier.copyReferences();
		return clone;
	}
}

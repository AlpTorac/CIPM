package cipm.consistency.fluentapi.gen.methods;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import api.AbstractInitialisation;
import api.FluentEObjectAPI;

public final class AbstractInitialisationMethods {
	/**
	 * @return An instance of elementCls
	 */
	public static void newElement(AbstractInitialisation me, EClass elementCls) {
		me.setCurrentElement(elementCls.getEPackage().getEFactoryInstance().create(elementCls));
	}

	public static void resetElement(AbstractInitialisation me) {
		me.setCurrentElement(null);
	}

	public static void dropInitialisation(AbstractInitialisation me) {
		toAPI().dropInitialisation(me);
	}

	public static EObject createElementNow(AbstractInitialisation me) {
		var elem = me.getCurrentElement();
		dropInitialisation(me);
		return elem;
	}

	public static <T extends EObject> T clone(AbstractInitialisation me, T eobj) {
		return EcoreUtil.copy(eobj);
	}

	@SuppressWarnings("unchecked")
	public static <T extends EObject> T deepClone(AbstractInitialisation me, T eobj) {
		var copier = new EcoreUtil.Copier();
		var clone = (T) copier.copy(eobj);
		copier.copyReferences();
		return clone;
	}

	public static FluentEObjectAPI toAPI(AbstractInitialisation me) {
		return me.getRootAPI();
	}
}

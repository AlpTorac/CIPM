package cipm.consistency.fluentapi.gen.methods;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import api.AbstractInitialisation;
import api.FluentEObjectAPI;

public final class FluentEObjectAPIMethods {

	@SuppressWarnings("unchecked")
	public static <T> AbstractInitialisation<T> getInitialisationForX(FluentEObjectAPI me, EClass eobjEClass,
			Class<T> eobjCls) {
		var init = getInits(me).get(eobjEClass);
		return (AbstractInitialisation<T>) init.getEPackage().getEFactoryInstance().create(init);
	}

	public static void dropInitialisation(FluentEObjectAPI me, AbstractInitialisation init) {
		getInitList(me).get(init.getInitialisedEClass()).remove(init);
	}

	public static <T extends EObject> T clone(FluentEObjectAPI me, T eobj) {
		return getInitialisationForX(me, eobj.eClass(), eobj.getClass()).clone(eobj);
	}

	public static <T extends EObject> T deepClone(FluentEObjectAPI me, T eobj) {
		return getInitialisationForX(me, eobj.eClass(), eobj.getClass()).deepClone(eobj);
	}

	public static <T> AbstractInitialisation<T> modifyElement(FluentEObjectAPI me, T eobj) {
		var init = getInitialisationForX(me, eobj.eClass(), eobj.getClass());
		init.setCurrentElement(eobj);
		return init;
	}

	public static <T> AbstractInitialisation<T> modifyElementClone(FluentEObjectAPI me, T eobj) {
		return modifyElement(me, clone(eobj));
	}

	public static <T> AbstractInitialisation<T> modifyElementDeepClone(FluentEObjectAPI me, T eobj) {
		return modifyElement(me, deepClone(eobj));
	}

	public static <T> AbstractInitialisation<T> newElement(FluentEObjectAPI me, EClass eobjEClass) {
		var init = getInitialisationForX(me, eobjEClass, eobjEClass.getInstanceClass());
		init.newElement();
		return init;
	}

	public static <T> AbstractInitialisation<T> continueElement(FluentEObjectAPI me, EClass eobjEClass) {
		var initList = getInitList(me).get(eobjEClass);
		return initList.get(initList.size() - 1);
	}

	public static void withInitPackage(FluentEObjectAPI me, EPackage initPackage) {
		for (var cls : initPackage.getEClassifiers()) {
			if (cls instanceof EClass) {
				var eobjCls = ((AbstractInitialisation) cls.getEPackage().getEFactoryInstance().create(cls))
						.getInitialisedEClass();
				getInits(me).put(eobjCls, cls);
			}
		}
	}

	private static Map<EClass, EClass> getInits(FluentEObjectAPI me) {
		return (Map<EClass, EClass>) me.getInits();
	}

	private static Map<EClass, List<AbstractInitialisation>> getInitList(FluentEObjectAPI me) {
		return (Map<EClass, List<AbstractInitialisation>>) me.getInitList();
	}
}

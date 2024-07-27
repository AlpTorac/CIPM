package cipm.consistency.fitests.similarity.java.initialiser;

import org.eclipse.emf.ecore.EObject;

public interface IInitialiser {
	/**
	 * @return A fresh instance of this initialiser's class.
	 */
	public IInitialiser newInitialiser();

	/**
	 * Attempts to instantiate the class denoted by this {@link IInitialiser}'s
	 * name. Depending on the return type, additional setup may be necessary.
	 */
	public Object instantiate();

	/**
	 * Attempts to initialise obj, so that it is "valid".
	 */
	public boolean initialise(Object obj);

	/**
	 * Checks whether a given {@link IInitialiser} class has any methods that modify
	 * given {@link EObject} instances.
	 */
	public static boolean hasModificationMethods(Class<? extends IInitialiser> initCls) {
		if (initCls == null) {
			return false;
		}

		var methods = initCls.getDeclaredMethods();

		if (methods.length > 0) {
			for (var met : methods) {
				/*
				 * Instead of using a naming convention for modification methods, use the fact
				 * that modification methods take the EObject instance as a parameter, which
				 * initCls is also capable of instantiating.
				 */
				for (var p : met.getParameters()) {
					var pType = p.getType();
					if (EObject.class.isAssignableFrom(pType) && isInitialiserFor(initCls, pType)) {
						return true;
					}
				}
			}
		}

		return false;
	}

	/**
	 * A variant of {@link #hasModificationMethods(Class)} for {@link EObject}
	 * instances. <br>
	 * <br>
	 * Extracts {@link EObject} class object from init.
	 */
	public static boolean hasModificationMethods(IInitialiser init) {
		return hasModificationMethods(init.getClass());
	}

	/**
	 * @return True, if initCls is an initaliser class, which is meant to
	 *         instantiate objects of class objClass. An initialiser class is
	 *         assumed to be able to instantiate the class objClass, if it declares
	 *         a method, whose return type is objClass and which has no parameters.
	 *         For the result to be true, initCls has to be able to instantiate
	 *         exactly objClass.
	 */
	public static boolean isInitialiserFor(Class<? extends IInitialiser> initCls, Class<?> objClass) {
		if (objClass == null) {
			return false;
		}

		var methods = initCls.getDeclaredMethods();

		for (var m : methods) {
			/*
			 * Instead of using name checks or annotations, use the fact that the
			 * instantiation method should have the return type objClass and that it should
			 * take no parameters.
			 * 
			 * Internally generated methods caused by covariant return types must still be
			 * filtered out, if initCls is an interface.
			 * 
			 * Also, make sure that the inspected methods are not generated internally by
			 * Java. This DOES NOT filter the methods in interfaces as desired, because of
			 * the way extending interfaces works in Java.
			 */
			if (!m.isBridge() && !m.isSynthetic() && m.getReturnType().getSimpleName().equals(objClass.getSimpleName())
					&& m.getParameters().length == 0) {

				if (initCls.isInterface()) {
					var ifc = initCls.getInterfaces();
					for (var i : ifc) {
						for (var mInner : i.getDeclaredMethods()) {
							/*
							 * Check for covariant methods in the directly extended interfaces.
							 */
							if (m.getName().equals(mInner.getName()) && mInner.getParameters().length == 0
									&& m.getReturnType().equals(mInner.getReturnType())) {
								return false;
							}
						}
					}
				}

				return true;
			}
		}

		return false;
	}

	/**
	 * A variant of {@link #isInitialiserFor(Class, Class)}, where initCls is
	 * extracted from init.
	 */
	public static boolean isInitialiserFor(IInitialiser init, Class<?> objClass) {
		return isInitialiserFor(init.getClass(), objClass);
	}

	/**
	 * The dynamic variant of {@link #hasModificationMethods(Class)}. <br>
	 * <br>
	 * Uses the class of this instance.
	 */
	public default boolean hasModificationMethods() {
		return hasModificationMethods(this.getClass());
	}

	/**
	 * The dynamic variant of {@link #isInitialiserFor(IInitialiser, Class)}. <br>
	 * <br>
	 * Uses this initialiser as init.
	 */
	public default boolean isInitialiserFor(Class<?> objClass) {
		return isInitialiserFor(this, objClass);
	}
}
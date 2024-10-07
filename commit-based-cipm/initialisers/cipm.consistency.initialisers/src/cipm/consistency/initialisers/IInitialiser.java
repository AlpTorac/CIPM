package cipm.consistency.initialisers;

import java.util.function.BiFunction;

/**
 * An interface to be implemented by initialisers. Initialisers are interfaces
 * or classes, which are meant to instantiate objects ({@link #instantiate()}).
 * For intuition, their names can be used to denote what they instantiate. <br>
 * <br>
 * Initialisers can also implement (default) methods that modify their
 * designated objects. It is suggested to declare the return types of such
 * modification methods as "boolean". This enables returning true or false to
 * ensure that the method actually worked as intended and made the modifications
 * it was meant to do. It is also possible to extract this behaviour into
 * additional assertion methods. <br>
 * <br>
 * <b>Modification methods DO NOT check, if the object that is being modified is
 * null. Attempting to modify null objects will result in EXCEPTIONS being
 * thrown. They only have null checks for the other passed parameters, which are
 * used to perform the modifications.</b> The reason behind this is that it is
 * important to know, if a null object is attempted to be modified, since this
 * should never happen.<br>
 * <br>
 * It is recommended to separate instantiation and initialisation (modification)
 * methods, as doing so will allow using the individual methods in
 * sub-interfaces and sub-classes. Implementing initialisers similar to the way
 * the objects are implemented, which they instantiate, may make initialisers
 * more flexible and ease implementing them. <br>
 * <br>
 * A further suggestion is to not declare attributes in the concrete
 * implementors, so that all functionality is present in form of methods. This
 * alleviates having to declare unnecessary attributes in sub-types and makes
 * overriding behaviour easier. If initialisation of the instantiated objects is
 * complex, consider realising it in form of initialiser adaptation strategies.
 * <br>
 * <br>
 * This interface also contains some static utility methods.
 * 
 * @author Alp Torac Genc
 * @see {@link IInitialiserBase}
 */
public interface IInitialiser {
	/**
	 * Can be used to create a (deep) copy of this.
	 * 
	 * @return A fresh instance of this initialiser's class.
	 */
	public IInitialiser newInitialiser();

	/**
	 * Attempts to instantiate the class this {@link IInitialiser} is designated
	 * for. Depending on the returned object, additional initialisation may be
	 * necessary.
	 */
	public Object instantiate();

	/**
	 * Attempts to initialise obj, so that it is "valid". <br>
	 * <br>
	 * <b>It is recommended to only use this method where necessary, as it may
	 * introduce additional modifications that are not obvious from outside.</b>
	 * 
	 * @param obj The object that will be made valid
	 */
	public boolean initialise(Object obj);

	/**
	 * Checks whether a given {@link IInitialiser} class directly declares any
	 * methods that modify given object instances.
	 */
	public static boolean declaresModificationMethods(Class<? extends IInitialiser> initCls) {
		if (initCls == null) {
			return false;
		}

		var methods = initCls.getDeclaredMethods();

		/*
		 * Instead of using a naming convention for modification methods, use the fact
		 * that modification methods take an object instance obj as a parameter, where
		 * initCls is capable of instantiating obj.
		 */
		for (var met : methods) {

			// A modification method must at least take obj as a parameter to modify it
			if (met.getParameterCount() <= 0)
				continue;

			// One of the parameters has to have the exact type of obj
			// initCls should thus be able to instantiate the type obj
			for (var p : met.getParameters()) {
				var pType = p.getType();
				if (isInitialiserFor(initCls, pType)) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * A variant of {@link #declaresModificationMethods(Class)} for
	 * {@link IInitialiser} instances. <br>
	 * <br>
	 * Uses the type of the given parameter init.
	 */
	public static boolean declaresModificationMethods(IInitialiser init) {
		return init != null && declaresModificationMethods(init.getClass());
	}

	/**
	 * @return True, if initCls is an initialiser type, which is meant to
	 *         instantiate objects of class objClass. An initialiser class is
	 *         assumed to be able to instantiate the class objClass, if it has a
	 *         method (instantiation method), whose return type is objClass and
	 *         which has no parameters. Methods inherited by initCls will also be
	 *         inspected. <br>
	 *         <br>
	 *         For the result to be true, initCls has to be able to instantiate
	 *         <i><b>exactly</b></i> objClass, i.e. the return type of the
	 *         instantiation method has to be <i><b>exactly</b></i> objClass.
	 */
	public static boolean isInitialiserFor(Class<? extends IInitialiser> initCls, Class<?> objClass) {
		if (initCls == null || objClass == null) {
			return false;
		}

		/*
		 * Count inherited methods as well, in order to allow initialisers to be
		 * extended without having to explicitly declare/override their instantiation
		 * method.
		 */
		var methods = initCls.getMethods();

		for (var m : methods) {
			/*
			 * Instead of using name checks or annotations, use the fact that the
			 * instantiation method should have the return type objClass and that it should
			 * take no parameters.
			 * 
			 * Also, make sure that the inspected methods are not generated internally by
			 * Java.
			 */
			if (!m.isBridge() && !m.isSynthetic() && m.getReturnType().equals(objClass)
					&& m.getParameters().length == 0) {
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
		return init != null && objClass != null && isInitialiserFor(init.getClass(), objClass);
	}

	/**
	 * The dynamic variant of {@link #declaresModificationMethods(Class)}. <br>
	 * <br>
	 * Uses the class of this instance.
	 */
	public default boolean declaresModificationMethods() {
		return declaresModificationMethods(this.getClass());
	}

	/**
	 * The dynamic variant of {@link #isInitialiserFor(IInitialiser, Class)}. <br>
	 * <br>
	 * Uses this initialiser as init.
	 */
	public default boolean isInitialiserFor(Class<?> objClass) {
		return objClass != null && isInitialiserFor(this, objClass);
	}

	/**
	 * A helper method for implementors, which provides them with a template for
	 * versions of their methods, which take arrays of parameters rather than
	 * singular ones, and perform multiple modifications. <br>
	 * <br>
	 * <b>If addFunction returns false for an element x of xs, the method will FAIL
	 * EARLY and return false.</b> This means, addFunction WILL NOT be called for
	 * the remaining xs once it fails. <br>
	 * <br>
	 * Because of this, it is important to perform modifications one by one, if
	 * performing the said modifications is expected to fail for some x. <br>
	 * <br>
	 * This method is not intended to be used directly from outside.
	 * 
	 * @param <T>         The type of the object being modified
	 * @param <X>         The parameter passed to the modification function
	 *                    (addFunction)
	 * @param obj         The object being modified. {@code obj == null} will cause
	 *                    null pointer exceptions, if xs has at least one element.
	 * @param xs          Array of parameters that will be passed to addFunction
	 * @param addFunction The modification function that will be run on obj, using
	 *                    xs as parameters (one addFunction call each x in xs)
	 * 
	 * @return
	 *         <ul>
	 *         <li>True, if either:
	 *         <ul>
	 *         <li>xs is null (because no modifications were performed and nothing
	 *         can fail)
	 *         <li>All modification method calls returned true (i.e. all
	 *         modifications were successfully performed)
	 *         </ul>
	 *         <li>Otherwise false, i.e. if {@code xs != null} and a modification
	 *         method call returned false.
	 *         </ul>
	 */
	public default <T extends Object, X extends Object> boolean doMultipleModifications(T obj, X[] xs,
			BiFunction<T, X, Boolean> addFunction) {
		if (xs != null) {
			for (var x : xs) {
				if (!addFunction.apply(obj, x))
					return false;
			}
		}

		return true;
	}
}
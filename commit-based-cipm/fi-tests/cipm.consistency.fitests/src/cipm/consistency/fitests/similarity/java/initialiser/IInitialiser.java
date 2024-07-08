package cipm.consistency.fitests.similarity.java.initialiser;

public interface IInitialiser {
	/**
	 * @return A fresh instance of this initialiser's class.
	 */
	public IInitialiser newInitialiser();

	/**
	 * Attempts to instantiate the class denoted by this {@link IInitialiser}'s name.
	 * Depending on the return type, additional setup may be necessary.
	 */
	public Object instantiate();
	
	/**
	 * Attempts to initialise obj, so that it is "valid".
	 */
	public boolean initialise(Object obj);

	public static boolean hasModificationMethods(Class<? extends IInitialiser> initCls) {
		if (initCls == null) {
			return false;
		}
		
		var methods = initCls.getDeclaredMethods();
		
		if (methods.length > 0) {
			for (var met : methods) {
				if (met.getAnnotation(ModificationMethod.class) != null) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public static boolean hasModificationMethods(IInitialiser init) {
		return hasModificationMethods(init.getClass());
	}
	
	public static boolean isInitialiserFor(Class<? extends IInitialiser> initCls, Class<?> objClass) {
		if (objClass == null) {
			return false;
		}
		
		try {
			return initCls.getMethod("instantiate").getReturnType().equals(objClass);
		} catch (NoSuchMethodException | SecurityException e) {
			return false;
		}
	}
	
	public static boolean isInitialiserFor(IInitialiser init, Class<?> objClass) {
		return isInitialiserFor(init.getClass(), objClass);
	}
	
	public default boolean hasModificationMethods() {
		return hasModificationMethods(this.getClass());
	}
	
	public default boolean isInitialiserFor(Class<?> objClass) {
		return isInitialiserFor(this, objClass);
	}
 }
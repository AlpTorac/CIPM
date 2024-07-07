package cipm.consistency.fitests.similarity.java.initialiser;

public interface IInitialiser {
	/**
	 * @return A fresh instance of this initialiser's class.
	 */
	public IInitialiser newInitialiser();

	/**
	 * Attempts to instantiate the class denoted by this {@link IInitialiser}'s name.
	 */
	public Object instantiate();

	public static boolean hasModificationMethods(Class<? extends IInitialiser> cls) {
		if (cls == null) {
			return false;
		}
		
		var methods = cls.getDeclaredMethods();
		
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
	
	public static boolean isInitialiserFor(Class<? extends IInitialiser> initCls, Class<?> obj) {
		if (obj == null) {
			return false;
		}
		
		try {
			return initCls.getMethod("instantiate").getReturnType().equals(obj);
		} catch (NoSuchMethodException | SecurityException e) {
			return false;
		}
	}
	
	public static boolean isInitialiserFor(IInitialiser init, Class<?> obj) {
		return isInitialiserFor(init.getClass(), obj);
	}
	
	public default boolean hasModificationMethods() {
		return hasModificationMethods(this.getClass());
	}
	
	public default boolean isInitialiserFor(Class<?> obj) {
		return isInitialiserFor(this, obj);
	}
 }
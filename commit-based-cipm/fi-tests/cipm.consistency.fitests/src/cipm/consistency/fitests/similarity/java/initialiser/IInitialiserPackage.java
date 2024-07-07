package cipm.consistency.fitests.similarity.java.initialiser;

import java.util.ArrayList;
import java.util.Collection;

public interface IInitialiserPackage {
	public default Collection<IInitialiser> getInitialiserInstances() {
		return this.initCol();
	}

	public default Collection<Class<? extends IInitialiser>> getInitialiserClasses() {
		return this.initCol();
	}

	public default Collection<IInitialiserPackage> getSubPackages() {
		return this.initCol();
	}
	
	public default <T extends Object> Collection<T> initCol() {
		return new ArrayList<T>();
	}
	
	public default <T extends Object> Collection<T> initCol(T[] elems) {
		Collection<T> res = this.initCol();
		
		for (var e : elems) {
			res.add(e);
		}
		
		return res;
	}
	
	public default Collection<IInitialiserPackage> getAllSubPackages() {
		var result = this.getSubPackages();
		
		for (var pac : this.getSubPackages()) {
			result.addAll(pac.getSubPackages());
		}
		
		return result;
	}
	
	public default Collection<IInitialiser> getAllInitialiserInstances() {
		var result = this.getInitialiserInstances();
		
		for (var pac : this.getAllSubPackages()) {
			result.addAll(pac.getInitialiserInstances());
		}
		
		return result;
	}
	
	public default Collection<Class<? extends IInitialiser>> getAllInitialiserClasses() {
		var result = this.getInitialiserClasses();
		
		for (var pac : this.getAllSubPackages()) {
			result.addAll(pac.getInitialiserClasses());
		}
		
		return result;
	}
	
	public default Class<? extends IInitialiser> getInitialiserClsFor(Class<?> cls) {
		var initClss = this.getAllInitialiserClasses();
		
		for (var initCls : initClss) {
			if (IInitialiser.isInitialiserFor(initCls, cls)) {
				return initCls;
			}
		}
		
		return null;
	}
	
	public default IInitialiser getInitialiserInstanceFor(Class<?> cls) {
		var init = this.getAllInitialiserInstances();
		
		for (var i : init) {
			if (i.isInitialiserFor(cls)) {
				return i;
			}
		}
		
		return null;
	}
}

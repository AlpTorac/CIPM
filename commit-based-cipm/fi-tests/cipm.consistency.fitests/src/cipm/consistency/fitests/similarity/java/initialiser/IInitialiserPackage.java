package cipm.consistency.fitests.similarity.java.initialiser;

import java.util.ArrayList;
import java.util.Collection;

public interface IInitialiserPackage {
	public default Collection<EObjectInitialiser> getInitialisers() {
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
	
	public default Collection<EObjectInitialiser> getAllInitialisers() {
		var result = this.getInitialisers();
		
		for (var pac : this.getAllSubPackages()) {
			result.addAll(pac.getInitialisers());
		}
		
		return result;
	}
}

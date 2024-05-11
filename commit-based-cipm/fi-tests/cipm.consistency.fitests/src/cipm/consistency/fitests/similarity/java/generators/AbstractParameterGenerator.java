package cipm.consistency.fitests.similarity.java.generators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractParameterGenerator<T> {
	private List<T> objList;
	
	public AbstractParameterGenerator() {
		this.objList = new ArrayList<T>();
	}
	
	protected void addToList(T obj) {
		this.objList.add(obj);
	}
	
	public void reset() {
		this.objList.clear();
	}
	
	public boolean removeFromList(T obj) {
		return this.objList.remove(obj);
	}
	
	/**
	 * <b>Use {@link #cloneObjAt(int)}, if the objects in
	 * {@link #objList} are not immutable</b>
	 * 
	 * @return The original element at the given index
	 */
	public T getObjAt(int index) {
		return this.objList.get(index);
	}
	
	/**
	 * @return A clone of the element at the given index
	 */
	public T cloneObjAt(int index) {
		return this.getObjAt(index);
	}
	
	/**
	 * Creates an instance of T with neither initialisation
	 * nor adding to the list.
	 */
	protected abstract T createElement();
	
	/**
	 * Generates an element that is different compared to others,
	 * adds it to {@link #objList} and then returns a clone of it.
	 */
	public T generateElement() {
		T result = this.createElement();
		this.addToList(result);
		return result;
	}
	
	public Collection<T> generateElements(int count) {
		var elems = new ArrayList<T>();
		
		for (int i = 0; i < count; i++) {
			elems.add(this.generateElement());
		}
		
		return elems;
	}
}
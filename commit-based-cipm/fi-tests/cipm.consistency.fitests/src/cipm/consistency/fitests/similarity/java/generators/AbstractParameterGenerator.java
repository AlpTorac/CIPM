package cipm.consistency.fitests.similarity.java.generators;

import java.util.ArrayList;
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
	protected abstract T createDefaultElement();
	
	/**
	 * Generates an element that is different compared to others,
	 * adds it to {@link #objList} and then returns a clone of it.
	 */
	public T generateDefaultElement() {
		T result = this.createDefaultElement();
		this.addToList(result);
		return result;
	}
	
	/*
	 * Since T is in any case an instance of Object,
	 * the type cast at return is safe.
	 */
	@SuppressWarnings("unchecked")
	public T[] generateDefaultElements(int count) {
		var elems = new ArrayList<T>();
		
		for (int i = 0; i < count; i++) {
			elems.add(this.generateDefaultElement());
		}
		
		return (T[]) elems.toArray(Object[]::new);
	}
}
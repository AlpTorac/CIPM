package cipm.consistency.fitests.similarity.java.action;

public interface IDevAction<T> {
	public boolean apply(T obj);
	
	public default boolean canBePerformed(T obj) {
		return obj != null;
	}

	public IDevAction<T> newActionInstance();
	public IDevAction<T> cloneAction();
}

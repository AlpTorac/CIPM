package cipm.consistency.fitests.similarity.java.action;

public interface IDevAction<T> {
	public boolean apply(T obj);
	
	public default boolean canBePerformed(T obj) {
		return obj != null;
	}

	public IDevAction<T> newActionInstance();
	public IDevAction<T> cloneAction();

	public default ICompositeDevAction<T> asCompositeAction() {
		if (this instanceof ICompositeDevAction) {
			return (ICompositeDevAction<T>) this;
		} else {
			var wrappedAction = new CustomCompositeDevAction<T>();
			wrappedAction.addAction(this.cloneAction());
			return wrappedAction;
		}
	}

	public default ICompositeDevAction<T> then(IDevAction<? extends T> proceedingAction) {
		var result = this.cloneAction().asCompositeAction();
		result.addAction(proceedingAction);
		return result;
	}
}

package cipm.consistency.fitests.similarity.java.action;

import java.util.Collection;

public interface ICompositeDevAction<T> extends IDevAction<T> {
	@Override
	public ICompositeDevAction<T> newActionInstance();
	@Override
	public default ICompositeDevAction<T> cloneAction() {
		var clone = this.newActionInstance();

		for (var action : this.cloneActions()) {
			clone.addAction(action);
		}

		return clone;
	}
	public Collection<IDevAction<? extends T>> cloneActions();

	public boolean addAction(IDevAction<? extends T> proceedingAction);

	public int getProceedingActionCount();
}

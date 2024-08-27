package cipm.consistency.fitests.similarity.java.action;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EObject;

public abstract class AbstractCompositeDevAction<T> implements ICompositeDevAction<T> {

	/**
	 * An unsorted collection that will be used to store actions, which will be
	 * executed directly after this action. The returned collection should not
	 * internally modify the order of actions, i.e. it should not sort them or
	 * re-organise them.
	 */
	private Collection<IDevAction<? extends T>> proceedingActions;

	public AbstractCompositeDevAction() {
		this.proceedingActions = new ArrayList<IDevAction<? extends T>>();
	}

	protected boolean isContained(IDevAction<? extends T> action) {
		return this.proceedingActions.contains(action);
	}

	@Override
	public boolean canBePerformed(T obj) {
		return this.proceedingActions.stream().map((a) -> ((IDevAction<T>) a).canBePerformed(obj)).reduce(true,
				(lhs, rhs) -> lhs && rhs);
	}

	@Override
	public boolean apply(T obj) {
		return this.canBePerformed(obj)
				&& this.proceedingActions.stream().map((a) -> ((IDevAction<T>) a).apply(obj)).reduce(true, (lhs, rhs) -> lhs && rhs);
	}

	@Override
	public boolean addAction(IDevAction<? extends T> action) {
		if (!this.isContained(action)) {
			this.proceedingActions.add(action);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int getProceedingActionCount() {
		return this.proceedingActions.size();
	}

	@Override
	public Collection<IDevAction<? extends T>> cloneActions() {
		var actionClones = new ArrayList<IDevAction<? extends T>>();

		for (var action : this.proceedingActions) {
			actionClones.add(action.cloneAction());
		}

		return actionClones;
	}
}

package cipm.consistency.fitests.similarity.java.action;

public final class CustomCompositeDevAction<T> extends AbstractCompositeDevAction<T> {
	@Override
	public ICompositeDevAction<T> newActionInstance() {
		return new CustomCompositeDevAction<T>();
	}
}

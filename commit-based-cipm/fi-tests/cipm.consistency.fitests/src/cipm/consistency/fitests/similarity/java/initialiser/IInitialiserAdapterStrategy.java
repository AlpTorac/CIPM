package cipm.consistency.fitests.similarity.java.initialiser;

public interface IInitialiserAdapterStrategy {
	public boolean apply(IInitialiser init, Object obj);
	
	public IInitialiserAdapterStrategy newStrategy();
}

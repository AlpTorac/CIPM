package cipm.consistency.vsum.test;

public abstract class AbstractFITestResourceGenerator extends AbstractRepoTest {
	/**
	 * The object responsible for generating Java-Models.
	 * 
	 * Has to be instantiated via
	 * {@link FITestResourceGenerator#init(String, String)}
	 */
	private FITestResourceGenerator resGen = null;

	@Override
	public void tearDown() throws Exception {
		super.tearDown();
		this.resGen.cleanUp();
	}

	/**
	 * Instantiates a {@link FITestResourceGenerator} but does not initialise it.
	 * Make sure to call {@link #initResGen(Object)} at the start of each test to
	 * initialise the generated instance.
	 */
	protected FITestResourceGenerator createResGen() {
		return new FITestResourceGenerator();
	}

	/**
	 * Computes a unique suffix for the model to be generated based on the given
	 * parameter, so that generating multiple models is allowed.
	 * 
	 * @param commitHashIdentifier see {@link HasRepoSettings#getCommitHash(Object)}
	 * 
	 * @return The suffix of the file name (excluding extension)
	 */
	protected abstract String getTestModelFileIdentifier(Object commitHashIdentifier);

	/**
	 * @param commitHashIdentifier see {@link HasRepoSettings#getCommitHash(Object)}
	 * 
	 * @return The full file name (excluding extension)
	 */
	protected String getTestModelFileNameWOExtension(Object commitHashIdentifier) {
		return this.getTestModelFileNamePrefix() + this.getTestModelFileIdentifier(commitHashIdentifier);
	}

	/**
	 * Initialises {@link #resGen}
	 * 
	 * @param commitHashIdentifier see {@link HasRepoSettings#getCommitHash(Object)}
	 */
	protected void initResGen(Object commitHashIdentifier) {
		if (this.resGen != null) {
			this.resGen.cleanUp();
		}

		this.resGen = this.createResGen();
		this.resGen.init(this.getTestType(), this.getTestModelFileExtension());
		this.resGen.addModelGenerationHook(this.getTestModelFileNameWOExtension(commitHashIdentifier));
	}

	/**
	 * @return The extension of the model file to be generated
	 */
	protected String getTestModelFileExtension() {
		return ".javaxmi";
	}

	/**
	 * @return The common prefix of the name of the model file to be generated
	 */
	protected String getTestModelFileNamePrefix() {
		return "JavaModel-test";
	}

	/**
	 * Attempts to generate a model for the commit hash with the given identifier
	 * 
	 * @param commitHashIdentifier see {@link HasRepoSettings#getCommitHash(Object)}
	 */
	protected void generateResourcesFor(Object commitHashIdentifier) throws Exception {
		// Only the last commit is relevant here
		this.resGen.generateResourceWhile(
				() -> executePropagationAndEvaluation(null, this.getCommitHash(commitHashIdentifier), 0));
	}
}

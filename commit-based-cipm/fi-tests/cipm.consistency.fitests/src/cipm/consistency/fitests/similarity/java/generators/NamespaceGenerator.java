package cipm.consistency.fitests.similarity.java.generators;

import java.util.ArrayList;

public class NamespaceGenerator extends AbstractParameterGenerator<String[]> {
	private NameGenerator nGen = new NameGenerator() {{
		setNamePrefix("ns");
	}};
	
	@Override
	public void reset() {
		super.reset();
		this.nGen.reset();
	}
	
	@Override
	protected String[] createDefaultElement() {
		return this.generateNamespace(3);
	}
	
	protected String generateName() {
		return this.nGen.generateDefaultElement();
	}
	
	public String[] generateNamespace(int nsCount) {
		var result = new String[nsCount];
		
		for (int i = 0; i < nsCount; i++) {
			result[i] = this.generateName();
		}
		
		return result;
	}
	
	/**
	 * Complements the given namespace with additional namespaces.
	 */
	public String[] complementNamespace(String[] ns, int additionalNsCount) {
		var result = new ArrayList<String>();
		
		for (var n : ns) {
			result.add(n);
		}
		
		for (int i = 0; i < additionalNsCount; i++) {
			result.add(this.generateName());
		}
		
		return result.toArray(String[]::new);
	}
}

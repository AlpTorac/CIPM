package cipm.consistency.fitests.similarity.eobject;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.resource.ResourceSet;

public abstract class AbstractResourceParsingStrategy {
	private ResourceSet resourceSet;
	private final Set<String> exclusionPatterns = new HashSet<>();

	public void setResourceSet(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}

	public ResourceSet getResourceSet() {
		return this.resourceSet;
	}

	public void addExclusionPattern(String pattern) {
		this.exclusionPatterns.add(pattern);
	}

	public void removeExclusionPattern(String pattern) {
		this.exclusionPatterns.remove(pattern);
	}

	public void clearExclusionPatterns() {
		this.exclusionPatterns.clear();
	}

	public abstract ResourceSet parseModelResource(Path modelDir);
}

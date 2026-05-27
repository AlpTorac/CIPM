package cipm.consistency.fluentapi.gen.methods.mark;

import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

public class FluentAPIMarkContainer {
	private final Map<Object, EObject> markToObj = new LinkedHashMap<>();

	public void mark(Object markKey, EObject markVal) {
		markToObj.put(markKey, markVal);
	}

	public EObject unmark(Object markKey) {
		return markToObj.remove(markKey);
	}

	public EObject getMarked(Object markKey) {
		return markToObj.getOrDefault(markKey, null);
	}
}

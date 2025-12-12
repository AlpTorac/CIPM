package cipm.consistency.fluentapi.gen.context;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FluentAPIContext {
	/**
	 * Key: Instance, whose context is to be kept
	 * 
	 * Value: The context associated with the key
	 */
	private final Map<Object, List<Object>> contextMap = new LinkedHashMap<>();

	private boolean initialiseContextListIfNull(Object keepContextFor) {
		if (!contextMap.containsKey(keepContextFor)) {
			contextMap.put(keepContextFor, new ArrayList<Object>());
			return true;
		}
		return false;
	}

	public boolean addContextFor(Object keepContextFor, Object context) {
		initialiseContextListIfNull(keepContextFor);
		return contextMap.get(keepContextFor).add(context);
	}

	public boolean addUniqueContextFor(Object keepContextFor, Object context) {
		initialiseContextListIfNull(keepContextFor);
		if (getContextOccurrenceCount(keepContextFor, context) == 0) {
			return addContextFor(keepContextFor, context);
		}
		return false;
	}

	public List<Object> getContextFor(Object keepContextFor) {
		return contextMap.containsKey(keepContextFor) ? List.copyOf(contextMap.get(keepContextFor)) : List.of();
	}

	public long getContextOccurrenceCount(Object keepContextFor, Object context) {
		var contextList = getContextFor(keepContextFor);
		return contextList.stream().filter((c) -> c.equals(context)).count();
	}
}

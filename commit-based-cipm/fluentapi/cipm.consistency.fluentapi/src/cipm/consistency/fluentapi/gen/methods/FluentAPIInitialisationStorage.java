package cipm.consistency.fluentapi.gen.methods;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

public final class FluentAPIInitialisationStorage {
	private static final Set<EClass> inits = new LinkedHashSet<>();
	private static final List<EObject> ongoingInits = new ArrayList<>();

	public static List<EClass> getInits() {
		return List.copyOf(inits);
	}

	public static List<EObject> getOngoingInits() {
		return List.copyOf(ongoingInits);
	}

	public static void dropInitialisation(EObject init) {
		ongoingInits.remove(init);
	}

	public static void addOngoingInitialisation(EObject init) {
		ongoingInits.add(init);
	}

	public static void clear() {
		inits.clear();
		ongoingInits.clear();
	}
}

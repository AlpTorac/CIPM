package cipm.consistency.fluentapi.methods;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

public final class FluentAPIInitialisationStorage {
	private static final List<EObject> ongoingInits = new ArrayList<>();

	public static List<EObject> getOngoingInitialisations() {
		return List.copyOf(ongoingInits);
	}

	public static void dropOngoingInitialisation(EObject init) {
		ongoingInits.remove(init);
	}

	public static void addOngoingInitialisation(EObject init) {
		ongoingInits.add(init);
	}

	public static void clearAllOngoingInitialisations() {
		ongoingInits.clear();
	}
}

package cipm.consistency.fluentapi.gen;

import java.util.List;

import org.eclipse.emf.ecore.EOperation;

public class FluentAPIParameterUtil {
	public static String getSerialisedParametersFor(EOperation op) {
		return String.join(",", op.getEParameters().stream().map((p) -> p.getName()).toArray(String[]::new));
	}

	public static boolean hasClashingMethods(List<EOperation> allOps, EOperation opToCheckForClashes) {
		var clashingMethodExists = false;
		for (var op : allOps) {
			if (!op.getName().equals(opToCheckForClashes.getName()))
				continue;
			if (op.getEParameters().size() != opToCheckForClashes.getEParameters().size())
				continue;

			clashingMethodExists = op.getEParameters().stream()
					.allMatch((opParam) -> opToCheckForClashes.getEParameters().stream()
							.anyMatch((clashOpParam) -> opParam.getName().equals(clashOpParam.getName()) && opParam
									.getEType().getInstanceClass().equals(clashOpParam.getEType().getInstanceClass())));

			if (clashingMethodExists)
				break;
		}
		return clashingMethodExists;
	}
}

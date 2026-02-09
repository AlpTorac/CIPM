package cipm.consistency.fluentapi.gen;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EcorePackage;

public class FluentAPITargetMetamodelGenerationSettings {
	/**
	 * Yields the convenience overloads for methods that use parameters of certain
	 * types. This method contains cases that are independent of the concrete
	 * metamodel. Override
	 * {@link #getMetamodelSpecificParameterOverloads(EClassifier)} for
	 * metamodel-specific cases.
	 * 
	 * @param eClassifier The type of the parameter. Used to determine what
	 *                    overloads are applicable
	 * 
	 * @return List of (Parameter type, new value expression) pairs for method
	 *         overloads in certain cases.
	 */
	public static List<FluentAPIMethodParameterOverload> getGlobalParameterOverloads(EClassifier eClassifier) {
		var pairs = new ArrayList<FluentAPIMethodParameterOverload>();
		if (eClassifier.equals(EcorePackage.Literals.EBIG_INTEGER)) {
			pairs.add(new FluentAPIMethodParameterOverload(EcorePackage.Literals.ELONG,
					"java.math.BigInteger.valueOf(%s)"));
			pairs.add(new FluentAPIMethodParameterOverload(EcorePackage.Literals.EINT,
					"java.math.BigInteger.valueOf(%s)"));
		}
		return pairs;
	}

	/**
	 * Yields the convenience overloads for methods that use parameters of certain
	 * types. This method contains metamodel-specific cases.
	 * 
	 * <p>
	 * Override this method in concrete implementors, if necessary.
	 * 
	 * @return List of (Parameter type, new value expression) pairs for method
	 *         overloads in certain cases.
	 */
	public List<FluentAPIMethodParameterOverload> getMetamodelSpecificParameterOverloads(EClassifier eClassifier) {
		return new ArrayList<>();
	}
}

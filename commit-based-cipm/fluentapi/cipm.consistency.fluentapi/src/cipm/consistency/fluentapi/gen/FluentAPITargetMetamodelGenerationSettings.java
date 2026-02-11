package cipm.consistency.fluentapi.gen;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;

public class FluentAPITargetMetamodelGenerationSettings {
	/*
	 * TODO: See if you can take the original method and generate an overloading
	 * method based on the original method instead
	 */

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
	public static List<FluentAPIMethodOverload> getGlobalParameterOverloads() {
		var pairs = new ArrayList<FluentAPIMethodOverload>();
		pairs.add(new FluentAPIGlobalMethodParameterOverload());
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
	public List<FluentAPIMethodOverload> getMetamodelSpecificParameterOverloads(EOperation op) {
		return new ArrayList<>();
	}
}

package cipm.consistency.fitests.similarity.jamopp.gens;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public abstract class AbstractGenerator {
	public GeneratorOutput generateOutput(EObject lhs, EObject rhs, EStructuralFeature... changedAttrs) {
		return new GeneratorOutput(lhs, rhs, changedAttrs);
	}

	public Collection<GeneratorOutput> createEmptyCol() {
		return new ArrayList<GeneratorOutput>();
	}

	public Collection<GeneratorOutput> generateAll() {
		var col = this.createEmptyCol();

		/*
		 * Find methods that generate GeneratorOutput and take no parameters
		 */
		var generatorMethods = List.of(this.getClass().getMethods()).stream()
				.filter((met) -> met.getParameterCount() == 0)
				.filter((met) -> GeneratorOutput.class.isAssignableFrom(met.getReturnType())
						|| (met.getReturnType().getComponentType() != null
								&& GeneratorOutput.class.isAssignableFrom(met.getReturnType().getComponentType())))
				.toArray(java.lang.reflect.Method[]::new);

		for (var met : generatorMethods) {
			Object output = null;
			try {
				output = met.invoke(this);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
				return null;
			}
			if (output.getClass().isArray()) {
				var outputArr = (Object[]) output;
				for (var o : outputArr) {
					col.add((GeneratorOutput) o);
				}
			} else {
				col.add((GeneratorOutput) output);
			}
		}
		return col;
	}
}

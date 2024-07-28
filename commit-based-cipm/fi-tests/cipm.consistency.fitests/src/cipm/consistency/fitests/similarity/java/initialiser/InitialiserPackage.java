package cipm.consistency.fitests.similarity.java.initialiser;

import java.util.Collection;

import cipm.consistency.fitests.similarity.java.initialiser.annotations.AnnotationsInitialiserPackage;
import cipm.consistency.fitests.similarity.java.initialiser.arrays.ArraysInitialiserPackage;
import cipm.consistency.fitests.similarity.java.initialiser.classifiers.ClassifierInitialiserPackage;
import cipm.consistency.fitests.similarity.java.initialiser.commons.CommonsInitialiserPackage;
import cipm.consistency.fitests.similarity.java.initialiser.containers.ContainersInitialiserPackage;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.ExpressionsInitialiserPackage;
import cipm.consistency.fitests.similarity.java.initialiser.generics.GenericsInitialiserPackage;
import cipm.consistency.fitests.similarity.java.initialiser.imports.ImportsInitialiserPackage;
import cipm.consistency.fitests.similarity.java.initialiser.instantiations.InstantiationsInitialiserPackage;
import cipm.consistency.fitests.similarity.java.initialiser.literals.LiteralsInitialiserPackage;
import cipm.consistency.fitests.similarity.java.initialiser.members.MembersInitialiserPackage;
import cipm.consistency.fitests.similarity.java.initialiser.modifiers.ModifiersInitialiserPackage;
import cipm.consistency.fitests.similarity.java.initialiser.modules.ModulesInitialiserPackage;
import cipm.consistency.fitests.similarity.java.initialiser.operators.OperatorsInitialiserPackage;
import cipm.consistency.fitests.similarity.java.initialiser.parameters.ParametersInitialiserPackage;
import cipm.consistency.fitests.similarity.java.initialiser.references.ReferencesInitialiserPackage;
import cipm.consistency.fitests.similarity.java.initialiser.statements.StatementsInitialiserPackage;
import cipm.consistency.fitests.similarity.java.initialiser.types.TypesInitialiserPackage;
import cipm.consistency.fitests.similarity.java.initialiser.variables.VariablesInitialiserPackage;

/**
 * The topmost implementor of {@link IInitialiserPackage}.
 * <br><br>
 * Includes all implemented initialisers.
 * 
 * @author atora
 */
public class InitialiserPackage implements IInitialiserPackage {
	@Override
	public Collection<IInitialiserPackage> getSubPackages() {
		return this.initCol(new IInitialiserPackage[] {
			new AnnotationsInitialiserPackage(),
			new ArraysInitialiserPackage(),
			new ClassifierInitialiserPackage(),
			new CommonsInitialiserPackage(),
			new ContainersInitialiserPackage(),
			new ExpressionsInitialiserPackage(),
			new GenericsInitialiserPackage(),
			new ImportsInitialiserPackage(),
			new InstantiationsInitialiserPackage(),
			new LiteralsInitialiserPackage(),
			new MembersInitialiserPackage(),
			new ModifiersInitialiserPackage(),
			new ModulesInitialiserPackage(),
			new OperatorsInitialiserPackage(),
			new ParametersInitialiserPackage(),
			new ReferencesInitialiserPackage(),
			new StatementsInitialiserPackage(),
			new TypesInitialiserPackage(),
			new VariablesInitialiserPackage()
		});
	}
}

package cipm.consistency.fitests.similarity.java.eobject.initialiser;

import java.util.Collection;

import cipm.consistency.fitests.similarity.initialiser.IInitialiserPackage;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.annotations.AnnotationsInitialiserPackage;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.arrays.ArraysInitialiserPackage;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.classifiers.ClassifierInitialiserPackage;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.commons.CommonsInitialiserPackage;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.containers.ContainersInitialiserPackage;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.expressions.ExpressionsInitialiserPackage;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.generics.GenericsInitialiserPackage;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.imports.ImportsInitialiserPackage;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.instantiations.InstantiationsInitialiserPackage;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.literals.LiteralsInitialiserPackage;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.members.MembersInitialiserPackage;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.modifiers.ModifiersInitialiserPackage;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.modules.ModulesInitialiserPackage;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.operators.OperatorsInitialiserPackage;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.parameters.ParametersInitialiserPackage;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.references.ReferencesInitialiserPackage;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.statements.StatementsInitialiserPackage;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.types.TypesInitialiserPackage;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.variables.VariablesInitialiserPackage;

/**
 * The topmost implementor of {@link IInitialiserPackage}. <br>
 * <br>
 * Includes all implemented initialisers.
 * 
 * @author atora
 */
public class EObjectInitialiserPackage implements IInitialiserPackage {
	@Override
	public Collection<IInitialiserPackage> getSubPackages() {
		return this.initCol(new IInitialiserPackage[] { new AnnotationsInitialiserPackage(),
				new ArraysInitialiserPackage(), new ClassifierInitialiserPackage(), new CommonsInitialiserPackage(),
				new ContainersInitialiserPackage(), new ExpressionsInitialiserPackage(),
				new GenericsInitialiserPackage(), new ImportsInitialiserPackage(),
				new InstantiationsInitialiserPackage(), new LiteralsInitialiserPackage(),
				new MembersInitialiserPackage(), new ModifiersInitialiserPackage(), new ModulesInitialiserPackage(),
				new OperatorsInitialiserPackage(), new ParametersInitialiserPackage(),
				new ReferencesInitialiserPackage(), new StatementsInitialiserPackage(), new TypesInitialiserPackage(),
				new VariablesInitialiserPackage() });
	}
}

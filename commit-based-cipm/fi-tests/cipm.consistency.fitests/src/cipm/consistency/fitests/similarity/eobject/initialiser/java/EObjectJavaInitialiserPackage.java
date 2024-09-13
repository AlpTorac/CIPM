package cipm.consistency.fitests.similarity.eobject.initialiser.java;

import java.util.Collection;

import cipm.consistency.fitests.similarity.eobject.initialiser.java.annotations.AnnotationsInitialiserPackage;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.arrays.ArraysInitialiserPackage;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.classifiers.ClassifierInitialiserPackage;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.commons.CommonsInitialiserPackage;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.containers.ContainersInitialiserPackage;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.expressions.ExpressionsInitialiserPackage;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.generics.GenericsInitialiserPackage;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.imports.ImportsInitialiserPackage;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.instantiations.InstantiationsInitialiserPackage;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.literals.LiteralsInitialiserPackage;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.members.MembersInitialiserPackage;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.modifiers.ModifiersInitialiserPackage;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.modules.ModulesInitialiserPackage;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.operators.OperatorsInitialiserPackage;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.parameters.ParametersInitialiserPackage;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.references.ReferencesInitialiserPackage;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.statements.StatementsInitialiserPackage;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.types.TypesInitialiserPackage;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.variables.VariablesInitialiserPackage;
import cipm.consistency.fitests.similarity.initialiser.IInitialiserPackage;

/**
 * The topmost implementor of {@link IInitialiserPackage}. <br>
 * <br>
 * Includes all implemented initialisers.
 * 
 * @author atora
 */
public class EObjectJavaInitialiserPackage implements IInitialiserPackage {
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

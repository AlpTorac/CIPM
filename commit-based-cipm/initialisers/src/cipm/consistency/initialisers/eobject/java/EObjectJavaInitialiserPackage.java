package cipm.consistency.initialisers.eobject.java;

import java.util.Collection;

import cipm.consistency.initialisers.IInitialiserPackage;
import cipm.consistency.initialisers.eobject.java.annotations.AnnotationsInitialiserPackage;
import cipm.consistency.initialisers.eobject.java.arrays.ArraysInitialiserPackage;
import cipm.consistency.initialisers.eobject.java.classifiers.ClassifierInitialiserPackage;
import cipm.consistency.initialisers.eobject.java.commons.CommonsInitialiserPackage;
import cipm.consistency.initialisers.eobject.java.containers.ContainersInitialiserPackage;
import cipm.consistency.initialisers.eobject.java.expressions.ExpressionsInitialiserPackage;
import cipm.consistency.initialisers.eobject.java.generics.GenericsInitialiserPackage;
import cipm.consistency.initialisers.eobject.java.imports.ImportsInitialiserPackage;
import cipm.consistency.initialisers.eobject.java.instantiations.InstantiationsInitialiserPackage;
import cipm.consistency.initialisers.eobject.java.literals.LiteralsInitialiserPackage;
import cipm.consistency.initialisers.eobject.java.members.MembersInitialiserPackage;
import cipm.consistency.initialisers.eobject.java.modifiers.ModifiersInitialiserPackage;
import cipm.consistency.initialisers.eobject.java.modules.ModulesInitialiserPackage;
import cipm.consistency.initialisers.eobject.java.operators.OperatorsInitialiserPackage;
import cipm.consistency.initialisers.eobject.java.parameters.ParametersInitialiserPackage;
import cipm.consistency.initialisers.eobject.java.references.ReferencesInitialiserPackage;
import cipm.consistency.initialisers.eobject.java.statements.StatementsInitialiserPackage;
import cipm.consistency.initialisers.eobject.java.types.TypesInitialiserPackage;
import cipm.consistency.initialisers.eobject.java.variables.VariablesInitialiserPackage;

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

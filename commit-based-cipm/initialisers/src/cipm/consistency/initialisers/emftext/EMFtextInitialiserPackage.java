package cipm.consistency.initialisers.emftext;

import java.util.Collection;

import cipm.consistency.initialisers.IInitialiserPackage;
import cipm.consistency.initialisers.emftext.annotations.AnnotationsInitialiserPackage;
import cipm.consistency.initialisers.emftext.arrays.ArraysInitialiserPackage;
import cipm.consistency.initialisers.emftext.classifiers.ClassifierInitialiserPackage;
import cipm.consistency.initialisers.emftext.commons.CommonsInitialiserPackage;
import cipm.consistency.initialisers.emftext.containers.ContainersInitialiserPackage;
import cipm.consistency.initialisers.emftext.expressions.ExpressionsInitialiserPackage;
import cipm.consistency.initialisers.emftext.generics.GenericsInitialiserPackage;
import cipm.consistency.initialisers.emftext.imports.ImportsInitialiserPackage;
import cipm.consistency.initialisers.emftext.instantiations.InstantiationsInitialiserPackage;
import cipm.consistency.initialisers.emftext.literals.LiteralsInitialiserPackage;
import cipm.consistency.initialisers.emftext.members.MembersInitialiserPackage;
import cipm.consistency.initialisers.emftext.modifiers.ModifiersInitialiserPackage;
import cipm.consistency.initialisers.emftext.modules.ModulesInitialiserPackage;
import cipm.consistency.initialisers.emftext.operators.OperatorsInitialiserPackage;
import cipm.consistency.initialisers.emftext.parameters.ParametersInitialiserPackage;
import cipm.consistency.initialisers.emftext.references.ReferencesInitialiserPackage;
import cipm.consistency.initialisers.emftext.statements.StatementsInitialiserPackage;
import cipm.consistency.initialisers.emftext.types.TypesInitialiserPackage;
import cipm.consistency.initialisers.emftext.variables.VariablesInitialiserPackage;

/**
 * The topmost implementor of {@link IInitialiserPackage}. <br>
 * <br>
 * Includes all implemented initialisers.
 * 
 * @author atora
 */
public class EMFtextInitialiserPackage implements IInitialiserPackage {
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

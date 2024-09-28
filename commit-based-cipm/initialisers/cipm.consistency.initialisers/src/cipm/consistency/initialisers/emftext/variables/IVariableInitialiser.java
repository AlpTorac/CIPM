package cipm.consistency.initialisers.emftext.variables;

import org.emftext.language.java.variables.Variable;

import cipm.consistency.initialisers.emftext.generics.ITypeArgumentableInitialiser;
import cipm.consistency.initialisers.emftext.references.IReferenceableElementInitialiser;
import cipm.consistency.initialisers.emftext.types.ITypedElementInitialiser;

/**
 * An interface meant for {@link IInitialiser} implementors that are supposed to
 * create {@link Variable} instances. <br>
 * <br>
 * <b>Note: "createMethodCall..." methods in {@link Variable} do not modify
 * {@link Variable} instances.</b>
 * 
 * @author atora
 */
public interface IVariableInitialiser
		extends IReferenceableElementInitialiser, ITypeArgumentableInitialiser, ITypedElementInitialiser {
	@Override
	public Variable instantiate();
}
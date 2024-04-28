package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.containers.Package;
import org.emftext.language.java.parameters.Parameter;

public interface IParameterInitialiser extends IAnnotableAndModifiableInitialiser, IVariableInitialiser {
	@Override
	public Parameter instantiate();
}

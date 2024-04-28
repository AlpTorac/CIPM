package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.parameters.Parameter;
import org.emftext.language.java.parameters.Parametrizable;

public interface IParametrizableInitialiser extends ICommentableInitialiser {
	@Override
	public Parametrizable instantiate();
	
	public default void addParameter(Parametrizable parametrizable, Parameter param) {
		if (param != null) {
			parametrizable.getParameters().add(param);
			assert parametrizable.getParameters().contains(param);
		}
	}
}

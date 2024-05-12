package cipm.consistency.fitests.similarity.java.initialiser.testable;

import org.emftext.language.java.parameters.Parameter;
import org.emftext.language.java.parameters.Parametrizable;

import cipm.consistency.fitests.similarity.java.initialiser.ICommentableInitialiser;

public interface IParametrizableInitialiser extends ICommentableInitialiser {
	public default void addParameter(Parametrizable parametrizable, Parameter param) {
		if (param != null) {
			parametrizable.getParameters().add(param);
			assert parametrizable.getParameters().contains(param);
		}
	}
	
	public default void addParameters(Parametrizable parametrizable, Parameter[] params) {
		this.addXs(parametrizable, params, (o,p)->this.addParameter(o, p));
	}
}

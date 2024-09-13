package cipm.consistency.fitests.similarity.java.eobject.initialiser.operators;

import org.emftext.language.java.operators.NotEqual;

public interface INotEqualInitialiser extends IEqualityOperatorInitialiser {
	@Override
	public NotEqual instantiate();

}

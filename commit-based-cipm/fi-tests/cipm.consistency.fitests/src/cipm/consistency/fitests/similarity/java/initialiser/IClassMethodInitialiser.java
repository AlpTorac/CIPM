package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.members.ClassMethod;

public interface IClassMethodInitialiser extends IMethodInitialiser, IStatementListContainerInitialiser {
	@Override
	public ClassMethod instantiate();
	
	@Override
	public default ClassMethod minimalInstantiation() {
		return (ClassMethod) IMethodInitialiser.super.minimalInstantiation();
	}
}

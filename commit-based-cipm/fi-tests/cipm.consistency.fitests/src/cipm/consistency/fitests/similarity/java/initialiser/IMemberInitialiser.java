package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.members.Member;

public interface IMemberInitialiser extends INamedElementInitialiser {
	@Override
	public Member instantiate();
}

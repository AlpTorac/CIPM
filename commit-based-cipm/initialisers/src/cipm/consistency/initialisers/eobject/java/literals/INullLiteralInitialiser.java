package cipm.consistency.initialisers.eobject.java.literals;

import org.emftext.language.java.literals.NullLiteral;

public interface INullLiteralInitialiser extends ILiteralInitialiser {
	@Override
	public NullLiteral instantiate();

}

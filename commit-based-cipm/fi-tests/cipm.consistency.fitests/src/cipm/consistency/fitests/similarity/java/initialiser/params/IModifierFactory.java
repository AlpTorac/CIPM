package cipm.consistency.fitests.similarity.java.initialiser.params;

import org.emftext.language.java.modifiers.Abstract;
import org.emftext.language.java.modifiers.Default;
import org.emftext.language.java.modifiers.Final;
import org.emftext.language.java.modifiers.ModifiersFactory;
import org.emftext.language.java.modifiers.Native;
import org.emftext.language.java.modifiers.Private;
import org.emftext.language.java.modifiers.Protected;
import org.emftext.language.java.modifiers.Public;
import org.emftext.language.java.modifiers.Static;
import org.emftext.language.java.modifiers.Strictfp;
import org.emftext.language.java.modifiers.Synchronized;
import org.emftext.language.java.modifiers.Transient;
import org.emftext.language.java.modifiers.Volatile;

// TODO: Move these methods to UsesModifiers and remove this interface and its implementors

public interface IModifierFactory {
	public ModifiersFactory getFactory();
	
	public default Abstract createAbstract() {
		return this.getFactory().createAbstract();
	}
	public default Default createDefault() {
		return this.getFactory().createDefault();
	}
	public default Final createFinal() {
		return this.getFactory().createFinal();
	}
	public default Native createNative() {
		return this.getFactory().createNative();
	}
	public default Private createPrivate() {
		return this.getFactory().createPrivate();
	}
	public default Protected createProtected() {
		return this.getFactory().createProtected();
	}
	public default Public createPublic() {
		return this.getFactory().createPublic();
	}
	public default Static createStatic() {
		return this.getFactory().createStatic();
	}
	public default Strictfp createStrictfp() {
		return this.getFactory().createStrictfp();
	}
	public default Synchronized createSynchronized() {
		return this.getFactory().createSynchronized();
	}
	public default Transient createTransient() {
		return this.getFactory().createTransient();
	}
	public default Volatile createVolatile() {
		return this.getFactory().createVolatile();
	}
}

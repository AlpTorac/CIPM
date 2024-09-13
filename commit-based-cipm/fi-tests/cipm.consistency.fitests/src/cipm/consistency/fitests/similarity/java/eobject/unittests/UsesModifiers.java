package cipm.consistency.fitests.similarity.java.eobject.unittests;

import org.emftext.language.java.modifiers.Abstract;
import org.emftext.language.java.modifiers.Default;
import org.emftext.language.java.modifiers.Final;
import org.emftext.language.java.modifiers.Native;
import org.emftext.language.java.modifiers.Private;
import org.emftext.language.java.modifiers.Protected;
import org.emftext.language.java.modifiers.Public;
import org.emftext.language.java.modifiers.Static;
import org.emftext.language.java.modifiers.Strictfp;
import org.emftext.language.java.modifiers.Synchronized;
import org.emftext.language.java.modifiers.Transient;
import org.emftext.language.java.modifiers.Volatile;

import cipm.consistency.fitests.similarity.java.eobject.initialiser.modifiers.AbstractInitialiser;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.modifiers.DefaultInitialiser;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.modifiers.FinalInitialiser;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.modifiers.NativeInitialiser;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.modifiers.PrivateInitialiser;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.modifiers.ProtectedInitialiser;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.modifiers.PublicInitialiser;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.modifiers.StaticInitialiser;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.modifiers.StrictfpInitialiser;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.modifiers.SynchronizedInitialiser;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.modifiers.TransientInitialiser;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.modifiers.VolatileInitialiser;

/**
 * An interface that can be implemented by tests, which work with
 * {@link Modifier} instances. <br>
 * <br>
 * Contains methods that can be used to create {@link Modifier} instances.
 */
public interface UsesModifiers {
	public default Default createDefault() {
		return new DefaultInitialiser().instantiate();
	}

	public default Final createFinal() {
		return new FinalInitialiser().instantiate();
	}

	public default Native createNative() {
		return new NativeInitialiser().instantiate();
	}

	public default Private createPrivate() {
		return new PrivateInitialiser().instantiate();
	}

	public default Public createPublic() {
		return new PublicInitialiser().instantiate();
	}

	public default Static createStatic() {
		return new StaticInitialiser().instantiate();
	}

	public default Strictfp createStrictfp() {
		return new StrictfpInitialiser().instantiate();
	}

	public default Transient createTransient() {
		return new TransientInitialiser().instantiate();
	}

	public default Abstract createAbstract() {
		return new AbstractInitialiser().instantiate();
	}

	public default Synchronized createSynchronized() {
		return new SynchronizedInitialiser().instantiate();
	}

	public default Volatile createVolatile() {
		return new VolatileInitialiser().instantiate();
	}

	public default Protected createProtected() {
		return new ProtectedInitialiser().instantiate();
	}
}

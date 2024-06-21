package cipm.consistency.fitests.similarity.java.unittests;

import org.emftext.language.java.modifiers.Abstract;
import org.emftext.language.java.modifiers.Protected;
import org.emftext.language.java.modifiers.Synchronized;
import org.emftext.language.java.modifiers.Volatile;

import cipm.consistency.fitests.similarity.java.initialiser.modifiers.AbstractInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.modifiers.ProtectedInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.modifiers.SynchronizedInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.modifiers.VolatileInitialiser;

public interface UsesModifiers {
	public default Abstract createAbstractModifier() {
		return new AbstractInitialiser().instantiate();
	}
	public default Synchronized createSynchronizedModifier() {
		return new SynchronizedInitialiser().instantiate();
	}
	public default Volatile createVolatileModifier() {
		return new VolatileInitialiser().instantiate();
	}
	public default Protected createProtectedModifier() {
		return new ProtectedInitialiser().instantiate();
	}
}

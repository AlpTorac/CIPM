package cipm.consistency.fitests.similarity.jamopp.unittests;

import cipm.consistency.initialisers.jamopp.types.BooleanInitialiser;
import cipm.consistency.initialisers.jamopp.types.ByteInitialiser;
import cipm.consistency.initialisers.jamopp.types.CharInitialiser;
import cipm.consistency.initialisers.jamopp.types.DoubleInitialiser;
import cipm.consistency.initialisers.jamopp.types.FloatInitialiser;
import cipm.consistency.initialisers.jamopp.types.IntInitialiser;
import cipm.consistency.initialisers.jamopp.types.LongInitialiser;
import cipm.consistency.initialisers.jamopp.types.ShortInitialiser;
import cipm.consistency.initialisers.jamopp.types.VoidInitialiser;

/**
 * An interface that can be implemented by tests, which work with
 * {@link PrimitiveType} instances. <br>
 * <br>
 * Contains methods that can be used to create {@link PrimitiveType} instances.
 */
public interface UsesPrimitiveTypes {
	public default org.emftext.language.java.types.Boolean createBoolean() {
		return new BooleanInitialiser().instantiate();
	}

	public default org.emftext.language.java.types.Byte createByte() {
		return new ByteInitialiser().instantiate();
	}

	public default org.emftext.language.java.types.Char createChar() {
		return new CharInitialiser().instantiate();
	}

	public default org.emftext.language.java.types.Double createDouble() {
		return new DoubleInitialiser().instantiate();
	}

	public default org.emftext.language.java.types.Float createFloat() {
		return new FloatInitialiser().instantiate();
	}

	public default org.emftext.language.java.types.Int createInt() {
		return new IntInitialiser().instantiate();
	}

	public default org.emftext.language.java.types.Long createLong() {
		return new LongInitialiser().instantiate();
	}

	public default org.emftext.language.java.types.Short createShort() {
		return new ShortInitialiser().instantiate();
	}

	public default org.emftext.language.java.types.Void createVoid() {
		return new VoidInitialiser().instantiate();
	}
}

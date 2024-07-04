package cipm.consistency.fitests.similarity.java.initialiser.literals;

import java.util.Collection;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiserPackage;

public class LiteralsInitialiserPackage implements IInitialiserPackage {
	@Override
	public Collection<EObjectInitialiser> getInitialisers() {
		return this.initCol(new EObjectInitialiser[] {
				new BinaryIntegerLiteralInitialiser(),
				new BinaryLongLiteralInitialiser(),
				new BooleanLiteralInitialiser(),
				new CharacterLiteralInitialiser(),
				new DecimalDoubleLiteralInitialiser(),
				new DecimalFloatLiteralInitialiser(),
				new DecimalIntegerLiteralInitialiser(),
				new DecimalLongLiteralInitialiser(),
				new HexDoubleLiteralInitialiser(),
				new HexFloatLiteralInitialiser(),
				new HexIntegerLiteralInitialiser(),
				new HexLongLiteralInitialiser(),
				new NullLiteralInitialiser(),
				new OctalIntegerLiteralInitialiser(),
				new OctalLongLiteralInitialiser(),
				new SuperInitialiser(),
				new ThisInitialiser(),
		});
	}
}

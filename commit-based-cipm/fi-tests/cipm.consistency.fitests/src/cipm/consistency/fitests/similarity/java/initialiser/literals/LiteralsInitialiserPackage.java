package cipm.consistency.fitests.similarity.java.initialiser.literals;

import java.util.Collection;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiserPackage;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class LiteralsInitialiserPackage implements IInitialiserPackage {
	@Override
	public Collection<IInitialiser> getInitialiserInstances() {
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
	
	@Override
	public Collection<Class<? extends IInitialiser>> getInitialiserClasses() {
		return this.initCol(new Class[] {
				IBinaryIntegerLiteralInitialiser.class,
				IBinaryLongLiteralInitialiser.class,
				IBooleanLiteralInitialiser.class,
				ICharacterLiteralInitialiser.class,
				IDecimalDoubleLiteralInitialiser.class,
				IDecimalFloatLiteralInitialiser.class,
				IDecimalIntegerLiteralInitialiser.class,
				IDecimalLongLiteralInitialiser.class,
				IDoubleLiteralInitialiser.class,
				IFloatLiteralInitialiser.class,
				IHexDoubleLiteralInitialiser.class,
				IHexFloatLiteralInitialiser.class,
				IHexIntegerLiteralInitialiser.class,
				IHexLongLiteralInitialiser.class,
				IIntegerLiteralInitialiser.class,
				ILiteralInitialiser.class,
				ILongLiteralInitialiser.class,
				INullLiteralInitialiser.class,
				IOctalIntegerLiteralInitialiser.class,
				IOctalLongLiteralInitialiser.class,
				ISelfInitialiser.class,
				ISuperInitialiser.class,
				IThisInitialiser.class,
		});
	}
}

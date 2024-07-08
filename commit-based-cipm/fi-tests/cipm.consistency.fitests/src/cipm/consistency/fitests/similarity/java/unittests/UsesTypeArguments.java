package cipm.consistency.fitests.similarity.java.unittests;

import org.emftext.language.java.generics.ExtendsTypeArgument;
import org.emftext.language.java.generics.SuperTypeArgument;
import org.emftext.language.java.types.TypeReference;

import cipm.consistency.fitests.similarity.java.initialiser.generics.ExtendsTypeArgumentInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.generics.SuperTypeArgumentInitialiser;

public interface UsesTypeArguments extends UsesTypeReferences {
	// TODO: Decide whether Qualified and Unknown type arguments are needed here
	
	public default ExtendsTypeArgument createMinimalExtendsTA(TypeReference tref) {
		var init = new ExtendsTypeArgumentInitialiser();
		
		ExtendsTypeArgument result = init.instantiate();
		init.initialise(result);
		init.setExtendType(result, tref);
		return result;
	}
	
	public default SuperTypeArgument createMinimalSuperTA(TypeReference tref) {
		var init = new SuperTypeArgumentInitialiser();
		
		SuperTypeArgument result = init.instantiate();
		init.initialise(result);
		init.setSuperType(result, tref);
		return result;
	}
	
	public default ExtendsTypeArgument createMinimalExtendsTAWithCls(String clsName) {
		return this.createMinimalExtendsTA(this.createMinimalClsRef(clsName));
	}
	
	public default SuperTypeArgument createMinimalSuperTAWithCls(String clsName) {
		return this.createMinimalSuperTA(this.createMinimalClsRef(clsName));
	}
}

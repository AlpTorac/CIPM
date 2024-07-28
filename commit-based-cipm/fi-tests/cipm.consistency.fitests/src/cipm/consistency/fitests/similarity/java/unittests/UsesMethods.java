package cipm.consistency.fitests.similarity.java.unittests;

import org.emftext.language.java.members.ClassMethod;
import org.emftext.language.java.members.InterfaceMethod;

import cipm.consistency.fitests.similarity.java.initialiser.members.ClassMethodInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.members.InterfaceMethodInitialiser;

public interface UsesMethods extends UsesStatements {
	public default ClassMethod createMinimalClsMethod(String methodName) {
		var init = new ClassMethodInitialiser();
		ClassMethod result = this.createMinimalClsMethod();
		init.setName(result, methodName);
		return result;
	}
	
	public default ClassMethod createMinimalClsMethod() {
		var init = new ClassMethodInitialiser();
		ClassMethod result = init.instantiate();
		init.addStatement(result, this.createMinimalNullReturn());
		return result;
	}
	
	public default InterfaceMethod createMinimalInterfaceMethod(String methodName) {
		var init = new InterfaceMethodInitialiser();
		InterfaceMethod result = this.createMinimalInterfaceMethod();
		init.setName(result, methodName);
		return result;
	}

	public default InterfaceMethod createMinimalInterfaceMethod() {
		var init = new InterfaceMethodInitialiser();
		InterfaceMethod result = init.instantiate();
		init.setDefaultValue(result, this.createNullLiteral());
		return result;
	}
	
}

package cipm.consistency.fitests.similarity.java.unittests;

import org.emftext.language.java.members.EnumConstant;

import cipm.consistency.fitests.similarity.java.initialiser.members.EnumConstantInitialiser;

public interface UsesEnumConstants {
	public default EnumConstant createMinimalEnumConstant(String ecName) {
		var ecInit = new EnumConstantInitialiser();
		var ec = ecInit.instantiate();
		ecInit.minimalInitialisation(ec);
		ecInit.initialiseName(ec, ecName);
		return ec;
	}
}
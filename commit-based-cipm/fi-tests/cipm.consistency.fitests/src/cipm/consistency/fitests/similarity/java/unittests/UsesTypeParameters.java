package cipm.consistency.fitests.similarity.java.unittests;

import org.emftext.language.java.generics.TypeParameter;
import org.emftext.language.java.types.TypeReference;

import cipm.consistency.fitests.similarity.java.initialiser.generics.ITypeParameterInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.generics.TypeParameterInitialiser;

public interface UsesTypeParameters extends UsesTypeReferences {
	public default TypeParameter createMinimalTypeParamWithClsRef(String clsName) {
		return this.createMinimalTypeParam(new TypeParameterInitialiser(),
				new TypeReference[] {this.createMinimalClsRef(clsName)});
	}
	
	public default TypeParameter createMinimalTypeParamWithClsRefs(ITypeParameterInitialiser init, int count) {
		var arr = new TypeReference[count];
		
		for (int i = 0; i < count; i++) {
			arr[i] = this.createMinimalClsRef("cls"+i);
		}
		
		return this.createMinimalTypeParam(init, arr);
	}
	
	public default TypeParameter createMinimalTypeParam(ITypeParameterInitialiser init, TypeReference[] trefs) {
		TypeParameter result = init.instantiate();
		init.initialise(result);
		init.addExtendTypes(result, trefs);
		return result;
	}
}

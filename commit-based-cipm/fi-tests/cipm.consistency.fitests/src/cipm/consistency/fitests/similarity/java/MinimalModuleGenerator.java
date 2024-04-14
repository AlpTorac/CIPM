package cipm.consistency.fitests.similarity.java;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EObject;

import cipm.consistency.fitests.similarity.java.initialiser.ModuleInitialiser;

public class MinimalModuleGenerator implements IModelTestParamGenerator {
	private static final String defaultName = "mod";
	private final String nameParam;
	
	public MinimalModuleGenerator(String nameParam) {
		if (nameParam != null) {
			this.nameParam = nameParam;
		} else {
			this.nameParam = defaultName;
		}
	}
	
	public MinimalModuleGenerator() {
		this(null);
	}
	
	@Override
	public Collection<? extends EObject> generateParam() {
		var initialiser = new ModuleInitialiser();
		initialiser.instantiate();
		initialiser.initialiseName(this.nameParam);
		var param = initialiser.build();
		
		var result = new ArrayList<EObject>();
		result.add(param);
		
		return result;
	}
	
	@Override
	public Collection<? extends EObject> generateDefaultParam() {
		return this.generateParam();
	}
}

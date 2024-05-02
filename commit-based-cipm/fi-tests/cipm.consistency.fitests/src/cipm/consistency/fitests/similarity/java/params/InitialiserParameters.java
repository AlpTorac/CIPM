package cipm.consistency.fitests.similarity.java.params;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.classifiers.AnnotationInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.classifiers.ClassInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.classifiers.EnumerationInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.classifiers.InterfaceInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.containers.CompilationUnitInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.containers.ModuleInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.containers.PackageInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.generics.TypeParameterInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.members.ClassMethodInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.members.ConstructorInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.members.EnumConstantInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.members.FieldInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.members.InterfaceMethodInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.parameters.CatchParameterInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.parameters.OrdinaryParameterInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.parameters.ReceiverParameterInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.parameters.VariableLengthParameterInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.statements.BlockInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.variables.AdditionalLocalVariableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.variables.LocalVariableInitialiser;

public class InitialiserParameters {
	/*
	 * TODO: Include whether certain differing aspects are supposed
	 * to cause the respective EObject instances to be considered
	 * different.
	 */
	
	@SuppressWarnings("serial")
	public List<EObjectInitialiser> getAllInitialisers() {
		return new ArrayList<EObjectInitialiser>() {{
			add(new AdditionalLocalVariableInitialiser());
			add(new AnnotationInitialiser());
			add(new BlockInitialiser());
			add(new CatchParameterInitialiser());
			add(new ClassInitialiser());
			add(new ClassMethodInitialiser());
			add(new CompilationUnitInitialiser());
			add(new ConstructorInitialiser());
			add(new EnumConstantInitialiser());
			add(new EnumerationInitialiser());
			add(new FieldInitialiser());
			add(new InterfaceInitialiser());
			add(new InterfaceMethodInitialiser());
			add(new LocalVariableInitialiser());
			add(new ModuleInitialiser());
			add(new OrdinaryParameterInitialiser());
			add(new PackageInitialiser());
			add(new ReceiverParameterInitialiser());
			add(new TypeParameterInitialiser());
			add(new VariableLengthParameterInitialiser());
		}};
	}
	
	public List<EObjectInitialiser> getInitialisersBy(Predicate<EObjectInitialiser> pred) {
		var result = new ArrayList<EObjectInitialiser>();
		this.getAllInitialisers().stream().filter(pred).forEach((i) -> result.add(i));
		return result;
	}
	
	public List<EObjectInitialiser> getInitialisersBySuper(Stream<Class<? extends EObjectInitialiser>> clss) {
		return this.getInitialisersBy(
					(i) -> clss.anyMatch((cls) -> cls.isInstance(i))
				);
	}
	
	public EObjectInitialiser getInitialiserByClass(Stream<Class<? extends EObjectInitialiser>> clss) {
		return this.getAllInitialisers().stream()
				.filter((i) -> clss.anyMatch((cls) -> i.getClass().equals(cls)))
				.findFirst()
				.orElseGet(null);
	}
	
	public List<EObjectInitialiser> getInitialisersBySuper(Class<? extends EObjectInitialiser> cls) {
		return this.getInitialisersBy(
				(i) -> cls.isInstance(i)
				);
	}
	
	public EObjectInitialiser getInitialiserByClass(Class<? extends EObjectInitialiser> cls) {
		return this.getAllInitialisers().stream()
				.filter((i) -> i.getClass().equals(cls))
				.findFirst()
				.orElseGet(null);
	}
}

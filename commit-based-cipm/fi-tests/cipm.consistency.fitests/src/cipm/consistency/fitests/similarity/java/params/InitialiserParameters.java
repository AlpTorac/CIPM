package cipm.consistency.fitests.similarity.java.params;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.AdditionalLocalVariableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.AnnotationInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.BlockInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.CatchParameterInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.ClassInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.ClassMethodInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.CompilationUnitInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.ConstructorInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.EnumConstantInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.EnumerationInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.FieldInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.InterfaceInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.InterfaceMethodInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.LocalVariableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.ModuleInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.OrdinaryParameterInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.PackageInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.ReceiverParameterInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.TypeParameterInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.VariableLengthParameterInitialiser;

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

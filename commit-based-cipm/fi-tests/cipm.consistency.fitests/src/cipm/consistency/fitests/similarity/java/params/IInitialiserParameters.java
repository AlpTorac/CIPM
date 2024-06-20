package cipm.consistency.fitests.similarity.java.params;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;

public interface IInitialiserParameters {
	public List<EObjectInitialiser> getAllInitialisers();
	
	public default List<EObjectInitialiser> getInitialisersBy(Predicate<EObjectInitialiser> pred) {
		var result = new ArrayList<EObjectInitialiser>();
		this.getAllInitialisers().stream().filter(pred).forEach((i) -> result.add(i));
		return result;
	}
	
	public default List<EObjectInitialiser> getInitialisersBySuper(Stream<Class<? extends EObjectInitialiser>> clss) {
		return this.getInitialisersBy(
					(i) -> clss.anyMatch((cls) -> cls.isInstance(i))
				);
	}
	
	public default EObjectInitialiser getInitialiserByClass(Stream<Class<? extends EObjectInitialiser>> clss) {
		return this.getAllInitialisers().stream()
				.filter((i) -> clss.anyMatch((cls) -> i.getClass().equals(cls)))
				.findFirst()
				.orElseGet(null);
	}
	
	public default List<EObjectInitialiser> getInitialisersBySuper(Class<? extends EObjectInitialiser> cls) {
		return this.getInitialisersBy(
				(i) -> cls.isInstance(i)
				);
	}
	
	public default EObjectInitialiser getInitialiserByClass(Class<? extends EObjectInitialiser> cls) {
		return this.getAllInitialisers().stream()
				.filter((i) -> i.getClass().equals(cls))
				.findFirst()
				.orElseGet(null);
	}
}

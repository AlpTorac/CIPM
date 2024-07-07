package cipm.consistency.fitests.similarity.java.params;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Stream;

import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;

public interface IInitialiserParameters {
	public Collection<IInitialiser> getAllInitialisers();
	
	public default Collection<IInitialiser> getInitialisersBy(Predicate<IInitialiser> pred) {
		var result = new ArrayList<IInitialiser>();
		this.getAllInitialisers().stream().filter(pred).forEach((i) -> result.add(i));
		return result;
	}
	
	public default Collection<IInitialiser> getInitialisersBySuper(Stream<Class<? extends IInitialiser>> clss) {
		return this.getInitialisersBy(
					(i) -> clss.anyMatch((cls) -> cls.isInstance(i))
				);
	}
	
	public default IInitialiser getInitialiserByClass(Stream<Class<? extends IInitialiser>> clss) {
		return this.getAllInitialisers().stream()
				.filter((i) -> clss.anyMatch((cls) -> i.getClass().equals(cls)))
				.findFirst()
				.orElseGet(null);
	}
	
	public default Collection<IInitialiser> getInitialisersBySuper(Class<? extends IInitialiser> cls) {
		return this.getInitialisersBy(
				(i) -> cls.isInstance(i)
				);
	}
	
	public default IInitialiser getInitialiserByClass(Class<? extends IInitialiser> cls) {
		return this.getAllInitialisers().stream()
				.filter((i) -> i.getClass().equals(cls))
				.findFirst()
				.orElseGet(null);
	}
}

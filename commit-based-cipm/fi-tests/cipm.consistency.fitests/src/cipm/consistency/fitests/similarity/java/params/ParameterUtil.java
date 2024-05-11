package cipm.consistency.fitests.similarity.java.params;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

public class ParameterUtil {
	// TODO: Find a way to generate display names for arguments
	// FIXME: Clone Arguments while permutating them
	
	/**
	 * @param arg1 Argument on the left hand side
	 * @param arg2 Argument on the right hand side
	 * @param unallowedCombs: Collection of combinations (arg1, arg2) that are not 
	 * allowed in form of a collection of arrays [arg1, arg2].
	 * Null, if all combinations are allowed.
	 * @return Whether the given combination is allowed.
	 */
	public boolean isCombAllowed(Arguments arg1, Arguments arg2,
			Collection<Arguments[]> unallowedCombs) {
		
		if (unallowedCombs == null) return true;
		
		return unallowedCombs.stream()
				.noneMatch((arr) -> arr[0] == arg1 && arr[1] == arg2);
	}
	
	/**
	 * @param args1 Arguments on the left hand side
	 * @param args2 Arguments on the right hand side
	 * @param unallowedCombs: Collection of combinations (arg1, arg2) that are not 
	 * allowed in form of a collection of arrays [arg1, arg2].
	 * Null, if all combinations are allowed.
	 * @return The cross-product of arg1 and arg2, without the given exceptions
	 */
	public Collection<Arguments> permute(Collection<Arguments> args1,
			Collection<Arguments> args2, Collection<Arguments[]> unallowedCombs) {
		var result = new ArrayList<Arguments>();
		
		for (var arg1 : args1) {
			var arg1Content = arg1.get()[0];
			
			for (var arg2 : args2) {
				if (this.isCombAllowed(arg1, arg2, unallowedCombs))
					result.add(Arguments.of(arg1Content, arg2.get()[0]));
			}
		}
		
		return result;
	}
	
	/**
	 * @see {@link #permute(Collection, Collection, Collection)}
	 */
	public Collection<Arguments> permute(Collection<Arguments> args1,
			Collection<Arguments> args2) {
		
		return this.permute(args1, args2, null);
	}
	
	public Collection<Arguments> streamToCol(Stream<Arguments> stream) {
		var result = new ArrayList<Arguments>();
		
		stream.forEach((a) -> result.add(a));
		return result;
	}
	
	public Stream<Arguments> toArgumentStream(Stream<? extends Object> stream) {
		return stream.map((o) -> Arguments.of(o));
	}
	
	public Collection<Arguments> toArgumentCol(Stream<? extends Object> stream) {
		return this.streamToCol(this.toArgumentStream(stream));
	}
	
	public Collection<Arguments> toArgumentCol(Collection<? extends Object> col) {
		var result = new ArrayList<Arguments>();
		
		col.forEach((o) -> result.add(Arguments.of(o)));
		return result;
	}
 }

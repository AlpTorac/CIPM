package cipm.consistency.fitests.similarity.java.unittests.complextests;

import org.eclipse.emf.common.util.EList;
import org.emftext.language.java.arrays.ArraySelector;
import org.emftext.language.java.classifiers.impl.ClassImpl;
import org.emftext.language.java.generics.TypeArgument;
import org.emftext.language.java.references.IdentifierReference;
import org.emftext.language.java.references.Reference;
import org.emftext.language.java.references.ReferenceableElement;
import org.emftext.language.java.types.Type;
import org.emftext.language.java.types.TypeReference;

/**
 * A dummy class that implements both {@link ReferenceableElement} (via {@link ClassImpl})
 * and {@link Reference} interfaces. Can be used to test cases, where for an
 * {@link IdentifierReference} instance {@code IR} the following holds:
 * <br><br>
 * {@code IR.getNext() == IR.getTarget()}
 * <br><br>
 * Currently there is no actual class, which implements both interfaces named
 * above. This class was implemented to test for the mentioned scenario regardless,
 * as references are common in code and such cases could cause problems in the future,
 * if such classes are implemented.
 * <br><br>
 * This dummy class takes two {@link Reference} instances: It wraps and uses one of them
 * for the method implementations needed by the {@link Reference} interface, except for the
 * {@code .getPrevious()} method. The other
 * {@link Reference} instance is the return value for the {@code .getPrevious()} method. This
 * had to be done, as setting a previous {@link Reference} directly is currently not possible.
 * <br><br>
 * <b>!!! IMPORTANT: This class is NOT a proper implementation of the {@link Reference} interface. Therefore,
 * unexpected things might happen if unintended methods of this class are called or
 * instances of this class are provided as parameters to unintended methods. The sole purpose of this class is to be used as a parameter for {@code IR.setNext(...)} and
 * {@code IR.setTarget(...)} !!!</b>
 * 
 * @author atora
 */
class DummyClassDecorator extends ClassImpl implements Reference {
	private Reference innerRef;
	private Reference prev;
	
	/**
	 * Constructs an instance.
	 * 
	 * @param prev The return value of {@code this.}{@link #getPrevious()}
	 * @param innerRef The {@link Reference} instance, to whom the calls
	 * to methods required by the {@link Reference} interface will be delegated,
	 * except for {@link #getPrevious()}.
	 */
	DummyClassDecorator(Reference prev, Reference innerRef) {
		this.prev = prev;
		this.innerRef = innerRef;
	}
	
	@Override
	public Type getAlternativeType() {
		return innerRef.getAlternativeType();
	}

	@Override
	public long getArrayDimension() {
		return innerRef.getArrayDimension();
	}

	@Override
	public Type getOneType(boolean arg0) {
		return innerRef.getOneType(arg0);
	}

	@Override
	public TypeReference getOneTypeReference(boolean arg0) {
		return innerRef.getOneTypeReference(arg0);
	}

	@Override
	public Type getType() {
		return innerRef.getType();
	}

	@Override
	public EList<TypeArgument> getTypeArguments() {
		return innerRef.getTypeArguments();
	}

	@Override
	public EList<TypeReference> getActualTargets() {
		return innerRef.getActualTargets();
	}

	@Override
	public EList<ArraySelector> getArraySelectors() {
		return innerRef.getArraySelectors();
	}

	@Override
	public Reference getNext() {
		return innerRef.getNext();
	}

	/**
	 * @return The parameter prev that was passed to the
	 * constructor
	 * @see {@link DummyClassDecorator#DummyClassDecorator(Reference, Reference)}
	 */
	@Override
	public Reference getPrevious() {
		return prev;
	}

	@Override
	public Type getReferencedType() {
		return innerRef.getReferencedType();
	}

	@Override
	public TypeReference getReferencedTypeReference() {
		return innerRef.getReferencedTypeReference();
	}

	@Override
	public void setNext(Reference arg0) {
		innerRef.setNext(arg0);
	}
}

package cipm.consistency.fitests.similarity.java.action;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.jupiter.api.Assertions;

public class EMFTextUtil {
	private static EMFTextUtil instance;

	private EMFTextUtil() {
	}

	/**
	 * Constructs an instance for the singleton {@link EMFTextUtil} and returns,
	 * whether there is an existing instance after this method is executed.
	 */
	public static boolean initialise() {
		if (instance == null) {
			instance = new EMFTextUtil();
		}
		return instance != null;
	}

	public static EMFTextUtil getInstance() {
		initialise();
		return instance;
	}

	/**
	 * Creates a clone copy of the given obj and its contents. <br>
	 * <br>
	 * <b>Note: DOES NOT clone the container {@code obj.eContainer()} of this
	 * object. Only copies the given object and the contents nested in it.</b>
	 * 
	 * @return A clone of obj without its container and clones of its contents.
	 * @see {@link EcoreUtil#copy(EObject)}
	 */
	public <T extends EObject> T cloneEObj(T obj) {
		return EcoreUtil.copy(obj);
	}

	/**
	 * Finds the topmost EObject (objTop) that can be reached from obj, clones
	 * objTop, finds the clone of obj among the contents of objTop and returns that
	 * clone.
	 * 
	 * @return A clone of obj, which preserves obj's place in its hierarchy. The
	 *         returned clone contains clones of obj's contents and is contained by
	 *         clones of all containers of obj.
	 * @see {@link EcoreUtil#copy(EObject)}
	 */
	@SuppressWarnings("unchecked")
	public <T extends EObject> T cloneEObjWithContainers(T obj) {
		if (obj.eContainer() == null) {
			return this.cloneEObj(obj);
		}

		EObject cObj = obj;

		while (cObj.eContainer() != null) {
			cObj = cObj.eContainer();
		}

		EObject clone = this.cloneEObj(cObj);
		var contents = clone.eAllContents();

		while (contents.hasNext()) {
			var cCloneObj = contents.next();
			if (this.getActualEquality(obj, cCloneObj)) {
				return (T) cCloneObj;
			}
		}

		Assertions.fail("Cloning unsuccessful");
		return null;
	}

	/**
	 * Creates a clone copy of all given objs.
	 * 
	 * @see {@link EcoreUtil#copyAll(Collection)}
	 */
	public <T extends EObject> Collection<T> cloneEObjList(Collection<T> objs) {
		return EcoreUtil.copyAll(objs);
	}

	/**
	 * Computes the equality of two {@link EObject} instances using
	 * {@link EcoreUtil}. <br>
	 * <br>
	 * <b>Note: The equality here is not the same as similarity checking that is
	 * being tested. This form of equality is much stricter than similarity, since
	 * there might be some attributes and/or nested classes, which are irrelevant
	 * for similarity in certain cases.</b>
	 */
	public boolean getActualEquality(EObject elem1, EObject elem2) {
		return EcoreUtil.equals(elem1, elem2);
	}

	/**
	 * Computes the equality of two lists of {@link EObject} using
	 * {@link EcoreUtil}. <br>
	 * <br>
	 * <b>Note: The equality here is not the same as similarity checking that is
	 * being tested. This form of equality is much stricter than similarity, since
	 * there might be some attributes and/or nested classes, which are irrelevant
	 * for similarity in certain cases.</b>
	 */
	public boolean getActualEquality(List<? extends EObject> elems1, List<? extends EObject> elems2) {
		return EcoreUtil.equals(elems1, elems2);
	}

	public void addContentToResource(Resource res, EObject obj) {
		res.getContents().add(obj);
	}

	public void iterateAllContents(Resource res, Consumer<EObject> consumer) {
		res.getAllContents().forEachRemaining(consumer);
	}

	/**
	 * @return Whether stopFunc returned true for any content in res
	 */
	public boolean iterateAllContentsTill(Resource res, Function<EObject, Boolean> stopFunc) {
		var it = res.getAllContents();

		while (it.hasNext()) {
			var eo = it.next();
			if (stopFunc.apply(eo))
				return true;
		}

		return false;
	}

	/**
	 * @return Whether obj is a direct content of res. obj is a direct content of
	 *         res, if obj can be found under res' contents without inspecting
	 *         further elements.
	 */
	public boolean hasDirectContent(Resource res, EObject obj) {
		return res.getContents().contains(obj);
	}

	/**
	 * @return Whether obj is contained either directly in res or somewhere in res'
	 *         direct contents.
	 */
	public boolean hasContent(Resource res, EObject obj) {
		return this.iterateAllContentsTill(res, (eo) -> eo.equals(obj));
	}
}

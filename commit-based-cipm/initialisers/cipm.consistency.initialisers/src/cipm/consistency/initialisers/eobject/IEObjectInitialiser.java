package cipm.consistency.initialisers.eobject;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import cipm.consistency.initialisers.IInitialiser;
import cipm.consistency.initialisers.IInitialiserAdapterStrategy;

/**
 * An interface for {@link IInitialiser} sub-types, whose purpose is to create
 * and modify {@link EObject} instances.
 * 
 * @author Alp Torac Genc
 */
public interface IEObjectInitialiser extends IInitialiser {
	/**
	 * {@inheritDoc} <br>
	 * <br>
	 * <b>Note: The created instance may not be "valid" due to certain attributes
	 * not being set. Using proper {@link IInitialiserAdapter} instances on
	 * implementors can circumvent potential issues.</b>
	 * 
	 * @see {@link IInitialiserAdapter}, {@link IInitialiserAdapterStrategy}
	 */
	@Override
	public EObject instantiate();

	@Override
	public IEObjectInitialiser newInitialiser();

	/**
	 * {@inheritDoc}
	 * 
	 * @return The instance class associated with the EClass of the return value of
	 *         {@link #instantiate()}, or null if the said method returns null.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public default Class<? extends EObject> getInstanceClassOfInitialiser() {
		var obj = this.instantiate();
		return obj != null ? (Class<? extends EObject>) obj.eClass().getInstanceClass() : null;
	}

	/**
	 * Moves an element {@code obj} from {@code elemList} to a new position: <br>
	 * <br>
	 * {@code oldElem = elemList.get(oldElemPos)} will be moved to
	 * {@code newElemPos}, resulting in {@code elemList.get(newElemPos) == oldElem}.
	 * <br>
	 * <br>
	 * If {@code oldElemPos < newElemPos}, all elements' indices within that range
	 * will be decremented by 1 (since their predecessor {@code oldElem} is moved to
	 * the front). If {@code oldElemPos > newElemPos}, all elements' indices within
	 * that range will be incremented by 1 (since they get {@code oldElem} as a new
	 * predecessor). {@code oldElem}'s index will be {@code newElemPos}, as
	 * intended. <br>
	 * <br>
	 * It is suggested to use {@link #changeAttributePosition(EObject, int)}
	 * instead, since the potential attribute containing {@code elemList} may not
	 * allow it to be changed, for which the said method accounts. If this is the
	 * case, <b>THIS METHOD MAY RESULT IN EXCEPTIONS</b>. Otherwise, if the given
	 * {@code elemList} is in a vacuum (i.e. not contained in an attribute), this
	 * method can be used. <br>
	 * <br>
	 * <b><i>!!! DOES NOT SWAP POSITIONS, ONLY INSERTS AN ELEMENT TO ANOTHER
	 * POSITION !!!</i></b>
	 * 
	 * @param elemList   The list, whose elements are to be moved
	 * @param oldElemPos The current position of the element to be moved
	 * @param newElemPos The new position of the element to be moved
	 * @return Whether the move operation was successful and all elements (including
	 *         the ones between the given positions) are re-arranged successfully.
	 *         Always returns true, if oldElemPos == newElemPos.
	 */
	public default boolean changeAttributePosition(EList<?> elemList, int oldElemPos, int newElemPos) {
		if (oldElemPos == newElemPos) {
			// There are no position changes to be performed
			// regardless of the given positions
			return true;
		}

		var valsSize = elemList.size();

		// Check given positions
		if (oldElemPos < 0 || oldElemPos >= valsSize) {
			return false;
		}
		if (newElemPos < 0 || newElemPos >= valsSize) {
			return false;
		}

		var objToMove = elemList.get(oldElemPos);

		var oldElems = List.copyOf(elemList);
		var movedObj = elemList.move(newElemPos, oldElemPos);

		// Ensure that the move operation went as expected for the targeted element
		if (objToMove != movedObj || movedObj != oldElems.get(oldElemPos) || movedObj != elemList.get(newElemPos)) {
			return false;
		}

		for (int i = 0; i < elemList.size(); i++) {
			var oldElem = oldElems.get(i);
			var newElem = elemList.get(i);

			var oldElemPosInNewElems = i;
			var newElemPosInOldElems = i;

			if (oldElemPos < i && i < newElemPos) {
				oldElemPosInNewElems -= 1;
				newElemPosInOldElems += 1;
			} else if (newElemPos < i && i < oldElemPos) {
				oldElemPosInNewElems += 1;
				newElemPosInOldElems -= 1;
			} else if (oldElemPos == i && i < newElemPos) {
				oldElemPosInNewElems = newElemPos;
				newElemPosInOldElems += 1;
			} else if (newElemPos == i && i < oldElemPos) {
				oldElemPosInNewElems += 1;
				newElemPosInOldElems = oldElemPos;
			} else if (newElemPos < i && i == oldElemPos) {
				oldElemPosInNewElems = newElemPos;
				newElemPosInOldElems -= 1;
			} else if (oldElemPos < i && i == newElemPos) {
				oldElemPosInNewElems -= 1;
				newElemPosInOldElems = oldElemPos;
			}

			if (oldElem != elemList.get(oldElemPosInNewElems) || newElem != oldElems.get(newElemPosInOldElems)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * A variant of {@link #changeAttributePosition(EList, int, int)}, where the
	 * given elem's containing list, as well as its position within, are derived.
	 * <br>
	 * <br>
	 * Also makes sure that the attribute containing the said list allows it to be
	 * changed.
	 * 
	 * @param elemToMove An element, which is in vals
	 */
	public default <T extends EObject> boolean changeAttributePosition(T elemToMove, int newElemPos) {
		var con = elemToMove.eContainer();
		var feat = elemToMove.eContainingFeature();
		if (con != null && feat != null && feat.isMany() && feat.isChangeable()) {
			@SuppressWarnings("unchecked")
			var list = (EList<T>) con.eGet(feat);
			return this.changeAttributePosition(list, list.indexOf(elemToMove), newElemPos);
		}
		return false;
	}

	/**
	 * Swaps the elements at the given positions in the given list. <br>
	 * <br>
	 * It is suggested to use {@link #swapAttributePosition(EObject, EObject)}
	 * instead, since the potential attribute containing {@code elemList} may not
	 * allow it to be changed, for which the said method accounts. If this is the
	 * case, <b>THIS METHOD MAY RESULT IN EXCEPTIONS</b>. Otherwise, if the given
	 * {@code elemList} is in a vacuum (i.e. not contained in an attribute), this
	 * method can be used. <br>
	 * <br>
	 * 
	 * @param elemList The list, whose elements are to be swapped
	 * @param elem1Pos An element's position
	 * @param elem2Pos The other element's position
	 * @return Whether the swap operation was successful. Always returns true, if
	 *         both positions are equal.
	 * 
	 * @see {@link #changeAttributePosition(EList, int, int)}
	 */
	public default boolean swapAttributePosition(EList<?> elemList, int elem1Pos, int elem2Pos) {
		if (elem1Pos == elem2Pos) {
			// There are no position changes to be performed
			// regardless of the given positions
			return true;
		}

		var valsSize = elemList.size();

		// Check given positions
		if (elem1Pos < 0 || elem1Pos >= valsSize) {
			return false;
		}
		if (elem2Pos < 0 || elem2Pos >= valsSize) {
			return false;
		}

		var elem1 = elemList.get(elem1Pos);
		var elem2 = elemList.get(elem2Pos);

		/*
		 * Swap from smaller index towards larger index, in order to avoid having to
		 * account for 2 cases (see changeAttributePosition).
		 */
		var minIdx = elem1Pos < elem2Pos ? elem1Pos : elem2Pos;
		var maxIdx = elem1Pos < elem2Pos ? elem2Pos : elem1Pos;

		return this.changeAttributePosition(elemList, minIdx, maxIdx)
				&& this.changeAttributePosition(elemList, maxIdx - 1, minIdx) && elemList.indexOf(elem1) == elem2Pos
				&& elemList.indexOf(elem2) == elem1Pos;
	}

	/**
	 * A variant of {@link #swapAttributePosition(EList, int, int)}, where the given
	 * elems' containing lists, as well as their positions within, are derived. <br>
	 * <br>
	 * Also makes sure that the attribute containing the said lists allows them to
	 * be changed.
	 * 
	 * @param elem1 An element
	 * @param elem2 The other element
	 */
	public default <T extends EObject> boolean swapAttributePosition(T elem1, T elem2) {
		var con1 = elem1.eContainer();
		var feat1 = elem1.eContainingFeature();

		var con2 = elem2.eContainer();
		var feat2 = elem2.eContainingFeature();

		if (con1 != con2 || feat1 != feat2) {
			return false;
		}

		if (con1 != null && feat1 != null && feat1.isMany() && feat1.isChangeable()) {
			@SuppressWarnings("unchecked")
			var list = (EList<T>) con1.eGet(feat1);
			return this.swapAttributePosition(list, list.indexOf(elem1), list.indexOf(elem2));
		}
		return false;
	}
}

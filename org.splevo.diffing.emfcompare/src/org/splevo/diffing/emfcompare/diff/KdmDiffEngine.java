package org.splevo.diffing.emfcompare.diff;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.compare.FactoryException;
import org.eclipse.emf.compare.diff.engine.GenericDiffEngine;
import org.eclipse.emf.compare.diff.engine.IMatchManager;
import org.eclipse.emf.compare.diff.metamodel.ConflictingDiffElement;
import org.eclipse.emf.compare.diff.metamodel.DiffElement;
import org.eclipse.emf.compare.diff.metamodel.DiffFactory;
import org.eclipse.emf.compare.diff.metamodel.DiffGroup;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.emf.compare.diff.metamodel.ModelElementChangeLeftTarget;
import org.eclipse.emf.compare.diff.metamodel.ModelElementChangeRightTarget;
import org.eclipse.emf.compare.diff.metamodel.UpdateReference;
import org.eclipse.emf.compare.match.internal.statistic.NameSimilarity;
import org.eclipse.emf.compare.match.metamodel.MatchModel;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil.CrossReferencer;
import org.eclipse.gmt.modisco.java.Type;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.VariableDeclarationFragment;
import org.eclipse.gmt.modisco.java.VariableDeclarationStatement;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceFile;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceRegion;
import org.eclipse.modisco.java.composition.javaapplication.JavaNodeSourceRegion;
import org.eclipse.modisco.kdm.source.extension.ui.utils.Utils;
import org.splevo.diffing.emfcompare.diff.handlers.ImportInsertHandler;
import org.splevo.diffing.emfcompare.diff.handlers.Kdm2JavaDiffHandler;
import org.splevo.diffing.emfcompare.diff.handlers.PackageChangeHandler;

public class KdmDiffEngine extends GenericDiffEngine {

	@Override
	public DiffModel doDiff(MatchModel match, boolean threeWay) {
		System.out.println("starting diff 1");
		DiffModel diff = super.doDiff(match, threeWay);
		return diff;
	}

	@Override
	public DiffModel doDiff(MatchModel match, boolean threeWay,
			IMatchManager manager) {
		System.out.println("starting diff 2");
		DiffModel diff = super.doDiff(match, threeWay, manager);
		executeTreeHandlers(diff);
		return diff;
	}

	@Override
	public DiffModel doDiffResourceSet(MatchModel match, boolean threeWay,
			CrossReferencer crossReferencer) {
		System.out.println("diff 3");
		DiffModel diff = super.doDiffResourceSet(match, threeWay,
				crossReferencer);
		executeTreeHandlers(diff);
		return diff;
	}

	@Override
	public DiffModel doDiffResourceSet(MatchModel match, boolean threeWay,
			IMatchManager manager) {
		System.out.println("diff 4");
		DiffModel diff = super.doDiffResourceSet(match, threeWay, manager);
		executeTreeHandlers(diff);
		return diff;
	}

	
	
	private void executeTreeHandlers(DiffModel diffModel) {
		List<Kdm2JavaDiffHandler> handlers = new ArrayList<Kdm2JavaDiffHandler>();
		handlers.add(new ImportInsertHandler());
		handlers.add(new PackageChangeHandler());

		for (TreeIterator<EObject> tIter = diffModel.eAllContents(); tIter
				.hasNext();) {
			EObject node = tIter.next();
			if (node instanceof DiffElement) {
				DiffElement diffElement = (DiffElement) node;
				for (Kdm2JavaDiffHandler handler : handlers) {
					if (handler.isValidSubtreeHandler(diffElement) && diffElement.eContainer() instanceof DiffElement) {
						System.out.println("found handler " + handler.getClass() + " and handling " + diffElement);
						DiffElement parent = (DiffElement) diffElement.eContainer();
						
						DiffElement processedElement = handler.handleSubtree(diffElement);
						
						//parent.getSubDiffElements().add(processedElement);
						tIter.prune();
					}
				}
			}
		}
	}
	
	@Deprecated
	private void traverseDiffModel(DiffModel dModel) {
		System.out.println("traversing diff model " + dModel.getClass());
		/*
		 * for (DiffElement difference : dModel.getDifferences()) {
		 * System.out.println("found difference " + difference.toString() +
		 * " with class " + difference.getClass()); for (DiffElement subElem :
		 * difference.getSubDiffElements()) {
		 * System.out.println("  sub-element " + subElem.toString()); } }
		 */
		for (DiffElement ownedElem : dModel.getOwnedElements()) {
			/*
			 * System.out.println("owned element " + ownedElem.toString()); for
			 * (DiffElement subElemtn : ownedElem.getSubDiffElements()) {
			 * System.out.println("   sub-elment " + subElemtn.toString()); }
			 */
			traverseDiffElement(ownedElem, 0);
		}
		System.out.println("____________");
		System.out.println("diff tree");
		traverseDiffTreeForRefactorings(dModel);
		System.out.println("____________");

	}

	@Deprecated
	private void traverseDiffElement(DiffElement dElem, int indent) {
		if (dElem instanceof DiffGroup
				|| dElem instanceof ConflictingDiffElement) {
			// traverse next level

			for (int i = 0; i < indent; i++) {
				System.out.print(" ");
			}
			System.out.println("entering inner node " + dElem.toString()
					+ " of class " + dElem.getClass() + " and meta-class "
					+ dElem.eClass());
			for (DiffElement subElem : dElem.getSubDiffElements()) {
				traverseDiffElement(subElem, indent + 1);
			}
		} else {
			for (int i = 0; i < indent; i++) {
				System.out.print(" ");
			}
			System.out.println("leaf change " + dElem.toString() + " of class "
					+ dElem.getClass() + " and meta-class " + dElem.eClass());
			if (dElem instanceof UpdateReference) {
				UpdateReference upRef = (UpdateReference) dElem;
				try {
					Object leftObj = upRef.getLeftElement().eGet(
							upRef.getReference());
					Object rightObj = upRef.getRightElement().eGet(
							upRef.getReference());
					System.out.println("updated reference from "
							+ upRef.getReference().getName() + " left "
							+ leftObj + " right " + rightObj + " name "
							+ NameSimilarity.findName(upRef.getLeftElement()));
					System.out.println("pure left element "
							+ upRef.getLeftElement());
					if (leftObj instanceof TypeAccess
							&& rightObj instanceof TypeAccess) {
						TypeAccess leftTypeAccess = (TypeAccess) leftObj;
						Type leftType = leftTypeAccess.getType();
						TypeAccess rightTypeAccess = (TypeAccess) rightObj;
						Type rightType = rightTypeAccess.getType();
						System.out.println("!!! refactoring from class "
								+ leftType.getName() + " to "
								+ rightType.getName());
						if (upRef.getLeftElement() instanceof JavaNodeSourceRegion
								&& upRef.getRightElement() instanceof JavaNodeSourceRegion) {
							JavaNodeSourceRegion leftRegion = (JavaNodeSourceRegion) upRef
									.getLeftElement();
							JavaNodeSourceRegion rightRegion = (JavaNodeSourceRegion) upRef
									.getRightElement();
							// System.out.println("original source left " +
							// leftRegion.toString());
							// System.out.println("original source right " +
							// rightRegion.toString());
							System.out.println("left source");
							printRegion(leftRegion);
							System.out.println("right source");
							printRegion(rightRegion);
						}
					}
				} catch (FactoryException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Test method that traverses the diff tree and looks for type refactorings in variable declaration.
	 * @param diffModel the model to traverse
	 */
	@Deprecated
	private void traverseDiffTreeForRefactorings(DiffModel diffModel) {
		for (TreeIterator<EObject> tIter = diffModel.eAllContents(); tIter
				.hasNext();) {
			EObject node = tIter.next();
			
			if (node instanceof DiffGroup) {
				DiffGroup group = (DiffGroup) node;
				if (group.getRightParent() != null
						&& group.getRightParent() instanceof VariableDeclarationStatement) {
					VariableDeclarationStatement vds = (VariableDeclarationStatement) group.getRightParent();
					System.out.println("variable declaration of type " + vds.getType().getType());
					for (VariableDeclarationFragment fragment : vds.getFragments()) {
						System.out.println("    fragment " + fragment.toString() + " with name " + fragment.getName());
					}
					
					if (group.getSubDiffElements().size() == 1
							&& group.getSubDiffElements().get(0) instanceof DiffGroup) {
						DiffGroup subGroup = (DiffGroup) group
								.getSubDiffElements().get(0);
						
						if (subGroup.getSubDiffElements().size() == 2
								&& subGroup.getSubDiffElements().get(0) instanceof ModelElementChangeLeftTarget
								&& subGroup.getSubDiffElements().get(1) instanceof ModelElementChangeRightTarget) {
							ModelElementChangeLeftTarget leftT = (ModelElementChangeLeftTarget) subGroup.getSubDiffElements().get(0);
							ModelElementChangeRightTarget rightT = (ModelElementChangeRightTarget) subGroup.getSubDiffElements().get(1);
							if (leftT.getRightParent() instanceof VariableDeclarationFragment
									&& rightT.getLeftParent() instanceof VariableDeclarationFragment) {
								VariableDeclarationFragment leftRightFrag = (VariableDeclarationFragment) leftT.getRightParent();
								VariableDeclarationFragment rightLeftFrag = (VariableDeclarationFragment) rightT.getLeftParent();
								Type rightType = leftRightFrag.getVariablesContainer().getType().getType();
								Type leftType = rightLeftFrag.getVariablesContainer().getType().getType();
								
								System.out.println("      variable declaration with name " + leftRightFrag.getName() + " of type " + rightType);
								System.out.println("  vs  variable declaration with name " + rightLeftFrag.getName() + " of type " + leftType);
								
								
								if (leftRightFrag.getName().equals(rightLeftFrag.getName())
										&& !leftType.equals(rightType)) {
									System.out.println("  => found refactoring");
									UpdateReference newElem = DiffFactory.eINSTANCE.createUpdateReference();
									
									newElem.setLeftElement(leftRightFrag);
									newElem.setLeftTarget(leftType);
									
									newElem.setRightElement(rightLeftFrag);
									newElem.setRightTarget(rightType);
									
									
									group.getSubDiffElements().clear();
									group.getSubDiffElements().add(newElem);
									
									//don't follow the newly created sub element
									tIter.prune();
								}
							}
						}

						
					}
				}
			}
		}

	}

	private void printRegion(SourceRegion region) {
		SourceFile source = region.getFile();
		IFile sourceFile = Utils.getIFileFromSourceRegion(region);
		try {
			BufferedReader sourceReader = new BufferedReader(
					new InputStreamReader(sourceFile.getContents()));
			int lineNo = 0;
			for (lineNo = 0; lineNo < region.getStartLine(); lineNo++) {
				sourceReader.readLine();
			}
			while (lineNo <= region.getEndLine()) {
				String line = sourceReader.readLine();
				System.out.println(line);
				lineNo++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

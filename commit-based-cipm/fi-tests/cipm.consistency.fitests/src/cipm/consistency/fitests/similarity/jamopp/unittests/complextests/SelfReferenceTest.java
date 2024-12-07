package cipm.consistency.fitests.similarity.jamopp.unittests.complextests;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.IStructuralFeatureTest;
import cipm.consistency.initialisers.eobject.IEObjectInitialiser;

/**
 * A test class that checks the robustness of similarity checking against cyclic
 * references, where {@link EObject} sub-types used in JaMoPP reference
 * themselves.
 * 
 * @author Alp Torac Genc
 */
public class SelfReferenceTest extends AbstractJaMoPPSimilarityTest implements IStructuralFeatureTest {
	/**
	 * Scans the object type generated with {@code init} for references
	 * ({@link EReference}) {@code Ref}, which can point at the object containing
	 * it. Then sets {@code Ref} to the object containing it, which results in the
	 * said object referencing itself. Finally generates a {@link DynamicTest}
	 * asserting that similarity checking can handle such cases without throwing
	 * exceptions.
	 */
	private Collection<DynamicTest> initialiseCyclicFeatures(IEObjectInitialiser init) {
		var tests = new ArrayList<DynamicTest>();

		var obj = init.instantiate();
		var objCls = obj.eClass().getInstanceClass();

		for (var attr : obj.eClass().getEAllStructuralFeatures()) {
			if (attr.isChangeable()) {
				var attrType = attr.getEType().getInstanceClass();

				/*
				 * Construct 2 objects that reference each other and compare them to each other.
				 * Make sure to not clone those objects, since cloning method itself may cause
				 * an endless recursion.
				 */
				if (attrType.isAssignableFrom(objCls)) {
					var obj1 = init.instantiate();
					this.setValueOf(obj1, attr, obj1);
					var obj2 = init.instantiate();
					this.setValueOf(obj2, attr, obj2);
					tests.add(DynamicTest.dynamicTest(objCls.getSimpleName() + "." + attr.getName(), () -> {
						Assertions.assertDoesNotThrow(() -> this.isSimilar(obj1, obj2));
					}));
				}
			}
		}

		return tests;
	}

	/**
	 * Ensures that similarity checking can handle {@link EObject} instances used in
	 * JaMoPP that contain a reference to themselves and therefore have cyclic
	 * references to themselves.
	 */
	@TestFactory
	public Collection<DynamicTest> testSelfReference() {
		var tests = new ArrayList<DynamicTest>();
		for (var init : this.getUsedInitialiserPackage().getAllInitialiserInstances()) {
			tests.addAll(this.initialiseCyclicFeatures((IEObjectInitialiser) init));
		}
		return tests;
	}
}

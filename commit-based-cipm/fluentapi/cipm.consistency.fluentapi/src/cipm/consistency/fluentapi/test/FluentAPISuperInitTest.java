package cipm.consistency.fluentapi.test;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.emftext.language.java.classifiers.ClassifiersPackage;
import org.emftext.language.java.commons.CommonsPackage;
import org.emftext.language.java.containers.ContainersPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.api.ApiFactory;

public class FluentAPISuperInitTest {
	private static final EClass modECls = ContainersPackage.Literals.MODULE;
	private static final EClass clsECls = ClassifiersPackage.Literals.CLASS;
	private static final EClass cuECls = ContainersPackage.Literals.COMPILATION_UNIT;

	private static final EStructuralFeature nameFeat = CommonsPackage.Literals.NAMED_ELEMENT__NAME;
	private static final EStructuralFeature namespacesFeat = CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES;
	private static final EStructuralFeature classifiersFeat = ContainersPackage.Literals.COMPILATION_UNIT__CLASSIFIERS;

	@Test
	public void xWithFeatTest() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var modName = "modName";

		var mod = api.newX(modECls).xWithFeat(nameFeat, modName)
				.createNow(org.emftext.language.java.containers.Module.class);

		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, mod);
		Assertions.assertEquals(modName, mod.getName());
	}

	@Test
	public void xWithoutFeatTest() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var modName = "modName";

		var mod = api.newX(modECls).xWithFeat(nameFeat, modName)
				.createNow(org.emftext.language.java.containers.Module.class);

		Assertions.assertEquals(modName, mod.getName());
		api.modifyX(mod).xWithoutFeat(nameFeat);
		Assertions.assertNull(mod.getName());
	}

	@Test
	public void xWithAddedFeatTest() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var modNss = new String[] { "ns1", "ns2", "ns3" };

		var mod = api.newX(modECls).xWithAddedFeat(namespacesFeat, modNss)
				.createNow(org.emftext.language.java.containers.Module.class);
		Assertions.assertEquals(modNss.length, mod.getNamespaces().size());
		Assertions.assertFalse(mod.getNamespaces().retainAll(List.of(modNss)));
	}

	@Test
	public void xWithRemovedFeatTest() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var modNss = new String[] { "ns1", "ns2", "ns3" };

		var mod = api.newX(modECls).xWithAddedFeat(namespacesFeat, modNss)
				.createNow(org.emftext.language.java.containers.Module.class);
		Assertions.assertEquals(modNss.length, mod.getNamespaces().size());
		Assertions.assertFalse(mod.getNamespaces().retainAll(List.of(modNss)));
		api.modifyX(mod).xWithRemovedFeat(namespacesFeat, modNss[0]);
		Assertions.assertFalse(mod.getNamespaces().contains(modNss[0]));
	}

	@Test
	public void xWithExactFeatTest() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var modNss = new String[] { "ns1", "ns2", "ns3" };

		var mod = api.newX(modECls).xWithAddedFeat(namespacesFeat, modNss)
				.createNow(org.emftext.language.java.containers.Module.class);
		Assertions.assertEquals(modNss.length, mod.getNamespaces().size());
		Assertions.assertFalse(mod.getNamespaces().retainAll(List.of(modNss)));

		var newNss = new String[] { "ns4", "ns5" };

		api.modifyX(mod).xWithExactFeat(namespacesFeat, newNss);
		for (var ns : modNss)
			Assertions.assertFalse(mod.getNamespaces().contains(ns));
		Assertions.assertEquals(newNss.length, mod.getNamespaces().size());
		Assertions.assertFalse(mod.getNamespaces().retainAll(List.of(newNss)));
	}

	@Test
	public void xWithFeatOfContainerTest() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var cuName = "cu";

		var cls = api.newX(clsECls).createNow(org.emftext.language.java.classifiers.Class.class);
		api.newX(cuECls).xWithFeat(nameFeat, cuName).xWithAddedFeat(classifiersFeat, cls)
				.createNow(org.emftext.language.java.containers.CompilationUnit.class);

		Assertions.assertNull(cls.getName());
		api.modifyX(cls).xWithFeatOfContainer(nameFeat);
		Assertions.assertEquals(cuName, cls.getName());
	}

	@Test
	public void onceExistsTest() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var metName = "met";
		var returnTypeName = "returnType";

		org.emftext.language.java.members.ClassMethod met = null;

		api.newX(org.emftext.language.java.members.ClassMethod.class).markCurrent(metName);
		met = api.getMarkedClassMethod(metName);

		Assertions.assertNull(met.getTypeReference());
		api.onceExists(returnTypeName,
				() -> api.continueMarkedClassMethod(metName)
						.withTypeReference(
								api.newClassifierReference().withTarget(api.getMarkedClass(returnTypeName)).createNow())
						.createNow());
		Assertions.assertNull(met.getTypeReference());

		var returnType = api.newClass().withName(returnTypeName).markCurrent(returnTypeName).createNow();
		Assertions.assertEquals(returnType, met.getTypeReference().getPureClassifierReference().getTarget());

		met = api.continueClassMethod().createNow(org.emftext.language.java.members.ClassMethod.class);
		Assertions.assertEquals(returnType, met.getTypeReference().getPureClassifierReference().getTarget());
	}

	@Test
	public void markCurrentTest_SingleMark() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var key = new Object();

		var mod = api.newX(modECls).markCurrent(key).createNow();

		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, mod);
		Assertions.assertSame(mod, api.getMarked(key));
	}

	@Test
	public void markCurrentTest_MultipleMarks() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var key1 = new Object();
		var key2 = new Object();

		var mod = api.newX(modECls).markCurrent(key1).markCurrent(key2).createNow();

		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, mod);
		Assertions.assertSame(mod, api.getMarked(key1));
		Assertions.assertSame(mod, api.getMarked(key2));
	}

	@Test
	public void unmarkCurrentTest_SingleMark() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var key = new Object();

		var mod = api.newX(modECls).markCurrent(key).unmarkCurrent(key).createNow();

		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, mod);
		Assertions.assertNull(api.getMarked(key));
	}

	@Test
	public void unmarkCurrentTest_MultipleMarks() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var key1 = new Object();
		var key2 = new Object();

		var mod = api.newX(modECls).markCurrent(key1).markCurrent(key2).unmarkCurrent(key1).createNow();

		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, mod);
		Assertions.assertNull(api.getMarked(key1));
		Assertions.assertSame(mod, api.getMarked(key2));
	}
}

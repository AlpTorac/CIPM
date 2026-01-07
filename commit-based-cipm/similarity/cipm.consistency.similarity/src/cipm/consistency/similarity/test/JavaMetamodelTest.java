package cipm.consistency.similarity.test;

import java.util.List;

import org.eclipse.emf.ecore.EcorePackage;
import org.emftext.language.java.classifiers.ClassifiersPackage;
import org.emftext.language.java.commons.CommonsPackage;
import org.emftext.language.java.members.MembersPackage;
import org.emftext.language.java.parameters.ParametersPackage;
import org.emftext.language.java.references.ReferencesPackage;
import org.emftext.language.java.types.TypesPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.similarity.JavaMetamodelProvider;
import cipm.consistency.similarity.MetamodelProvider;
import cipm.consistency.similarity.MetamodelResolver;
import cipm.consistency.similarity.features.DerivedTargetFeature;
import cipm.consistency.similarity.features.OriginalTargetFeature;
import cipm.consistency.similarity.features.TargetFeatureChain;

public class JavaMetamodelTest {
	private static final MetamodelProvider provider = new JavaMetamodelProvider();
	private static final MetamodelResolver resolver = new MetamodelResolver(provider);

	@Test
	public void resolveValidClass() {
		var concreteClassifierECls = ClassifiersPackage.Literals.CONCRETE_CLASSIFIER;
		Assertions.assertEquals(concreteClassifierECls, resolver.resolveClass(concreteClassifierECls.getName()));
	}

	@Test
	public void resolveInvalidClass() {
		var ePackageECls = EcorePackage.Literals.EPACKAGE;
		Assertions.assertNull(resolver.resolveClass(ePackageECls.getName()));
	}

	@Test
	public void resolveValidOriginalFeature() {
		var nameFeature = CommonsPackage.Literals.NAMED_ELEMENT__NAME;
		Assertions.assertEquals(1, resolver.resolveOriginalFeature(nameFeature.getName()).size());
		Assertions.assertEquals(nameFeature, resolver.resolveOriginalFeature(nameFeature.getName()).get(0));
	}

	@Test
	public void resolveInvalidOriginalFeature() {
		var contentsFeature = EcorePackage.Literals.EANNOTATION__CONTENTS;
		Assertions.assertEquals(0, resolver.resolveOriginalFeature(contentsFeature.getName()).size());
	}

	@Test
	public void resolveValidDerivedFeature() {
		var derivedFeat = provider.getTargetMetamodelDerivedFeatures().get(0);
		Assertions.assertEquals(derivedFeat,
				resolver.resolveDerivedFeature(derivedFeat.getFeatEClass(), derivedFeat.getFeatName()));
	}

	@Test
	public void resolveInvalidDerivedFeature_NonExistentClass() {
		var derivedFeat = provider.getTargetMetamodelDerivedFeatures().get(0);
		Assertions.assertNull(resolver.resolveDerivedFeature("someNonExistentClass", derivedFeat.getFeatName()));
	}

	@Test
	public void resolveInvalidDerivedFeature_NonExistentClassAndFeature() {
		Assertions.assertNull(resolver.resolveDerivedFeature("someNonExistentClass", "someNonExistentDerivedFeature"));
	}

	@Test
	public void resolveValidOriginalTargetFeature() {
		var concreteClassifierECls = ClassifiersPackage.Literals.CONCRETE_CLASSIFIER;
		var nameFeature = CommonsPackage.Literals.NAMED_ELEMENT__NAME;

		var originalFeat = new OriginalTargetFeature(ClassifiersPackage.Literals.CONCRETE_CLASSIFIER,
				CommonsPackage.Literals.NAMED_ELEMENT__NAME);
		Assertions.assertEquals(originalFeat,
				resolver.getTargetFeature(concreteClassifierECls.getName(), nameFeature.getName()));
	}

	@Test
	public void resolveInvalidOriginalTargetFeature() {
		var concreteClassifierECls = ClassifiersPackage.Literals.CONCRETE_CLASSIFIER;
		var namespacesFeature = CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES;
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> resolver.getTargetFeature(concreteClassifierECls.getName(), namespacesFeature.getName()));
	}

	@Test
	public void resolveValidDerivedTargetFeatureChain() {

		// Method.parameters.typeReference.target
		// typeReference.target is derived here, since TypeReference does not physically
		// have a feature for it

		var methodECls = MembersPackage.Literals.METHOD;
		var paramsFeat = ParametersPackage.Literals.PARAMETRIZABLE__PARAMETERS;
		var typeRefFeat = TypesPackage.Literals.TYPED_ELEMENT__TYPE_REFERENCE;
		var targetFeat = ReferencesPackage.Literals.ELEMENT_REFERENCE__TARGET;

		var expectedFeatChain = new TargetFeatureChain(List.of(new OriginalTargetFeature(methodECls, paramsFeat),
				new OriginalTargetFeature(ParametersPackage.Literals.PARAMETER, typeRefFeat),
				new DerivedTargetFeature(TypesPackage.Literals.TYPE_REFERENCE,
						org.emftext.language.java.types.Type.class, null, "target")));

		var resolvedFeatChain = resolver.getTargetFeatureChain(String.format("%s.%s.%s.%s", methodECls.getName(),
				paramsFeat.getName(), typeRefFeat.getName(), targetFeat.getName()));

		Assertions.assertTrue(expectedFeatChain.equals(resolvedFeatChain));
	}

	@Test
	public void resolveValidOriginalTargetFeatureChain() {

		// Method.parameters.typeReference

		var methodECls = MembersPackage.Literals.METHOD;
		var paramsFeat = ParametersPackage.Literals.PARAMETRIZABLE__PARAMETERS;
		var typeRefFeat = TypesPackage.Literals.TYPED_ELEMENT__TYPE_REFERENCE;

		var expectedFeatChain = new TargetFeatureChain(List.of(new OriginalTargetFeature(methodECls, paramsFeat),
				new OriginalTargetFeature(ParametersPackage.Literals.PARAMETER, typeRefFeat)));

		var resolvedFeatChain = resolver.getTargetFeatureChain(
				String.format("%s.%s.%s", methodECls.getName(), paramsFeat.getName(), typeRefFeat.getName()));

		Assertions.assertTrue(expectedFeatChain.equals(resolvedFeatChain));
	}

	@Test
	public void resolveInvalidOriginalTargetFeatureChain() {

		// Method.parameters.namespaces
		//
		// "Parameter.namespaces" here is invalid

		var methodECls = MembersPackage.Literals.METHOD;
		var paramsFeat = ParametersPackage.Literals.PARAMETRIZABLE__PARAMETERS;
		var namespacesFeat = CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES;

		Assertions.assertThrows(IllegalArgumentException.class, () -> resolver.getTargetFeatureChain(
				String.format("%s.%s.%s", methodECls.getName(), paramsFeat.getName(), namespacesFeat.getName())));
	}

	@Test
	public void resolveInvalidDerivedTargetFeatureChain() {

		// Method.parameters.someFeat
		//
		// "Parameter.someFeat" does not exist

		var methodECls = MembersPackage.Literals.METHOD;
		var paramsFeat = ParametersPackage.Literals.PARAMETRIZABLE__PARAMETERS;

		Assertions.assertThrows(IllegalArgumentException.class, () -> resolver
				.getTargetFeatureChain(String.format("%s.%s.someFeat", methodECls.getName(), paramsFeat.getName())));
	}
}

package cipm.consistency.similarity;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.emftext.language.java.JavaPackage;
import org.emftext.language.java.classifiers.ClassifiersPackage;
import org.emftext.language.java.commons.CommonsPackage;
import org.emftext.language.java.containers.ContainersPackage;
import org.emftext.language.java.statements.StatementsPackage;
import org.emftext.language.java.types.TypesPackage;

import cipm.consistency.similarity.features.DerivedTargetFeature;

public class JavaMetamodelProvider extends MetamodelProvider {
	private static final List<DerivedTargetFeature> derivedFeats = List.of(
			// TODO Complete

			// Concrete classifier
			new DerivedTargetFeature(ClassifiersPackage.Literals.CONCRETE_CLASSIFIER, String.class,
					List.of(CommonsPackage.Literals.NAMED_ELEMENT__NAME,
							CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES),
					"qualifiedName"),

			// Compilation unit
			new DerivedTargetFeature(ContainersPackage.Literals.COMPILATION_UNIT, String.class,
					List.of(CommonsPackage.Literals.NAMED_ELEMENT__NAME), "normalisedName"),
			new DerivedTargetFeature(ContainersPackage.Literals.COMPILATION_UNIT, String.class,
					List.of(CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES), "normalisedNamespaces"),

			// Expression statement
			new DerivedTargetFeature(StatementsPackage.Literals.EXPRESSION_STATEMENT,
					org.emftext.language.java.statements.Statement.class, null, "predecessor"),
			new DerivedTargetFeature(StatementsPackage.Literals.EXPRESSION_STATEMENT,
					org.emftext.language.java.statements.Statement.class, null, "successor"),

			// Type Reference
			new DerivedTargetFeature(TypesPackage.Literals.TYPE_REFERENCE, org.emftext.language.java.types.Type.class,
					null, "target"), // Added as derived feature, since it does not physically exist under
										// TypeReference, but is required by it
			new DerivedTargetFeature(TypesPackage.Literals.TYPE_REFERENCE, int.class, null, "arrayDimension")

	);

	@Override
	public List<EPackage> getTargetMetamodelPackages() {
		return MetamodelUtil.getAllSubPackages(JavaPackage.eINSTANCE);
	}

	@Override
	public List<EClass> getTargetMetamodelEClasses() {
		return List.copyOf(MetamodelUtil.getAllEClasses(JavaPackage.eINSTANCE));
	}

	@Override
	public List<EStructuralFeature> getTargetMetamodelFeatures() {
		return List.copyOf(MetamodelUtil.getAllFeatures(JavaPackage.eINSTANCE));
	}

	@Override
	public List<DerivedTargetFeature> getTargetMetamodelDerivedFeatures() {
		return derivedFeats;
	}
}

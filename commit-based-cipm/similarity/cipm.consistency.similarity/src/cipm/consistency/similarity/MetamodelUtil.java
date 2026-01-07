package cipm.consistency.similarity;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;

public final class MetamodelUtil {
	/**
	 * A variant of {@link #getAllClasses(Predicate)} with no given predicate.
	 */
	public static Collection<Class<? extends EObject>> getAllClasses(EPackage topPac) {
		return getAllClasses(topPac);
	}

	/**
	 * If the given predicate is null, does not filter the found types.
	 * 
	 * @return All types accessible under the sub-packages of {@link JavaPackage} in
	 *         form of {@link EClass}, whose instance class
	 *         {@code eClass.getInstanceClass()} will be in the return value, which
	 *         fulfill the given predicate.
	 */
	public static Collection<Class<? extends EObject>> getAllClasses(Predicate<EClass> pred, EPackage topPac) {
		var res = new ArrayList<Class<? extends EObject>>();
		Predicate<EClass> predToUse = pred != null ? pred : (a) -> true;
		getAllEClasses(topPac).stream().filter(predToUse)
				.forEach((eCls) -> res.add(getInstanceClassOfEClassifier(eCls)));
		return res;
	}

	/**
	 * @return All {@link EClass}es accessible under the sub-packages of topPac.
	 */
	public static Collection<EClass> getAllEClasses(EPackage topPac) {
		var res = new ArrayList<EClass>();
		var ePacs = topPac.getESubpackages();
		ePacs.forEach((pac) -> pac.getEClassifiers().stream().filter((eClsf) -> eClsf instanceof EClass)
				.forEach((c) -> res.add((EClass) c)));
		return res;
	}

	public static Collection<EClass> getAllConcreteEClasses(EPackage topPac) {
		var res = new ArrayList<EClass>();
		var ePacs = topPac.getESubpackages();
		ePacs.forEach((pac) -> pac.getEClassifiers().stream().filter((eClsf) -> eClsf instanceof EClass)
				.map((c) -> (EClass) c).filter((c) -> !c.isAbstract() && !c.isInterface()).forEach((c) -> res.add(c)));
		return res;
	}

	protected static <T extends EObject> Class<? extends EObject> getInstanceClassOfEObject(T obj) {
		return getInstanceClassOfEClassifier(obj.eClass());
	}

	@SuppressWarnings("unchecked")
	protected static Class<? extends EObject> getInstanceClassOfEClassifier(EClassifier eClsfier) {
		return (Class<? extends EObject>) eClsfier.getInstanceClass();
	}

	public static List<EPackage> getAllSubPackages(EPackage topPac) {
		return new ArrayList<>(topPac.getESubpackages());
	}

	/**
	 * Finds the {@link EClass} corresponding to the given cls, whose instance class
	 * is equal to cls: {@code eCls.getInstanceClass().equals(cls)}.
	 * 
	 * @param cls The type of the Java element, whose {@link EClass} will be
	 *            returned, if cls is the type of a Java element.
	 * 
	 * @return The {@link EClass} corresponding to the class represented by cls.
	 *         Null, if no such {@link EClass} is found under {@link JavaPackage}.
	 */
	public static EClass getEClassForJavaElement(Class<?> cls, EPackage topPac) {
		var ePacs = getAllSubPackages(topPac);
		for (var ePac : ePacs) {
			var eClss = ePac.getEClassifiers();
			for (var eCls : eClss) {
				if (eCls.getInstanceClass().equals(cls)) {
					return eCls instanceof EClass ? (EClass) eCls : null;
				}
			}
		}
		return null;
	}

	/**
	 * Finds the {@link EClass} corresponding to the given cls, whose instance
	 * class' concrete type is equal to cls.
	 * 
	 * @param cls The type of the concrete implementation of the Java element, whose
	 *            corresponding {@link EClass} will be returned.
	 * 
	 * @return The {@link EClass} corresponding to the interface type of cls. Null,
	 *         if no such {@link EClass} is found under {@link JavaPackage}. <b>Note
	 *         that the returned {@link EClass} will be from the interface of cls.
	 *         This means, if cls represents the type xImpl, the returned
	 *         {@link EClass} will belong to x.</b>
	 */
	public static EClass getEClassForJavaElementImpl(Class<?> cls, EPackage topPac) {
		var interfaceType = getInterfaceTypeForJavaElementImpl(cls);
		if (interfaceType != null) {
			return getEClassForJavaElement(interfaceType, topPac);
		}
		return null;
	}

	/**
	 * The interface {@code I} of the implementation of a Java element type
	 * {@code T} within JaMoPP is directly implemented by it and also contains its
	 * name, i.e.: <br>
	 * <br>
	 * T extends I directly and the simple name of T contains the simple name of I.
	 * T can neither be an interface nor abstract.
	 * 
	 * @param <T> The type of the passed parameter. Used to allow making assumptions
	 *            on the return value.
	 * @param cls The type of an implementation of a Java element within JaMoPP.
	 * @return The interface of cls matching the description from above, if it
	 *         exists. Otherwise null.
	 */
	@SuppressWarnings("unchecked")
	public static Class<? extends EObject> getInterfaceTypeForJavaElementImpl(Class<?> cls) {
		if (cls.isInterface() || Modifier.isAbstract(cls.getModifiers())) {
			return null;
		}

		var directIfcs = cls.getInterfaces();
		for (var ifc : directIfcs) {
			if (cls.getSimpleName().contains(ifc.getSimpleName())) {
				return (Class<? extends EObject>) ifc;
			}
		}

		return null;
	}

	/**
	 * @return Types of concrete implementations and interfaces of all Java-Model
	 *         elements.
	 */
	public static Set<Class<? extends EObject>> getAllPossibleTypes(EPackage topPac) {
		return getAllPossibleTypes(topPac.getESubpackages());
	}

	/**
	 * Recursively discovers sub-packages of cPac (including cPac) for
	 * {@link EClassifier}s contained within, aggregates the types represented by
	 * the EClassifiers as a Set and returns the Set.
	 * 
	 * @param cPac The package, which is the start point of the discovery.
	 * @return All types represented by EClassifiers contained in cPac and its
	 *         sub-packages. Includes types of interfaces as well as concrete
	 *         implementation classes.
	 */
	public static Set<Class<? extends EObject>> getAllNestedPossibleTypes(EPackage cPac) {
		var clss = cPac.getEClassifiers();
		var subPacs = cPac.getESubpackages();

		var foundClss = new HashSet<Class<? extends EObject>>();

		if (clss != null) {
			for (var cls : clss) {
				foundClss.add(getInstanceClassOfEClassifier(cls));

				/*
				 * Although cls is technically of type EClassifier, it also implements EClass
				 */
				if (cls instanceof EClass) {
					var castedCls = (EClass) cls;

					/*
					 * Add the concrete implementation class, if cls represents a concrete class
					 */
					if (!castedCls.isAbstract()) {
						foundClss.add(cPac.getEFactoryInstance().create(castedCls).getClass());
					}
				}
			}
		}

		if (subPacs != null) {
			foundClss.addAll(getAllPossibleTypes(subPacs));
		}

		return foundClss;
	}

	/**
	 * @return All types represented by {@link EClassifiers} contained in pacs and
	 *         their sub-packages. Includes types of interfaces as well as concrete
	 *         implementation classes.
	 * @see {@link #getAllPossibleTypes(EPackage)}}
	 */
	public static Set<Class<? extends EObject>> getAllPossibleTypes(Collection<EPackage> pacs) {
		var foundClss = new HashSet<Class<? extends EObject>>();

		for (var pac : pacs) {
			foundClss.addAll(getAllPossibleTypes(pac));
		}

		return foundClss;
	}

	public static List<EStructuralFeature> getAllFeatures(EPackage pac) {
		var eClss = getAllEClasses(pac);
		var feats = new ArrayList<EStructuralFeature>();

		for (var cls : eClss) {
			for (var feat : cls.getEStructuralFeatures()) {
				if (!feats.contains(feat))
					feats.add(feat);
			}
		}

		return feats;
	}
}

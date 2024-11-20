package cipm.consistency.initialisers.jamopp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.emftext.language.java.JavaPackage;
import org.emftext.language.java.commons.Commentable;

/**
 * A utility class that provides information about EObjects used by JaMoPP, as
 * well as methods to access their types. There are further methods, which map
 * their types to the initialisers implemented in sub-packages. <br>
 * <br>
 * This class is intended to be used in tests, which ensure that all necessary
 * initialisers are implemented and can be accessed.
 * 
 * @author Alp Torac Genc
 */
public class JaMoPPHelper {
	/**
	 * @return All types accessible under the sub-packages of {@link JavaPackage} in
	 *         form of {@link EClass}, whose instance class
	 *         {@code eClass.getInstanceClass()} will be in the return value.
	 */
	public Collection<Class<?>> getAllClasses() {
		return this.getAllClasses(null);
	}

	public Collection<Class<?>> getAllClasses(Predicate<EClass> pred) {
		var res = new ArrayList<Class<?>>();
		Predicate<EClass> predToUse = pred != null ? pred : (a) -> true;
		this.getAllEClasses().stream().filter(predToUse).forEach((eCls) -> res.add(eCls.getInstanceClass()));
		return res;
	}

	/**
	 * @return All {@link EClass}es accessible under the sub-packages of
	 *         {@link JavaPackage}.
	 */
	public Collection<EClass> getAllEClasses() {
		var res = new ArrayList<EClass>();
		var ePacs = JavaPackage.eINSTANCE.getESubpackages();
		ePacs.forEach((pac) -> pac.getEClassifiers().stream().filter((eClsf) -> eClsf instanceof EClass)
				.forEach((c) -> res.add((EClass) c)));
		return res;
	}

	/**
	 * @param cls The type of the Java element, whose {@link EClass} will be
	 *            returned, if cls is the type of a Java element.
	 * 
	 * @return The {@link EClass} corresponding to the class represented by cls.
	 *         Null, if no such {@link EClass} is found under {@link JavaPackage}.
	 */
	public EClass getEClassForJavaElement(Class<?> cls) {
		var ePacs = JavaPackage.eINSTANCE.getESubpackages();
		for (var ePac : ePacs) {
			var eClss = ePac.getEClassifiers();
			for (var eCls : eClss) {
				if (eCls.getInstanceClass().equals(cls)) {
					return (EClass) eCls;
				}
			}
		}
		return null;
	}

	/**
	 * @param cls The type of the concrete implementation of the Java element, whose
	 *            corresponding {@link EClass} will be returned.
	 * 
	 * @return The {@link EClass} corresponding to the interface type of cls. Null,
	 *         if no such {@link EClass} is found under {@link JavaPackage}. <b>Note
	 *         that the returned {@link EClass} will be from the interface of cls.
	 *         This means, if cls represents the type xImpl, the returned
	 *         {@link EClass} will belong to x.</b>
	 */
	public EClass getEClassForJavaElementImpl(Class<?> cls) {
		var ePacs = JavaPackage.eINSTANCE.getESubpackages();
		for (var ePac : ePacs) {
			var eClss = ePac.getEClassifiers();
			for (var eCls : eClss) {
				if (cls.getSimpleName().equals(eCls.getInstanceClass().getSimpleName() + this.getImplSuffix())) {
					return (EClass) eCls;
				}
			}
		}
		return null;
	}

	/**
	 * The suffix used in the concrete implementation of EObject classes.
	 */
	public String getImplSuffix() {
		return "Impl";
	}

	/**
	 * @return Types of concrete implementations and interfaces of all Java-Model
	 *         elements.
	 */
	public Set<Class<?>> getAllPossibleTypes() {
		return this.getAllPossibleTypes(JavaPackage.eINSTANCE.getESubpackages());
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
	public Set<Class<?>> getAllPossibleTypes(EPackage cPac) {
		var clss = cPac.getEClassifiers();
		var subPacs = cPac.getESubpackages();

		var foundClss = new HashSet<Class<?>>();

		if (clss != null) {
			for (var cls : clss) {
				foundClss.add(cls.getInstanceClass());

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
			foundClss.addAll(this.getAllPossibleTypes(subPacs));
		}

		return foundClss;
	}

	/**
	 * @return All types represented by {@link EClassifiers} contained in pacs and
	 *         their sub-packages. Includes types of interfaces as well as concrete
	 *         implementation classes.
	 * @see {@link #getAllPossibleTypes(EPackage)}}
	 */
	public Set<Class<?>> getAllPossibleTypes(Collection<EPackage> pacs) {
		var foundClss = new HashSet<Class<?>>();

		for (var pac : pacs) {
			foundClss.addAll(this.getAllPossibleTypes(pac));
		}

		return foundClss;
	}

	/**
	 * Used to determine which EObject implementors should have an initialiser. <br>
	 * <br>
	 * Here, such implementors (initialiser candidates) implement
	 * {@link Commentable} and their names do not end with {@link #implSuffix}.
	 * 
	 * @return The classes from {@link #getAllPossibleTypes()}, which should have a
	 *         corresponding initialiser interface.
	 */
	public Collection<Class<?>> getAllInitialiserCandidates() {
		var fullHierarchy = getAllPossibleTypes();

		var intfcs = fullHierarchy.stream().filter((c) -> Commentable.class.isAssignableFrom(c))
				.filter((c) -> !c.getSimpleName().endsWith(this.getImplSuffix())).toArray(Class<?>[]::new);

		return List.of(intfcs);
	}

	/**
	 * @return The EObject types from {@link #getAllPossibleTypes()}, which should
	 *         have a corresponding concrete initialiser that can instantiate the
	 *         said type.
	 */
	public Collection<Class<?>> getAllConcreteInitialiserCandidates() {
		var fullHierarchy = getAllPossibleTypes();

		var intfcs = fullHierarchy.stream().filter((c) -> Commentable.class.isAssignableFrom(c))
				.filter((c) -> fullHierarchy.stream()
						.anyMatch((c2) -> c2.getSimpleName().equals(c.getSimpleName() + this.getImplSuffix())))
				.toArray(Class<?>[]::new);

		return List.of(intfcs);
	}
}

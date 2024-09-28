package cipm.consistency.initialisers.emftext.generics;

import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.generics.TypeParameter;
import org.emftext.language.java.imports.Import;
import org.emftext.language.java.imports.PackageImport;
import org.emftext.language.java.types.TypeReference;

import cipm.consistency.initialisers.emftext.annotations.IAnnotableInitialiser;
import cipm.consistency.initialisers.emftext.classifiers.IClassifierInitialiser;

/**
 * An interface meant for {@link IInitialiser} implementors that are supposed to
 * create {@link TypeParameter} instances. <br>
 * <br>
 * <b>Note: {@link TypeParameter} cannot add {@link Import}s to its container,
 * so attempting to add {@link Import} to it has no effect.</b>
 * 
 * @author atora
 *
 */
public interface ITypeParameterInitialiser extends IClassifierInitialiser, IAnnotableInitialiser {
	@Override
	public TypeParameter instantiate();

	public default boolean addExtendType(TypeParameter tp, TypeReference extType) {
		if (extType != null) {
			tp.getExtendTypes().add(extType);
			return tp.getExtendTypes().contains(extType);
		}
		return true;
	}

	/**
	 * {@link TypeParameter} cannot add imports to its container, so attempting to
	 * add imports to it has no effect. <br>
	 * <br>
	 * This is caused by the inconsistency in the {@link Classifier} sub-hierarchy.
	 */
	@Override
	public default boolean addImport(Classifier cls, Import imp) {
		return false;
	}

	/**
	 * {@link TypeParameter} cannot add imports to its container, so attempting to
	 * add imports to it has no effect. <br>
	 * <br>
	 * This is caused by the inconsistency in the {@link Classifier} sub-hierarchy.
	 */
	@Override
	public default boolean addPackageImport(Classifier cls, PackageImport pacImp) {
		return false;
	}

	public default boolean addExtendTypes(TypeParameter tp, TypeReference[] extTypes) {
		return this.doMultipleModifications(tp, extTypes, this::addExtendType);
	}
}

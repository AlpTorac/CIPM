package cipm.consistency.fitests.similarity.java.initialiser.generics;

import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.generics.TypeParameter;
import org.emftext.language.java.imports.Import;
import org.emftext.language.java.types.TypeReference;

import cipm.consistency.fitests.similarity.java.initialiser.annotations.IAnnotableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.classifiers.IClassifierInitialiser;

public interface ITypeParameterInitialiser extends IClassifierInitialiser, IAnnotableInitialiser {
    @Override
    public TypeParameter instantiate();
	public default boolean addExtendType(TypeParameter tp, TypeReference tref) {
		if (tref != null) {
			tp.getExtendTypes().add(tref);
			return tp.getExtendTypes().contains(tref);
		}
		return true;
	}
    
    // FIXME: Find out if imports can somehow be added to TypeParameter
    
    /**
     * {@link TypeParameter} cannot add imports to its container, so attempting
     * to add imports to it has no effect.
     * 
     * This is caused by the inconsistency in the {@link Classifier} sub-hierarchy.
     */
    @Override
    public default boolean addImport(Classifier cls, Import imp) {
    	return true;
    }
    
    /**
     * {@link TypeParameter} cannot add imports to its container, so attempting
     * to add imports to it has no effect.
     * 
     * This is caused by the inconsistency in the {@link Classifier} sub-hierarchy.
     */
    @Override
    public default boolean addPackageImport(Classifier cls, Import imp) {
    	return true;
    }
    
	public default boolean addExtendTypes(TypeParameter tp, TypeReference[] trefs) {
		return this.addXs(tp, trefs, this::addExtendType);
	}
}

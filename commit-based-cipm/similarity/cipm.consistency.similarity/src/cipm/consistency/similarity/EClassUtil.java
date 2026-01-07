package cipm.consistency.similarity;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;

public final class EClassUtil {
	public static List<EClass> getEClasses(MetamodelProvider provider, Predicate<EClass> eClsPred) {
		Predicate<EClass> pred = eClsPred != null ? eClsPred : (t) -> true;
		return provider.getTargetMetamodelEClasses().stream().filter(pred).collect(Collectors.toList());
	}

	public static List<EClass> getConcreteEClasses(MetamodelProvider provider) {
		return getEClasses(provider, (cls) -> !cls.isAbstract() && !cls.isInterface());
	}

	public static List<EClass> getAllSubEClasses(MetamodelProvider provider, EClass eCls) {
		return getEClasses(provider, (c) -> eCls.isSuperTypeOf(c));
	}

	public static List<EClass> getAllSuperEClasses(MetamodelProvider provider, EClass eCls) {
		return eCls.getEAllSuperTypes();
	}
}

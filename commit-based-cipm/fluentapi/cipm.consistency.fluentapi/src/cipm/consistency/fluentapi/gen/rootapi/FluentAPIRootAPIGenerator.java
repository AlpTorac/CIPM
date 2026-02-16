package cipm.consistency.fluentapi.gen.rootapi;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import cipm.consistency.fluentapi.gen.FluentAPIConstants;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationContext;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.init.FluentAPIInitialisationConstants;
import cipm.consistency.fluentapi.gen.init.FluentAPIInitialisationEClassGenerator;
import cipm.consistency.fluentapi.gen.superinit.FluentAPISuperInitialisationEClassGenerator;

public class FluentAPIRootAPIGenerator {
	public List<EPackage> generateRootAPIPackages(FluentAPIGenerationContext context) {
		var rootPacs = generateFluentAPIRootPackage();

		context.setRootPackage(rootPacs.get(0));
		context.setApiPackage(rootPacs.get(rootPacs.size() - 1));
		context.setInitsPackage(generateInitialisationsPackage(context));
		context.setPlaceholderEDataTypesPac(generateArrayTypesPackage(context));

		context.setFluentAPIECls(generateRootAPIEClass());
		context.getApiPackage().getEClassifiers().add(context.getFluentAPIECls());

		context.setInitSuperECls(generateInitSuperTypeEClass());
		context.getApiPackage().getEClassifiers().add(context.getInitSuperECls());

		generateConcreteInitEClasses(context);
		context.getInitsPackage().getEClassifiers().addAll(context.getAllInitEClss());

		setupRootAPIEClass(context);
		setupInitSuperTypeEClass(context);
		setupConcreteInitEClasses(context);

		return rootPacs;
	}

	public List<EPackage> generateFluentAPIRootPackage() {
		return FluentAPIGenerationUtil.generatePackages(FluentAPIRootAPIConstants.getFluentAPIRootPackageURI(),
				FluentAPIRootAPIConstants.getFluentAPIRootPackageName());
	}

	private EPackage generateInitialisationsPackage(FluentAPIGenerationContext context) {
		return FluentAPIGenerationUtil.generateSubPackage(context.getApiPackage(),
				FluentAPIInitialisationConstants.getFluentAPIInitialisationsPackageName());
	}

	private EPackage generateArrayTypesPackage(FluentAPIGenerationContext context) {
		return FluentAPIGenerationUtil.generateSubPackage(context.getApiPackage(),
				FluentAPIConstants.getFluentAPIPlaceholderEDataTypesPackageName());
	}

	private EClass generateInitSuperTypeEClass() {
		return new FluentAPISuperInitialisationEClassGenerator().generateSuperInitialisationEClass();
	}

	private void setupInitSuperTypeEClass(FluentAPIGenerationContext context) {
		new FluentAPISuperInitialisationEClassGenerator().setupSuperInitialisationEClass(context);
	}

	private List<EClass> generateConcreteInitEClasses(FluentAPIGenerationContext context) {
		var initEClss = new FluentAPIInitialisationEClassGenerator().generateFluentAPIInitialisationClasses(context);
		return initEClss;
	}

	private void setupConcreteInitEClasses(FluentAPIGenerationContext context) {
		context.getAllInitEClss().forEach((cls) -> cls.getESuperTypes().add(context.getInitSuperECls()));
	}

	private EClass generateRootAPIEClass() {
		return new FluentAPIRootAPIEClassGenerator().generateRootAPIEClass();
	}

	private void setupRootAPIEClass(FluentAPIGenerationContext context) {
		new FluentAPIRootAPIEClassGenerator().setupRootAPIEClass(context);
	}
}

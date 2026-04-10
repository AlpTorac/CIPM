package cipm.consistency.fluentapi.builder;

import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationContext;
import cipm.consistency.fluentapi.gen.FluentAPIGenerator;
import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelFeatureFilter;
import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelPackageProvider;
import cipm.consistency.fluentapi.gen.metamodels.java.FluentAPIGenerationJavaMetamodelPostProcessor;
import cipm.consistency.fluentapi.gen.metamodels.java.FluentAPIJavaMetamodelFeatureFilter;
import cipm.consistency.fluentapi.gen.metamodels.java.FluentAPIJavaMetamodelPackageProvider;
import cipm.consistency.fluentapi.gen.postprocessor.FluentAPIGenerationBigNumberParameterPostProcessor;
import cipm.consistency.fluentapi.gen.postprocessor.FluentAPIGenerationForEachOverloadPostProcessor;
import cipm.consistency.fluentapi.gen.postprocessor.FluentAPIGenerationMultipleValueParameterSameMethodBodyOverloadPostProcessor;

public class FluentJavaAPIBuilder extends FluentAPIAbstractBuilder {
	private static final FluentAPITargetMetamodelPackageProvider provider = new FluentAPIJavaMetamodelPackageProvider();
	private static final FluentAPITargetMetamodelFeatureFilter filter = new FluentAPIJavaMetamodelFeatureFilter();

	@Test
	public void generateModelFiles() {
		cleanPreviousModelFiles();

		// TODO Generate API in "src-gen" folder instead of "src" folder

		// TODO Automate GenModel generation

		// TODO Fully automate API generation

		var context = new FluentAPIGenerationContext();
		context.setTargetMetamodelPackageProvider(getTargetMetamodelPackageProvider());
		context.setTargetMetamodelFeatureFilter(getTargetMetamodelFeatureFilter());

		var resSet = new ResourceSetImpl();
		var res = resSet.createResource(URI.createFileURI(getEcoreModelFilePath().toString()));

		new FluentAPIGenerator().generateRootAPIPackages(context);

		new FluentAPIGenerationJavaMetamodelPostProcessor(context.getAllInitEClss()).apply();

		new FluentAPIGenerationBigNumberParameterPostProcessor(context.getAllInitEClss()).apply();

		var allEClss = new ArrayList<EClass>();
		allEClss.add(context.getFluentAPIECls());
		allEClss.add(context.getInitSuperECls());
		allEClss.addAll(context.getAllInitEClss());

		new FluentAPIGenerationForEachOverloadPostProcessor(context, allEClss).apply();
		new FluentAPIGenerationMultipleValueParameterSameMethodBodyOverloadPostProcessor(context, allEClss).apply();

		res.getContents().add(context.getRootPackage());

		try {
			res.save(null);
		} catch (IOException e) {
			e.printStackTrace();
			Assertions.fail(e);
		}
	}

	@Override
	protected FluentAPITargetMetamodelPackageProvider getTargetMetamodelPackageProvider() {
		return provider;
	}

	@Override
	protected FluentAPITargetMetamodelFeatureFilter getTargetMetamodelFeatureFilter() {
		return filter;
	}
}

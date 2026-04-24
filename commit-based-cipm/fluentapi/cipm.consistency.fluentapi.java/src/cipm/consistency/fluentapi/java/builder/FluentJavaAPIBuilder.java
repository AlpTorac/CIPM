package cipm.consistency.fluentapi.java.builder;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelFactory;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.builder.FluentAPIAbstractBuilder;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationContext;
import cipm.consistency.fluentapi.gen.FluentAPIGenerator;
import cipm.consistency.fluentapi.java.metamodel.FluentAPIJavaMetamodelFeatureFilter;
import cipm.consistency.fluentapi.java.metamodel.FluentAPIJavaMetamodelPackageProvider;
import cipm.consistency.fluentapi.java.postprocessor.FluentAPIGenerationJavaMetamodelPostProcessor;
import cipm.consistency.fluentapi.metamodel.FluentAPITargetMetamodelFeatureFilter;
import cipm.consistency.fluentapi.metamodel.FluentAPITargetMetamodelPackageProvider;
import cipm.consistency.fluentapi.postprocessor.FluentAPIGenerationBigNumberParameterPostProcessor;
import cipm.consistency.fluentapi.postprocessor.FluentAPIGenerationForEachOverloadPostProcessor;
import cipm.consistency.fluentapi.postprocessor.FluentAPIGenerationMultipleValueParameterSameMethodBodyOverloadPostProcessor;

public class FluentJavaAPIBuilder extends FluentAPIAbstractBuilder {
	private static final FluentAPITargetMetamodelPackageProvider provider = new FluentAPIJavaMetamodelPackageProvider();
	private static final FluentAPITargetMetamodelFeatureFilter filter = new FluentAPIJavaMetamodelFeatureFilter();

	@Test
	public void generateModelFiles() {
		cleanPreviousModelFiles();

		var context = new FluentAPIGenerationContext();
		context.setTargetMetamodelPackageProvider(provider);
		context.setTargetMetamodelFeatureFilter(filter);

		var modelResSet = new ResourceSetImpl();
		var ecoreRes = modelResSet.createResource(URI.createFileURI(getEcoreModelFilePath().toString()));
		var genModelRes = modelResSet.createResource(URI.createFileURI(getGenModelFilePath().toString()));

		generateEcoreModel(ecoreRes, context);
		generateGenModel(genModelRes, ecoreRes);

		try {
			ecoreRes.save(null);
			genModelRes.save(null);
		} catch (IOException e) {
			e.printStackTrace();
			Assertions.fail(e);
		}
	}

	private GenModel generateGenModel(Resource genModelRes, Resource ecoreRes) {
		var pluginName = "cipm.consistency.fluentapi.java";
		var relativeModelDirPath = Path.of(pluginName, "src-gen");

		var genModel = GenModelFactory.eINSTANCE.createGenModel();
		genModelRes.getContents().add(genModel);

		genModel.setModelDirectory("/" + relativeModelDirPath.toString());
		genModel.setOperationReflection(true);
		genModel.setImportOrganizing(true);

		genModel.setComplianceLevel(getJDKVersion());
		genModel.setModelName(getModelName());
		genModel.setModelPluginID(pluginName);
		genModel.getForeignModel().add(ecoreRes.getURI().lastSegment());

		var javaGenModel = provider.getTargetMetamodelGenModels().get(0);
		genModel.getUsedGenPackages().addAll(javaGenModel.getGenPackages());

		var initEPacs = new ArrayList<EPackage>();
		var toGen = (EPackage) ecoreRes.getContents().get(0);

		initEPacs.add(toGen);
		genModel.initialize(initEPacs);

		genModel.reconcile();

		genModel.setCanGenerate(true);
		return genModel;
	}

	private void generateEcoreModel(Resource ecoreRes, FluentAPIGenerationContext context) {
		new FluentAPIGenerator().generateRootAPIPackages(context);
		new FluentAPIGenerationJavaMetamodelPostProcessor(context.getAllInitEClss(), provider).apply();
		new FluentAPIGenerationBigNumberParameterPostProcessor(context.getAllInitEClss()).apply();

		var allEClss = new ArrayList<EClass>();
		allEClss.add(context.getFluentAPIECls());
		allEClss.add(context.getInitSuperECls());
		allEClss.addAll(context.getAllInitEClss());

		new FluentAPIGenerationForEachOverloadPostProcessor(context, allEClss).apply();
		new FluentAPIGenerationMultipleValueParameterSameMethodBodyOverloadPostProcessor(context, allEClss).apply();

		ecoreRes.getContents().add(context.getRootPackage());
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

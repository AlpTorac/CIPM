package cipm.consistency.vsum.test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import cipm.consistency.commitintegration.IJavaModelParserListener;
import cipm.consistency.commitintegration.JavaParserAndPropagatorUtils;
import tools.vitruv.framework.vsum.VirtualModel;

public class FITestResourceGenerator extends AbstractTEAMMATESCITest {
	private static final Logger LOGGER = Logger.getLogger("cipm." + FITestResourceGenerator.class.getSimpleName());
	private int testNumber = 0;
	
	@AfterEach
	@Override
	public void tearDown() throws Exception {
		super.tearDown();
		JavaParserAndPropagatorUtils.removeAllParseListeners();
		this.testNumber = testNumber + 1;
	}
	
	@Override
	protected void prepareForControllerInit() {
		this.addJavaModelParseListeners();
	}
	
	protected void addJavaModelParseListeners() {
		var parseListener = new IJavaModelParserListener() {
			private Path fiTestResourcePath;
			
			@Override
			public void javaModelParsed(Path dir, Path target, VirtualModel vsum, Path configPath, Resource all) {
				/* 
				 * Changing the URI of the Resource all and
				 * then calling all.save(null) results in NullPointerException
				 */ 
				try {
					JavaParserAndPropagatorUtils.parseJavaCodeIntoOneModel(dir, this.fiTestResourcePath, configPath).save(null);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				throw new ResourceGeneratedException();
			}

			@Override
			public void setVariables(Object... vars) {
				this.fiTestResourcePath = (Path) vars[0];
			}
		};
		
		var newPathString = this.getFITestsTargetResourcePath();
		var newURI = URI.createFileURI(newPathString);
		var newPath = Path.of(newPathString);
		
		LOGGER.debug("Setting listener path to: " + newPath.toString() + " made of: " + newURI.toString() + " made of: " + newPath);
		parseListener.setVariables(newPath);
		JavaParserAndPropagatorUtils.addParseListener(parseListener);
	}
	
	protected String getTestSpecificModelID() {
		return String.valueOf(this.testNumber);
	}
	
	protected String getTestSpecificModelIDPrefix() {
		return "test";
	}
	
	protected String getFITestsPath() {
		return "fi-tests" + File.separator + "cipm.consistency.fitests";
	}
	
	protected String getFITestsTargetResourcePath() {
		return  this.getFITestsAbsoluteTargetPath() + File.separator +
				this.getTestName() +
				File.separator + "JavaModel-" +
				this.getTestSpecificModelIDPrefix() +
				this.getTestSpecificModelID() + ".javaxmi";
	}
	
	protected String getFITestsAbsoluteTargetPath() {
		var currentPath = new File(".").getAbsoluteFile();
		var pathToTarget = currentPath.getParentFile()
				.getParentFile()
				.getParentFile()
				.getAbsolutePath();
		pathToTarget += File.separator + this.getFITestsPath() + File.separator + "target";
		return pathToTarget;
	}
	
//	@Disabled("Enable to generate resource")
	@Test
	public void testTeammatesIntegration() throws Exception {
		try {
			executePropagationAndEvaluation(null, this.getCommitHash(0), 0);
		} catch (ResourceGeneratedException e) {
			LOGGER.debug("Resource generated for commit hash 0");
		}
	}
	
	protected class ResourceGeneratedException extends RuntimeException {
		private static final long serialVersionUID = 810010594491292049L;
	}
}

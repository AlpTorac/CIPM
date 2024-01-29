package cipm.consistency.vsum.test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;

import cipm.consistency.commitintegration.IJavaModelParserListener;
import cipm.consistency.commitintegration.JavaParserAndPropagatorUtils;
import tools.vitruv.framework.vsum.VirtualModel;

/**
 * A class that helps initialise the hook needed to generate test resources required for
 * FirstInstance tests (fitests). 
 * <br><br>
 * It has to be initialised via {@link #init(String, String, boolean)} and finalised via
 * {@link #finalise()} to add and remove the hook respectively. If the boolean parameter, fastReturn,
 * is set to true, the generation of the test resource will result in a {@link ResourceGeneratedException},
 * which has to be caught by passing the action that generates the test resource as a Callable instance
 * to {@link #generateResourceWhile(Callable)}.
 * <br><br>
 * If fastReturn parameter of {@link #init(String, String, boolean)} was true, make sure to use
 * {@link #generateResourceWhile(Callable)} method to avoid getting {@link ResourceGeneratedException}.
 * 
 * @author atora
 * @see {@link ResourceGeneratedException}, {@link #init(String, String, boolean)}
 */
class FITestResourceGenerator {
	private static final Logger LOGGER = Logger.getLogger("cipm." + FITestResourceGenerator.class.getSimpleName());
	private IJavaModelParserListener parseListener;
	
	/**
	 * If the boolean parameter, fastReturn, is set to true, the generation of the test resource
	 * will result in a {@link ResourceGeneratedException}, which has to be caught by passing
	 * the action that generates the test resource as a Callable instance
	 * to {@link #generateResourceWhile(Callable)}.
	 * 
	 * @param testType The name of the test group ({@link AbstractCITest#getTestType()}
	 * @param modelFileName The name of the file, to which the generated Java model will be saved
	 * @param fastReturn Whether the call chain should end with the generation of the new model
	 * with an Exception, which will be caught internally (
	 * {@link #generateResourceWhile(Callable)},
	 * {@link #generateResource(Path, Path, VirtualModel, Path, Resource, boolean)}
	 */
	public void init(String testType, String modelFileName, boolean fastReturn) {
		this.removeListener();
		
		this.parseListener = new IJavaModelParserListener() {
			private Path fiTestResourcePath;
			private boolean fastReturn;
			
			@Override
			public void javaModelParsed(Path dir, Path target, VirtualModel vsum, Path configPath, Resource all) {
				generateResource(dir, this.fiTestResourcePath, vsum, configPath, all, this.fastReturn);
			}

			@Override
			public void setVariables(Object... vars) {
				this.fiTestResourcePath = (Path) vars[0];
				this.fastReturn = (boolean) vars[1];
			}
		};
		
		var newPathString = this.getFITestsTargetResourcePath(testType, modelFileName);
		var newURI = URI.createFileURI(newPathString);
		var newPath = Path.of(newPathString);
		
		LOGGER.debug("Setting listener path to: " + newPath.toString() + " made of: " + newURI.toString() + " made of: " + newPath);
		this.parseListener.setVariables(newPath, fastReturn);
		JavaParserAndPropagatorUtils.addParseListener(this.parseListener);
	}
	
	/**
	 * Method to be called to clean up
	 */
	public void finalise() {
		this.removeListener();
	}

	/**
	 * Removes the hook from {@link JavaParserAndPropagatorUtils}
	 */
	protected void removeListener() {
		if (this.parseListener != null) {
			JavaParserAndPropagatorUtils.removeParseListener(this.parseListener);
		}
	}
	
	/**
	 * @return The absolute path of target folder of FirstInstance tests
	 */
	protected String getFITestsAbsoluteTargetPath() {
		var currentPath = new File(".").getAbsoluteFile();
		
		var pathToTarget = currentPath.getParentFile()
				.getParentFile()
				.getParentFile()
				.getAbsolutePath();
		
		pathToTarget += File.separator + "fi-tests" +
				File.separator + "cipm.consistency.fitests" +
				File.separator + "target";
		
		return pathToTarget;
	}
	
	/**
	 * @param testType The name of the test group
	 * @param modelFileName The name of the file,
	 * to which the newly generated Java-Model will be saved (without extension)
	 * @return The absolute path of the file (described via parameters)
	 * in the target folder of FirstInstance
	 */
	protected String getFITestsTargetResourcePath(String testType, String modelFileName) {
		return  this.getFITestsAbsoluteTargetPath() + File.separator +
				testType + File.separator + modelFileName + ".javaxmi";
	}
	
	/**
	 * Use this method to make the call chain terminate as soon as the needed
	 * test resource is generated.
	 * 
	 * This has no effect, unless fastReturn parameter of {@link #init(String, String, boolean)}
	 * was true.
	 */
	public void generateResourceWhile(Callable<Boolean> c) throws Exception {
		try {
			c.call();
		} catch (ResourceGeneratedException e) {
			LOGGER.debug("FITest resource generated");
		}
	}
	
	/**
	 * Generates the FirstInstance test resource. Meant to be used only the listener defined
	 * in {@link #init(String, String, boolean)}
	 * 
	 * Throws a {@link ResourceGeneratedException} return as soon as the said test resource is generated,
	 * if the fastReturn parameter is true.
	 */
	protected void generateResource(Path dir, Path target, VirtualModel vsum, Path configPath, Resource all, boolean fastReturn) {
		/* 
		 * Changing the URI of the Resource all and
		 * then calling all.save(null) results in NullPointerException
		 */ 
		try {
			JavaParserAndPropagatorUtils.parseJavaCodeIntoOneModel(dir, target, configPath).save(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (fastReturn) {
			throw new ResourceGeneratedException();
		}
	}

	/**
	 * An Exception that is only meant to be thrown/caught by the internals of
	 * {@link FITestResourceGenerator}. This is a temporary solution for generating
	 * test resources for FirstInstance tests without having to propagate models,
	 * which can take a very long time.
	 * 
	 * @author atora
	 */
	private class ResourceGeneratedException extends RuntimeException {
		private static final long serialVersionUID = 810010594491292049L;
	}
}
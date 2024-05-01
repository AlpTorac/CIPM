package cipm.consistency.fitests.similarity.java;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;
import java.util.stream.Stream;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import jamopp.options.ParserOptions;
import jamopp.parser.jdt.singlefile.JaMoPPJDTSingleFileParser;

public class SPLevoModelsSimilarityTest extends AbstractSimilarityTest {
	private static final Logger LOGGER = Logger.getLogger("cipm." + SPLevoModelsSimilarityTest.class.getSimpleName());
	
	/**
	 * The name of the root directory of the models from SPLevo
	 */
	private static final String splevoModelImplDirName = "splevo-testmodels";
	/**
	 * Path to the root folder of the models from SPLevo
	 */
	private static final String splevoModelImplPath = new File("").getAbsoluteFile().getAbsolutePath()
			+ File.separator + splevoModelImplDirName;
	
	/**
	 * List of the parent directory of the directories
	 * {@link #model1Name} and {@link #model2Name}.
	 */
	private static final Collection<Path> modelDirs = new ArrayList<Path>();
	
	/**
	 * The first model to parse.
	 */
	private static final String model1Name = "a";
	/**
	 * The second model to parse.
	 */
	private static final String model2Name = "b";
	
	/**
	 * The path, at which the resource file's URI will point at.
	 */
	private static Path targetPath = Path.of(AbstractSimilarityTest.getAbstractSimilarityTestResourceRootPath());
	
	@BeforeAll
	public static void setUpBeforeAll() {
		modelDirs.addAll(discoverFiles(new File(splevoModelImplPath)));
	}
	
	@BeforeEach
	@Override
	public void setUp() {
		super.setUp();
	}
	
	private static String readEffectiveCode(File f) throws IOException {
		var content = Files.readString(f.toPath());
		
		return content.replaceAll("\\n", "")
				.replaceAll("\\r", "")
				.replaceAll("\\s", "");
	}
	
	private static boolean filesEqual(File f1, File f2) throws IOException {
		var f1Content = readEffectiveCode(f1);
		var f2Content = readEffectiveCode(f2);
		
		return f1Content.equals(f2Content);
	}
	
	private static boolean dirsEqual(File dir1, File dir2) throws IOException {
		LOGGER.debug("Comparing: " + dir1.getName() + " and " + dir2.getName());
		
		// There cannot be 2 files with the same path, name and extension
		// so using TreeSet, which sorts the files spares doing so here
		var files1 = new TreeSet<File>();
		var files2 = new TreeSet<File>();
		
		for (var f : dir1.listFiles()) {
			files1.add(f);
		}
		
		for (var f : dir2.listFiles()) {
			files2.add(f);
		}
		
		if (files1.size() != files2.size()) {
			return false;
		}
		
		var fileIter1 = files1.iterator();
		var fileIter2 = files2.iterator();
		
		for (int i = 0; i < files1.size(); i++) {
			var f1 = fileIter1.next();
			var f2 = fileIter2.next();
			
			if (f1.isDirectory() && f2.isDirectory()) {
				if (!dirsEqual(f1, f2)) {
					LOGGER.debug("Directories " + f1.getName() + " and " + f2.getName() + " are not equal");
					return false;
				}
			}
			else if (f1.isFile() && f2.isFile()) {
				if (!filesEqual(f1, f2)) {
					LOGGER.debug("Files " + f1.getName() + " and " + f2.getName() + " are not equal");
					return false;
				}
			}
			else {
				LOGGER.debug("Unexpected case there is a file and a directory");
				return false;
			}
		}
		
		return true;
	}
	
	private static Resource parseModelsDir(Path modelDir) {
		// Leave out commented options
		ParserOptions.CREATE_LAYOUT_INFORMATION.setValue(Boolean.FALSE);
		ParserOptions.REGISTER_LOCAL.setValue(Boolean.TRUE);
//		ParserOptions.RESOLVE_EVERYTHING.setValue(Boolean.TRUE);
//		ParserOptions.RESOLVE_ALL_BINDINGS.setValue(Boolean.TRUE);
		
		JaMoPPJDTSingleFileParser parser = new JaMoPPJDTSingleFileParser();
		parser.setResourceSet(new ResourceSetImpl());
		ResourceSet resourceSet = parser.parseDirectory(modelDir);
		
		ResourceSet next = new ResourceSetImpl();
		Resource all = next.createResource(URI.createFileURI(targetPath.toAbsolutePath().toString()));
		
		var filteredResources = new ArrayList<Resource>();
		resourceSet.getResources().stream()
			.filter((r) -> r.getURI().path().contains(splevoModelImplDirName))
			.forEach((r) -> filteredResources.add(r));
		
		for (Resource r : filteredResources) {
			// Filter Resources in ResourceSet that belong in the modelDir (based on URI)
			all.getContents().addAll(r.getContents());
		}
		return all;
	}
	
	private static void discoverFiles(File dirToDiscover, Collection<Path> foundModelDirs) {
		if (dirToDiscover != null && dirToDiscover.isDirectory()) {
			var discovered = new ArrayList<File>();
			
			for (var f : dirToDiscover.listFiles()) {
				if (!f.getName().equals(model1Name) &&
						!f.getName().equals(model2Name)) {
					discovered.add(f);
				} else if (!foundModelDirs.contains(dirToDiscover.toPath())) {
					foundModelDirs.add(dirToDiscover.toPath());
				}
			}
			
			discovered.forEach((d) -> discoverFiles(d, foundModelDirs));
		}
	}
	
	private static Collection<Path> discoverFiles(File dirToDiscover) {
		var foundModelDirs = new ArrayList<Path>();
		discoverFiles(dirToDiscover, foundModelDirs);
		return foundModelDirs;
	}
	
	private static Stream<Arguments> generateReferenceEqualityTestParams() {
		var args = new ArrayList<Arguments>();
		
		modelDirs.forEach((md) -> {
			var res1 = parseModelsDir(Paths.get(md.toString(), model1Name));
			var res2 = parseModelsDir(Paths.get(md.toString(), model2Name));
			
			args.add(Arguments.of(res1, res1, true));
			args.add(Arguments.of(res2, res2, true));
		});
		
		return args.stream();
	}
	
	private static Stream<Arguments> generateEqualityTestParams() {
		var args = new ArrayList<Arguments>();
		
		modelDirs.forEach((md) -> {
			var model1Path = Paths.get(md.toString(), model1Name);
			var model2Path = Paths.get(md.toString(), model2Name);
			
			var res11 = parseModelsDir(model1Path);
			var res12 = parseModelsDir(model1Path);
			
			var res21 = parseModelsDir(model2Path);
			var res22 = parseModelsDir(model2Path);
			
			args.add(Arguments.of(res11, res12, true));
			args.add(Arguments.of(res21, res22, true));
		});
		
		return args.stream();
	}
	
	private static Stream<Arguments> generateUnsimilarityTestParams() {
		var args = new ArrayList<Arguments>();
		
		modelDirs.forEach((md) -> {
			var model1Path = Paths.get(md.toString(), model1Name);
			var model2Path = Paths.get(md.toString(), model2Name);
			
			var contentEquality = false;
			
			try {
				contentEquality = dirsEqual(model1Path.toFile(), model2Path.toFile());
			} catch (IOException e) {
				LOGGER.debug("Could not read paths: " + model1Path.toString() + " and " + model2Path.toString());
				Assertions.fail();
			}
			
			LOGGER.debug(md.getFileName() + " contents equal: " + contentEquality);
			
			var res1 = parseModelsDir(model1Path);
			var res2 = parseModelsDir(model2Path);
			
			args.add(Arguments.of(res1, res2, contentEquality));
		});
		
		return args.stream();
	}
	
	protected void testSimilarity(Resource res1, Resource res2, Boolean areSimilar) {
		Assertions.assertEquals(areSimilar, this.areSimilar(res1.getContents(), res2.getContents()));
	}
	
	@ParameterizedTest
	@MethodSource({"generateReferenceEqualityTestParams"})
	public void sameResourceSimilarityTest(Resource res1, Resource res2, Boolean areSimilar) {
		this.testSimilarity(res1, res2, areSimilar);
	}
	
	@ParameterizedTest
	@MethodSource({"generateEqualityTestParams"})
	public void sameFileSimilarityTest(Resource res1, Resource res2, Boolean areSimilar) {
		this.testSimilarity(res1, res2, areSimilar);
	}
	
	@ParameterizedTest
	@MethodSource({"generateUnsimilarityTestParams"})
	public void differentFileSimilarityTest(Resource res1, Resource res2, Boolean areSimilar) {
		// TODO: Make a break point and check the parsed java resource or save it as a file and check
		this.testSimilarity(res1, res2, areSimilar);
	}
}

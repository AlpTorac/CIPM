package cipm.consistency.fitests;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.merge.BatchMerger;
import org.eclipse.emf.compare.merge.IMerger;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class ResourcePersistenceTest extends AbstractFITest {
	private final String workingTeammatesPath = this.getWorkingResourcesPath() + File.separator + "TeammatesTest";
	
	private static final String INITIAL_MODEL_NAME = "JavaModel-test0.javaxmi";
	private static final String FIRST_MODEL_NAME = "JavaModel-test1.javaxmi";
	
	@BeforeEach
	public void setUp() throws IOException {
		this.setUpLogger();
//		this.cleanModels(WORKING_RESOURCES_PATH);
		this.copyModels(this.getTargetPath(), this.getWorkingResourcesPath());
	}
	
	@Disabled
	@Test
	public void modelLoadingTest() throws IOException {
		var res1Path = this.getWorkingTeammatesPath() + File.separator + FIRST_MODEL_NAME;
		
		var res = this.makeAndLoadResource(new ResourceSetImpl(), URI.createFileURI(res1Path));
		
		this.assertNoResourceErrors(res);
		this.assertNoResourceWarnings(res);
		this.validateEObjects(res);
		
		// Attempt to save it back to check for potential errors
		res.save(null);
		
		res.unload();
	}
	
	@Disabled("Takes a long time to complete")
	@Test
	public void modelComparingTest() throws IOException {
		var res1Path = this.getWorkingTeammatesPath() + File.separator + INITIAL_MODEL_NAME;
		var res2Path = this.getWorkingTeammatesPath() + File.separator + FIRST_MODEL_NAME;
		
		var res1 = this.makeAndLoadResource(new ResourceSetImpl(), URI.createFileURI(res1Path));
		var res1List = new ArrayList<Resource>();
		res1List.add(res1);
		
		var res2 = this.makeAndLoadResource(new ResourceSetImpl(), URI.createFileURI(res2Path));
		var res2List = new ArrayList<Resource>();
		res2List.add(res2);
		
		var cmp = this.compareModels(res2, res1, res2List, res1List, null);
		this.assertComparisonSuccessful(cmp, true, true);
		
		res1.unload();
		res2.unload();
	}
	
	@Disabled
	@Test
	public void changeReplayTest() throws IOException {
		var res1Path = this.getWorkingTeammatesPath() + File.separator + INITIAL_MODEL_NAME;
		var res2Path = this.getWorkingTeammatesPath() + File.separator + FIRST_MODEL_NAME;
		
		var res1 = this.makeAndLoadResource(new ResourceSetImpl(), URI.createFileURI(res1Path));
		var res1List = new ArrayList<Resource>();
		res1List.add(res1);
		
		var res2 = this.makeAndLoadResource(new ResourceSetImpl(), URI.createFileURI(res2Path));
		var res2List = new ArrayList<Resource>();
		res2List.add(res2);
		
		var changes = this.compareModels(res2, res1, res2List, res1List, null);
		
		var diffs = changes.getDifferences();

		var mergerRegistry = IMerger.RegistryImpl.createStandaloneInstance();
		var merger = new BatchMerger(mergerRegistry);

		var newRes1Path = this.getWorkingTeammatesPath() + File.separator + "new0.javaxmi";
		var newRes2Path = this.getWorkingTeammatesPath() + File.separator + "new1.javaxmi";
		res1.setURI(URI.createFileURI(newRes1Path));
		res2.setURI(URI.createFileURI(newRes2Path));
		
		merger.copyAllLeftToRight(diffs, new BasicMonitor());
		
		this.validateEObjects(res1);
		this.validateEObjects(res2);
		
		res1.save(null);
		res2.save(null);
	}
	
	protected String getWorkingTeammatesPath() {
		return this.workingTeammatesPath;
	}
}
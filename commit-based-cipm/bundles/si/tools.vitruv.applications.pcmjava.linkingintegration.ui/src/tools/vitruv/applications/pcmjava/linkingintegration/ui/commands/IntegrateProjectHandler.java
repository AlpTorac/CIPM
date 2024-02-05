package tools.vitruv.applications.pcmjava.linkingintegration.ui.commands;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

import tools.vitruv.applications.pcmjava.linkingintegration.PcmJavaCorrespondenceModelTransformation;
import tools.vitruv.domains.java.JavaDomainProvider;
import tools.vitruv.domains.pcm.PcmDomainProvider;
import tools.vitruv.framework.domains.VitruvDomain;
import tools.vitruv.framework.domains.repository.VitruvDomainRepository;
import tools.vitruv.framework.propagation.ChangePropagationSpecification;
import tools.vitruv.framework.propagation.ChangePropagationSpecificationProvider;
import tools.vitruv.framework.userinteraction.UserInteractionFactory;
import tools.vitruv.framework.vsum.helper.VsumFileSystemLayout;
import tools.vitruv.framework.vsum.internal.InternalVirtualModel;
import tools.vitruv.framework.vsum.internal.VirtualModelImpl;

public class IntegrateProjectHandler extends AbstractHandler {

	private Logger logger = Logger.getLogger(IntegrateProjectHandler.class.getName());

	public IntegrateProjectHandler() {
		super();
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		logger.setLevel(Level.ALL);

		final ISelection selection = HandlerUtil.getActiveMenuSelection(event);
		final IStructuredSelection structuredSelection = (IStructuredSelection) selection;

		final Object firstElement = structuredSelection.getFirstElement();
		final IJavaProject javaProject = (IJavaProject) firstElement;
		final IProject project = javaProject.getProject();

		integrateProject(project);

		return null;
	}

	@SuppressWarnings("restriction")
	public void integrateProject(final IProject project) {
		// IPath projectPath = project.getFullPath(); // workspace relative Path
        final IPath projectPath = project.getLocation(); // absolute path
        final IPath models = projectPath.append("model").addTrailingSeparator().append("internal_architecture_model");

        final IPath scdmPath = models.addFileExtension("sourcecodedecorator");
        final IPath pcmPath = models.addFileExtension("repository");
        
        List<IPath> srcPaths = new ArrayList<IPath>();
        IJavaProject javaProject = JavaCore.create(project);
        try {
        	IPackageFragmentRoot[] packageFragmentRoot = javaProject.getAllPackageFragmentRoots();
        	for (int i = 0; i < packageFragmentRoot.length; i++){
            	if (packageFragmentRoot[i].getElementType() == IJavaElement.PACKAGE_FRAGMENT_ROOT && !packageFragmentRoot[i].isArchive()) {
            		srcPaths.add(packageFragmentRoot[i].getPath());
            	}
        	}
        } catch (JavaModelException e) {
        	e.printStackTrace();
        }
        List<IPath> jamoppPaths = new ArrayList<>();
        for (IPath path : srcPaths) {
        	IPath projectRelative = path.removeFirstSegments(1);
        	IPath abs = project.getLocation().append(projectRelative);
        	jamoppPaths.add(abs);
        }
        
        final IPath projectBase = projectPath.removeLastSegments(1);

        final File f = scdmPath.toFile();
        if (!f.exists()) {
            throw new IllegalArgumentException("Run SoMoX first!");
        }

        final List<VitruvDomain> metamodels = new ArrayList<VitruvDomain>();
        metamodels.add(new PcmDomainProvider().getDomain());
        metamodels.add(new JavaDomainProvider().getDomain());
//        VirtualModelConfiguration config = new VirtualModelConfiguration();
        final List<ChangePropagationSpecification> innerConfigs = new ArrayList<ChangePropagationSpecification>();
        ChangePropagationSpecificationProvider config = new ChangePropagationSpecificationProvider() {
        	private final List<ChangePropagationSpecification> configs = innerConfigs;
        	
			@Override
			public Iterator<ChangePropagationSpecification> iterator() {
				return configs.iterator();
			}
			
			@Override
			public List<ChangePropagationSpecification> getChangePropagationSpecifications(VitruvDomain arg0) {
				ChangePropagationSpecification[] matches = configs.stream().filter((c) ->
				c.getTargetDomain().equals(arg0))
						.toArray(ChangePropagationSpecification[]::new);
				
				List<ChangePropagationSpecification> result = new ArrayList<ChangePropagationSpecification>();
				
				if (matches != null) {
					for (ChangePropagationSpecification spec : matches) {
						result.add(spec);
					}
				}
				
				return result;
			}
		};
		VitruvDomainRepository rep = new VitruvDomainRepository() {
			private final List<VitruvDomain> list = metamodels;
			@Override
			public Iterator<VitruvDomain> iterator() {
				return list.iterator();
			}
			
			@Override
			public boolean hasDomain(EObject arg0) {
				return list.stream().anyMatch((d) -> d.isInstanceOfDomainMetamodel(arg0));
			}
			
			@Override
			public VitruvDomain getDomain(String arg0) {
				final VitruvDomain[] result = new VitruvDomain[1];
				
				list.stream().filter((d) -> d.getName().equals(arg0))
				.findFirst().ifPresent((r) -> result[0] = r);
				
				return result[0];
			}
			
			@Override
			public VitruvDomain getDomain(EObject arg0) {
				final VitruvDomain[] result = new VitruvDomain[1];
				
				list.stream().filter((d) -> d.isInstanceOfDomainMetamodel(arg0))
				.findFirst().ifPresent((r) -> result[0] = r);
				
				return result[0];
			}
		};
		
//        for (VitruvDomain metamodel : metamodels) {
//        	innerConfigs.add((ChangePropagationSpecification) metamodel);
//        }
		
        // TODO HK Use other name
        File vsumFolder = new File(ResourcesPlugin.getWorkspace().getRoot().getLocation().toFile(), "vitruvius.meta");
        VsumFileSystemLayout layout = new VsumFileSystemLayout(vsumFolder.toPath());
        
        final InternalVirtualModel vsum = new VirtualModelImpl(layout, UserInteractionFactory.instance.createDialogUserInteractor(), rep, config);

        final PcmJavaCorrespondenceModelTransformation transformation = new PcmJavaCorrespondenceModelTransformation(
                scdmPath.toString(), pcmPath.toString(), jamoppPaths, vsum, projectBase);

        transformation.createCorrespondences();
	}

}

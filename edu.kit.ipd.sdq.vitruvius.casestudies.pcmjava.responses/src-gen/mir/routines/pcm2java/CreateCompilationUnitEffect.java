package mir.routines.pcm2java;

import com.google.common.collect.Iterables;
import edu.kit.ipd.sdq.vitruvius.casestudies.pcmjava.responses.pcm2java.Pcm2JavaHelper;
import edu.kit.ipd.sdq.vitruvius.dsls.response.runtime.AbstractEffectRealization;
import edu.kit.ipd.sdq.vitruvius.dsls.response.runtime.ResponseExecutionState;
import edu.kit.ipd.sdq.vitruvius.dsls.response.runtime.structure.CallHierarchyHaving;
import java.io.IOException;
import mir.routines.pcm2java.RoutinesFacade;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.xbase.lib.Extension;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.containers.impl.ContainersFactoryImpl;
import org.palladiosimulator.pcm.core.entity.NamedElement;

@SuppressWarnings("all")
public class CreateCompilationUnitEffect extends AbstractEffectRealization {
  public CreateCompilationUnitEffect(final ResponseExecutionState responseExecutionState, final CallHierarchyHaving calledBy) {
    super(responseExecutionState, calledBy);
  }
  
  private NamedElement sourceElementMappedToClass;
  
  private ConcreteClassifier classifier;
  
  private org.emftext.language.java.containers.Package containingPackage;
  
  private boolean isSourceElementMappedToClassSet;
  
  private boolean isClassifierSet;
  
  private boolean isContainingPackageSet;
  
  public void setSourceElementMappedToClass(final NamedElement sourceElementMappedToClass) {
    this.sourceElementMappedToClass = sourceElementMappedToClass;
    this.isSourceElementMappedToClassSet = true;
  }
  
  public void setClassifier(final ConcreteClassifier classifier) {
    this.classifier = classifier;
    this.isClassifierSet = true;
  }
  
  public void setContainingPackage(final org.emftext.language.java.containers.Package containingPackage) {
    this.containingPackage = containingPackage;
    this.isContainingPackageSet = true;
  }
  
  private EObject getElement0(final NamedElement sourceElementMappedToClass, final ConcreteClassifier classifier, final org.emftext.language.java.containers.Package containingPackage, final CompilationUnit compilationUnit) {
    return compilationUnit;
  }
  
  private EObject getElement1(final NamedElement sourceElementMappedToClass, final ConcreteClassifier classifier, final org.emftext.language.java.containers.Package containingPackage, final CompilationUnit compilationUnit) {
    return sourceElementMappedToClass;
  }
  
  public boolean allParametersSet() {
    return isSourceElementMappedToClassSet&&isClassifierSet&&isContainingPackageSet;
  }
  
  protected void executeEffect() throws IOException {
    getLogger().debug("Called routine CreateCompilationUnitEffect with input:");
    getLogger().debug("   NamedElement: " + this.sourceElementMappedToClass);
    getLogger().debug("   ConcreteClassifier: " + this.classifier);
    getLogger().debug("   Package: " + this.containingPackage);
    
    if (isAborted()) {
    	return;
    }
    CompilationUnit compilationUnit = ContainersFactoryImpl.eINSTANCE.createCompilationUnit();
    initializeCreateElementState(compilationUnit);
    
    addCorrespondenceBetween(getElement0(sourceElementMappedToClass, classifier, containingPackage, compilationUnit), getElement1(sourceElementMappedToClass, classifier, containingPackage, compilationUnit), "");
    preProcessElements();
    new mir.routines.pcm2java.CreateCompilationUnitEffect.EffectUserExecution(getExecutionState(), this).executeUserOperations(
    	sourceElementMappedToClass, classifier, containingPackage, compilationUnit);
    postProcessElements();
  }
  
  private static class EffectUserExecution extends AbstractEffectRealization.UserExecution {
    @Extension
    private RoutinesFacade effectFacade;
    
    public EffectUserExecution(final ResponseExecutionState responseExecutionState, final CallHierarchyHaving calledBy) {
      super(responseExecutionState);
      this.effectFacade = new RoutinesFacade(responseExecutionState, calledBy);
    }
    
    private void executeUserOperations(final NamedElement sourceElementMappedToClass, final ConcreteClassifier classifier, final org.emftext.language.java.containers.Package containingPackage, final CompilationUnit compilationUnit) {
      EList<String> _namespaces = compilationUnit.getNamespaces();
      EList<String> _namespaces_1 = containingPackage.getNamespaces();
      Iterables.<String>addAll(_namespaces, _namespaces_1);
      EList<String> _namespaces_2 = compilationUnit.getNamespaces();
      String _name = containingPackage.getName();
      _namespaces_2.add(_name);
      String _name_1 = classifier.getName();
      compilationUnit.setName(_name_1);
      EList<ConcreteClassifier> _classifiers = compilationUnit.getClassifiers();
      _classifiers.add(classifier);
      String _buildJavaFilePath = Pcm2JavaHelper.buildJavaFilePath(compilationUnit);
      this.persistProjectRelative(sourceElementMappedToClass, compilationUnit, _buildJavaFilePath);
    }
  }
}

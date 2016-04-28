package mir.routines.pcm2java;

import edu.kit.ipd.sdq.vitruvius.dsls.response.runtime.AbstractEffectRealization;
import edu.kit.ipd.sdq.vitruvius.dsls.response.runtime.ResponseExecutionState;
import edu.kit.ipd.sdq.vitruvius.dsls.response.runtime.structure.CallHierarchyHaving;
import java.io.IOException;
import mir.routines.pcm2java.RoutinesFacade;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.xbase.lib.Extension;
import org.palladiosimulator.pcm.repository.RepositoryComponent;

@SuppressWarnings("all")
public class CreateImplementationForComponentEffect extends AbstractEffectRealization {
  public CreateImplementationForComponentEffect(final ResponseExecutionState responseExecutionState, final CallHierarchyHaving calledBy) {
    super(responseExecutionState, calledBy);
  }
  
  private RepositoryComponent component;
  
  private boolean isComponentSet;
  
  public void setComponent(final RepositoryComponent component) {
    this.component = component;
    this.isComponentSet = true;
  }
  
  public boolean allParametersSet() {
    return isComponentSet;
  }
  
  private EObject getCorrepondenceSourceComponentPackage(final RepositoryComponent component) {
    return component;
  }
  
  protected void executeEffect() throws IOException {
    getLogger().debug("Called routine CreateImplementationForComponentEffect with input:");
    getLogger().debug("   RepositoryComponent: " + this.component);
    
    org.emftext.language.java.containers.Package componentPackage = initializeRetrieveElementState(
    	() -> getCorrepondenceSourceComponentPackage(component), // correspondence source supplier
    	(org.emftext.language.java.containers.Package _element) -> true, // correspondence precondition checker
    	() -> null, // tag supplier
    	org.emftext.language.java.containers.Package.class,
    	false, true, false);
    if (isAborted()) {
    	return;
    }
    
    preProcessElements();
    new mir.routines.pcm2java.CreateImplementationForComponentEffect.EffectUserExecution(getExecutionState(), this).executeUserOperations(
    	component, componentPackage);
    postProcessElements();
  }
  
  private static class EffectUserExecution extends AbstractEffectRealization.UserExecution {
    @Extension
    private RoutinesFacade effectFacade;
    
    public EffectUserExecution(final ResponseExecutionState responseExecutionState, final CallHierarchyHaving calledBy) {
      super(responseExecutionState);
      this.effectFacade = new RoutinesFacade(responseExecutionState, calledBy);
    }
    
    private void executeUserOperations(final RepositoryComponent component, final org.emftext.language.java.containers.Package componentPackage) {
      String _entityName = component.getEntityName();
      String _plus = (_entityName + "Impl");
      this.effectFacade.callCreateJavaClass(component, componentPackage, _plus);
    }
  }
}

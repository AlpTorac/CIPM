package cipm.consistency.fluentapi.gen;

import org.apache.commons.lang.StringUtils;

public final class ModelConstants {

	// TODO Extract TOP_NAME computation
	// TODO Use lower case class name in NAME members if possible

	/**
	 * %s: Metamodel name (small case)
	 * 
	 * TODO Change to "http://www.cipmfluentapi.com/%s" after refactoring
	 * 
	 */
	public static final IFluentAPIFillableTemplate ROOT_PACKAGE_URI = new FluentAPIFillableTemplate(
			"http://www.cipmfluentapi.com/java");
	/**
	 * %s: Metamodel name (small case)
	 * 
	 * TODO Change to "cipm.consistency.fluentapi.api.%s" after refactoring
	 */
	public static final IFluentAPIFillableTemplate ROOT_PACKAGE_NAME = new FluentAPIFillableTemplate(
			"cipm.consistency.fluentapi.api");

	/**
	 * %s: Metamodel name (small case)
	 */
	public static final IFluentAPIFillableTemplate INITIALISATIONS_PACKAGE_NAME = new FluentAPIFillableTemplate(
			"inits");

	/**
	 * %s: Metamodel name (small case)
	 */
	public static final IFluentAPIFillableTemplate INITIALISATIONS_PACKAGE_URI = new FluentAPIFillableTemplate(
			ROOT_PACKAGE_URI.get() + "/" + INITIALISATIONS_PACKAGE_NAME.get());

	public static final IFluentAPITemplate PLACEHOLDER = new FluentAPIFixTemplate("x");
	public static final IFluentAPITemplate GEN_MODEL_SOURCE_URL = new FluentAPIFixTemplate(
			"http://www.eclipse.org/emf/2002/GenModel");
	public static final IFluentAPITemplate INITIALISATION_NAME_SUFFIX = new FluentAPIFixTemplate("Initialisation");

	public static final IFluentAPITemplate EDATATYPE_WRAPPERS_PACKAGE_NAME = new FluentAPIFixTemplate(
			"placeholderTypes");

	public static final class RootAPI {
		/**
		 * %s: Metamodel name (capitalised)
		 * 
		 * TODO Change to "Fluent%sAPI"
		 */
		public static final IFluentAPIFillableTemplate CLASS_NAME = new FluentAPIFillableTemplate("FluentEObjectAPI");

		public static final IFluentAPITemplate CLASS_DOC = new FluentAPIFixTemplate("<p>"
				+ ModelConstants.RootAPI.CLASS_NAME.get()
				+ " is at the center of the fluent API and enables creation of EObject sub-types within the EMF-based metamodel MM this API targets. To this end, this class offers various methods that lead to underlying "
				+ ModelConstants.INITIALISATION_NAME_SUFFIX.get()
				+ " classes, each being responsible for a concrete element from MM. For more information on what individual EObject sub-types and their features represent, refer to MM's documentation.");

		public static final class Continue {
			public static final IFluentAPITemplate NAME_PREFIX = new FluentAPIFixTemplate("continue");
			/**
			 * %s: Class name (capitalised)
			 */
			public static final IFluentAPIFillableTemplate NAME = new FluentAPIFillableTemplate(
					NAME_PREFIX.get() + "%s");

			public static final IFluentAPITemplate TOP_NAME = new FluentAPIFixTemplate(
					NAME_PREFIX.get() + StringUtils.capitalize(ModelConstants.PLACEHOLDER.get()));
		}

		public static final class ContinueMarked {
			public static final IFluentAPITemplate NAME_PREFIX = new FluentAPIFixTemplate("continueMarked");
			/**
			 * %s: Class name (capitalised)
			 */
			public static final IFluentAPIFillableTemplate NAME = new FluentAPIFillableTemplate(
					NAME_PREFIX.get() + "%s");

			public static final IFluentAPITemplate TOP_NAME = new FluentAPIFixTemplate(
					NAME_PREFIX.get() + StringUtils.capitalize(ModelConstants.PLACEHOLDER.get()));
		}

		public static final class CreateNew {
			public static final IFluentAPITemplate TYPE_PARAMETER_NAME = new FluentAPIFixTemplate("T");
			public static final IFluentAPITemplate ECLASS_PARAMETER_NAME = new FluentAPIFixTemplate("eObjCls");
			public static final IFluentAPITemplate NAME_PREFIX = new FluentAPIFixTemplate("createNew");
			/**
			 * %s: Class name (capitalised)
			 */
			public static final IFluentAPIFillableTemplate NAME = new FluentAPIFillableTemplate(
					NAME_PREFIX.get() + "%s");
			public static final IFluentAPITemplate TOP_NAME = new FluentAPIFixTemplate(
					NAME_PREFIX.get() + StringUtils.capitalize(ModelConstants.PLACEHOLDER.get()));
		}

		public static final class DropInitialisation {
			public static final IFluentAPITemplate INITIALISATION_PARAMETER_NAME = new FluentAPIFixTemplate(
					"initToDrop");
			public static final IFluentAPITemplate NAME = new FluentAPIFixTemplate("dropInitialisation");
		}

		public static final class GetAllSupportedEClasses {
			public static final IFluentAPITemplate NAME = new FluentAPIFixTemplate("getAllSupportedClasses");
		}

		public static final class GetInitialisationFor {
			public static final IFluentAPITemplate NAME = new FluentAPIFixTemplate(
					"getInitialisationFor" + StringUtils.capitalize(ModelConstants.PLACEHOLDER.get()));

			public static final IFluentAPITemplate ECLASS_PARAMETER_NAME = new FluentAPIFixTemplate("eClsToInit");
			public static final IFluentAPITemplate CLASS_PARAMETER_NAME = new FluentAPIFixTemplate("clsToInit");
			public static final IFluentAPITemplate EOBJECT_PARAMETER_NAME = new FluentAPIFixTemplate("eobjToInit");
		}

		public static final class GetMarked {
			public static final IFluentAPITemplate NAME_PREFIX = new FluentAPIFixTemplate("getMarked");
			/**
			 * %s: Class name (capitalised)
			 */
			public static final IFluentAPIFillableTemplate NAME = new FluentAPIFillableTemplate(
					NAME_PREFIX.get() + "%s");
			public static final IFluentAPITemplate TOP_NAME = new FluentAPIFixTemplate(NAME_PREFIX.get());
		}

		public static final class Unmark {
			public static final IFluentAPITemplate NAME = new FluentAPIFixTemplate("unmark");

			public static final IFluentAPITemplate SUMMARY = new FluentAPIFixTemplate(
					"Removes the given " + FluentAPIGeneralParameterGenerator.getFluentAPIMarkKeyParameterName()
							+ "'s marking, does not modify the (formerly) marked object.");
			public static final IFluentAPITemplate DOC = new FluentAPIFixTemplate(FluentAPIDocumentationUtil
					.appendSummaryToStart(SUMMARY.get()) + "Removes any associations between the given "
					+ FluentAPIGeneralParameterGenerator.getFluentAPIMarkKeyParameterName()
					+ " and its corresponding EObject obj. Doing so unmarks obj, meaning that "
					+ FluentAPIGeneralParameterGenerator.getFluentAPIMarkKeyParameterName()
					+ " can no longer be used to retrieve obj. Does nothing, if this API instance did not mark obj with "
					+ FluentAPIGeneralParameterGenerator.getFluentAPIMarkKeyParameterName() + ".");
		}

		public static final class Modify {
			public static final IFluentAPITemplate NAME_PREFIX = new FluentAPIFixTemplate("modify");
			/**
			 * %s: Class name (capitalised)
			 */
			public static final IFluentAPIFillableTemplate NAME = new FluentAPIFillableTemplate(
					NAME_PREFIX.get() + "%s");
			public static final IFluentAPITemplate TOP_NAME = new FluentAPIFixTemplate(
					NAME_PREFIX.get() + StringUtils.capitalize(ModelConstants.PLACEHOLDER.get()));
		}

		public static final class ModifyMarked {
			public static final IFluentAPITemplate NAME_PREFIX = new FluentAPIFixTemplate("modifyMarked");
			/**
			 * %s: Class name (capitalised)
			 */
			public static final IFluentAPIFillableTemplate NAME = new FluentAPIFillableTemplate(
					NAME_PREFIX.get() + "%s");
			public static final IFluentAPITemplate TOP_NAME = new FluentAPIFixTemplate(
					NAME_PREFIX.get() + StringUtils.capitalize(ModelConstants.PLACEHOLDER.get()));
		}

		public static final class New {
			public static final IFluentAPITemplate NAME_PREFIX = new FluentAPIFixTemplate("new");
			/**
			 * %s: Class name (capitalised)
			 */
			public static final IFluentAPIFillableTemplate NAME = new FluentAPIFillableTemplate(
					NAME_PREFIX.get() + "%s");
			public static final IFluentAPITemplate TOP_NAME = new FluentAPIFixTemplate(
					NAME_PREFIX.get() + StringUtils.capitalize(ModelConstants.PLACEHOLDER.get()));

			public static final IFluentAPITemplate ECLASS_PARAMETER_NAME = new FluentAPIFixTemplate("eObjEClass");
			public static final IFluentAPITemplate CLASS_PARAMETER_NAME = new FluentAPIFixTemplate("eObjCls");
			public static final IFluentAPITemplate FEATURE_VALUE_PARAMETER_NAME = new FluentAPIFixTemplate("featVal");
		}

		public static final class OnceExists {
			public static final IFluentAPITemplate NAME = new FluentAPIFixTemplate("onceExists");
			public static final IFluentAPITemplate SUMMARY = new FluentAPIFixTemplate(
					"Suspends certain model construction steps till certain markKey(s) exist.");
			public static final IFluentAPITemplate DOC = new FluentAPIFixTemplate(
					"Allows specifying model construction steps as a Runnable instance R, which this API will execute after using the given markKey(s) to mark objects. This method enables preserving the flow of model construction by enabling the specification of construction steps on objects that may not yet exist. The main purpose of this method is to facilitate model constructions, where dependencies between model elements either forcefully require bottom-up approaches or require mixing the construction of several model elements.");
		}

		public static final class WithFeat {
			public static final IFluentAPITemplate NAME = new FluentAPIFixTemplate(
					ModelConstants.PLACEHOLDER.get() + "WithFeat");
			public static final IFluentAPITemplate SUMMARY = new FluentAPIFixTemplate(
					"Sets the given value of a certain single-valued EStructuralFeature for a certain EObject.");
		}

		public static final class WithoutFeat {
			public static final IFluentAPITemplate NAME = new FluentAPIFixTemplate(
					ModelConstants.PLACEHOLDER.get() + "WithoutFeat");
			public static final IFluentAPITemplate SUMMARY = new FluentAPIFixTemplate(
					"Unsets the value of a certain single-valued EStructuralFeature for a certain EObject.");
		}

		public static final class WithAddedFeat {
			public static final IFluentAPITemplate NAME = new FluentAPIFixTemplate(
					ModelConstants.PLACEHOLDER.get() + "WithAddedFeat");
			public static final IFluentAPITemplate SUMMARY = new FluentAPIFixTemplate(
					"Adds the given value(s) to a certain many-valued EStructuralFeature for a certain EObject.");
		}

		public static final class WithRemovedFeat {
			public static final IFluentAPITemplate NAME = new FluentAPIFixTemplate(
					ModelConstants.PLACEHOLDER.get() + "WithRemovedFeat");
			public static final IFluentAPITemplate SUMMARY = new FluentAPIFixTemplate(
					"Removes the given value(s) from a certain many-valued EStructuralFeature for a certain EObject.");
		}

		public static final class CleanFeat {
			public static final IFluentAPITemplate NAME = new FluentAPIFixTemplate(
					ModelConstants.PLACEHOLDER.get() + "CleanFeat");
			public static final IFluentAPITemplate SUMMARY = new FluentAPIFixTemplate(
					"Removes all values of a certain many-valued EStructuralFeature for a certain EObject.");
		}

		public static final class Mark {
			public static final IFluentAPITemplate NAME = new FluentAPIFixTemplate("mark");

			public static final IFluentAPITemplate SUMMARY = new FluentAPIFixTemplate(
					"Marks the object with " + FluentAPIGeneralParameterGenerator.getFluentAPIMarkKeyParameterName()
							+ ", does not modify the object.");
			public static final IFluentAPITemplate DOC = new FluentAPIFixTemplate(
					FluentAPIDocumentationUtil.appendSummaryToStart(SUMMARY.get()) + "Associates the given object with "
							+ FluentAPIGeneralParameterGenerator.getFluentAPIMarkKeyParameterName()
							+ ". Doing so marks the given object, meaning that using "
							+ FluentAPIGeneralParameterGenerator.getFluentAPIMarkKeyParameterName()
							+ " in mark-related operations will result in retrieving the given object.");
		}

		public static final class GetOngoingInitialisations {
			public static final IFluentAPITemplate NAME = new FluentAPIFixTemplate("getOngoingInits");
		}

		public static final class ClearAllOngoingInitialisations {
			public static final IFluentAPITemplate NAME = new FluentAPIFixTemplate("clearAllOngoingInits");
		}
	}

	public static final class SuperInitialisation {
		/**
		 * %s: Metamodel name (capitalised)
		 * 
		 * TODO Change to "Fluent%sAPISuperInitialisation"
		 */
		public static final IFluentAPIFillableTemplate CLASS_NAME = new FluentAPIFillableTemplate(
				"FluentAPISuperInitialisation");

		public static final IFluentAPIFillableTemplate CLASS_DOC = new FluentAPIFillableTemplate("The top-most "
				+ ModelConstants.INITIALISATION_NAME_SUFFIX.get() + " class, which all concrete "
				+ ModelConstants.INITIALISATION_NAME_SUFFIX.get()
				+ " classes extend. Contains various methods that facilitate the programmatic construction of model object instances."
				+ FluentAPIDocumentationUtil.getClassMethodOverviewIntroTemplate());

		public static final class RootAPIRef {
			public static final IFluentAPIFeatureTemplate NAME = new FluentAPIFeatureTemplate("rootAPI");
		}

		public static final class CurrentElementRef {
			public static final IFluentAPIFeatureTemplate NAME = new FluentAPIFeatureTemplate("currentElement");
		}

		public static final class ToAPI {
			public static final IFluentAPITemplate NAME = new FluentAPIFixTemplate("toAPI");

			public static final IFluentAPITemplate SUMMARY = new FluentAPIFixTemplate("Swaps to the API instance");
			public static final IFluentAPITemplate DOC = new FluentAPIFixTemplate(
					FluentAPIDocumentationUtil.appendSummaryToStart(SUMMARY.get())
							+ "Swaps from this to the API, which created it. This method is currently the same as this"
							+ ModelConstants.SuperInitialisation.RootAPIRef.NAME.getterCall()
							+ ". Its purpose is to isolate the use of this"
							+ ModelConstants.SuperInitialisation.RootAPIRef.NAME.getterCall()
							+ ", which is a getter method that is generated by EMF.");
		}

		public static final class Drop {
			public static final IFluentAPITemplate NAME = new FluentAPIFixTemplate("drop");
		}

		public static final class GetInitialisedEClass {
			public static final IFluentAPITemplate NAME = new FluentAPIFixTemplate("getInitialisedEClass");

			public static final IFluentAPITemplate SUMMARY = new FluentAPIFixTemplate("Returns the targeted EClass.");
			public static final IFluentAPITemplate DOC = new FluentAPIFixTemplate(
					FluentAPIDocumentationUtil.appendSummaryToStart(SUMMARY.get())
							+ "Returns the EClass, which this targets.");
		}

		public static final class NewElement {
			public static final IFluentAPITemplate NAME = new FluentAPIFixTemplate("newElement");
			public static final IFluentAPITemplate SUMMARY = new FluentAPIFixTemplate(
					"Creates a minimal instance of the targeted type within this "
							+ ModelConstants.INITIALISATION_NAME_SUFFIX.get() + " instance.");
			public static final IFluentAPITemplate DOC = new FluentAPIFixTemplate(FluentAPIDocumentationUtil
					.appendSummaryToStart(SUMMARY.get())
					+ "Creates a minimal EObject instance, without modifying any of its features, and sets it as the current element (i.e. return value of this"
					+ ModelConstants.SuperInitialisation.CurrentElementRef.NAME.getterCall() + ") in concrete "
					+ ModelConstants.INITIALISATION_NAME_SUFFIX.get() + " classes. Does nothing in this class.");
		}

		public static final class MarkCurrent {
			public static final IFluentAPITemplate NAME = new FluentAPIFixTemplate("markCurrent");
		}

		public static final class UnmarkCurrent {
			public static final IFluentAPITemplate NAME = new FluentAPIFixTemplate("unmarkCurrent");
		}

		public static final class Reset {
			public static final IFluentAPITemplate NAME = new FluentAPIFixTemplate("reset");

			public static final IFluentAPITemplate SUMMARY = new FluentAPIFixTemplate(
					"Removes the current object under construction from this.");
			public static final IFluentAPITemplate DOC = new FluentAPIFixTemplate(
					FluentAPIDocumentationUtil.appendSummaryToStart(SUMMARY.get()) + "Resets this, which discards this"
							+ ModelConstants.SuperInitialisation.CurrentElementRef.NAME.getterCall()
							+ ". Does not drop this from this" + ModelConstants.SuperInitialisation.ToAPI.NAME.call()
							+ ", meaning that it will still be accessible from this"
							+ ModelConstants.SuperInitialisation.ToAPI.NAME.call()
							+ ". This can then be re-used by calling this"
							+ ModelConstants.SuperInitialisation.NewElement.NAME.call() + ".");
		}

		public static final class OnceExists {
			public static final IFluentAPITemplate NAME = ModelConstants.RootAPI.OnceExists.NAME;
			public static final IFluentAPITemplate SUMMARY = ModelConstants.RootAPI.OnceExists.SUMMARY;
			public static final IFluentAPITemplate DOC = ModelConstants.RootAPI.OnceExists.DOC;
		}

		public static final class CreateNow {
			public static final IFluentAPITemplate TYPE_PARAMETER_NAME = new FluentAPIFixTemplate("T");
			public static final IFluentAPITemplate CLASS_PARAMETER_NAME = new FluentAPIFixTemplate("returnTypeCls");
			public static final IFluentAPITemplate NAME = new FluentAPIFixTemplate("createNow");

			public static final IFluentAPITemplate SUMMARY = new FluentAPIFixTemplate(
					"Finalises and returns the object under construction.");
			public static final IFluentAPITemplate DOC = new FluentAPIFixTemplate(
					FluentAPIDocumentationUtil.appendSummaryToStart(SUMMARY.get())
							+ "Finalises the construction of this"
							+ ModelConstants.SuperInitialisation.CurrentElementRef.NAME.getterCall()
							+ " and returns it. Drops this from this"
							+ ModelConstants.SuperInitialisation.ToAPI.NAME.call()
							+ ", meaning that this will no longer be accessible from this"
							+ ModelConstants.SuperInitialisation.ToAPI.NAME.call() + ".");

			public static final IFluentAPITemplate CLASS_PARAMETER_DOC = new FluentAPIFixTemplate(DOC.get()
					+ FluentAPIDocumentationUtil.getDocParagraphSeparator()
					+ "EMF-based metamodels consider interfaces, which allow diamond structures in the type hierarchy of their implementors. To spare type casting in model construction, this method can be given a class parameter, to which the returned value will be cast.");
		}
	}

	public static final class Initialiation {
		/**
		 * %s: Class name (capitalised)
		 */
		public static final IFluentAPIFillableTemplate CLASS_NAME = new FluentAPIFillableTemplate(
				"%s" + ModelConstants.INITIALISATION_NAME_SUFFIX.get());

		/**
		 * %s: Initialised class name
		 * 
		 * %s: Metamodel name
		 * 
		 * %s: Initialised class name
		 * 
		 * %s: Serialised method names and summaries
		 * 
		 */
		public static final IFluentAPIFillableTemplate initClassDocTemplate = new FluentAPIFillableTemplate("An "
				+ ModelConstants.INITIALISATION_NAME_SUFFIX.get()
				+ " class that targets the type '%s' within the '%s' metamodel. Contains various methods that facilitate the programmatic construction of '%s' instances."
				+ FluentAPIDocumentationUtil.getClassMethodOverviewIntroTemplate());

		public static final class NewElement {
			public static final IFluentAPITemplate NAME = ModelConstants.SuperInitialisation.NewElement.NAME;
			public static final IFluentAPITemplate SUMMARY = ModelConstants.SuperInitialisation.NewElement.SUMMARY;

			public static final IFluentAPIFillableTemplate DOC = new FluentAPIFillableTemplate(
					FluentAPIDocumentationUtil
							.appendSummaryToStart(ModelConstants.SuperInitialisation.NewElement.SUMMARY.get())
							+ "Creates a minimal %s instance, without modifying any of its features, and sets it as the current element (i.e. return value of this"
							+ ModelConstants.SuperInitialisation.CurrentElementRef.NAME.getterCall() + ")."
							+ FluentAPIDocumentationUtil.appendDoNotUseFromOutsideDocNoteAtEnd());
		}

		public static final class With {
			public static final IFluentAPITemplate PARAMETER_NAME = new FluentAPIFixTemplate("newFeatVal");
			public static final IFluentAPIFillableTemplate NAME = new FluentAPIFillableTemplate("with%s");

			/**
			 * %s: Feature name
			 */
			public static final IFluentAPIFillableTemplate DOC = new FluentAPIFillableTemplate(
					FluentAPIDocumentationUtil.appendSummaryToStart(ModelConstants.RootAPI.WithFeat.SUMMARY.get())
							+ "Sets the value of the feature %s in this"
							+ ModelConstants.SuperInitialisation.CurrentElementRef.NAME.getterCall()
							+ " to the given value.");

			/**
			 * %s: Feature name
			 */
			public static final IFluentAPIFillableTemplate PARAMETER_DOC = new FluentAPIFillableTemplate(
					"The new value of the feature %s, which will replace its current value in the initialised object this"
							+ ModelConstants.SuperInitialisation.CurrentElementRef.NAME.getterCall());
		}

		public static final class Without {
			public static final IFluentAPIFillableTemplate NAME = new FluentAPIFillableTemplate("without%s");

			// %s: Feature name
			public static final IFluentAPIFillableTemplate DOC = new FluentAPIFillableTemplate(
					FluentAPIDocumentationUtil.appendSummaryToStart(ModelConstants.RootAPI.WithoutFeat.SUMMARY.get())
							+ "Unsets the value of the feature %s in this"
							+ ModelConstants.SuperInitialisation.CurrentElementRef.NAME.getterCall()
							+ ", which sets its value to null.");
		}

		public static final class WithAdded {
			public static final IFluentAPITemplate PARAMETER_NAME = new FluentAPIFixTemplate("featValToAdd");
			public static final IFluentAPIFillableTemplate NAME = new FluentAPIFillableTemplate("withAdded%s");

			// %s: Feature name
			public static final IFluentAPIFillableTemplate DOC = new FluentAPIFillableTemplate(
					FluentAPIDocumentationUtil.appendSummaryToStart(ModelConstants.RootAPI.WithAddedFeat.SUMMARY.get())
							+ "Adds the given values to the current values of the feature %s in this"
							+ ModelConstants.SuperInitialisation.CurrentElementRef.NAME.getterCall() + ".");

			// %s: Feature name
			public static final IFluentAPIFillableTemplate PARAMETER_DOC = new FluentAPIFillableTemplate(
					"Value(s) for the feature %s, which will be added to its current values in the initialised object this"
							+ ModelConstants.SuperInitialisation.CurrentElementRef.NAME.getterCall() + ".");
		}

		public static final class WithRemoved {
			public static final IFluentAPITemplate PARAMETER_NAME = new FluentAPIFixTemplate("featValToRemove");
			public static final IFluentAPIFillableTemplate NAME = new FluentAPIFillableTemplate("withRemoved%s");

			// %s: Feature name
			public static final IFluentAPIFillableTemplate DOC = new FluentAPIFillableTemplate(
					FluentAPIDocumentationUtil
							.appendSummaryToStart(ModelConstants.RootAPI.WithRemovedFeat.SUMMARY.get())
							+ "Removes the given values from the current values of the feature %s in this"
							+ ModelConstants.SuperInitialisation.CurrentElementRef.NAME.getterCall() + ".");

			// %s: Feature name
			public static final IFluentAPIFillableTemplate PARAMETER_DOC = new FluentAPIFillableTemplate(
					"Value(s) for the feature %s, which will be removed from its current values in the initialised object this"
							+ ModelConstants.SuperInitialisation.CurrentElementRef.NAME.getterCall() + ".");
		}

		public static final class Clean {
			public static final IFluentAPIFillableTemplate NAME = new FluentAPIFillableTemplate("clean%s");

			// %s: Feature name
			public static final IFluentAPIFillableTemplate DOC = new FluentAPIFillableTemplate(
					FluentAPIDocumentationUtil.appendSummaryToStart(ModelConstants.RootAPI.CleanFeat.SUMMARY.get())
							+ "Clears all values of the (many-valued) feature %s in this"
							+ ModelConstants.SuperInitialisation.CurrentElementRef.NAME.getterCall()
							+ " to the given value.");
		}
	}
}

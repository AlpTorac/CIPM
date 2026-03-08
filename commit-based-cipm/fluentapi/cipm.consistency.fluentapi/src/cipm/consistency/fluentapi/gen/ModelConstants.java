package cipm.consistency.fluentapi.gen;

import org.apache.commons.lang.StringUtils;

public class ModelConstants {
	/**
	 * TODO %s: Metamodel name (small case)
	 * 
	 * TODO Change to "http://www.cipmfluentapi.com/%s" after refactoring
	 * 
	 */
	public static final IFluentAPIFillableTemplate ROOT_PACKAGE_URI = new FluentAPIFillableTemplate(
			"http://www.cipmfluentapi.com/java");
	/**
	 * TODO %s: Metamodel name (small case)
	 * 
	 * TODO Change to "cipm.consistency.fluentapi.api.%s" after refactoring
	 */
	public static final IFluentAPIFillableTemplate ROOT_PACKAGE_NAME = new FluentAPIFillableTemplate(
			"cipm.consistency.fluentapi.api");

	/**
	 * TODO %s: Metamodel name (small case)
	 */
	public static final IFluentAPIFillableTemplate INITIALISATIONS_PACKAGE_NAME = new FluentAPIFillableTemplate(
			"inits");

	/**
	 * TODO %s: Metamodel name (small case)
	 */
	public static final IFluentAPIFillableTemplate INITIALISATIONS_PACKAGE_URI = new FluentAPIFillableTemplate(
			ROOT_PACKAGE_URI.get() + "/" + INITIALISATIONS_PACKAGE_NAME.get());

	public static final IFluentAPITemplate PLACEHOLDER = new FluentAPIFixTemplate("x");
	public static final IFluentAPITemplate GEN_MODEL_SOURCE_URL = new FluentAPIFixTemplate(
			"http://www.eclipse.org/emf/2002/GenModel");
	public static final IFluentAPITemplate INITIALISATION_NAME_SUFFIX = new FluentAPIFixTemplate("Initialisation");

	public static final IFluentAPITemplate EDATATYPE_WRAPPERS_PACKAGE_NAME = new FluentAPIFixTemplate(
			"placeholderTypes");

	public static class GeneralParameters {
		public static final IFluentAPITemplate USED_EOBJECT_PARAMETER_NAME = new FluentAPIFixTemplate("eobj");
		public static final IFluentAPITemplate USED_EOBJECT_PARAMETER_NAME_DOC = new FluentAPIFixTemplate(
				"The EObject that this method will use");

		public static final IFluentAPITemplate MODIFIED_FEATURE_PARAMETER_NAME = new FluentAPIFixTemplate(
				"featToModify");
		public static final IFluentAPITemplate MODIFIED_FEATURE_PARAMETER_NAME_DOC = new FluentAPIFixTemplate(
				"The feature, whose value in " + MODIFIED_FEATURE_PARAMETER_NAME + " will be modified");

		public static final IFluentAPITemplate FEATURE_VALUE_PARAMETER_NAME = new FluentAPIFixTemplate("featVal");
		public static final IFluentAPITemplate FEATURE_VALUE_PARAMETER_NAME_DOC = new FluentAPIFixTemplate(
				"The value of the feature, which will be used to modify the given feature in certain ways, denoted by the method name");

		public static final IFluentAPITemplate MARK_VALUE_PARAMETER_NAME = new FluentAPIFixTemplate("markVal");
		public static final IFluentAPITemplate MARK_VALUE_PARAMETER_NAME_DOC = new FluentAPIFixTemplate(
				"The object that is / will be marked.");

		public static final IFluentAPITemplate MARK_KEY_PARAMETER_NAME = new FluentAPIFixTemplate("markKey");
		public static final IFluentAPITemplate MARK_KEY_PARAMETER_NAME_DOC = new FluentAPIFixTemplate(
				"The object instance (key), whose memory address is serving / will serve as a key in mark-related operations. Note that the contents of the key are fully irrelevant here, only its memory address matters.");

		public static final Class<?> ONCE_EXISTS_TASK_CLASS = Runnable.class;
		public static final IFluentAPITemplate ONCE_EXISTS_TASK_PARAMETER_NAME = new FluentAPIFixTemplate(
				"toDoOnceExists");
		public static final IFluentAPITemplate ONCE_EXISTS_TASK_PARAMETER_NAME_DOC = new FluentAPIFixTemplate(
				"The model construction task, which will be executed upon object(s) getting marked with certain "
						+ ONCE_EXISTS_TASK_PARAMETER_NAME + "(s).");
	}

	private static String getMethodName(Class<?> cls) {
		return StringUtils.uncapitalize(cls.getSimpleName());
	}

	private static String getFeatureName(Class<?> cls) {
		return StringUtils.uncapitalize(cls.getSimpleName());
	}

	private static String getTopMethodName(Class<?> cls) {
		return getMethodName(cls) + StringUtils.capitalize(ModelConstants.PLACEHOLDER.get());
	}

	public static class FluentAPI {
		/**
		 * TODO %s: Metamodel name (capitalised)
		 * 
		 * TODO Change to "Fluent%sAPI"
		 */
		public static final IFluentAPIFillableTemplate CLASS_NAME = new FluentAPIFillableTemplate("FluentEObjectAPI");

		public static final IFluentAPITemplate CLASS_DOC = new FluentAPIFixTemplate("<p>"
				+ ModelConstants.FluentAPI.CLASS_NAME.get()
				+ " is at the center of the fluent API and enables creation of EObject sub-types within the EMF-based metamodel MM this API targets. To this end, this class offers various methods that lead to underlying "
				+ ModelConstants.INITIALISATION_NAME_SUFFIX.get()
				+ " classes, each being responsible for a concrete element from MM. For more information on what individual EObject sub-types and their features represent, refer to MM's documentation.");

		public static class Continue {
			public static final IFluentAPITemplate NAME_PREFIX = new FluentAPIFixTemplate(
					getMethodName(Continue.class));
			/**
			 * %s: Class name (capitalised)
			 */
			public static final IFluentAPIFillableTemplate NAME = new FluentAPIFillableTemplate(
					NAME_PREFIX.get() + "%s");

			public static final IFluentAPITemplate TOP_NAME = new FluentAPIFixTemplate(
					getTopMethodName(Continue.class));
		}

		public static class ContinueMarked {
			public static final IFluentAPITemplate NAME_PREFIX = new FluentAPIFixTemplate(
					getMethodName(ContinueMarked.class));
			/**
			 * %s: Class name (capitalised)
			 */
			public static final IFluentAPIFillableTemplate NAME = new FluentAPIFillableTemplate(
					NAME_PREFIX.get() + "%s");

			public static final IFluentAPITemplate TOP_NAME = new FluentAPIFixTemplate(
					getTopMethodName(ContinueMarked.class));
		}

		public static class CreateNew {
			public static final IFluentAPITemplate TYPE_PARAMETER_NAME = new FluentAPIFixTemplate("T");
			public static final IFluentAPITemplate ECLASS_PARAMETER_NAME = new FluentAPIFixTemplate("eObjCls");
			public static final IFluentAPITemplate NAME_PREFIX = new FluentAPIFixTemplate(
					getMethodName(CreateNew.class));
			/**
			 * %s: Class name (capitalised)
			 */
			public static final IFluentAPIFillableTemplate NAME = new FluentAPIFillableTemplate(
					NAME_PREFIX.get() + "%s");
			public static final IFluentAPITemplate TOP_NAME = new FluentAPIFixTemplate(
					getTopMethodName(CreateNew.class));
		}

		public static class DropInitialisation {
			public static final IFluentAPITemplate INITIALISATION_PARAMETER_NAME = new FluentAPIFixTemplate(
					"initToDrop");
			public static final IFluentAPITemplate NAME = new FluentAPIFixTemplate(
					getMethodName(DropInitialisation.class));
		}

		public static class GetAllSupportedEClasses {
			public static final IFluentAPITemplate NAME = new FluentAPIFixTemplate(
					getMethodName(GetAllSupportedEClasses.class));
		}

		public static class GetInitialisationFor {
			public static final IFluentAPITemplate NAME = new FluentAPIFixTemplate(
					getMethodName(GetInitialisationFor.class)
							+ StringUtils.capitalize(ModelConstants.PLACEHOLDER.get()));

			public static final IFluentAPITemplate ECLASS_PARAMETER_NAME = new FluentAPIFixTemplate("eClsToInit");
			public static final IFluentAPITemplate CLASS_PARAMETER_NAME = new FluentAPIFixTemplate("clsToInit");
			public static final IFluentAPITemplate EOBJECT_PARAMETER_NAME = new FluentAPIFixTemplate("eobjToInit");
		}

		public static class GetMarked {
			public static final IFluentAPITemplate NAME_PREFIX = new FluentAPIFixTemplate(
					getMethodName(GetMarked.class));
			/**
			 * %s: Class name (capitalised)
			 */
			public static final IFluentAPIFillableTemplate NAME = new FluentAPIFillableTemplate(
					NAME_PREFIX.get() + "%s");

			public static final IFluentAPITemplate TOP_NAME = new FluentAPIFixTemplate(
					getTopMethodName(GetMarked.class));
		}

		public static class Unmark {
			public static final IFluentAPITemplate NAME = new FluentAPIFixTemplate(getMethodName(Unmark.class));

			public static final IFluentAPITemplate SUMMARY = new FluentAPIFixTemplate(
					"Removes the given " + ModelConstants.GeneralParameters.MARK_KEY_PARAMETER_NAME.get()
							+ "'s marking, does not modify the (formerly) marked object.");
			public static final IFluentAPITemplate DOC = new FluentAPIFixTemplate(FluentAPIDocumentationUtil
					.appendSummaryToStart(SUMMARY.get()) + "Removes any associations between the given "
					+ ModelConstants.GeneralParameters.MARK_KEY_PARAMETER_NAME.get()
					+ " and its corresponding EObject obj. Doing so unmarks obj, meaning that "
					+ ModelConstants.GeneralParameters.MARK_KEY_PARAMETER_NAME.get()
					+ " can no longer be used to retrieve obj. Does nothing, if this API instance did not mark obj with "
					+ ModelConstants.GeneralParameters.MARK_KEY_PARAMETER_NAME.get() + ".");
		}

		public static class Modify {
			public static final IFluentAPITemplate NAME_PREFIX = new FluentAPIFixTemplate(getMethodName(Modify.class));
			/**
			 * %s: Class name (capitalised)
			 */
			public static final IFluentAPIFillableTemplate NAME = new FluentAPIFillableTemplate(
					NAME_PREFIX.get() + "%s");
			public static final IFluentAPITemplate TOP_NAME = new FluentAPIFixTemplate(getTopMethodName(Modify.class));
		}

		public static class ModifyMarked {
			public static final IFluentAPITemplate NAME_PREFIX = new FluentAPIFixTemplate(
					getMethodName(ModifyMarked.class));
			/**
			 * %s: Class name (capitalised)
			 */
			public static final IFluentAPIFillableTemplate NAME = new FluentAPIFillableTemplate(
					NAME_PREFIX.get() + "%s");
			public static final IFluentAPITemplate TOP_NAME = new FluentAPIFixTemplate(
					getTopMethodName(ModifyMarked.class));
		}

		public static class New {
			public static final IFluentAPITemplate NAME_PREFIX = new FluentAPIFixTemplate(getMethodName(New.class));
			/**
			 * %s: Class name (capitalised)
			 */
			public static final IFluentAPIFillableTemplate NAME = new FluentAPIFillableTemplate(
					NAME_PREFIX.get() + "%s");
			public static final IFluentAPITemplate TOP_NAME = new FluentAPIFixTemplate(getTopMethodName(New.class));

			public static final IFluentAPITemplate ECLASS_PARAMETER_NAME = new FluentAPIFixTemplate("eObjEClass");
			public static final IFluentAPITemplate CLASS_PARAMETER_NAME = new FluentAPIFixTemplate("eObjCls");
			public static final IFluentAPITemplate FEATURE_VALUE_PARAMETER_NAME = new FluentAPIFixTemplate("featVal");
		}

		public static class OnceExists {
			public static final IFluentAPITemplate NAME = new FluentAPIFixTemplate(getMethodName(OnceExists.class));
			public static final IFluentAPITemplate SUMMARY = new FluentAPIFixTemplate(
					"Suspends certain model construction steps till certain markKey(s) exist.");
			public static final IFluentAPITemplate DOC = new FluentAPIFixTemplate(
					"Allows specifying model construction steps as a Runnable instance R, which this API will execute after using the given markKey(s) to mark objects. This method enables preserving the flow of model construction by enabling the specification of construction steps on objects that may not yet exist. The main purpose of this method is to facilitate model constructions, where dependencies between model elements either forcefully require bottom-up approaches or require mixing the construction of several model elements.");
		}

		public static class WithFeat {
			public static final IFluentAPITemplate NAME = new FluentAPIFixTemplate(
					ModelConstants.PLACEHOLDER.get() + WithFeat.class.getSimpleName());
			public static final IFluentAPITemplate SUMMARY = new FluentAPIFixTemplate(
					"Sets the given value of a certain single-valued EStructuralFeature for a certain EObject.");
		}

		public static class WithoutFeat {
			public static final IFluentAPITemplate NAME = new FluentAPIFixTemplate(
					ModelConstants.PLACEHOLDER.get() + WithoutFeat.class.getSimpleName());
			public static final IFluentAPITemplate SUMMARY = new FluentAPIFixTemplate(
					"Unsets the value of a certain single-valued EStructuralFeature for a certain EObject.");
		}

		public static class WithAddedFeat {
			public static final IFluentAPITemplate NAME = new FluentAPIFixTemplate(
					ModelConstants.PLACEHOLDER.get() + WithAddedFeat.class.getSimpleName());
			public static final IFluentAPITemplate SUMMARY = new FluentAPIFixTemplate(
					"Adds the given value(s) to a certain many-valued EStructuralFeature for a certain EObject.");
		}

		public static class WithRemovedFeat {
			public static final IFluentAPITemplate NAME = new FluentAPIFixTemplate(
					ModelConstants.PLACEHOLDER.get() + WithRemovedFeat.class.getSimpleName());
			public static final IFluentAPITemplate SUMMARY = new FluentAPIFixTemplate(
					"Removes the given value(s) from a certain many-valued EStructuralFeature for a certain EObject.");
		}

		public static class CleanFeat {
			public static final IFluentAPITemplate NAME = new FluentAPIFixTemplate(
					ModelConstants.PLACEHOLDER.get() + CleanFeat.class.getSimpleName());
			public static final IFluentAPITemplate SUMMARY = new FluentAPIFixTemplate(
					"Removes all values of a certain many-valued EStructuralFeature for a certain EObject.");
		}

		public static class Mark {
			public static final IFluentAPITemplate NAME = new FluentAPIFixTemplate(getMethodName(Mark.class));

			public static final IFluentAPITemplate SUMMARY = new FluentAPIFixTemplate(

					"Marks the object with " + ModelConstants.GeneralParameters.MARK_KEY_PARAMETER_NAME.get()
							+ ", does not modify the object.");
			public static final IFluentAPITemplate DOC = new FluentAPIFixTemplate(
					FluentAPIDocumentationUtil.appendSummaryToStart(SUMMARY.get()) + "Associates the given object with "
							+ ModelConstants.GeneralParameters.MARK_KEY_PARAMETER_NAME.get()
							+ ". Doing so marks the given object, meaning that using "
							+ ModelConstants.GeneralParameters.MARK_KEY_PARAMETER_NAME.get()
							+ " in mark-related operations will result in retrieving the given object.");
		}

		public static class GetOngoingInitialisations {
			public static final IFluentAPITemplate NAME = new FluentAPIFixTemplate(
					getMethodName(GetOngoingInitialisations.class));
		}

		public static class ClearAllOngoingInitialisations {
			public static final IFluentAPITemplate NAME = new FluentAPIFixTemplate(
					getMethodName(ClearAllOngoingInitialisations.class));
		}
	}

	public static class SuperInitialisation {
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

		public static class RootAPI {
			public static final IFluentAPIFeatureTemplate NAME = new FluentAPIFeatureTemplate(
					getFeatureName(RootAPI.class));
		}

		public static class CurrentElement {
			public static final IFluentAPIFeatureTemplate NAME = new FluentAPIFeatureTemplate(
					getFeatureName(CurrentElement.class));
		}

		public static class ToAPI {
			public static final IFluentAPITemplate NAME = new FluentAPIFixTemplate(getMethodName(ToAPI.class));

			public static final IFluentAPITemplate SUMMARY = new FluentAPIFixTemplate("Swaps to the API instance");
			public static final IFluentAPITemplate DOC = new FluentAPIFixTemplate(
					FluentAPIDocumentationUtil.appendSummaryToStart(SUMMARY.get())
							+ "Swaps from this to the API, which created it. This method is currently the same as this"
							+ ModelConstants.SuperInitialisation.RootAPI.NAME.getterCall()
							+ ". Its purpose is to isolate the use of this"
							+ ModelConstants.SuperInitialisation.RootAPI.NAME.getterCall()
							+ ", which is a getter method that is generated by EMF.");
		}

		public static class Drop {
			public static final IFluentAPITemplate NAME = new FluentAPIFixTemplate(getMethodName(Drop.class));
		}

		public static class GetInitialisedEClass {
			public static final IFluentAPITemplate NAME = new FluentAPIFixTemplate(
					getMethodName(GetInitialisedEClass.class));

			public static final IFluentAPITemplate SUMMARY = new FluentAPIFixTemplate("Returns the targeted EClass.");
			public static final IFluentAPITemplate DOC = new FluentAPIFixTemplate(
					FluentAPIDocumentationUtil.appendSummaryToStart(SUMMARY.get())
							+ "Returns the EClass, which this targets.");
		}

		public static class NewElement {
			public static final IFluentAPITemplate NAME = new FluentAPIFixTemplate(getMethodName(NewElement.class));
			public static final IFluentAPITemplate SUMMARY = new FluentAPIFixTemplate(
					"Creates a minimal instance of the targeted type within this "
							+ ModelConstants.INITIALISATION_NAME_SUFFIX.get() + " instance.");
			public static final IFluentAPITemplate DOC = new FluentAPIFixTemplate(FluentAPIDocumentationUtil
					.appendSummaryToStart(SUMMARY.get())
					+ "Creates a minimal EObject instance, without modifying any of its features, and sets it as the current element (i.e. return value of "
					+ ModelConstants.SuperInitialisation.CurrentElement.NAME.thisGetterCall() + ") in concrete "
					+ ModelConstants.INITIALISATION_NAME_SUFFIX.get() + " classes. Does nothing in this class.");
		}

		public static class MarkCurrent {
			public static final IFluentAPITemplate NAME = new FluentAPIFixTemplate(getMethodName(MarkCurrent.class));
		}

		public static class UnmarkCurrent {
			public static final IFluentAPITemplate NAME = new FluentAPIFixTemplate(getMethodName(UnmarkCurrent.class));
		}

		public static class Reset {
			public static final IFluentAPITemplate NAME = new FluentAPIFixTemplate(getMethodName(Reset.class));

			public static final IFluentAPITemplate SUMMARY = new FluentAPIFixTemplate(
					"Removes the current object under construction from this.");
			public static final IFluentAPITemplate DOC = new FluentAPIFixTemplate(
					FluentAPIDocumentationUtil.appendSummaryToStart(SUMMARY.get()) + "Resets this, which discards "
							+ ModelConstants.SuperInitialisation.CurrentElement.NAME.thisGetterCall()
							+ ". Does not drop this from " + ModelConstants.SuperInitialisation.ToAPI.NAME.thisCall()
							+ ", meaning that it will still be accessible from "
							+ ModelConstants.SuperInitialisation.ToAPI.NAME.thisCall()
							+ ". This can then be re-used by calling "
							+ ModelConstants.SuperInitialisation.NewElement.NAME.thisCall() + ".");
		}

		public static class CreateNow {
			public static final IFluentAPITemplate TYPE_PARAMETER_NAME = new FluentAPIFixTemplate("T");
			public static final IFluentAPITemplate CLASS_PARAMETER_NAME = new FluentAPIFixTemplate("returnTypeCls");
			public static final IFluentAPITemplate NAME = new FluentAPIFixTemplate(getMethodName(CreateNow.class));

			public static final IFluentAPITemplate SUMMARY = new FluentAPIFixTemplate(
					"Finalises and returns the object under construction.");
			public static final IFluentAPITemplate DOC = new FluentAPIFixTemplate(
					FluentAPIDocumentationUtil.appendSummaryToStart(SUMMARY.get())
							+ "Finalises the construction of "
							+ ModelConstants.SuperInitialisation.CurrentElement.NAME.thisGetterCall()
							+ " and returns it. Drops this from "
							+ ModelConstants.SuperInitialisation.ToAPI.NAME.thisCall()
							+ ", meaning that this will no longer be accessible from "
							+ ModelConstants.SuperInitialisation.ToAPI.NAME.thisCall() + ".");

			public static final IFluentAPITemplate CLASS_PARAMETER_DOC = new FluentAPIFixTemplate(DOC.get()
					+ FluentAPIDocumentationUtil.getDocParagraphSeparator()
					+ "EMF-based metamodels consider interfaces, which allow diamond structures in the type hierarchy of their implementors. To spare type casting in model construction, this method can be given a class parameter, to which the returned value will be cast.");
		}
	}

	public static class Initialiation {
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
		public static final IFluentAPIFillableTemplate INIT_ECLASS_DOC = new FluentAPIFillableTemplate("An "
				+ ModelConstants.INITIALISATION_NAME_SUFFIX.get()
				+ " class that targets the type '%s' within the '%s' metamodel. Contains various methods that facilitate the programmatic construction of '%s' instances."
				+ FluentAPIDocumentationUtil.getClassMethodOverviewIntroTemplate());

		public static class NewElement extends ModelConstants.SuperInitialisation.NewElement {
			public static final IFluentAPIFillableTemplate DOC = new FluentAPIFillableTemplate(
					FluentAPIDocumentationUtil
							.appendSummaryToStart(ModelConstants.SuperInitialisation.NewElement.SUMMARY.get())
							+ "Creates a minimal %s instance, without modifying any of its features, and sets it as the current element (i.e. return value of this"
							+ ModelConstants.SuperInitialisation.CurrentElement.NAME.getterCall() + ")."
							+ FluentAPIDocumentationUtil.appendDoNotUseFromOutsideDocNoteAtEnd());
		}

		public static class With {
			public static final IFluentAPITemplate PARAMETER_NAME = new FluentAPIFixTemplate("newFeatVal");
			public static final IFluentAPIFillableTemplate NAME = new FluentAPIFillableTemplate(
					getMethodName(With.class) + "%s");

			/**
			 * %s: Feature name
			 */
			public static final IFluentAPIFillableTemplate DOC = new FluentAPIFillableTemplate(
					FluentAPIDocumentationUtil.appendSummaryToStart(ModelConstants.FluentAPI.WithFeat.SUMMARY.get())
							+ "Sets the value of the feature %s in this"
							+ ModelConstants.SuperInitialisation.CurrentElement.NAME.getterCall()
							+ " to the given value.");

			/**
			 * %s: Feature name
			 */
			public static final IFluentAPIFillableTemplate PARAMETER_DOC = new FluentAPIFillableTemplate(
					"The new value of the feature %s, which will replace its current value in the initialised object this"
							+ ModelConstants.SuperInitialisation.CurrentElement.NAME.getterCall());
		}

		public static class Without {
			public static final IFluentAPIFillableTemplate NAME = new FluentAPIFillableTemplate(
					getMethodName(Without.class) + "%s");

			// %s: Feature name
			public static final IFluentAPIFillableTemplate DOC = new FluentAPIFillableTemplate(
					FluentAPIDocumentationUtil.appendSummaryToStart(ModelConstants.FluentAPI.WithoutFeat.SUMMARY.get())
							+ "Unsets the value of the feature %s in this"
							+ ModelConstants.SuperInitialisation.CurrentElement.NAME.getterCall()
							+ ", which sets its value to null.");
		}

		public static class WithAdded {
			public static final IFluentAPITemplate PARAMETER_NAME = new FluentAPIFixTemplate("featValToAdd");
			public static final IFluentAPIFillableTemplate NAME = new FluentAPIFillableTemplate(
					getMethodName(WithAdded.class) + "%s");

			// %s: Feature name
			public static final IFluentAPIFillableTemplate DOC = new FluentAPIFillableTemplate(
					FluentAPIDocumentationUtil
							.appendSummaryToStart(ModelConstants.FluentAPI.WithAddedFeat.SUMMARY.get())
							+ "Adds the given values to the current values of the feature %s in this"
							+ ModelConstants.SuperInitialisation.CurrentElement.NAME.getterCall() + ".");

			// %s: Feature name
			public static final IFluentAPIFillableTemplate PARAMETER_DOC = new FluentAPIFillableTemplate(
					"Value(s) for the feature %s, which will be added to its current values in the initialised object this"
							+ ModelConstants.SuperInitialisation.CurrentElement.NAME.getterCall() + ".");
		}

		public static class WithRemoved {
			public static final IFluentAPITemplate PARAMETER_NAME = new FluentAPIFixTemplate("featValToRemove");
			public static final IFluentAPIFillableTemplate NAME = new FluentAPIFillableTemplate(
					getMethodName(WithRemoved.class) + "%s");

			// %s: Feature name
			public static final IFluentAPIFillableTemplate DOC = new FluentAPIFillableTemplate(
					FluentAPIDocumentationUtil
							.appendSummaryToStart(ModelConstants.FluentAPI.WithRemovedFeat.SUMMARY.get())
							+ "Removes the given values from the current values of the feature %s in this"
							+ ModelConstants.SuperInitialisation.CurrentElement.NAME.getterCall() + ".");

			// %s: Feature name
			public static final IFluentAPIFillableTemplate PARAMETER_DOC = new FluentAPIFillableTemplate(
					"Value(s) for the feature %s, which will be removed from its current values in the initialised object this"
							+ ModelConstants.SuperInitialisation.CurrentElement.NAME.getterCall() + ".");
		}

		public static class Clean {
			public static final IFluentAPIFillableTemplate NAME = new FluentAPIFillableTemplate(
					getMethodName(Clean.class) + "%s");

			// %s: Feature name
			public static final IFluentAPIFillableTemplate DOC = new FluentAPIFillableTemplate(
					FluentAPIDocumentationUtil.appendSummaryToStart(ModelConstants.FluentAPI.CleanFeat.SUMMARY.get())
							+ "Clears all values of the (many-valued) feature %s in this"
							+ ModelConstants.SuperInitialisation.CurrentElement.NAME.getterCall()
							+ " to the given value.");
		}
	}
}

package cipm.consistency.fluentapi.gen;

import org.apache.commons.lang.StringUtils;

public final class ModelConstants {

	// TODO Extract TOP_NAME computation
	// TODO Use lower case class name in NAME members if possible

	public static final class GlobalConstants {
		/**
		 * %s: Metamodel name (small case)
		 * 
		 * TODO Change to "http://www.cipmfluentapi.com/%s" after refactoring
		 * 
		 */
		private static final IFluentAPIFillableTemplate ROOT_PACKAGE_URI = new FluentAPIFillableTemplate(
				"http://www.cipmfluentapi.com/java");
		/**
		 * %s: Metamodel name (small case)
		 * 
		 * TODO Change to "cipm.consistency.fluentapi.api.%s" after refactoring
		 */
		private static final IFluentAPIFillableTemplate ROOT_PACKAGE_NAME = new FluentAPIFillableTemplate(
				"cipm.consistency.fluentapi.api");

		/**
		 * %s: Metamodel name (small case)
		 */
		private static final IFluentAPIFillableTemplate INITIALISATIONS_PACKAGE_NAME = new FluentAPIFillableTemplate(
				"inits");

		/**
		 * %s: Metamodel name (small case)
		 */
		private static final IFluentAPIFillableTemplate INITIALISATIONS_PACKAGE_URI = new FluentAPIFillableTemplate(
				ROOT_PACKAGE_URI.get() + "/" + INITIALISATIONS_PACKAGE_NAME.get());

		private static final IFluentAPITemplate PLACEHOLDER = new FluentAPIFixTemplate("x");
		private static final IFluentAPITemplate GEN_MODEL_SOURCE_URL = new FluentAPIFixTemplate(
				"http://www.eclipse.org/emf/2002/GenModel");
		private static final IFluentAPITemplate INITIALISATION_NAME_SUFFIX = new FluentAPIFixTemplate("Initialisation");
	}

	public static final class EDataTypeWrappersPackage {
		private static final IFluentAPITemplate PACKAGE_NAME = new FluentAPIFixTemplate("placeholderTypes");
	}

	public static final class RootAPI {
		/**
		 * %s: Metamodel name (capitalised)
		 */
		private static final IFluentAPIFillableTemplate CLASS_NAME = new FluentAPIFillableTemplate("Fluent%sAPI");

		public static final class Continue {
			/**
			 * %s: Class name (capitalised)
			 */
			private static final IFluentAPIFillableTemplate NAME = new FluentAPIFillableTemplate("continue%s");
		}

		public static final class ContinueMarked {
			/**
			 * %s: Class name (capitalised)
			 */
			private static final IFluentAPIFillableTemplate NAME = new FluentAPIFillableTemplate("continueMarked%s");
		}

		public static final class CreateNew {
			private static final IFluentAPITemplate TYPE_PARAMETER_NAME = new FluentAPIFixTemplate("T");
			private static final IFluentAPITemplate ECLASS_PARAMETER_NAME = new FluentAPIFixTemplate("eObjCls");
			private static final IFluentAPITemplate NAME_PREFIX = new FluentAPIFixTemplate("createNew");
			/**
			 * %s: Class name (capitalised)
			 */
			private static final IFluentAPIFillableTemplate NAME = new FluentAPIFillableTemplate(
					NAME_PREFIX.get() + "%s");
			private static final IFluentAPITemplate TOP_NAME = new FluentAPIFixTemplate(
					NAME_PREFIX.get() + StringUtils.capitalize(ModelConstants.GlobalConstants.PLACEHOLDER.get()));
		}

		public static final class DropInitialisation {
			private static final IFluentAPITemplate INITIALISATION_PARAMETER_NAME = new FluentAPIFixTemplate(
					"initToDrop");
			private static final IFluentAPITemplate NAME = new FluentAPIFixTemplate("dropInitialisation");
		}

		public static final class GetAllSupportedEClasses {
			private static final IFluentAPITemplate NAME = new FluentAPIFixTemplate("getAllSupportedClasses");
		}

		public static final class GetInitialisationFor {
			private static final IFluentAPITemplate NAME = new FluentAPIFixTemplate(
					"getInitialisationFor" + StringUtils.capitalize(ModelConstants.GlobalConstants.PLACEHOLDER.get()));
		}

		public static final class GetMarked {
			private static final IFluentAPITemplate NAME_PREFIX = new FluentAPIFixTemplate("getMarked");
			/**
			 * %s: Class name (capitalised)
			 */
			private static final IFluentAPIFillableTemplate NAME = new FluentAPIFillableTemplate(
					NAME_PREFIX.get() + "%s");
			private static final IFluentAPITemplate TOP_NAME = new FluentAPIFixTemplate(NAME_PREFIX.get());
		}

		public static final class Unmark {
			private static final IFluentAPITemplate NAME = new FluentAPIFixTemplate("unmark");
		}

		public static final class Modify {
			private static final IFluentAPITemplate NAME_PREFIX = new FluentAPIFixTemplate("modify");
			/**
			 * %s: Class name (capitalised)
			 */
			private static final IFluentAPIFillableTemplate NAME = new FluentAPIFillableTemplate(
					NAME_PREFIX.get() + "%s");
			private static final IFluentAPITemplate TOP_NAME = new FluentAPIFixTemplate(
					NAME_PREFIX.get() + StringUtils.capitalize(ModelConstants.GlobalConstants.PLACEHOLDER.get()));
		}

		public static final class ModifyMarked {
			/**
			 * %s: Class name (capitalised)
			 */
			private static final IFluentAPIFillableTemplate NAME = new FluentAPIFillableTemplate("modifyMarked%s");
		}

		public static final class New {
			private static final IFluentAPITemplate NAME_PREFIX = new FluentAPIFixTemplate("new");
			/**
			 * %s: Class name (capitalised)
			 */
			private static final IFluentAPIFillableTemplate NAME = new FluentAPIFillableTemplate(
					NAME_PREFIX.get() + "%s");
			private static final IFluentAPITemplate TOP_NAME = new FluentAPIFixTemplate(
					NAME_PREFIX.get() + StringUtils.capitalize(ModelConstants.GlobalConstants.PLACEHOLDER.get()));
		}

		public static final class OnceExists {
			private static final IFluentAPITemplate NAME = new FluentAPIFixTemplate("onceExists");
			private static final IFluentAPITemplate SUMMARY = new FluentAPIFixTemplate(
					"Suspends certain model construction steps till certain markKey(s) exist.");
			private static final IFluentAPITemplate DOC = new FluentAPIFixTemplate(
					"Allows specifying model construction steps as a Runnable instance R, which this API will execute after using the given markKey(s) to mark objects. This method enables preserving the flow of model construction by enabling the specification of construction steps on objects that may not yet exist. The main purpose of this method is to facilitate model constructions, where dependencies between model elements either forcefully require bottom-up approaches or require mixing the construction of several model elements.");
		}

		public static final class WithFeat {
			private static final IFluentAPITemplate NAME = new FluentAPIFixTemplate(
					ModelConstants.GlobalConstants.PLACEHOLDER.get() + "WithFeat");
			private static final IFluentAPITemplate SUMMARY = new FluentAPIFixTemplate(
					"Sets the given value of a certain single-valued EStructuralFeature for a certain EObject.");
		}

		public static final class WithoutFeat {
			private static final IFluentAPITemplate NAME = new FluentAPIFixTemplate(
					ModelConstants.GlobalConstants.PLACEHOLDER.get() + "WithoutFeat");
			private static final IFluentAPITemplate SUMMARY = new FluentAPIFixTemplate(
					"Unsets the value of a certain single-valued EStructuralFeature for a certain EObject.");
		}

		public static final class WithAddedFeat {
			private static final IFluentAPITemplate NAME = new FluentAPIFixTemplate(
					ModelConstants.GlobalConstants.PLACEHOLDER.get() + "WithAddedFeat");
			private static final IFluentAPITemplate SUMMARY = new FluentAPIFixTemplate(
					"Adds the given value(s) to a certain many-valued EStructuralFeature for a certain EObject.");
		}

		public static final class WithRemovedFeat {
			private static final IFluentAPITemplate NAME = new FluentAPIFixTemplate(
					ModelConstants.GlobalConstants.PLACEHOLDER.get() + "WithRemovedFeat");
			private static final IFluentAPITemplate SUMMARY = new FluentAPIFixTemplate(
					"Removes the given value(s) from a certain many-valued EStructuralFeature for a certain EObject.");
		}

		public static final class CleanFeat {
			private static final IFluentAPITemplate NAME = new FluentAPIFixTemplate(
					ModelConstants.GlobalConstants.PLACEHOLDER.get() + "CleanFeat");
			private static final IFluentAPITemplate SUMMARY = new FluentAPIFixTemplate(
					"Removes all values of a certain many-valued EStructuralFeature for a certain EObject.");
		}

		public static final class Mark {
			private static final IFluentAPITemplate NAME = new FluentAPIFixTemplate("mark");
		}

		public static final class GetOngoingInitialisations {
			private static final IFluentAPITemplate NAME = new FluentAPIFixTemplate("getOngoingInits");
		}

		public static final class ClearAllOngoingInitialisations {
			private static final IFluentAPITemplate NAME = new FluentAPIFixTemplate("clearAllOngoingInits");
		}
	}

	public static final class SuperInitialisation {
		/**
		 * %s: Metamodel name (capitalised)
		 */
		private static final IFluentAPIFillableTemplate CLASS_NAME = new FluentAPIFillableTemplate(
				"Fluent%sAPISuperInitialisation");

		public static final class RootAPIRef {
			private static final IFluentAPITemplate NAME = new FluentAPIFeatureTemplate("rootAPI");
		}

		public static final class CurrentElementRef {
			private static final IFluentAPITemplate NAME = new FluentAPIFeatureTemplate("currentElement");
		}

		public static final class ToAPI {
			private static final IFluentAPITemplate NAME = new FluentAPIFixTemplate("toAPI");
		}

		public static final class Drop {
			private static final IFluentAPITemplate NAME = new FluentAPIFixTemplate("drop");
		}

		public static final class GetInitialisedEClass {
			private static final IFluentAPITemplate NAME = new FluentAPIFixTemplate("getInitialisedEClass");
		}

		public static final class NewElement {
			private static final IFluentAPITemplate NAME = new FluentAPIFixTemplate("newElement");
			private static final IFluentAPITemplate SUMMARY = new FluentAPIFixTemplate(
					"Creates a minimal instance of the targeted type within this "
							+ ModelConstants.GlobalConstants.INITIALISATION_NAME_SUFFIX + " instance.");
		}

		public static final class MarkCurrent {
			private static final IFluentAPITemplate NAME = new FluentAPIFixTemplate("markCurrent");
		}

		public static final class UnmarkCurrent {
			private static final IFluentAPITemplate NAME = new FluentAPIFixTemplate("unmarkCurrent");
		}

		public static final class Reset {
			private static final IFluentAPITemplate NAME = new FluentAPIFixTemplate("reset");
		}

		public static final class OnceExists {
			private static final IFluentAPITemplate NAME = ModelConstants.RootAPI.OnceExists.NAME;
			private static final IFluentAPITemplate SUMMARY = ModelConstants.RootAPI.OnceExists.SUMMARY;
			private static final IFluentAPITemplate DOC = ModelConstants.RootAPI.OnceExists.DOC;
		}

		public static final class CreateNow {
			private static final IFluentAPITemplate TYPE_PARAMETER_NAME = new FluentAPIFixTemplate("T");
			private static final IFluentAPITemplate CLASS_PARAMETER_NAME = new FluentAPIFixTemplate("returnTypeCls");
			private static final IFluentAPITemplate NAME = new FluentAPIFixTemplate("createNow");
		}
	}

	public static final class Initialiation {
		/**
		 * %s: Class name (capitalised)
		 */
		private static final IFluentAPIFillableTemplate CLASS_NAME = new FluentAPIFillableTemplate(
				"%s" + ModelConstants.GlobalConstants.INITIALISATION_NAME_SUFFIX);

		public static final class With {
			private static final IFluentAPITemplate PARAMETER_NAME = new FluentAPIFixTemplate("newFeatVal");
			private static final IFluentAPIFillableTemplate NAME = new FluentAPIFillableTemplate("with%s");
		}

		public static final class Without {
			private static final IFluentAPIFillableTemplate NAME = new FluentAPIFillableTemplate("without%s");
		}

		public static final class WithAdded {
			private static final IFluentAPITemplate PARAMETER_NAME = new FluentAPIFixTemplate("featValToAdd");
			private static final IFluentAPIFillableTemplate NAME = new FluentAPIFillableTemplate("withAdded%s");
		}

		public static final class WithRemoved {
			private static final IFluentAPITemplate PARAMETER_NAME = new FluentAPIFixTemplate("featValToRemove");
			private static final IFluentAPIFillableTemplate NAME = new FluentAPIFillableTemplate("withRemoved%s");
		}

		public static final class Clean {
			private static final IFluentAPIFillableTemplate NAME = new FluentAPIFillableTemplate("clean%s");
		}
	}
}

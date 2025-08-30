# Introduction

TODO

# Terminology

- Similarity checking
	- Similarity result / Similarity checking result: (usually) a Boolean that indicates whether 2 or more objects are similar: Similar if the result is true, not similar if the result is false, undecidable if the result is null. Under normal circumstances, the result should not be null.
	- Similarity Checking: Computation of the similarity of objects, especially in-memory models. The result of this operation is the similarity result.
	- Similarity Checker: Mechanisms that perform similarity checking
	- Similarity Checker Container (SCC): A provider of similarity checkers that contains similarity checker(s)

- Models
	- Model source file: A file containing parts of or information about a model. These are the original files of a model provided as input to parsing methods, which parse an in-memory version of the model. These files typically only contain direct contents of the model, i.e. the contents of the actual model that are declared directly in the model. If the model has any outside dependencies, typically only references to those dependencies are stored.
	- Model source file directory: A directory that contains all model source files belonging to a (and only one) model. Passing a model source directory to a model parsing method will result in an in-memory model, which consists of the parsed contents of model source files. Model source files therein may or may not be nested in further directories.
	- Model source parent directory: A directory that contains model source file directories of one or more models. Each nested model source file directory should be passed to model parsing methods separately, if in-memory models to be parsed should be separate.
	- Model resource content: An in-memory representation of a model element, which is a part of a model.
	- Model resource: An in-memory representation of a model (usually in form of a Resource instance). If multiple model resources are involved, their in-memory representation may be an object that aggregates individual model resources (such as a ResourceSet instance). Model resources consist of model resource contents and may include other metadata.
	- Parsed model file / model resource file: A file containing parts of or information about a parsed model. These files persist the parsed in-memory models, allowing them to be loaded for speeding up tests. If all necessary parsed model files for a model are present, parsing the model from scratch is not necessary, as the resulting parsed model should have the same content as parsed model files. Keep in mind that the order of contents may differ, if model parsing methods are not fully deterministic.
	- Model comparison: Comparison of 2 or more model resources based on their contents
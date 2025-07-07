package cipm.consistency.fitests.similarity.jamopp;

import java.nio.file.Path;

import org.eclipse.emf.ecore.resource.ResourceSet;

import cipm.consistency.fitests.similarity.eobject.AbstractModelResourceParsingStrategy;
import jamopp.options.ParserOptions;
import jamopp.parser.jdt.singlefile.JaMoPPJDTSingleFileParser;
import jamopp.recovery.trivial.TrivialRecovery;

public class JaMoPPModelResourceParsingStrategy extends AbstractModelResourceParsingStrategy {
	private final JaMoPPJDTSingleFileParser parser;

	public JaMoPPModelResourceParsingStrategy() {
		super();
		this.parser = new JaMoPPJDTSingleFileParser();
		this.setUpModelParser();
	}

	protected JaMoPPJDTSingleFileParser getParser() {
		return parser;
	}

	/**
	 * Prepares the parser for parsing model resources. Can be overridden in
	 * sub-types to modify, if needed.
	 */
	protected void setUpModelParser() {
		/*
		 * Default values of ParserOptions are:
		 * 
		 * RESOLVE_ALL_BINDINGS = true
		 * 
		 * RESOLVE_BINDINGS = true
		 * 
		 * RESOLVE_BINDINGS_OF_INFERABLE_TYPES = true
		 * 
		 * CREATE_LAYOUT_INFORMATION = true
		 * 
		 * PREFER_BINDING_CONVERSION = true
		 */
		this.parser.setResourceSet(this.getResourceSet());

		ParserOptions.CREATE_LAYOUT_INFORMATION.setValue(Boolean.FALSE);
		ParserOptions.REGISTER_LOCAL.setValue(Boolean.TRUE);
		ParserOptions.RESOLVE_EVERYTHING.setValue(Boolean.FALSE);
		ParserOptions.RESOLVE_ALL_BINDINGS.setValue(Boolean.FALSE);
	}

	@Override
	public ResourceSet parseModelResource(Path modelDir) {
		return parser.parseDirectory(modelDir);
	}

	public void performTrivialRecovery() {
		this.performTrivialRecovery(this.getResourceSet());
	}

	public void performTrivialRecovery(ResourceSet resourceSet) {
		new TrivialRecovery(resourceSet).recover();
	}
}

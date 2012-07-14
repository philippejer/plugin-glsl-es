package com.petrbroz.webglsl.editors;

public class FragmentShaderScanner extends ShaderScanner {

	/**
	 * 
	 */
	public FragmentShaderScanner() {
		super();
		super.language = readTokens("keyword.tokens");
		super.types = readTokens("type.tokens");
		super.functions = readTokens("function.tokens");
		super.semantics = readTokens("semantic.tokens");
	}
}

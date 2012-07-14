package com.petrbroz.webglsl.editors;

public class VertexShaderScanner extends ShaderScanner {

	/**
	 * 
	 */
	public VertexShaderScanner() {
		super();
		super.language = readTokens("keyword.tokens");
		super.types = readTokens("type.tokens");
		super.functions = readTokens("function.tokens");
		super.semantics = new String[0];
	}
}

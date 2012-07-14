package com.petrbroz.webglsl.editors;

import com.petrbroz.webglsl.Messages;

public class VertexShaderConfiguration extends ShaderConfiguration {

	/**
	 * 
	 */
	public VertexShaderConfiguration() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected ShaderScanner getTagScanner() {
		if (scanner == null) {
			scanner = new VertexShaderScanner();
			scanner.setShaderRules();
			scanner.setDefaultReturnToken(TokenManager
					.getToken(Messages.Label_Default));
		}
		return scanner;
	}
}

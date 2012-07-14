package com.petrbroz.webglsl.editors;

import com.petrbroz.webglsl.Messages;

public class FragmentShaderConfiguration extends ShaderConfiguration {

	/**
	 * 
	 */
	public FragmentShaderConfiguration() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected ShaderScanner getTagScanner() {
		if (scanner == null) {
			scanner = new FragmentShaderScanner();
			scanner.setShaderRules();
			scanner.setDefaultReturnToken(TokenManager
					.getToken(Messages.Label_Default));
		}
		return scanner;
	}
}

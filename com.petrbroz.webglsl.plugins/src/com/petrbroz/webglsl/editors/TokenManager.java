package com.petrbroz.webglsl.editors;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.Token;

public class TokenManager {

	protected static Map<String, Token> tokenTable = new HashMap<String, Token>(5);

	public static Token getToken(String str) {
		Token token = tokenTable.get(str);
		if (token == null) {
			token = new Token(new TextAttribute(null));
			tokenTable.put(str, token);
		}
		return token;
	}

}

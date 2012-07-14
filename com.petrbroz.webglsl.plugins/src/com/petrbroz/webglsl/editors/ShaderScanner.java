package com.petrbroz.webglsl.editors;

import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;
import java.io.*;

import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.WordRule;

import com.petrbroz.webglsl.Messages;

public class ShaderScanner extends RuleBasedScanner {

	static char ESCAPE_CHARS[] = { '\n', ' ', '.', ';', ',', '(', ')', '[', ']' };
	String language[];
	String types[];
	String functions[];
	String semantics[];
	String[] keys = null;

	/**
	 * 
	 */
	public ShaderScanner() {
		super();
	}

	public String[] readTokens(String tokenFile) {
		String line;
		Set<String> results = new HashSet<String>();
		try {
			InputStream is = getClass().getResourceAsStream(tokenFile);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				results.add(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results.toArray(new String[0]);
	}

	public void setShaderRules() {
		if (keys == null) {
			keys = new String[language.length + types.length + functions.length
					+ semantics.length];
			System.arraycopy(language, 0, keys, 0, language.length);
			System.arraycopy(types, 0, keys, language.length, types.length);
			System.arraycopy(functions, 0, keys,
					language.length + types.length, functions.length);
			System.arraycopy(semantics, 0, keys, language.length + types.length
					+ functions.length, semantics.length);
		}

		IToken commentToken = TokenManager.getToken(Messages.Label_Comments);
		IToken languageToken = TokenManager.getToken(Messages.Label_Keywords);
		IToken typeToken = TokenManager.getToken(Messages.Label_Types);
		IToken functionToken = TokenManager.getToken(Messages.Label_Functions);
		IToken semanticToken = TokenManager.getToken(Messages.Label_Semantics);
		IToken defaultToken = TokenManager.getToken(Messages.Label_Default);

		IRule[] rules = new IRule[3];
		rules[0] = new MultiLineRule("/*", "*/", commentToken);
		rules[1] = new EndOfLineRule("//", commentToken);
		rules[2] = new WordRule(new IWordDetector() {

			BitSet set = new BitSet(keys.length);

			int index = 0;

			@Override
			public boolean isWordStart(char c) {
				set.clear();
				index = 1;
				boolean start = false;
				for (int i = 0; i < keys.length; i++) {

					if (keys[i].charAt(0) == c) {
						set.set(i);
						start = true;
					}
				}
				return start;
			}

			@Override
			public boolean isWordPart(char c) {
				if (set.isEmpty())
					return isNotEscChar(c);
				for (int i = 0; i < keys.length; i++) {
					if (keys[i].length() > index && set.get(i)) {
						if (keys[i].charAt(index) != c)
							set.clear(i);
					} else
						set.clear(i);
				}
				index++;
				return !set.isEmpty() || isNotEscChar(c);
			}

			public boolean isNotEscChar(char c) {
				for (int i = 0; i < ESCAPE_CHARS.length; i++)
					if (c == ESCAPE_CHARS[i])
						return false;
				return true;
			}

		}, defaultToken);

		for (int i = 0; i < language.length; i++)
			((WordRule) rules[2]).addWord(language[i], languageToken);

		for (int i = 0; i < types.length; i++)
			((WordRule) rules[2]).addWord(types[i], typeToken);

		for (int i = 0; i < semantics.length; i++)
			((WordRule) rules[2]).addWord(semantics[i], semanticToken);

		for (int i = 0; i < functions.length; i++)
			((WordRule) rules[2]).addWord(functions[i], functionToken);

		setRules(rules);
	}
}

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

public class ShaderScanner extends RuleBasedScanner {

	protected static char ESCAPE_CHARS[] = { '\n', ' ', '.', ';', ',', '(',
			')', '[', ']' };
	protected static String FUNCTIONS[];
	protected static String KEYWORDS[];
	protected static String SEMANTICS[];
	protected static String TYPES[];
	protected static String KEYS[];

	static {
		FUNCTIONS = readResourceStrings("functions");
		KEYWORDS = readResourceStrings("keywords");
		SEMANTICS = readResourceStrings("semantics");
		TYPES = readResourceStrings("types");

		int f = FUNCTIONS.length;
		int k = KEYWORDS.length;
		int s = SEMANTICS.length;
		int t = TYPES.length;
		KEYS = new String[f + k + s + t];
		System.arraycopy(FUNCTIONS, 0, KEYS, 0, f);
		System.arraycopy(KEYWORDS, 0, KEYS, f, k);
		System.arraycopy(SEMANTICS, 0, KEYS, f + k, s);
		System.arraycopy(TYPES, 0, KEYS, f + k + s, t);
	}

	/**
	 * 
	 */
	public ShaderScanner() {
		super();
		setScannerRules();
	}

	private static String[] readResourceStrings(String inputFile) {
		String line;
		Set<String> results = new HashSet<String>();
		try {
			InputStream is = ShaderScanner.class.getResourceAsStream(inputFile);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				results.add(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results.toArray(new String[0]);
	}

	protected void setScannerRules() {
		IToken commentToken = TokenManager
				.getToken(ShaderConfiguration.COMMENT_TOKEN);
		IToken functionToken = TokenManager
				.getToken(ShaderConfiguration.FUNCTION_TOKEN);
		IToken keywordToken = TokenManager
				.getToken(ShaderConfiguration.KEYWORD_TOKEN);
		IToken semanticToken = TokenManager
				.getToken(ShaderConfiguration.SEMANTIC_TOKEN);
		IToken typeToken = TokenManager
				.getToken(ShaderConfiguration.TYPE_TOKEN);
		IToken defaultToken = TokenManager
				.getToken(ShaderConfiguration.DEFAULT_TOKEN);

		IRule[] rules = new IRule[3];
		rules[0] = new MultiLineRule("/*", "*/", commentToken);
		rules[1] = new EndOfLineRule("//", commentToken);
		rules[2] = new WordRule(new IWordDetector() {

			BitSet set = new BitSet(KEYS.length);

			int index = 0;

			@Override
			public boolean isWordStart(char c) {
				set.clear();
				index = 1;
				boolean start = false;
				for (int i = 0; i < KEYS.length; i++) {
					if (KEYS[i].charAt(0) == c) {
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
				for (int i = 0; i < KEYS.length; i++) {
					if (KEYS[i].length() > index && set.get(i)) {
						if (KEYS[i].charAt(index) != c)
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

		for (int i = 0; i < FUNCTIONS.length; i++)
			((WordRule) rules[2]).addWord(FUNCTIONS[i], functionToken);
		for (int i = 0; i < KEYWORDS.length; i++)
			((WordRule) rules[2]).addWord(KEYWORDS[i], keywordToken);
		for (int i = 0; i < SEMANTICS.length; i++)
			((WordRule) rules[2]).addWord(SEMANTICS[i], semanticToken);
		for (int i = 0; i < TYPES.length; i++)
			((WordRule) rules[2]).addWord(TYPES[i], typeToken);

		setRules(rules);
	}
}

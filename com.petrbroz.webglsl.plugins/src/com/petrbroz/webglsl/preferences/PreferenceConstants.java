package com.petrbroz.webglsl.preferences;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;

import com.petrbroz.webglsl.Messages;

/**
 * Constant definitions for plug-in preferences
 */
public class PreferenceConstants {

	public static String DEFAULT[] = { Messages.Label_Default,
			Messages.Label_DefaultColor, Messages.Label_DefaultStyle };
	public static String COMMENT[] = { Messages.Label_Comments,
			Messages.Label_CommentColor, Messages.Label_CommentStyle };
	public static String TYPE[] = { Messages.Label_Types,
			Messages.Label_TypeColor, Messages.Label_TypeStyle };
	public static String SEMANTIC[] = { Messages.Label_Semantics,
			Messages.Label_SemanticColor, Messages.Label_SemanticStyle };
	public static String FUNCTION[] = { Messages.Label_Functions,
			Messages.Label_FunctionColor, Messages.Label_FunctionStyle };
	public static String LANGUAGE[] = { Messages.Label_Keywords,
			Messages.Label_KeywordColor, Messages.Label_KeywordStyle };

	public static String PREFERENCES[][] = { COMMENT, DEFAULT, TYPE, SEMANTIC,
			FUNCTION, LANGUAGE };

	public static Object DEFAULT_COMMENT[] = { new RGB(0, 128, 0),
			new Integer(SWT.NORMAL) };
	public static Object DEFAULT_DEFAULT[] = { new RGB(0, 0, 0),
			new Integer(SWT.NORMAL) };
	public static Object DEFAULT_TYPE[] = { new RGB(0, 0, 192),
			new Integer(SWT.ITALIC) };
	public static Object DEFAULT_SEMANTIC[] = { new RGB(127, 0, 85),
			new Integer(SWT.BOLD) };
	public static Object DEFAULT_FUNCTION[] = { new RGB(128, 128, 0),
			new Integer(SWT.NORMAL) };
	public static Object DEFAULT_LANGUAGE[] = { new RGB(128, 0, 0),
			new Integer(SWT.BOLD) };

	public static Object DEFAULT_PREFERENCES[][] = { DEFAULT_COMMENT,
			DEFAULT_DEFAULT, DEFAULT_TYPE, DEFAULT_SEMANTIC, DEFAULT_FUNCTION,
			DEFAULT_LANGUAGE };

}

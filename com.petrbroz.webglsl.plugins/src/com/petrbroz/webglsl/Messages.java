package com.petrbroz.webglsl;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.petrbroz.webglsl.messages"; //$NON-NLS-1$
	public static String Label_Bold;
	public static String Label_Color;
	public static String Label_CommentColor;
	public static String Label_Comments;
	public static String Label_CommentStyle;
	public static String Label_ConfigurationDesc;
	public static String Label_Default;
	public static String Label_DefaultColor;
	public static String Label_DefaultStyle;
	public static String Label_FunctionColor;
	public static String Label_Functions;
	public static String Label_FunctionStyle;
	public static String Label_Italic;
	public static String Label_KeywordColor;
	public static String Label_Keywords;
	public static String Label_KeywordStyle;
	public static String Label_SemanticColor;
	public static String Label_Semantics;
	public static String Label_SemanticStyle;
	public static String Label_Strikethrough;
	public static String Label_TypeColor;
	public static String Label_Types;
	public static String Label_TypeStyle;
	public static String Label_Underline;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}

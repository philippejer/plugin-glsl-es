/**
 * 
 */
package com.petrbroz.webglsl.editors;

import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.Token;

/**
 * @author Petr
 *
 */
public class ShaderPartitionScanner extends RuleBasedPartitionScanner {

	public final static String BODY_PARTITION = "body"; //$NON-NLS-1$

	public ShaderPartitionScanner() {
		IToken body = new Token(BODY_PARTITION);
		IPredicateRule[] rules = new IPredicateRule[1];
		rules[0] = new MultiLineRule("{", "}", body);
		setPredicateRules(rules);
	}
}

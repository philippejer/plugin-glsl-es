/**
 * 
 */
package com.petrbroz.webglsl.editors;

import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;

/**
 * @author Petr
 * 
 */
public class BlockCompletionProcessor implements IContentAssistProcessor {

	private final IContextInformation[] NO_CONTEXTS = {};
	private final char[] PROPOSAL_ACTIVATION_CHARS = {};

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#
	 * computeCompletionProposals(org.eclipse.jface.text.ITextViewer, int)
	 */
	@Override
	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer,
			int offset) {
		ICompletionProposal[] proposals = new ICompletionProposal[ShaderScanner.FUNCTIONS.length];
		for (int i = 0; i < ShaderScanner.FUNCTIONS.length; i++) {
			proposals[i] = new CompletionProposal(ShaderScanner.FUNCTIONS[i],
					offset, 0, ShaderScanner.FUNCTIONS[i].length());
		}
		return proposals;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#
	 * computeContextInformation(org.eclipse.jface.text.ITextViewer, int)
	 */
	@Override
	public IContextInformation[] computeContextInformation(ITextViewer viewer,
			int offset) {
		return NO_CONTEXTS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#
	 * getCompletionProposalAutoActivationCharacters()
	 */
	@Override
	public char[] getCompletionProposalAutoActivationCharacters() {
		return PROPOSAL_ACTIVATION_CHARS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#
	 * getContextInformationAutoActivationCharacters()
	 */
	@Override
	public char[] getContextInformationAutoActivationCharacters() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.text.contentassist.IContentAssistProcessor#getErrorMessage
	 * ()
	 */
	@Override
	public String getErrorMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#
	 * getContextInformationValidator()
	 */
	@Override
	public IContextInformationValidator getContextInformationValidator() {
		// TODO Auto-generated method stub
		return null;
	}

}

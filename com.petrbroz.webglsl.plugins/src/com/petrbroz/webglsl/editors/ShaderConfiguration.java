package com.petrbroz.webglsl.editors;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

public class ShaderConfiguration extends SourceViewerConfiguration {

	public final static String COMMENT_TOKEN = "cmnt"; //$NON-NLS-1$
	public final static String DEFAULT_TOKEN = "dflt"; //$NON-NLS-1$
	public final static String FUNCTION_TOKEN = "func"; //$NON-NLS-1$
	public final static String KEYWORD_TOKEN = "kwrd"; //$NON-NLS-1$
	public final static String SEMANTIC_TOKEN = "smtc"; //$NON-NLS-1$
	public final static String TYPE_TOKEN = "type"; //$NON-NLS-1$

	private ShaderScanner scanner;

	/**
	 * 
	 */
	public ShaderConfiguration() {
		super();
	}

	protected ShaderScanner getScanner() {
		if (scanner == null) {
			scanner = new ShaderScanner();
			scanner.setDefaultReturnToken(TokenManager.getToken(DEFAULT_TOKEN));
		}
		return scanner;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.text.source.SourceViewerConfiguration#getContentAssistant
	 * (org.eclipse.jface.text.source.ISourceViewer)
	 */
	@Override
	public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
		ContentAssistant ca = new ContentAssistant();
		IContentAssistProcessor gcp = new GlobalCompletionProcessor();
		IContentAssistProcessor bcp = new BlockCompletionProcessor();

		ca.setContentAssistProcessor(gcp, IDocument.DEFAULT_CONTENT_TYPE);
		ca.setContentAssistProcessor(bcp, ShaderPartitionScanner.BODY_PARTITION);
		ca.setInformationControlCreator(getInformationControlCreator(sourceViewer));
		return ca;
	}

	@Override
	public IPresentationReconciler getPresentationReconciler(
			ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler();
		DefaultDamagerRepairer dr = new DefaultDamagerRepairer(getScanner());
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setDamager(dr, ShaderPartitionScanner.BODY_PARTITION);
		reconciler.setRepairer(dr, ShaderPartitionScanner.BODY_PARTITION);
		return reconciler;
	}
}

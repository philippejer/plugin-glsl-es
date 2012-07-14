package com.petrbroz.webglsl.editors;

import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.editors.text.TextEditor;

import com.petrbroz.webglsl.Plugin;
import com.petrbroz.webglsl.preferences.PreferenceConstants;

public class FragmentShaderEditor extends TextEditor implements
		IPropertyChangeListener {

	public FragmentShaderEditor() {
		super();
		setSourceViewerConfiguration(new ShaderConfiguration());
		setDocumentProvider(new ShaderDocumentProvider());
		Plugin.getDefault().getPreferenceStore()
				.addPropertyChangeListener(this);
	}

	@Override
	protected boolean affectsTextPresentation(PropertyChangeEvent event) {
		boolean affect = false;
		for (int i = 0; i < PreferenceConstants.PREFERENCES.length; i++) {
			affect = affect
					|| event.getProperty().equals(
							PreferenceConstants.PREFERENCES[i][0])
					|| event.getProperty().equals(
							PreferenceConstants.PREFERENCES[i][1])
					|| event.getProperty().equals(
							PreferenceConstants.PREFERENCES[i][2]);
		}
		return affect || super.affectsTextPresentation(event);
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		handlePreferenceStoreChanged(event);
	}

}

package com.petrbroz.webglsl.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.swt.graphics.RGB;

import com.petrbroz.webglsl.Plugin;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = Plugin.getDefault().getPreferenceStore();
		for(int i = 0; i < PreferenceConstants.PREFERENCES.length; i++)
		{
			PreferenceConverter.setDefault(store, PreferenceConstants.PREFERENCES[i][1], (RGB)PreferenceConstants.DEFAULT_PREFERENCES[i][0]);
			store.setDefault(PreferenceConstants.PREFERENCES[i][2], (Integer)PreferenceConstants.DEFAULT_PREFERENCES[i][1]);
		}
	}

}
